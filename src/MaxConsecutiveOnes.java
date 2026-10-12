/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

/**
 * 485. Max Consecutive Ones (Easy)
 * https://leetcode.com/problems/max-consecutive-ones/
 *
 * @author Timothy Julian
 * @version $Id: MaxConsecutiveOnes.java, v 0.1 2026‐09‐23 11.48 timothy.julian Exp $$
 */
public class MaxConsecutiveOnes {

    public MaxConsecutiveOnes() {
        Judge j = new Judge();
        j.check("3", findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        j.check("2", findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = Integer.MIN_VALUE;
        int before = nums[0];
        int currCount = before == 1 ? 1 : 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 1) {
                currCount++;
            } else {
                max = Math.max(max, currCount);
                currCount = 0;
            }
        }
        max = Math.max(max, currCount);

        return max;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new MaxConsecutiveOnes();
    }
}
