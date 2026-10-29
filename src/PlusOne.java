import java.util.Arrays;

/**
 * 66. Plus One (Easy)
 * https://leetcode.com/problems/plus-one/
 *
 * @author Timothy Julian
 * @version $Id: PlusOne.java, v 0.1 2026‐10‐30 00.37 timothy.julian Exp $$
 */
public class PlusOne {

    public PlusOne() {
        Judge j = new Judge();
        j.check("[1,2,4]", plusOne(new int[]{1, 2, 3}));
        j.check("[4,3,2,2]", plusOne(new int[]{4, 3, 2, 1}));
        j.check("[1,0]", plusOne(new int[]{9}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int[] plusOne(int[] digits) {
        int[] ans = new int[digits.length];
        int banked = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int addition = i == digits.length - 1 ? 1 : banked;
            int curr = digits[i] + addition;
            ans[i] = curr % 10;
            banked = curr / 10;
        }

        if (banked > 0) {
            int[] more = new int[digits.length+1];
            System.arraycopy(ans, 0, more, 1, digits.length);
            more[0] = banked;
            return more;
        }


        return ans;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new PlusOne();
    }
}
