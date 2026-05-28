/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Timothy Julian
 * @version $Id: HowManyNumbersAreSmallerThanTheCurrentNumber.java, v 0.1 2026‐05‐25 22.50 timothy.julian Exp $$
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber {

    public HowManyNumbersAreSmallerThanTheCurrentNumber() {
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{8,1,2,2,3})));
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{6,5,4,8})));
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{7,7,7,7})));
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            sortedMap.put(nums[i], sortedMap.getOrDefault(nums[i], 0) + 1);
        }
        final int[] sum = {0};
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sortedMap.forEach((k, v) -> {
            sumMap.put(k, sum[0]);
            sum[0] += v;
        });

//        System.out.println(sortedMap);
//        System.out.println(sumMap);
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = sumMap.get(nums[i]);
        }

        return ans;
    }


    public static void main(String[] args) {
        new HowManyNumbersAreSmallerThanTheCurrentNumber();
    }
}
