/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.Arrays;

/**
 * 1365. How Many Numbers Are Smaller Than the Current Number (Easy)
 * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 *
 * @author Timothy Julian
 * @version $Id: HowManyNumbersAreSmallerThanTheCurrentNumber.java, v 0.1 2026‐09‐23 23.23 timothy.julian Exp $$
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber {

    public HowManyNumbersAreSmallerThanTheCurrentNumber() {
        Judge j = new Judge();
        j.check("[4,0,1,1,3]", smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3}));
        j.check("[2,1,0,3]", smallerNumbersThanCurrent(new int[]{6, 5, 4, 8}));
        j.check("[0,0,0,0]", smallerNumbersThanCurrent(new int[]{7, 7, 7, 7}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int[] smallerNumbersThanCurrent(int[] nums) {

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] < nums[i]) count++;
//                System.out.printf("%d %d %d\n", nums[i], nums[j], count);
            }
            ans[i] = count;
        }

        return ans;
    }


    // ==== leetcode:end ====

    public static void main(String[] args) {
        new HowManyNumbersAreSmallerThanTheCurrentNumber();
    }
}
