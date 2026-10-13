/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array (Easy)
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * @author Timothy Julian
 * @version $Id: FindAllNumbersDisappearedInAnArray.java, v 0.1 2026‐10‐12 22.49 timothy.julian Exp $$
 */
public class FindAllNumbersDisappearedInAnArray {

    public FindAllNumbersDisappearedInAnArray() {
        Judge j = new Judge();
        j.check("[5,6]", findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        j.check("[2]", findDisappearedNumbers(new int[]{1, 1}));
        j.done();
    }

    // ==== leetcode:begin ====
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] temp = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            temp[nums[i]-1]++;
        }

        List<Integer> ans = new ArrayList<>();
        int x = 1;
        for (Integer i : temp) {
            if (i == 0) ans.add(x);
            x++;
        }

        return ans;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new FindAllNumbersDisappearedInAnArray();
    }
}
