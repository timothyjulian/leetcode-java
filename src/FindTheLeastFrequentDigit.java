import java.util.Arrays;

public class FindTheLeastFrequentDigit {

    public FindTheLeastFrequentDigit() {
        System.out.println(getLeastFrequentDigit(1553322));
        System.out.println(getLeastFrequentDigit(723344511));
    }

    public int getLeastFrequentDigit(int n) {
        int[] map = new int[10];
        int mul = 10;
        while (n > 0) {
            int div = n / mul;
//            System.out.printf("%d / %d = %d * %d = %d | %d\n",n, div, div, mul, div * mul, n - (div * mul));
            int left = n - div * mul;
            map[left] += 1;
            n = div;
        }
//        System.out.println(Arrays.toString(map));
        int minNum = Integer.MAX_VALUE;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0 && map[i] < minCount) {
                minNum = i;
                minCount = map[i];
            }
        }

        return minNum;
    }

    public static void main(String[] args) {
        new FindTheLeastFrequentDigit();
    }
}
