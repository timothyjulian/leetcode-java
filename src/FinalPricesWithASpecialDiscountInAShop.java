/**
 * DANA Indonesia
 * Copyright (c) 2017‐2026 All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1475. Final Prices With a Special Discount in a Shop (Easy)
 * https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
 *
 * @author Timothy Julian
 * @version $Id: FinalPricesWithASpecialDiscountInAShop.java, v 0.1 2026‐10‐21 15.57 timothy.julian Exp $$
 */
public class FinalPricesWithASpecialDiscountInAShop {

    public FinalPricesWithASpecialDiscountInAShop() {
        Judge j = new Judge();
        j.check("[4,2,4,2,3]", finalPrices(new int[]{8, 4, 6, 2, 3}));
        j.check("[1,2,3,4,5]", finalPrices(new int[]{1, 2, 3, 4, 5}));
        j.check("[9,0,1,6]", finalPrices(new int[]{10, 1, 1, 6}));
        j.check("[1,3,2,1,7,0,0,6,9,1]", finalPrices(new int[]{8,7,4,2,8,1,7,7,10,1}));
        j.check("[3,6,1,5,0,0,4,5,4]", finalPrices(new int[]{4,7,1,9,4,8,8,9,4}));
        j.check("[0,3,3,4,1,5,7,2]", finalPrices(new int[]{4,4,8,5,1,7,9,2}));
        j.done();
    }

    // ==== leetcode:begin ====
    public int[] finalPrices(int[] prices) {
        record Stack (int value, int index){
        }
        List<Stack> monotonicStack = new ArrayList<>();
        int[] ans = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
//            System.out.println("ans : " + Arrays.toString(ans));
//            System.out.println("monotonic: " + monotonicStack);
            int price = prices[i];
//            System.out.println("\ncurrent: " + price);

            if (monotonicStack.isEmpty() || price > monotonicStack.getLast().value) {
                monotonicStack.add(new Stack(price, i));
            } else {
                while(!monotonicStack.isEmpty() && price <= monotonicStack.getLast().value) {
                    Stack lastStack = monotonicStack.removeLast();
                    ans[lastStack.index] = lastStack.value - price;
                }
                monotonicStack.add(new Stack(price, i));
            }
        }
        for (Stack stack: monotonicStack) {
            ans[stack.index] = stack.value;
        }


//        System.out.println("ans : " + Arrays.toString(ans));
//        System.out.println("monotonic: " + monotonicStack);

        return ans;
    }
    // ==== leetcode:end ====

    public static void main(String[] args) {
        new FinalPricesWithASpecialDiscountInAShop();
    }
}
