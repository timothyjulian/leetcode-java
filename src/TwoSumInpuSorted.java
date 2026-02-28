import java.util.Arrays;

public class TwoSumInpuSorted {
    public TwoSumInpuSorted() {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(twoSum(new int[]{-1, 0}, -1)));
    }

    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i< numbers.length-1; i++) {
            int index = binSearch(numbers, i+1, numbers.length-1, target - numbers[i]);
//            System.out.printf("%d %d %d\n", i, index, target - numbers[i]);
            if (index != -1) {
                return new int[]{i+1, index+1};
            }
        }

        return new int[0];
    }

    private int binSearch(int[] numbers, int l, int r, int target) {
        while (l <= r) {
            int mid = (l + r)/2;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        new TwoSumInpuSorted();
    }
}
