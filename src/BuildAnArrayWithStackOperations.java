/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1441. Build an Array With Stack Operations (Medium)
 * https://leetcode.com/problems/build-an-array-with-stack-operations/
 *
 * @author Timothy Julian
 * @version $Id: BuildAnArrayWithStackOperations.java, v 0.1 2026‐10‐13 11.28 timothy.julian Exp $$
 */
public class BuildAnArrayWithStackOperations {

    public BuildAnArrayWithStackOperations() {
        Judge j = new Judge();
        j.check("[Push,Push,Pop,Push]", buildArray(new int[]{1, 3}, 3));
        j.check("[Push,Push,Push]", buildArray(new int[]{1, 2, 3}, 3));
        j.check("[Push,Push]", buildArray(new int[]{1, 2}, 4));
        j.done();
    }

    // ==== leetcode:begin ====
    public List<String> buildArray(int[] target, int n) {
        int[] temp = new int[target.length];
        List<String> ans = new ArrayList<>();
        final String PUSH = "Push";
        final String POP = "Pop";

        int idx = 0;
        for (int i = 1; i <= n; i++) {
            if (Arrays.equals(temp, target)) {
                break;
            }

            if (i == target[idx]) {
                ans.add(PUSH);
                temp[idx] = target[idx];
                idx++;
                continue;
            }

            ans.add(PUSH);
            ans.add(POP);
        }


        return ans;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new BuildAnArrayWithStackOperations();
    }
}
