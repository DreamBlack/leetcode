/*
 * @lc app=leetcode id=714 lang=cpp
 *
 * [714] Best Time to Buy and Sell Stock with Transaction Fee
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
 *
 * algorithms
 * Medium (50.43%)
 * Likes:    954
 * Dislikes: 32
 * Total Accepted:    42.5K
 * Total Submissions: 83.4K
 * Testcase Example:  '[1,3,2,8,4,9]\n2'
 *
 * Your are given an array of integers prices, for which the i-th element is
 * the price of a given stock on day i; and a non-negative integer fee
 * representing a transaction fee.
 * You may complete as many transactions as you like, but you need to pay the
 * transaction fee for each transaction.  You may not buy more than 1 share of
 * a stock at a time (ie. you must sell the stock share before you buy again.)
 * Return the maximum profit you can make.
 * 
 * Example 1:
 * 
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1Selling at prices[3] = 8Buying at prices[4] =
 * 4Selling at prices[5] = 9The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) =
 * 8.
 * 
 * 
 * 
 * Note:
 * 0 < prices.length .
 * 0 < prices[i] < 50000.
 * 0 .
 * 
 * 
 * 16:37
 * 想不出来，怎么判断是一次性卖出好，还是分次卖出好
 * [1,4,6,2,8,3,10,14]，fee=3
 * 动态规划：
 * 1、先确定是否满足动态规划的两个特征
 * （1）最优解结构。
 * （2）重叠子问题。
 * 2、确定状态转移方程
 * 最后要求的是获得的利润，所以保存的表肯定是什么什么情况下的获利
 * 画树的时候分成了不动/买/卖三种，但是题目有条件肯定是买过才能卖，
 * 卖光了才能买。所以要注意手里到底有没有股票，这是就可以想到用
 * hold[i]表示第i个股票发售时，手里还有股票时获得的最大利润；
 * sold[i]表示第i个股票发售时，手里没有股票时获得的最大利润。
 * 返回的便是sold[n-1]
 * 
 * hold[i]=max(hold[i-1],sold[i-1]-prices[i])
 * sold[i]=max(sold[i-1],hold[i-1]+prices[i]-fee)
 */
class Solution {
public:
    int maxProfit1(vector<int>& prices, int fee) {
        
       vector<int>hold(prices.size(),0);//i时，手里有股票的利润
       vector<int>sold(prices.size(),0);//i时，手里没股票的利润
       hold[0]=-prices[0];
       for(int i=1;i<prices.size();i++){
           //手里还有股票，要么是保持了上次的有股票；要么是上次没股票的时候这次买入了
           //记住题目条件是只有手里没股票才能再买
           hold[i]=max(hold[i-1],sold[i-1]-prices[i]);//买入的时候是减法
           //手里没股票，要么是上次没股票保持，要么是有股票这次卖了
           //这次卖的时候以现在的市价，所以有收益，但是卖的时候要手续费
           //买的时候不要手续费
           sold[i]=max(sold[i-1],hold[i-1]+prices[i]-fee);
       }
        return sold[prices.size()-1];
    }
    int maxProfit(vector<int>& prices, int fee) {
        //由状态转移方程可以知道，每次只用到hold[i-1]和sold[i-1]
      int hold=-prices[0],sold=0;
       for(int i=1;i<prices.size();i++){
           int temph=hold,temps=sold;
           //手里没股票，要么是上次没股票保持，要么是有股票这次卖了
           //这次卖的时候以现在的市价，所以有收益，但是卖的时候要手续费
           //买的时候不要手续费
          sold=max(temps,temph+prices[i]-fee);
           //手里还有股票，要么是保持了上次的有股票；要么是上次没股票的时候这次买入了
           //记住题目条件是只有手里没股票才能再买
           //如果sold=temph+prices[i]-fee，表示这次是卖掉了
           hold=max(hold,temps-prices[i]);//买入的时候是减法
           //如果hold=temps-prices[i]，表示这次是买入。
           
       }
        return sold;
    }
};

