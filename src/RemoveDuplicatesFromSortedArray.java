import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {
    public RemoveDuplicatesFromSortedArray() {
        removeDuplicates(new int[]{1,1,2});
        removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
    }

    public int removeDuplicates(int[] nums) {
        int ptr1 = 0;
        int ptr2 = 0;
        while (ptr2 < nums.length) {
//            System.out.println(Arrays.toString(nums));
//            System.out.println("ptr1: " + ptr1);
            if (nums[ptr1] == nums[ptr2]) {
                ptr2++;
            } else {
                ptr1++;
                nums[ptr1] = nums[ptr2];
            }
        }
        return ptr1+1;
    }

    public static void main(String[] args) {
        new RemoveDuplicatesFromSortedArray();
    }
}
