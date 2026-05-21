/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.Arrays;

/**
 * @author Timothy Julian
 * @version $Id: ConcatenationofArray.java, v 0.1 2026‐05‐21 16.36 timothy.julian Exp $$
 */
public class ConcatenationofArray {
    public ConcatenationofArray() {
        System.out.println(Arrays.toString(getConcatenation(new int[]{1,2,1})));
        System.out.println(Arrays.toString(getConcatenation(new int[]{1,3,2,1})));
    }

    public int[] getConcatenation(int[] nums) {
        int length = nums.length;
        int[] ans = new int[length*2];

        for (int i = 0; i < 2; i++) {
            System.arraycopy(nums, 0, ans, length * i, length);
        }

        return ans;
    }

    public static void main(String[] args) {
        new ConcatenationofArray();
    }
}
