import java.lang.reflect.Array;
import java.util.Arrays;

public class RemoveElement {

    public RemoveElement() {
        removeElement(new int[]{3,2,2,3}, 3);
        removeElement(new int[]{0,1,2,2,3,0,4,2}, 2);
    }

    public int removeElement(int[] nums, int val) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                nums[i] = -1;
            }
        }

        int ptr1 = 0;
        int ptr2 = 0;

        while (ptr2 < nums.length) {
            if (nums[ptr1] != -1 && nums[ptr2] != -1) {
                ptr1++;
                ptr2++;
            } else if (nums[ptr1] == -1 && nums[ptr2] != -1) {
                nums[ptr1] = nums[ptr2];
                nums[ptr2] = -1;
            } else if (nums[ptr1] != -1 && nums[ptr2] == -1) {
                ptr1++;
            } else {
                ptr2++;
            }
        }

        int counter = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != -1) {
                counter++;
            }
        }

        return counter;
    }

    public static void main(String[] args) {
        new RemoveElement();
    }

}
