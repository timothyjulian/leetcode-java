import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate2 {

    public ContainsDuplicate2() {
        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i])!=null) {
                int ans = Math.abs(map.get(nums[i]) - i);
                if (ans <= k ) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        new ContainsDuplicate2();
    }
}
