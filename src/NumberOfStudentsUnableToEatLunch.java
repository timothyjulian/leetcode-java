import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1700. Number of Students Unable to Eat Lunch (Easy)
 * https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
 *
 * @author Timothy Julian
 * @version $Id: NumberOfStudentsUnableToEatLunch.java, v 0.1 2026‐11‐03 17.31 timothy.julian Exp $$
 */
public class NumberOfStudentsUnableToEatLunch {

    public NumberOfStudentsUnableToEatLunch() {
        Judge j = new Judge();
        j.check("0", countStudents(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}));
        j.check("3", countStudents(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 1, 1}));
        j.check("5", countStudents(new int[]{0,0,0,1,0,1,1,1,1,0,1}, new int[]{0,0,0,1,0,0,0,0,0,1,0}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int countStudents(int[] students, int[] sandwiches) {
        Deque<Integer> qStudents = new ArrayDeque<>();
        Deque<Integer> sSandwiches = new ArrayDeque<>();

        for (int j : students) {
            qStudents.add(j);
        }

        for (int i = sandwiches.length - 1; i >= 0; i--) {
            sSandwiches.add(sandwiches[i]);
        }


        int count = 0;
        while (!qStudents.isEmpty() && !sSandwiches.isEmpty() && count <= sSandwiches.size()) {
//            System.out.println("current: " + qStudents.peekFirst());
//            System.out.println("student: " + qStudents);
//            System.out.println("sandwich: " + sSandwiches.reversed() + "\n");
            int current = qStudents.removeFirst();


            if (sSandwiches.peekLast() == current) {
                count = 0;
                sSandwiches.removeLast();
            } else {
                count++;
                qStudents.add(current);
            }

        }

        if (count == 0) return 0;
        return count - 1;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new NumberOfStudentsUnableToEatLunch();
    }
}
