/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

/**
 * 645. Set Mismatch (Easy)
 * https://leetcode.com/problems/set-mismatch/
 *
 * @author Timothy Julian
 * @version $Id: SetMismatch.java, v 0.1 2026‐09‐23 11.57 timothy.julian Exp $$
 */
public class SetMismatch {

    public SetMismatch() {
        Judge j = new Judge();
        j.check("[2,3]", findErrorNums(new int[]{1, 2, 2, 4}));
        j.check("[1,2]", findErrorNums(new int[]{1, 1}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];

        int[] table = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            table[nums[i]-1]++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (table[i] == 0) {
                ans[1] = i + 1;
            } else if (table[i] == 2) {
                ans[0] = i+ 1;
            }
        }

        return ans;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new SetMismatch();
    }
}
