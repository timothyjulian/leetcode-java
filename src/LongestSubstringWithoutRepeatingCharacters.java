import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public LongestSubstringWithoutRepeatingCharacters () {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(" "));
        System.out.println(lengthOfLongestSubstring("au"));
        System.out.println(lengthOfLongestSubstring("aab"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        int max = Integer.MIN_VALUE;
        int curr = 0;
        // Could have used map instead of set since need to re add all the char with for
        Set<Character> set = new HashSet<>();
        while (r < s.length()) {
            if (set.contains(s.charAt(r))) {
                int len = set.size();
                max = max > len ? max : len;
                while (l <= r) {
                    if (s.charAt(l) == s.charAt(r)) {
                        l++;
                        break;
                    }
                    l++;
                }
                set.clear();
                for (int i = l; i <= r; i++) {
                    set.add(s.charAt(i));
                }
            }
            set.add(s.charAt(r));
//            System.out.println(set);
            r++;
        }
//        System.out.println(set);

        if (!set.isEmpty()){
            int len = set.size();
            max = max > len ? max : len;
        }
        if (max == Integer.MIN_VALUE) max = 0;

        return max;
    }

    public static void main(String[] args) {
        new LongestSubstringWithoutRepeatingCharacters();
    }
}
