import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence {
    public LongestConsecutiveSequence() {
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
        System.out.println(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
        System.out.println(longestConsecutive(new int[]{1,0,1,2}));
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (var num : nums) {
            numSet.add(num);
        }

        int max = 0;
        for (var num : numSet) {
            if (!numSet.contains(num - 1)) {
                int count = 1;
                int temp = num;
                while (numSet.contains(temp+1)) {
                    count++;
                    temp++;
                }
                max = Math.max(max, count);
            }
        }


        return max;
    }



    public static void main(String[] args) {
        new LongestConsecutiveSequence();
    }
}
