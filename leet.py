#!/usr/bin/env python3
"""LeetCode -> local Java scaffold.

  python3 leet.py new  <url|slug>   # fetch problem, write src/<Class>.java
  python3 leet.py run  <Class>      # javac + java (Java 8 ok)
  python3 leet.py copy <Class>      # copy the submission class to the clipboard
  python3 leet.py selftest          # assert the parsing bits still work
"""
import html, json, os, re, shutil, subprocess, sys, urllib.request
from datetime import datetime
from textwrap import dedent, indent

SRC, OUT, BAK = "src", "out/leet", "out/leet-bak"
BEGIN, END = "// ==== leetcode:begin ====", "// ==== leetcode:end ===="
CLASS = r"(?:public\s+)?class\s+(\w+)\b"
METHOD = r"\bpublic\s+([\w.<>\[\],\s]+?)\s+(\w+)\s*\(([^)]*)\)\s*\{"

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
    for raw in re.findall(r"<strong>Output:?\s*</strong>\s*(.*?)(?:\n|</pre>)", content or ""):
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


def param_types(raw):
    return [p.rsplit(None, 1)[0] for p in split_params(raw)]


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


def design_calls(name, constructors, sigs, lines, outs):
    signatures = {}
    for ret, operation, params in [(None, name, raw) for raw in constructors or [""]] + sigs:
        types = param_types(params)
        signatures.setdefault((operation, len(types)), []).append((ret.strip() if ret else None, types))

    if len(lines) % 2:
        raise ValueError("expected pairs of operation and argument lists")
    calls = []
    for k, i in enumerate(range(0, len(lines), 2), 1):
        ops, inputs = map(json.loads, lines[i:i + 2])
        outputs = json.loads(outs[k - 1]) if k <= len(outs) and outs[k - 1] else None
        if (not isinstance(ops, list) or not isinstance(inputs, list)
                or not ops or ops[0] != name or len(ops) != len(inputs)):
            raise ValueError("invalid operation sequence for %s" % name)
        if outputs is not None and (not isinstance(outputs, list) or len(outputs) != len(ops)):
            raise ValueError("expected one output per operation")

        obj = "obj%d" % k
        for j, (op, values) in enumerate(zip(ops, inputs)):
            if not isinstance(op, str) or not isinstance(values, list):
                raise ValueError("expected operation names and argument lists")
            matches = signatures.get((op, len(values)), [])
            if len(matches) != 1:
                raise ValueError("cannot resolve %s with %d arguments" % (op, len(values)))
            ret, types = matches[0]
            args = ", ".join(lit(t, v) for t, v in zip(types, values))
            if j == 0:
                calls.append("%s %s = new %s(%s);" % (name, obj, name, args))
            else:
                call = "%s.%s(%s)" % (obj, op, args)
                if ret != "void" and outputs is not None:
                    calls.append("j.check(%s, %s);" % (json.dumps(java_str(outputs[j])), call))
                else:
                    calls.append(printer(ret, call))
    return calls


def fill_stub(method):
    ret, name, params = method.groups()
    if ret.strip() == "void":
        return method[0]
    return "public %s %s(%s) {\n        throw new UnsupportedOperationException();\n    }" % (ret, name, params)


