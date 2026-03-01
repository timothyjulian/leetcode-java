import java.util.HashSet;
import java.util.Set;

public class TrimTrailingVowels {
    public String trimTrailingVowels(String s) {
        Set<Character> vowels = new HashSet<Character>() {{
            add('a');
            add('i');
            add('u');
            add('e');
            add('o');
        }};
        char[] arr = s.toCharArray();
        int i = arr.length - 1;
        while (i >= 0) {  
            if (!vowels.contains(arr[i])) {
                break;
            }
            i--;
        }
        // System.out.println(i);
        return s.substring(0, i+1);
    }

    public TrimTrailingVowels() {
        System.out.println(trimTrailingVowels("idea"));
        System.out.println(trimTrailingVowels("day"));
        System.out.println(trimTrailingVowels("aeiou"));
    }

    public static void main(String[] args) {
        new TrimTrailingVowels();        
    }
}
