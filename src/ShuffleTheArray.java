import java.util.Arrays;

public class ShuffleTheArray {

    public ShuffleTheArray() {
        System.out.println(Arrays.toString(shuffle(new int[]{2,5,1,3,4,7}, 3)));
        System.out.println(Arrays.toString(shuffle(new int[]{1,2,3,4,4,3,2,1}, 4)));
        System.out.println(Arrays.toString(shuffle(new int[]{1,1,2,2}, 2)));

    }

    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[nums.length];
        int x = 0;
        int y = n;
        for(int i = 0; i < 2 * n; i+=2) {
            ans[i] = nums[x];
            ans[i+1] = nums[y];
            x++;
            y++;
        }

        return ans;
    }    

    public static void main(String[] args) {
        new ShuffleTheArray();        
    }
}
