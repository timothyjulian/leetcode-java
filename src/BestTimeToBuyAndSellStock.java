public class BestTimeToBuyAndSellStock {
    public BestTimeToBuyAndSellStock() {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));;
        maxProfit(new int[]{7,6,4,3,1});
    }

    public int maxProfit(int[] prices) {
        int ptr1;
        int ptr2 = 1;
        int curr = prices[0];
        int maxProfit = Integer.MIN_VALUE;
        while (ptr2 < prices.length) {
//            System.out.printf("%d %d %d\n", ptr2, curr, maxProfit);
            if (curr < prices[ptr2]) {
                int profit = prices[ptr2] - curr;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            } else {
                ptr1 = ptr2;
                curr = prices[ptr1];
            }
            ptr2++;
        }

        if (maxProfit < 0) {
            maxProfit = 0;
        }
        return maxProfit;
    }


    public static void main(String[] args) {
        new BestTimeToBuyAndSellStock();
    }
}
