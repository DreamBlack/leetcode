/*
 * @lc app=leetcode id=518 lang=java
 *
 * [518] Coin Change 2
 *
 * https://leetcode.com/problems/coin-change-2/description/
 *
 * algorithms
 * Medium (44.07%)
 * Likes:    1047
 * Dislikes: 43
 * Total Accepted:    63.5K
 * Total Submissions: 141.2K
 * Testcase Example:  '5\n[1,2,5]'
 *
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that
 * amount. You may assume that you have infinite number of each kind of
 * coin.
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: amount = 10, coins = [10] 
 * Output: 1
 * 
 * 
 * 
 * 
 * Note:
 * 
 * You can assume that
 * 
 * 
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 * 
 * 解题思路1：
 * 首先这题用完全背包肯定是能做出来的，后来自己试了一下果然可以。
 * 只要想清楚用第i个物品和不用第i个物品对题意要求所产生的影响就可以了。
 * 
 * 解题思路2：
 * 本来还想着按照递归的那个思路考虑一遍，发现会有相同的组合多次重复
 * 计算的问题。所以说有的时候还是先考虑背包吧
 */

// @lc code=start
class Solution {

    public int change(int amount, int[] coins) {
        // 完全背包的一维版本
        // 这次的dp初始化不是全为0了，dp[i][0]=1;
        if (amount == 0)
            return 1;
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    dp[j] = dp[j];
                } else if (j == coins[i - 1]) {
                    dp[j] = 1 + dp[j];// 这句话其实就是让dp[0]==1
                } else {
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
                }
            }
        }
        // for(int i=1;i<=coins.length;i++){
        // for(int j=1;j<=amount;j++){
        // System.out.print(dp[i][j]+" ");
        // }
        // System.out.println();
        // }
        return dp[amount];
    }

    public int change1(int amount, int[] coins) {
        // 完全背包的二维版本
        if (amount == 0)
            return 1;
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else if (j == coins[i - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        // for(int i=1;i<=coins.length;i++){
        // for(int j=1;j<=amount;j++){
        // System.out.print(dp[i][j]+" ");
        // }
        // System.out.println();
        // }
        return dp[coins.length][amount];
    }
}
// @lc code=end
