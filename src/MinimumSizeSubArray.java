public class MinimumSizeSubArray {
    public MinimumSizeSubArray () {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(minSubArrayLen(4, new int[]{1,4,4}));
        System.out.println(minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int r = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        while (r < nums.length) {
            sum += nums[r];
//            System.out.printf("%d %d %d %d\n", nums[l], nums[r], sum, min);
            while (sum >= target) {
                min = Math.min(min, r-l+1);
                sum -= nums[l];
                l++;
//                System.out.printf("%d %d %d %d\n", nums[l], nums[r], sum, min);
            }
            r++;
        }
        if (min == Integer.MAX_VALUE) return 0;

        return min;
    }

    public static void main(String[] args) {
        new MinimumSizeSubArray();
    }
}
