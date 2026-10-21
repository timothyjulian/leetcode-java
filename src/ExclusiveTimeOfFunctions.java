/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * 636. Exclusive Time of Functions (Medium)
 * https://leetcode.com/problems/exclusive-time-of-functions/
 *
 * @author Timothy Julian
 * @version $Id: ExclusiveTimeOfFunctions.java, v 0.1 2026‐10‐15 11.28 timothy.julian Exp $$
 */
public class ExclusiveTimeOfFunctions {

    public ExclusiveTimeOfFunctions() {
        Judge j = new Judge();
        j.check("[3,4]", exclusiveTime(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6")));
        j.check("[8]", exclusiveTime(1, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7")));
        j.check("[7,1]", exclusiveTime(2, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7")));
        j.check("[1,1,2]", exclusiveTime(3, Arrays.asList("0:start:0", "0:end:0", "1:start:1", "1:end:1", "2:start:2", "2:end:2", "2:start:3", "2:end:3")));
        j.done();
    }

    // ==== leetcode:begin ====
    public int[] exclusiveTime(int n, List<String> logs) {
        record Stack(int functionId, int value) {
        }
        List<Stack> runtimeStack = new ArrayList<>();
        List<Integer> functionRunningStack = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < logs.size(); i++) {
            String log = logs.get(i);
            String[] separateCurrent = log.split(":");
//            System.out.println(Arrays.toString(separateCurrent));
            if (i == 0) {
                idx = Integer.parseInt(separateCurrent[2]) - 1;
                functionRunningStack.add(Integer.parseInt(separateCurrent[0]));
//                System.out.println("runtime" + runtimeStack);
//                System.out.println("function: " + functionRunningStack);
                continue;
            }

            String[] separateBefore = logs.get(i-1).split(":");

            if (separateCurrent[1].equals("start") && separateBefore[1].equals("start")) {
                if (!functionRunningStack.isEmpty()) {
                    runtimeStack.add(new Stack(functionRunningStack.getLast(), Integer.parseInt(separateCurrent[2]) - idx - 1));
                }
                idx = Integer.parseInt(separateCurrent[2]) - 1;
            } else if (separateCurrent[1].equals("end") && separateBefore[1].equals("start")) {
                if (!functionRunningStack.isEmpty()) {
                    runtimeStack.add(new Stack(functionRunningStack.getLast(), Integer.parseInt(separateCurrent[2]) - idx));
                }
                idx = Integer.parseInt(separateCurrent[2]);
            } else if (separateCurrent[1].equals("end") && separateBefore[1].equals("end")) {
                runtimeStack.add(new Stack(Integer.parseInt(separateCurrent[0]), Integer.parseInt(separateCurrent[2]) - idx));
                idx = Integer.parseInt(separateCurrent[2]);
            } else if (separateCurrent[1].equals("start") && separateBefore[1].equals("end")){
                if (!functionRunningStack.isEmpty()) {
                    runtimeStack.add(new Stack(functionRunningStack.getLast(), Integer.parseInt(separateCurrent[2]) - idx - 1));
                }
                idx = Integer.parseInt(separateCurrent[2]) - 1;
            }

            if (separateCurrent[1].equals("start")) {
                functionRunningStack.add(Integer.parseInt(separateCurrent[0]));
            } else {
                functionRunningStack.removeLast();
            }
//            System.out.println("runtime" + runtimeStack);
//            System.out.println("function: " + functionRunningStack);
        }

        int[] ans = new int[n];
        for (Stack stack : runtimeStack) {
            ans[stack.functionId] += stack.value;
        }

        return ans;
    }
    // ==== leetcode:end ====

    static void main(String[] args) {
        new ExclusiveTimeOfFunctions();
    }
}
