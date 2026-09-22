#!/usr/bin/env python3
"""LeetCode -> local Java scaffold.

  python3 leet.py new  <url|slug>   # fetch problem, write src/<Class>.java
  python3 leet.py run  <Class>      # javac + java (Java 8 ok)
  python3 leet.py copy <Class>      # print `class Solution {...}` ready to paste back
  python3 leet.py selftest          # assert the parsing bits still work
"""
import html, json, os, re, shutil, subprocess, sys, urllib.request
from datetime import datetime

SRC, OUT, BAK = "src", "out/leet", "out/leet-bak"
BEGIN, END = "// ==== leetcode:begin ====", "// ==== leetcode:end ===="

HEADER = """/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */
"""


def fetch(slug):
    q = ("query q($titleSlug:String!){question(titleSlug:$titleSlug){questionFrontendId "
         "title difficulty content exampleTestcases codeSnippets{langSlug code}}}")
    body = json.dumps({"query": q, "variables": {"titleSlug": slug}}).encode()
    req = urllib.request.Request(
        "https://leetcode.com/graphql", data=body,
        headers={"Content-Type": "application/json",
                 "Referer": "https://leetcode.com/problems/%s/" % slug,
                 "User-Agent": "Mozilla/5.0"})
    q = json.load(urllib.request.urlopen(req))["data"]["question"]
    if not q:
        sys.exit("no such problem: %s" % slug)
    return q


def slug_of(arg):
    m = re.search(r"/problems/([^/?#]+)", arg)
    return m.group(1) if m else arg


def pascal(title):
    return "".join(w[0].upper() + w[1:]
                   for w in re.findall(r"[A-Za-z0-9]+", title.replace("'", "")))


def expected(content):
    """Pull the `Output:` line out of each example in the problem HTML."""
    outs = []
    for raw in re.findall(r"<strong>Output:?\s*</strong>(.*?)(?:\n|</pre>)", content or "", re.S):
        outs.append(html.unescape(re.sub(r"<[^>]+>", "", raw)).replace("\xa0", " ").strip())
    return outs


def collides(cls, listing):
    """Existing filename for this class, or None. Case-insensitive: macOS treats
    Foo.java and foo.java as one file, so a case-only match is still a collision."""
    want = (cls + ".java").lower()
    return next((f for f in listing if f.lower() == want), None)


def java_str(v):
    """Render a JSON value the way Judge.show() renders the real result."""
    if isinstance(v, bool):
        return "true" if v else "false"
    if v is None:
        return "null"
    if isinstance(v, list):
        return "[" + ",".join(java_str(x) for x in v) + "]"
    return str(v)


def want(raw):
    """Expected-output text from the statement -> Java string literal for Judge.check."""
    try:
        return json.dumps(java_str(json.loads(raw)))
    except ValueError:
        return json.dumps(raw)


def split_params(s):
    """Split 'int[] a, Map<String, Integer> m' on top-level commas only."""
    out, depth, cur = [], 0, ""
    for ch in s:
        if ch == "<":
            depth += 1
        elif ch == ">":
            depth -= 1
        if ch == "," and depth == 0:
            out.append(cur)
            cur = ""
        else:
            cur += ch
    if cur.strip():
        out.append(cur)
    return [p.strip() for p in out]


def lit(t, v, top=True):
    """JSON value -> Java literal for declared type t."""
    t = t.strip()
    if t == "String":
        return json.dumps(v)
    if t in ("char", "Character"):
        return "'%s'" % v
    if t == "boolean":
        return "true" if v else "false"
    if t in ("int", "long", "double", "float", "Integer"):
        return str(v) + ("L" if t == "long" else "")
    if t.endswith("[]"):
        base = t[:-2]
        inner = ", ".join(lit(base, x, top=False) for x in v)
        return ("new %s[]{%s}" % (base, inner)) if top else "{%s}" % inner
    m = re.match(r"(?:List|Collection|Iterable)<(.+)>$", t)
    if m:
        return "Arrays.asList(%s)" % ", ".join(lit(m.group(1), x) for x in v)
    return "null /* TODO %s = %s */" % (t, json.dumps(v))


def printer(ret, call):
    if ret.count("[]") >= 2:
        return "System.out.println(Arrays.deepToString(%s));" % call
    if ret.endswith("[]"):
        return "System.out.println(Arrays.toString(%s));" % call
    if ret == "void":
        return call + ";"
    return "System.out.println(%s);" % call


