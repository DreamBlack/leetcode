import java.util.ArrayList;
import java.util.Arrays;

/*
 * @lc app=leetcode id=188 lang=java
 *
 * [188] Best Time to Buy and Sell Stock IV
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 *
 * algorithms
 * Hard (26.82%)
 * Likes:    1013
 * Dislikes: 64
 * Total Accepted:    104.8K
 * Total Submissions: 386.5K
 * Testcase Example:  '2\n[2,4,1]'
 *
 * Say you have an array for which the i-th element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most k
 * transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * 
 * Example 1:
 * 
 * 
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit
 * = 4-2 = 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit
 * = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 =
 * 3.
 * 
 * 解题思路：
 * 1、按着123的思路，用一个k*2的数组分别保存k次操作买入和卖出时手里的最大值
 * 但是有2个用例超时了
 * 问题是下面怎么弄呢
 * 
 * 自己想不出来，看了网上的解法
 * 本来是用一个单个变量来记录，现在有k次可以用k个变量来记录，即使用
 * dpbuy[k]记录第k次买入，手里的最大钱的数量
 * dpsell[k]记录第k次卖出，手里的最大钱的数量
 * 
 * dpbuy[i]=Math.max(dpbuy[i],dpsell[i-1]-price);
 * dpsell[i]=Math.max(dpsell[i],dpbuy[i]+price);
 * 使用这个方法后仍然会有两个用例过不了的，答案加了一个quickSolve
 * 
 * 3、另一种不是很好理解的dp思路
 * dp[i][j]表示在股票天数0-j中，最多i次交易可以获得的最大利益，那么
 * dp[i][j]有两种选择，不买也不卖，卖（买肯定花钱，没有不买也不卖的钱多）
 * 不卖也不买：dp[i-1][j-1]
 * 卖：肯定是之前0-j-1中有一天买入了，并且还没卖，假设是第t天，则为
 * dp[i-1][t-1]-prices[t]+prices[j].要找这个的最大值也就是找dp[i-1][t-1]-prices[t]
 * 的最大值，本来这个操作需要O(n)的，但是观察到对于第i行来说，dp[i-1]一行的值是已知的，
 * 而dp[i-1][t-1]-prices[t]只是随着j的变化有可能变化，如果用temp来记录这个最大值，
 * 就可以少O（N）的时间了
 * 
 * tips:
 * 1、输入除了数组还有一个k，可能要从空间O(1)的dp变为空间O(K)的dp
 * 2、Arrays.fill(nums,0);
 */

// @lc code=start
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0)
            return 0;
        if (prices.length / 2 <= k)
            return quickSolve(prices);
        int[][] dp = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++) {
            int tmp = -prices[0];
            for (int j = 0; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], prices[j] + tmp);
                if (tmp < dp[i - 1][j - 1] - prices[j]) {
                    tmp = dp[i - 1][j - 1] - prices[j];
                }
            }
        }
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < prices.length; j++) {
                System.out.println(dp[i][j] + " ");

            }
            System.out.println("");
        }
        return dp[k][prices.length - 1];
    }

    public int maxProfit2(int k, int[] prices) {
        if (k == 0 || prices.length == 0)
            return 0;
        if (prices.length / 2 <= k)
            return quickSolve(prices);
        // 为什么是二分之一，因为买卖不可能在同一天，所以最多len/2次交易
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        return sell[k];
    }

    public int maxProfit1(int k, int[] prices) {
        if (k == 0 || prices.length == 0)
            return 0;
        if (prices.length / 2 <= k)
            return quickSolve(prices);
        int[][] dp = new int[k][2];
        // for(int i=0;i<k;i++){
        // dp[i][0]=Integer.MIN_VALUE;
        // // System.out.println(dp.get(i).get(0)+" "+dp.get(i).get(1)+" ");
        // }
        for (int i = 0; i < prices.length; i++) {
            int pre = 0;
            for (int j = 0; j < k; j++) {
                if (i == 0) {
                    dp[j][0] = Integer.MIN_VALUE;
                }
                dp[j][0] = Math.max(dp[j][0], pre - prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j][0] + prices[i]);
                pre = dp[j][1];
            }
        }
        return dp[k - 1][1];
        // return 0;
    }

    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
// @lc code=end
