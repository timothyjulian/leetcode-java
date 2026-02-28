public class MaxSquareDigit {

    public MaxSquareDigit() {
        System.out.println(maxSumOfSquares(2, 3));
        System.out.println(maxSumOfSquares(2, 17));
        System.out.println(maxSumOfSquares(1, 10));
    }

    public String maxSumOfSquares(int num, int sum) {
        long start = (long) Math.pow(10, num-1);
        long end = (long) Math.pow(10, num);

        StringBuilder ans = new StringBuilder();

        long maxAns = start;
        long max = Long.MIN_VALUE;

        for (long i = start; i < end; i++) {
            long temp = 0;
            long a = i;
            while (a != 0) {
                long x = a % 10;
                temp += x;
                a /= 10;
            }
//            System.out.println(temp);

            if (temp == sum) {
//                System.out.println(i);
                long z = i;
                long s = 0;
                while (z != 0) {
                    long x = z % 10;
//                    System.out.printf("sisa: %d\n", x);
                    s += (long) Math.pow(x, 2);
                    z /= 10;
                }
                if (s >= max) {
                    max = z;
                    maxAns = i;
                }
            }
        }

        if (max != Long.MIN_VALUE) {
            ans = new StringBuilder(String.valueOf(maxAns));
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        new MaxSquareDigit();
    }
}
