public class MaxConsecutiveOne {

    public MaxConsecutiveOne() {
        System.out.println(findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int lastZero = -1;
        int longest = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                longest = Math.max(i - lastZero - 1, longest);
                lastZero = i;
            }

            if (i == nums.length - 1 && nums[i] == 1) { 
                longest = Math.max(i - lastZero, longest);
            }
        }

        return longest;
    }


    public static void main(String[] args) {
        new MaxConsecutiveOne();   
    }
}