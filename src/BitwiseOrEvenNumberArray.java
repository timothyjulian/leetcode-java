public class BitwiseOrEvenNumberArray {
    public BitwiseOrEvenNumberArray() {
        System.out.println(evenNumberBitwiseORs(new int[]{1,2,3,4,5,6}));
        System.out.println(evenNumberBitwiseORs(new int[]{7, 9, 11}));
        System.out.println(evenNumberBitwiseORs(new int[]{1, 8, 16}));
    }

    public int evenNumberBitwiseORs(int[] nums) {
        int ans = 0;
        for (var num: nums) {
            if (num % 2 == 0) {
                ans |= num;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
       new BitwiseOrEvenNumberArray();
    }
}
