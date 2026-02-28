public class MaximumTotalSubarray {

    public MaximumTotalSubarray() {
        System.out.println(maxTotalValue(new int[]{1, 3, 2}, 2));
        System.out.println(maxTotalValue(new int[]{4, 2, 5, 1}, 3));
    }

    public long maxTotalValue(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }

        long ans = (long) (max - min) * k;

        return ans;
    }

    public static void main(String[] args) {
       new MaximumTotalSubarray();
    }
}
