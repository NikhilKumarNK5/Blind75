/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a
different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction.
If you cannot achieve any profit, return 0.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.

Constraints:
1 <= prices.length <= 105
0 <= prices[i] <= 104
*/

public class BestTimeToBuyAndSellStocks {
    // Approach 1 - Brute Force
    // We can take every single pair, and we can compare to check the max profit
    // TC => O(N^2) -> this works but will give TLE
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                int currentProfit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, currentProfit);
            }
        }
        return maxProfit;
    }

    // Approach 2 - Optimal Solution
    // MaxProfit would be when we buy at the lowest value and sell at the highest values in future
    // TC => O(N)
    public int maxProfitOptimal(int[] prices) {
        int buyPrice = prices[0]; // this will track the min buyPrice
        int maxProfit = 0; // initializing the max profit

        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < buyPrice) {
                buyPrice = prices[i]; // if the current price is less than the previous buy price we update the buyPrice
            } else { // if the current price is greater we calculate the current profit and max profit
                int currentProfit = prices[i] - buyPrice;
                maxProfit = Math.max(maxProfit, currentProfit);
            }
        }
        return maxProfit;
    }
}
