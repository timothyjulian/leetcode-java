/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

/**
 * 1929. Concatenation of Array (Easy)
 * https://leetcode.com/problems/concatenation-of-array/
 *
 * @author Timothy Julian
 * @version $Id: ConcatenationOfArray.java, v 0.1 2026‐09‐22 15.28 timothy.julian Exp $$
 */
public class ConcatenationOfArray {

    public ConcatenationOfArray() {
        Judge j = new Judge();
        j.check("[1,2,1,1,2,1]", getConcatenation(new int[]{1, 2, 1}));
        j.check("[1,3,2,1,1,3,2,1]", getConcatenation(new int[]{1, 3, 2, 1}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[2*nums.length];
        for (int i = 0; i < nums.length * 2; i++) {
            ans[i] = nums[i%nums.length];
        }

        return ans;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new ConcatenationOfArray();
    }
}
