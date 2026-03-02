/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Timothy Julian
 * @version $Id: SmallestPairWithDifferentFrequencies.java, v 0.1 2026‐03‐02 19.41 timothy.julian Exp $$
 */
public class SmallestPairWithDifferentFrequencies {

    public static void main(String[] args) {
        new SmallestPairWithDifferentFrequencies();
    }

    public SmallestPairWithDifferentFrequencies() {
        System.out.println(Arrays.toString(minDistinctFreqPair(new int[]{1, 1, 2, 2, 3, 4})));
        System.out.println(Arrays.toString(minDistinctFreqPair(new int[]{1,5})));
        System.out.println(Arrays.toString(minDistinctFreqPair(new int[]{7})));
    }

    public int[] minDistinctFreqPair(int[] nums) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        Arrays.sort(nums);
        Arrays.stream(nums).forEach(i -> {
            map.put(i, map.getOrDefault(i, 0)+1);
        });

        List<Integer> list = new ArrayList<>();
        map.forEach((k, v) -> {
//            System.out.println(k);
//            System.out.println(v);
//            System.out.println(list);
            if (list.isEmpty()) {
                list.add(k);
                return;
            } else if (list.size() == 2) {
                return;
            }

            if (Objects.equals(map.get(list.getFirst()), map.get(k))) {
                return;
            }
            list.add(k);
        });

        if (list.size() != 2) {
            return new int[]{-1, -1};
        }

        return new int[]{list.getFirst(), list.getLast()};
    }
}
