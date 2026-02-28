import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SmallestAbsentAveragePositive {

    public SmallestAbsentAveragePositive() {
        System.out.println(smallestAbsent(new int[]{3, 5}));
        System.out.println(smallestAbsent(new int[]{-1, 1, 2}));
        System.out.println(smallestAbsent(new int[]{4, -1}));
        System.out.println(smallestAbsent(new int[]{98, 100}));
    }

    public int smallestAbsent(int[] nums) {
        int sum = 0;
        for (var num : nums) {
            sum += num;
        }
        int average = sum / nums.length;
        Set<Integer> map = new HashSet<>();
        for (var num: nums) {
            map.add(num);
        }
        average = average > 0 ? average + 1 : 1;
        for (int i = average; i <= 101; i++) {
            if (!map.contains(i)) return i;
        }

        return 0;
    }

    public static void main(String[] args) {
        new SmallestAbsentAveragePositive();
    }
}
