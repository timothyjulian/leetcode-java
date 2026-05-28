/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.Arrays;

/**
 * @author Timothy Julian
 * @version $Id: SetMismatch.java, v 0.1 2026‐05‐25 22.29 timothy.julian Exp $$
 */
public class SetMismatch {

    public SetMismatch() {
        System.out.println(Arrays.toString(findErrorNums(new int[]{1,2,2,4})));
        System.out.println(Arrays.toString(findErrorNums(new int[]{1,1})));
        System.out.println(Arrays.toString(findErrorNums(new int[]{3,2,3,4,6,5})));
    }

    public int[] findErrorNums(int[] nums) {
        int[] count = new int[nums.length];
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]-1]++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 2) {
                ans[0] = i+1;
            } else if (count[i] == 0) {
                ans[1] = i+1;
            }
        }
//        System.out.println(Arrays.toString(count));

        return ans;
    }


    public static void main(String[] args) {
        new SetMismatch();
    }
}
