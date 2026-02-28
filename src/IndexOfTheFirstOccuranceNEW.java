import java.util.Arrays;

public class IndexOfTheFirstOccuranceNEW {
    public IndexOfTheFirstOccuranceNEW() {
        System.out.println(strStr("sadbutsad", "aabaaac"));
        System.out.println(strStr("sadbutsad", "ababaa"));
        System.out.println(strStr("sadbutsad", "sad"));
        System.out.println(strStr("leetcode", "leeto"));
    }

    public int strStr(String haystack, String needle) {
        var lps = new int[needle.length()];
        int len = 0;
        int i = 1;
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

//        System.out.println(Arrays.toString(lps));

        int a = 0;
        int b = 0;
        while (a < haystack.length()) {
            if (haystack.charAt(a) == needle.charAt(b)) {
                a++;
                b++;
            } else {
                if (b != 0) {
                    b = lps[b-1];
                } else {
                    a++;
                }
            }
            if (b >= needle.length()) {
//                System.out.printf("%d %d\n",a, b);
                return a - b;
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        new IndexOfTheFirstOccuranceNEW();
    }
}
