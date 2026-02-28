import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThreeSum {
    public ThreeSum() {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(threeSum(new int[]{0,1,1}));
        System.out.println(threeSum(new int[]{0,0,0}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int l = 0;
        Set<List<Integer>> ans = new HashSet<>();
        int target = 0;
        while (l < nums.length - 2) {
            int currTarget = target - nums[l];
//            System.out.println(currTarget);
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = l+1; i < nums.length; i++) {
//                System.out.println(map);
                int diff = currTarget - nums[i];
                if (map.get(nums[i]) != null) {
                    List<Integer> currList = new ArrayList<>();
                    currList.add(nums[l]);
                    currList.add(map.get(nums[i]));
                    currList.add(nums[i]);
                    Collections.sort(currList);

                    ans.add(currList);
                } else {
                    map.put(diff, nums[i]);
                }
            }
            l++;
        }
//        System.out.println(ans);

        return ans.stream().toList();
    }

    public static void main(String[] args) {
       new ThreeSum();
    }
}
