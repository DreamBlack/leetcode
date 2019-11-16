/*
 * @lc app=leetcode id=322 lang=java
 *
 * [322] Coin Change
 *
 * https://leetcode.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (31.73%)
 * Likes:    2416
 * Dislikes: 87
 * Total Accepted:    265K
 * Total Submissions: 818.7K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * You are given coins of different denominations and a total amount of money
 * amount. Write a function to compute the fewest number of coins that you need
 * to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 * 
 * Example 1:
 * 
 * 
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3 
 * Explanation: 11 = 5 + 5 + 1
 * 
 * Example 2:
 * 
 * 
 * Input: coins = [2], amount = 3
 * Output: -1
 * 
 * 
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 * 
 * 法一：
 * 自顶向下递归的同时用数字记录减少重复计算
 * 
 * 法二：
 * 直接自底向上记录，返回最后的顶部
 * 
 * 
 * 法三：完全背包问题的二维方法
 * 每个硬币可以用多次：完全背包问题
 * 难点1：能不能装如何体现:0表示不能装，
 * 难点2：dp保存什么东西：用i种物品能组成的和为j的最小的物品个数，如果不能组成则保存0
 * 难点3：其状态转移方程：
 *           dp[i][j]=Math.min(dp[i-1][j],dp[i][j-coins[i-1]]+1);
 *           前者是dp[i-1][j]表示不用第i个物品；
 *           后者是dp[i][j-weight]+value表示用第i个，由于是完全背包所以用的话就至少用过一次
 *           所以是i，而不是i-1
 * 
 * 法四：完全背包问题的一维方法
 * dp[i][j]=Math.min(dp[i-1][j],dp[i][j-coins[i-1]]+1);
 * 由于在讨论前i个的时候用到了dp[i][j-coins[i-1]]，必须从左到右遍历，这样才能用到i而不是i-1的
 * dp[j]=Math.min(dp[j],dp[j-coins[i-1]]+1);
 * 
 */

// @lc code=start
class Solution {
    public int dfs(int amount, int[] coins, int[] memo) {
        // 先确定是自顶向下递归
        if (amount == 0)
            return 0;
        // if(amount<0)return -1;//应该在这里判断，而不是在循环内部判断
        if (memo[amount - 1] != 0)
            return memo[amount - 1];
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            // 当前节点可以从以下coins.length个子节点中的任意一个过来
            // 只要这个子节点是可达的
            if (amount - coins[i] >= 0) {
                int now = dfs(amount - coins[i], coins, memo);
                if (now != -1 && now < mi) {
                    mi = now + 1;
                }

            }

        }
        ;
        mi = (mi == Integer.MAX_VALUE) ? -1 : mi;// 这句话应该放在循环外面而不是里面
        memo[amount - 1] = mi;
        return memo[amount - 1];
    }

    public int coinChangeDfs(int[] coins, int amount) {
        // 自顶向下递归方法
        return dfs(amount, coins, new int[amount]);
    }

    public int coinChangeBottomToTop(int[] coins, int amount) {
        // 既然在自顶向下的时候记录的顺序其实还是自底向上的
        // 那不然直接从底部开始记录
        if (amount == 0)
            return 0;
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int mi = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != -1) {
                    mi = Math.min(mi, dp[i - coin] + 1);
                }

            }
            dp[i] = (mi == Integer.MAX_VALUE) ? -1 : mi;
        }
        return dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        // 完全背包的一维dp的版本
        if (amount <= 0)
            return 0;
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= coins.length; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {

                if (j - coins[i - 1] == 0) {
                    dp[j] = 1;
                } else if (dp[j] == 0 && dp[j - coins[i - 1]] != 0) {// 不能用的时候，只能选不能用了
                    dp[j] = dp[j - coins[i - 1]] + 1;
                } else if (dp[j - coins[i - 1]] != 0) {// 能用不能用都行的时候，选小的
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                } else {// 否则就是都保持不动
                }
            }
        }

        return (dp[amount] != 0) ? dp[amount] : -1;
    }

    public int coinChange1(int[] coins, int amount) {
        // 用完全背包的思想看
        if (amount <= 0)
            return 0;
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 1; i <= coins.length; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                // 这里可以不用从j=1开始，可以从coins[i-1]开始遍历
                // 就不用判断j-coins[i-1]<0，这些全都是0
                if (j - coins[i - 1] == 0) {
                    dp[i][j] = 1;
                } else if (dp[i - 1][j] == 0 && dp[i][j - coins[i - 1]] != 0) {// 不能用的时候，只能选不能用了
                    dp[i][j] = dp[i][j - coins[i - 1]] + 1;
                } else if (dp[i][j - coins[i - 1]] != 0) {// 能用不能用都行的时候，选小的
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                } else {// 否则就是都保持不动
                }
            }
        }

        return (dp[coins.length][amount] != 0) ? dp[coins.length][amount] : -1;
    }

}
// @lc code=end
