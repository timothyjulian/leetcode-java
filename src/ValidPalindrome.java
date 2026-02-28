public class ValidPalindrome {

    public ValidPalindrome() {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome(" "));
        System.out.println(isPalindrome("0P"));
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        var clean = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                clean.append(s.charAt(i));
            }
        }

        var fin = clean.toString();

        int l = 0;
        int r = fin.length() - 1;
//        System.out.printf("%d %d %b\n", l, r, l < r);
        while (l < r) {
//            System.out.printf("%c %c %b\n", fin.charAt(l), fin.charAt(r), fin.charAt(l) == fin.charAt(r));
            if (fin.charAt(l) == fin.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }


        return true;
    }

    public static void main(String[] args) {
        new ValidPalindrome();
    }
}