def cmd_new(arg, force=False):
    q = fetch(slug_of(arg))
    cls = pascal(q["title"])
    path = os.path.join(SRC, cls + ".java")
    hit = collides(cls, os.listdir(SRC))
    if hit and not force:
        sys.exit("src/%s exists (use -f to redo it)" % hit)
    if hit:
        os.makedirs(BAK, exist_ok=True)
        bak = os.path.join(BAK, "%s.%s" % (hit, datetime.now().strftime("%Y%m%d-%H%M%S")))
        shutil.copy2(os.path.join(SRC, hit), bak)
        if hit != cls + ".java":
            # case-only rename: opening the new name would silently reuse the old
            # file, and core.ignorecase repos keep tracking the old path
            if subprocess.call(["git", "mv", "-f", os.path.join(SRC, hit), path],
                               stderr=subprocess.DEVNULL) != 0:
                os.rename(os.path.join(SRC, hit), path)
        print("replaced src/%s  (backup: %s)" % (hit, bak), file=sys.stderr)

    snippet = next(c["code"] for c in q["codeSnippets"] if c["langSlug"] == "java")
    sigs = re.findall(r"public\s+([\w.<>\[\],\s]+?)\s+(\w+)\s*\(([^)]*)\)\s*\{", snippet)
    calls, note = [], ""

    if len(sigs) == 1:
        ret, name, raw = sigs[0]
        ret = ret.strip()
        types = [p.rsplit(" ", 1)[0] for p in split_params(raw)]
        stub = "" if ret == "void" else "\n        throw new UnsupportedOperationException();"
        body = "    public %s %s(%s) {\n\n        // TODO%s\n    }" % (ret, name, raw, stub)
        lines = [l for l in q["exampleTestcases"].split("\n") if l.strip()]
        outs = expected(q.get("content"))
        n = max(len(types), 1)
        for k, i in enumerate(range(0, len(lines) - n + 1, n)):
            args = [lit(types[j], json.loads(lines[i + j])) for j in range(n)]
            call = "%s(%s)" % (name, ", ".join(args))
            if ret != "void" and k < len(outs):
                calls.append("j.check(%s, %s);" % (want(outs[k]), call))
            else:
                calls.append(printer(ret, call)
                             + (" // expect %s" % outs[k] if k < len(outs) else ""))
    else:
        note = "        // design problem - drive it yourself\n"
        body = "\n".join("    " + l for l in snippet.split("\n")[1:-1])

    blob = body + "".join(calls)
    imports = []
    if "Arrays." in blob:
        imports.append("import java.util.Arrays;")
    if re.search(r"\bList<", body):
        imports += ["import java.util.ArrayList;", "import java.util.List;"]

    judged = any(c.startswith("j.check(") for c in calls)
    if judged:
        calls = ["Judge j = new Judge();"] + calls + ["j.done();"]
        if not os.path.exists(os.path.join(SRC, "Judge.java")):
            print("warning: src/Judge.java missing - asserts will not compile", file=sys.stderr)
    ctor = note + "".join("        %s\n" % c for c in calls)
    now = datetime.now().strftime("%Y‐%m‐%d %H.%M")
    out = ("%s%s\n/**\n * %s. %s (%s)\n * https://leetcode.com/problems/%s/\n *\n"
           " * @author Timothy Julian\n * @version $Id: %s.java, v 0.1 %s timothy.julian Exp $$\n"
           " */\npublic class %s {\n\n    public %s() {\n%s    }\n\n    %s\n%s\n    %s\n\n"
           "    public static void main(String[] args) {\n        new %s();\n    }\n}\n") % (
        HEADER, ("\n" + "\n".join(imports) + "\n") if imports else "",
        q["questionFrontendId"], q["title"], q["difficulty"], slug_of(arg),
        cls, now, cls, cls, ctor, BEGIN, body, END, cls)

    open(path, "w").write(out)
    print(path)


def cmd_run(cls):
    os.makedirs(OUT, exist_ok=True)
    src = os.path.join(SRC, cls + ".java")
    rc = subprocess.call(["javac", "-sourcepath", SRC, "-d", OUT, src])
    sys.exit(rc or subprocess.call(["java", "-cp", OUT, cls]))


