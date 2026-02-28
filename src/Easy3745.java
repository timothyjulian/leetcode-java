import java.util.Arrays;

public class Easy3745 {

    public Easy3745(){
        System.out.println(maximizeExpressionOfThree(new int[]{}));
        System.out.println(maximizeExpressionOfThree(new int[]{}));
    }

    public int maximizeExpressionOfThree(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] + nums[nums.length-2] - nums[0];
    }

    public static void main(String[] args) {
        new Easy3745();
    }
}
