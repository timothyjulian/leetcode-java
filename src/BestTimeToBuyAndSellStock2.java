public class BestTimeToBuyAndSellStock2 {

    public BestTimeToBuyAndSellStock2() {
        maxProfit(new int[]{7,1,5,3,6,4});
        maxProfit(new int[]{1,2,3,4,5});
        maxProfit(new int[]{7,6,4,3,1});
    }

    public int maxProfit(int[] prices) {
        int curr = prices[0];
        int totalProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > curr) {
//                System.out.printf("%d %d\n", curr, prices[i]);
                totalProfit+= prices[i] - curr;
            }
            curr = prices[i];
        }

//        System.out.println(totalProfit);

        return totalProfit;
    }


    public static void main(String[] args) {
        new BestTimeToBuyAndSellStock2();
    }
}
