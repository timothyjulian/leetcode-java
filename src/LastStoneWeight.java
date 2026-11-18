import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1046. Last Stone Weight (Easy)
 * https://leetcode.com/problems/last-stone-weight/
 *
 * @author Timothy Julian
 * @version $Id: LastStoneWeight.java, v 0.1 2026‐11‐18 14.38 timothy.julian Exp $$
 */
public class LastStoneWeight {

    public LastStoneWeight() {
        Judge j = new Judge();
        j.check("1", lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        j.check("1", lastStoneWeight(new int[]{1}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = stones.length - 1; i >= 0; i--) {
            heap.add(stones[i]);
        }

        while (heap.size() > 1) {
            int y = heap.poll();
            int x = heap.poll();

            int result = y - x;
            if (result != 0) {
                heap.add(result);
            }
        }


        return heap.isEmpty() ? 0 : heap.peek() ;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new LastStoneWeight();
    }
}
