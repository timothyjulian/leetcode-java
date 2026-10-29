import java.util.ArrayList;
import java.util.List;

/**
 * 84. Largest Rectangle in Histogram (Hard)
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * @author Timothy Julian
 * @version $Id: LargestRectangleInHistogram.java, v 0.1 2026‐10‐23 15.08 timothy.julian Exp $$
 */
public class LargestRectangleInHistogram {

    public LargestRectangleInHistogram() {
        Judge j = new Judge();
        j.check("10", largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        j.check("4", largestRectangleArea(new int[]{2, 4}));
        j.check("4", largestRectangleArea(new int[]{2, 3}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE;
        record Stack (int height, int index, int minimumHeightUntilThis, int maximumAreaUntilThis) {}
        List<Stack> monotonicStackDown = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            if (monotonicStackDown.isEmpty()) {
                monotonicStackDown.add(new Stack(height, i, height, height));
            } else if (height <= monotonicStackDown.getLast().height) {
                Stack last = monotonicStackDown.getLast();
                int minimumHeightUntilThis = Math.min(last.height, height);
                int maximumAreUntilThis = Math.max(last.maximumAreaUntilThis, (monotonicStackDown.size() + 1) * minimumHeightUntilThis);
                monotonicStackDown.add(new Stack(height, i, minimumHeightUntilThis, maximumAreUntilThis));
            } else {
                max = Math.max(monotonicStackDown.getLast().maximumAreaUntilThis, max);
                monotonicStackDown.clear();
                monotonicStackDown.add(new Stack(height, i, height, height));
            }
//            System.out.println(monotonicStackDown);
//            System.out.println(max);
        }

        max = Math.max(monotonicStackDown.getLast().maximumAreaUntilThis, max);
//        System.out.println(monotonicStackDown);
//        System.out.println(max);
//
//        System.out.println("==split");

        monotonicStackDown.clear();
        for (int i = heights.length-1; i >= 0; i--) {
            int height = heights[i];
            if (monotonicStackDown.isEmpty()) {
                monotonicStackDown.add(new Stack(height, i, height, height));
            } else if (height <= monotonicStackDown.getLast().height) {
                Stack last = monotonicStackDown.getLast();
                int minimumHeightUntilThis = Math.min(last.height, height);
                int maximumAreUntilThis = Math.max(last.maximumAreaUntilThis, (monotonicStackDown.size() + 1) * minimumHeightUntilThis);
                monotonicStackDown.add(new Stack(height, i, minimumHeightUntilThis, maximumAreUntilThis));
            } else {
                max = Math.max(monotonicStackDown.getLast().maximumAreaUntilThis, max);
                monotonicStackDown.clear();
                monotonicStackDown.add(new Stack(height, i, height, height));
            }
//            System.out.println(monotonicStackDown);
//            System.out.println(max);
        }

        max = Math.max(monotonicStackDown.getLast().maximumAreaUntilThis, max);
//        System.out.println(monotonicStackDown);
//        System.out.println(max);

        return max;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new LargestRectangleInHistogram();
    }
}
