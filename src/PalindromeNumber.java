public class PalindromeNumber {
    public PalindromeNumber() {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(10));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int temp = x;
        int rev = 0;
        while (temp != 0) {
            int mod = temp % 10;
//            System.out.println(mod);
            rev = rev * 10 + mod;
//            System.out.println(rev);
            temp /= 10;
        }

//        System.out.println(rev);

        return rev == x;
    }

    public static void main(String[] args) {
        new PalindromeNumber();
    }
}
