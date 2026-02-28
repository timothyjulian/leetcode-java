import java.util.Arrays;

public class MergeSortedArray {

    public MergeSortedArray () {
        int[] num1 = new int[]{0};
        merge(num1, 0, new int[]{1}, 1);
//        System.out.println("final: " + Arrays.toString(num1));
        num1 = new int[]{1};
        merge(num1, 0, new int[]{}, 0);
//        System.out.println("final: " + Arrays.toString(num1));
        num1 = new int[]{1,2,3,0,0,0};
        merge(num1, 3, new int[]{2, 5, 6}, 3);
//        System.out.println("final: " + Arrays.toString(num1));
        num1 = new int[]{2, 0};
        merge(num1, 1, new int[]{1}, 1);
//        System.out.println("final: " + Arrays.toString(num1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }

        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, nums1.length);
            return;
        }

        int ptr0 = nums1.length - 1;
        int ptr1 = m - 1;
        int ptr2 = n - 1;

        while(ptr1 >= 0 || ptr2 >= 0) {
//            System.out.println("ptr0: " + ptr0);
//            System.out.println("ptr1: " + ptr1 + " "+ Arrays.toString(nums1));
//            System.out.println("ptr2: " + ptr2 + " "+ Arrays.toString(nums2));
            if (ptr1 == ptr0) {
                break;
            } else if (ptr1 < 0) {
                nums1[ptr0] = nums2[ptr2];
                ptr2--;
                ptr0--;
                continue;
            } else if (ptr2 < 0) {
                nums1[ptr0] = nums1[ptr1];
                nums1[ptr1] = 0;
                ptr1--;
                ptr0--;
                continue;
            }
            if (nums1[ptr1] > nums2[ptr2]) {
                nums1[ptr0] = nums1[ptr1];
                nums1[ptr1] = 0;
                ptr1--;
            } else {
                nums1[ptr0] = nums2[ptr2];
                ptr2--;
            }
            ptr0--;


        }

//        System.out.println("final: " + Arrays.toString(nums1));
    }

    public static void main(String[] args) {
        new MergeSortedArray();
    }
}
