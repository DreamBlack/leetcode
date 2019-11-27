/*
 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 *
 * algorithms
 * Easy (48.15%)
 * Likes:    3338
 * Dislikes: 149
 * Total Accepted:    625.8K
 * Total Submissions: 1.3M
 * Testcase Example:  '[7,1,5,3,6,4]'
 *
 * Say you have an array for which the i^th element is the price of a given
 * stock on day i.
 * 
 * If you were only permitted to complete at most one transaction (i.e., buy
 * one and sell one share of the stock), design an algorithm to find the
 * maximum profit.
 * 
 * Note that you cannot sell a stock before you buy one.
 * 
 * Example 1:
 * 
 * 
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit
 * = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * 
 * 解题思路：
 * 1、暴力法
 * O(N2)
 * 2、简单法
 * 考虑到本题只允许一次交易，那么肯定是prices[j]-prices[i]最小的那个；
 * 那么假设j固定，对这个j来说，maxprofit就是本身减去0-j中值最小的那个；
 * 所以对于每个j，保存对应的最小的prices[i]即可
 * 
 * 记录到第i个元素的最小值到minp，并随时更新，
 * 则所有prices[i]-minp中最大的就是maxProfit
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        int minp = prices[0];
        int ret = 0;
        for (int i = 1; i < prices.length; i++) {
            ret = Math.max(ret, prices[i] - minp);
            if (prices[i] <= minp) {
                minp = prices[i];
            }
        }
        return ret;
    }
}
// @lc code=end
