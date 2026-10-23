/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 739. Daily Temperatures (Medium)
 * https://leetcode.com/problems/daily-temperatures/
 *
 * @author Timothy Julian
 * @version $Id: DailyTemperatures.java, v 0.1 2026‐10‐22 15.36 timothy.julian Exp $$
 */
public class DailyTemperatures {

    public DailyTemperatures() {
        Judge j = new Judge();
        j.check("[1,1,4,2,1,1,0,0]", dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        j.check("[1,1,1,0]", dailyTemperatures(new int[]{30, 40, 50, 60}));
        j.check("[1,1,0]", dailyTemperatures(new int[]{30, 60, 90}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int[] dailyTemperatures(int[] temperatures) {
        record Stack(int index, int value) {
        }
        ;
        int[] ans = new int[temperatures.length];
        List<Stack> monotonicStack = new ArrayList<>();
        for (int i = 0; i < temperatures.length; i++) {
            int temperature = temperatures[i];

            while (!monotonicStack.isEmpty() && temperature > monotonicStack.getLast().value) {
                Stack removed = monotonicStack.removeLast();
                ans[removed.index] = i - removed.index;
            }
            monotonicStack.add(new Stack(i, temperature));
//            System.out.println(monotonicStack);
//            System.out.println(Arrays.toString(ans));
        }


        return ans;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new DailyTemperatures();
    }
}
