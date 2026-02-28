import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    public TwoSum() {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(twoSum(new int[]{3, 3}, 6)));
    }

    public int[] twoSum(int[] nums, int target) {
        var map = new HashMap<Integer, Integer>();
        for(int i = 0; i <nums.length; i++) {
            int diff = target - nums[i];
            if (map.get(nums[i]) == null) {
                map.put(diff, i);
            } else {
                return new int[]{map.get(nums[i]), i};
            }
        }

        return new int[]{0,0};
    }


    public static void main(String[] args) {
        new TwoSum();
    }
}