def cmd_copy(cls):
    txt = open(os.path.join(SRC, cls + ".java")).read()
    m = re.search(re.escape(BEGIN) + r"(.*?)" + re.escape(END), txt, re.S)
    if not m:
        sys.exit("no leetcode markers in %s" % cls)
    body = "\n".join(l.rstrip() for l in m.group(1).split("\n")).strip("\n")
    sol = "class Solution {\n%s\n}" % body
    print(sol)
    try:
        subprocess.run(["pbcopy"], input=sol.encode())
        print("\n-- copied to clipboard --", file=sys.stderr)
    except FileNotFoundError:
        pass


def cmd_selftest(_=None):
    assert slug_of("https://leetcode.com/problems/two-sum/?envType=x&envId=y") == "two-sum"
    assert slug_of("two-sum") == "two-sum"
    assert pascal("Pascal's Triangle") == "PascalsTriangle"
    assert pascal("Concatenation of Array") == "ConcatenationOfArray"

    assert collides("TwoSum", []) is None
    assert collides("TwoSum", ["ThreeSum.java"]) is None
    assert collides("TwoSum", ["TwoSum.java"]) == "TwoSum.java"
    assert collides("ConcatenationOfArray", ["ConcatenationofArray.java"]) == "ConcatenationofArray.java"

    assert split_params("int[] a, int t") == ["int[] a", "int t"]
    assert split_params("Map<String, Integer> m, int k") == ["Map<String, Integer> m", "int k"]
    assert split_params("") == []

    assert lit("int[]", [1, 2]) == "new int[]{1, 2}"
    assert lit("int[][]", [[1], [2, 3]]) == "new int[][]{{1}, {2, 3}}"
    assert lit("String", 'a"b') == '"a\\"b"'
    assert lit("String[]", ["a", "b"]) == 'new String[]{"a", "b"}'
    assert lit("char[][]", [["a"]]) == "new char[][]{{'a'}}"
    assert lit("boolean", True) == "true"
    assert lit("long", 5) == "5L"
    assert lit("List<Integer>", [1, 2]) == "Arrays.asList(1, 2)"
    assert lit("TreeNode", [1]).startswith("null /* TODO TreeNode")

    assert java_str(True) == "true"
    assert java_str(None) == "null"
    assert java_str([[1], [1, 1]]) == "[[1],[1,1]]"
    assert java_str(["a", "b"]) == "[a,b]"
    assert java_str(2) == "2"

    assert want("2") == '"2"'
    assert want("[[1],[1,1]]") == '"[[1],[1,1]]"'
    assert want('"1[.]1"') == '"1[.]1"'
    assert want("true") == '"true"'
    assert want("[1,2,1,1,2,1]") == '"[1,2,1,1,2,1]"'
    assert want("Impossible to parse") == '"Impossible to parse"'

    assert printer("int[]", "f(x)") == "System.out.println(Arrays.toString(f(x)));"
    assert printer("int[][]", "f(x)") == "System.out.println(Arrays.deepToString(f(x)));"
    assert printer("void", "f(x)") == "f(x);"
    assert printer("int", "f(x)") == "System.out.println(f(x));"

    assert expected("<pre><strong>Input:</strong> n = 5\n<strong>Output:</strong> 2\n</pre>") == ["2"]
    assert expected("<pre><strong>Output:</strong> [[1],[1,1]]\n</pre>") == ["[[1],[1,1]]"]
    assert expected('<strong>Output:</strong> &quot;1[.]1&quot;</pre>') == ['"1[.]1"']
    assert expected(None) == []

    # end to end: types -> literals -> statement, mirrors cmd_new's inner loop
    assert printer("int", "searchInsert(%s, %s)" % (lit("int[]", [1, 3]), lit("int", 5))) \
        == "System.out.println(searchInsert(new int[]{1, 3}, 5));"
    print("ok")


if __name__ == "__main__":
    a = [x for x in sys.argv[1:] if x != "-f"]
    if a == ["selftest"]:
        cmd_selftest()
    elif len(a) == 2 and a[0] in ("new", "run", "copy"):
        {"new": lambda x: cmd_new(x, "-f" in sys.argv),
         "run": cmd_run, "copy": cmd_copy}[a[0]](a[1])
    else:
        sys.exit(__doc__)
