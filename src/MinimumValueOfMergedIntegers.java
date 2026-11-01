/**
 * 4097. Minimum Value of Merged Integers (Easy)
 * https://leetcode.com/problems/minimum-value-of-merged-integers/
 *
 * @author Timothy Julian
 * @version $Id: MinimumValueOfMergedIntegers.java, v 0.1 2026‐11‐01 22.10 timothy.julian Exp $$
 */
public class MinimumValueOfMergedIntegers {

    public MinimumValueOfMergedIntegers() {
        Judge j = new Judge();
        j.check("2355", minMergedInteger(new int[]{23, 55, 42, 21}));
        j.check("347", minMergedInteger(new int[]{34, 7, 11, 11}));
        j.check("920", minMergedInteger(new int[]{9, 20}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int minMergedInteger(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i+=2) {
            String first = String.valueOf(nums[i - 1]);
            String second = String.valueOf(nums[i]);

            String result = first.concat(second);
            int number = Integer.parseInt(result);
            min = Math.min(min, number);
        }
        return min;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new MinimumValueOfMergedIntegers();
    }
}