def cmd_new(arg, force=False):
    q = fetch(slug_of(arg))
    cls = pascal(q["title"])
    snippet = next(c["code"] for c in q["codeSnippets"] if c["langSlug"] == "java")
    declaration = re.search(r"^" + CLASS, snippet, re.M)
    name = declaration[1] if declaration else None
    constructors = []
    if name:
        constructors = re.findall(r"\bpublic\s+" + re.escape(name) + r"\s*\(([^)]*)\)\s*\{", snippet)
    design = name is not None and (name != "Solution" or bool(constructors))
    if design and cls == name:
        cls += "Runner"
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

    sigs = re.findall(METHOD, snippet)
    lines = [line for line in q["exampleTestcases"].splitlines() if line.strip()]
    outs = expected(q.get("content"))
    calls, note = [], ""

    if design:
        body = re.sub(METHOD + r"\s*\}", fill_stub, snippet[declaration.start():].strip())
        body = indent("static " + body, "    ")
        try:
            calls = design_calls(name, constructors, sigs, lines, outs)
        except ValueError as exc:
            print("warning: could not generate design examples: %s" % exc, file=sys.stderr)
            note = "        // Add local example calls here.\n"
    elif len(sigs) == 1:
        ret, name, raw = sigs[0]
        ret = ret.strip()
        types = param_types(raw)
        stub = "" if ret == "void" else "\n        throw new UnsupportedOperationException();"
        body = "    public %s %s(%s) {\n\n        // TODO%s\n    }" % (ret, name, raw, stub)
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
    out = ("%s\n/**\n * %s. %s (%s)\n * https://leetcode.com/problems/%s/\n *\n"
           " * @author Timothy Julian\n * @version $Id: %s.java, v 0.1 %s timothy.julian Exp $$\n"
           " */\npublic class %s {\n\n    public %s() {\n%s    }\n\n    %s\n%s\n    %s\n\n"
           "    public static void main(String[] args) {\n        new %s();\n    }\n}\n") % (
        ("\n" + "\n".join(imports) + "\n") if imports else "",
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
    if re.match(r"\s*static\s+" + CLASS, body):
        sol = re.sub(r"^static\s+", "", dedent(body), count=1)
    else:
        sol = "class Solution {\n%s\n}" % body
    print(sol)
    try:
        command, encoding = (["clip.exe"], "utf-16-le") if os.name == "nt" else (["pbcopy"], "utf-8")
        subprocess.run(command, input=sol.encode(encoding), check=True)
        print("\n-- copied to clipboard --", file=sys.stderr)
    except (FileNotFoundError, subprocess.CalledProcessError) as exc:
        print("\n-- clipboard unavailable: %s --" % exc, file=sys.stderr)


def cmd_selftest(_=None):
    import io
    from contextlib import redirect_stdout, redirect_stderr
    from tempfile import TemporaryDirectory
    from unittest.mock import mock_open, patch

    source = BEGIN + "\n    // Unicode: \u2713\n" + END
    solution = "class Solution {\n    // Unicode: \u2713\n}"
    for platform, command, encoding in (("nt", "clip.exe", "utf-16-le"), ("posix", "pbcopy", "utf-8")):
        with patch("builtins.open", mock_open(read_data=source)), \
             patch.object(os, "name", platform), patch.object(subprocess, "run") as run, \
             redirect_stdout(io.StringIO()) as stdout, redirect_stderr(io.StringIO()) as stderr:
            cmd_copy("Example")
            run.assert_called_once_with([command], input=solution.encode(encoding), check=True)
            assert not run.call_args.kwargs["input"].startswith((b"\xff\xfe", b"\xfe\xff", b"\xef\xbb\xbf"))
            assert stdout.getvalue() == solution + "\n"
            assert "copied to clipboard" in stderr.getvalue()
            run.side_effect = subprocess.CalledProcessError(1, command)
            stderr.truncate(0)
            stderr.seek(0)
            cmd_copy("Example")
            assert "clipboard unavailable" in stderr.getvalue()
            assert "copied to clipboard" not in stderr.getvalue()

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

    snippet = ("class Counter {\n    public Counter(int start) {}\n"
               "    public int next() {}\n}\n\n/** Usage notes. */\n")
    case = '["Counter","next","next"]\n[[3],[],[]]\n'
    output = '<pre><strong>Output</strong>\n[null,3,4]\n</pre>'
    problem = {
        "title": "Counter", "questionFrontendId": "1", "difficulty": "Easy",
        "codeSnippets": [{"langSlug": "java", "code": snippet}],
        "exampleTestcases": case * 2, "content": output * 2,
    }
    with TemporaryDirectory() as tmp, patch(__name__ + ".SRC", tmp), \
         patch(__name__ + ".fetch", return_value=problem), patch.object(subprocess, "run"), \
         redirect_stdout(io.StringIO()) as stdout, redirect_stderr(io.StringIO()):
        cmd_new("counter")
        with open(os.path.join(tmp, "CounterRunner.java")) as generated:
            source = generated.read()
        assert "Counter obj1 = new Counter(3);" in source
        assert 'j.check("4", obj2.next());' in source
        cmd_copy("CounterRunner")
        copied = stdout.getvalue().split("\n", 1)[1]
        assert copied.startswith("class Counter {\n    public Counter(int start)")
        assert "throw new UnsupportedOperationException();" in copied
        assert copied.endswith("/** Usage notes. */\n")

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
