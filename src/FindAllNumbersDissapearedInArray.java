/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Timothy Julian
 * @version $Id: FindAllNumbersDissapearedInArray.java, v 0.1 2026‐05‐31 12.40 timothy.julian Exp $$
 */
public class FindAllNumbersDissapearedInArray {

    public FindAllNumbersDissapearedInArray() {
        System.out.println(findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

//        System.out.println(set);
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) ans.add(i);
        }

        return ans;
    }


    public static void main(String[] args) {
        new FindAllNumbersDissapearedInArray();
    }
}
