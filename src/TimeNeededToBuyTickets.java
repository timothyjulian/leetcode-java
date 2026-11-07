import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2073. Time Needed to Buy Tickets (Easy)
 * https://leetcode.com/problems/time-needed-to-buy-tickets/
 *
 * @author Timothy Julian
 * @version $Id: TimeNeededToBuyTickets.java, v 0.1 2026‐11‐06 16.04 timothy.julian Exp $$
 */
public class TimeNeededToBuyTickets {

    public TimeNeededToBuyTickets() {
        Judge j = new Judge();
        j.check("6", timeRequiredToBuy(new int[]{2, 3, 2}, 2));
        j.check("8", timeRequiredToBuy(new int[]{5, 1, 1, 1}, 0));
        j.done();
    }

    // ==== leetcode:begin ====
    public int timeRequiredToBuy(int[] tickets, int k) {
        List<Integer> queue = new ArrayList<>();
        List<Boolean> marking = new ArrayList<>();

        for(int i = 0; i < tickets.length; i++) {
            queue.add(tickets[i]);
            marking.add(i == k);
        }

//        System.out.println(queue);
//        System.out.println(marking);
        int count = 0;
        while (!queue.isEmpty()) {
            int front = queue.removeFirst();
            boolean mark = marking.removeFirst();
            front--;
            count++;

            if (front > 0) {
                queue.add(front);
                marking.add(mark);
            } else if (mark){
                break;
            }
        }

        return count;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new TimeNeededToBuyTickets();
    }
}
