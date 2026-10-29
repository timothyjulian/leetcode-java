import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 84. Largest Rectangle in Histogram (Hard)
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * @author Timothy Julian
 * @version $Id: LargestRectangleInHistogram.java, v 0.1 2026‐10‐30 00.05 timothy.julian Exp $$
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
        Deque<Integer> monotonicStack = new ArrayDeque<>();
        int max = 0;

        for (int i = 0; i <= heights.length; i++) {
            int height = i == heights.length ? 0 : heights[i];

            while (!monotonicStack.isEmpty() && heights[monotonicStack.peekLast()] > height) {
                int h = heights[monotonicStack.removeLast()];
                int left = monotonicStack.isEmpty() ? -1 : monotonicStack.peekLast();
                int area = h * (i - left - 1);
                max = Math.max(max, area);
            }

            if (i < heights.length) {
                monotonicStack.add(i);
            }
        }

        return max;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new LargestRectangleInHistogram();
    }
}
