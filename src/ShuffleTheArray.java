/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

/**
 * 1470. Shuffle the Array (Easy)
 * https://leetcode.com/problems/shuffle-the-array/
 *
 * @author Timothy Julian
 * @version $Id: ShuffleTheArray.java, v 0.1 2026‐09‐22 15.34 timothy.julian Exp $$
 */
public class ShuffleTheArray {

    public ShuffleTheArray() {
        Judge j = new Judge();
        j.check("[2,3,5,4,1,7]", shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3));
        j.check("[1,4,2,3,3,2,4,1]", shuffle(new int[]{1, 2, 3, 4, 4, 3, 2, 1}, 4));
        j.check("[1,2,1,2]", shuffle(new int[]{1, 1, 2, 2}, 2));
        j.done();
    }

    // ==== leetcode:begin ====
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[nums.length];

        int counterAns = 0;
        for (int i = 0; i < nums.length/2; i++) {
            ans[counterAns] = nums[i];
            ans[counterAns+1] = nums[i + n];
            counterAns += 2;
        }


        return ans;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new ShuffleTheArray();
    }
}
