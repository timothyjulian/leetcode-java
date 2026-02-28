import java.util.Arrays;

public class PlusOne {

    public PlusOne() {
        System.out.println(Arrays.toString(plusOne(new int[]{1,2,3})));
        System.out.println(Arrays.toString(plusOne(new int[]{4,3,2,1})));
        System.out.println(Arrays.toString(plusOne(new int[]{9})));
    }

    public int[] plusOne(int[] digits) {
        int remainder = 0;
        for(int i = digits.length - 1; i >= 0; i--) {
            int result = digits[i] + (i == digits.length - 1 ? 1 : remainder);
            digits[i] = result % 10;
            remainder = result / 10;
        }
        int[] res;
        if (remainder > 0) {
            res = new int[digits.length+1];
            res[0] = remainder;
            System.arraycopy(digits, 0, res, 1, digits.length);
        } else {
            res = digits;
        }

        return res;
    }

    public static void main(String[] args) {
        new PlusOne();
    }
}
