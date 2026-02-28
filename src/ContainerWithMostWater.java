public class ContainerWithMostWater {
    public ContainerWithMostWater() {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(maxArea(new int[]{1, 1}));
        System.out.println(maxArea(new int[]{1, 2, 1}));
    }

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length-1;
        int max = 0;
        while (l < r) {
            int minHeight = Math.min(height[l], height[r]);
            int area = minHeight * (r - l);
            max = Math.max(max, area);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return max;
    }


    public static void main(String[] args) {
        new ContainerWithMostWater();
    }
}
