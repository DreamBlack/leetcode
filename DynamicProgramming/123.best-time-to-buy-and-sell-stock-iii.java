import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/*
 * @lc app=leetcode id=123 lang=java
 *
 * [123] Best Time to Buy and Sell Stock III
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 *
 * algorithms
 * Hard (34.72%)
 * Likes:    1483
 * Dislikes: 60
 * Total Accepted:    176.5K
 * Total Submissions: 498.5K
 * Testcase Example:  '[3,3,5,0,0,3,1,4]'
 *
 * Say you have an array for which the i^th element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most two
 * transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (i.e.,
 * you must sell the stock before you buy again).
 * 
 * Example 1:
 * 
 * 
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit
 * = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 =
 * 3.
 * 
 * Example 2:
 * 
 * 
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit
 * = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you
 * are
 * engaging multiple transactions at the same time. You must sell before buying
 * again.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * 
 * 题目大意：
 * 121是只能有一次买和卖操作；
 * 122是随便多少次买和卖操作；
 * 123是最多只能有两次买和卖操作
 * 714是每次交易要额外花费的操作；
 * 
 * 解题思路：
 * 1、从122的角度看，是不是就是在所有递增序列中选择跨度范围最大的那两个
 * 所以把122中所有递增序列的和记下来，之后再里面选最大的两个加起来
 * 错误
 * 
 * 对于例子[1,2,4,2,5,7,2,4,9,0]，可以看到明显不是这种解法
 * 
 * 2、重新思考
 * 两次操作，买和卖是连续的，两次操作不能间隔开，
 * 也就是说对于每一个元素，可以计算其左边一次操作可获得最大利益dpleft[i]；
 * 其右边一次操作可获得最大利益dpright[i]。该元素处两个dp值相加最大的就是
 * 答案。
 * 有几个特殊情况：
 * （1）从头到尾单调减，那么dp里都是0
 * （2）只需要一次操作，也可以用该思路解决，无需特殊处理
 * 出现的问题：
 * （1）注意这里是dpleft[i]+dpright[i+1]错开的
 *  因为dpleft[i]表示0-i区间的最大收益，
 *  dpright[i]表示i-n区间的最大收益,因为两次操作不能重合，所以必须错开
 * （2）把dpright[0]和dpleft[length-1]漏掉，解决是在return时做判断
 * （3）其实，dpright,left不应该保存的是0-i或者i-length的当前第i个元素
 * 处的值，而是应该保留两边的最大值，因为并不是说作为第一次操作立刻就要
 * 做第二次操作，中间可以隔几天，比如用例[3,2,6,5,0,3]
 * 
 * 
 * 3、答案O（N）空间是O（1）
 * 用四个变量记录
 * hold1,如果刚买了第一支股票手里的钱；
 * hold2，如果刚买了第二支股票手里的钱；
 * release1，如果刚卖了第一支股票手里的钱；
 * release2k,如果刚卖了第二支股票手里的钱。
 * 
 * tips:
 * ArrayList<Integer>要用Integer而不是int
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;
        int hold1=Integer.MIN_VALUE,hold2=Integer.MIN_VALUE;
        int release1=0,release2=0;
        for(int i=0;i<prices.length;i++){
            hold1=Math.max(hold1, -prices[i]);
            release1=Math.max(release1, hold1+prices[i]);
            hold2=Math.max(hold2, release1-prices[i]);
            release2=Math.max(release2, hold2+prices[i]);
        }
        return release2;
    }
    public int maxProfit1(int[] prices) {
        if (prices.length <= 1)
            return 0;
        int ret = 0;
        int[] dpright = new int[prices.length];
        int[] dpleft = new int[prices.length];
        int leftmin = prices[0];
        int maxright = 0;
        for (int i = 1; i < prices.length; i++) {
            dpleft[i] = (prices[i] - leftmin > 0) ? prices[i] - leftmin : 0;
            if (prices[i] < leftmin) {
                leftmin = prices[i];
            }
            if (dpleft[i] > maxright) {
                maxright = dpleft[i];
            } else {
                dpleft[i] = maxright;
            }
        }
        int rightmax = prices[prices.length - 1];
        maxright = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            dpright[i] = (rightmax - prices[i] > 0) ? rightmax - prices[i] : 0;
            if (prices[i] > rightmax) {
                rightmax = prices[i];
            }
            if (dpright[i] > maxright) {
                maxright = dpright[i];
            } else {
                dpright[i] = maxright;
            }
        }
        for (int i = 0; i < prices.length - 1; i++) {
            // 注意这里是dpleft[i]+dpright[i+1]错开的
            // 因为dpleft[i]表示0-i区间的最大收益，
            // dpright[i]表示i-n区间的最大收益,因为两次操作不能重合，所以必须错开

            if (dpleft[i] + dpright[i + 1] > ret) {
                // 如果只这么写会把dpright[0]和dpleft[length-1]漏掉，所以return的时候要注意
                ret = dpleft[i] + dpright[i + 1];
            }
        }

        // for(int i=0;i<rets.size();i++){
        // System.out.print(rets.get(i)+" ");
        // }

        return Math.max(ret, dpright[0]);// 这里还是要做一次判断的
    }
}
// @lc code=end
