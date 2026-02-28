import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    public SummaryRanges() {
        System.out.println(summaryRanges(new int[]{0,1,2,4,5,7}));
        System.out.println(summaryRanges(new int[]{0,2,3,4,6,8,9}));
        System.out.println(summaryRanges(new int[]{-1}));
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums.length == 1){
            ans.add(String.valueOf(nums[0]));
            return ans;
        }
        int l = 0;
        int r = 1;
        while (r < nums.length) {
            if (nums[r-1] != nums[r] - 1) {
                if (l == r - 1) {
                    ans.add(String.valueOf(nums[r-1]));
                } else {
                    ans.add(String.format("%d->%d", nums[l], nums[r-1]));
                }
                l = r;
            }
            if (r == nums.length - 1) {
                if (nums[r] - 1 != nums[r-1]) {
                    ans.add(String.valueOf(nums[r]));
                } else {
                    ans.add(String.format("%d->%d", nums[l], nums[r]));
                }
            }
            r++;
        }

        return ans;
    }

    public static void main(String[] args) {
        new SummaryRanges();
    }
}
