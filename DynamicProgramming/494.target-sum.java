/*
 * @lc app=leetcode id=494 lang=java
 *
 * [494] Target Sum
 *
 * https://leetcode.com/problems/target-sum/description/
 *
 * algorithms
 * Medium (45.75%)
 * Likes:    1703
 * Dislikes: 81
 * Total Accepted:    117.8K
 * Total Submissions: 256.6K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * 
 * You are given a list of non-negative integers, a1, a2, ..., an, and a
 * target, S. Now you have 2 symbols + and -. For each integer, you should
 * choose one from + and - as its new symbol.
 * ⁠
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to
 * target S.  
 * 
 * 
 * Example 1:
 * 
 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * Output: 5
 * Explanation: 
 * 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * 
 * 
 * 
 * Note:
 * 
 * The length of the given array is positive and will not exceed 20. 
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 * 
 * 解题思路1：
 * 1、有个目标target：背包总质量
 * 2、每个有两种选择：0-1背包
 * 使得背包总weight=s，weight=value
 * 写的时候想到，S有可能为负数呀，那就不能用数组了
 * 
 * 解题思路2：
 * 看了答案
 * 如果一部分全取正号，这部分子集记为P；一部分全取负号，这部分子集记为N；
 * 则有Sum(P)-Sum(N)=target;又要考虑N,又要考虑P不好，所以两边同时加上Sum(N)+Sum(P)
 * 得到Sum(P)-Sum(N)+Sum(N)+Sum(P)=target+Sum(N)+Sum(P),即2*Sum(p)=target+Sum(P+N)
 * Sum(P+N)已知，这就变成了在商品中找一部分使得其和为(target+S)/2的背包问题
 */

// @lc code=start
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum=0;
        for(int num:nums){
            sum+=num;
        }
        if(sum<S||(S+sum)%2!=0)return 0;

        int[]dp=new int[(S+sum)/2+1];
        //dp不能用来表示容量了，因为要求的是能放下的个数
        //会受容量限制
        dp[0]=1;//表示有一种放的方法
        for(int num:nums){
            for(int j=(S+sum)/2;j>=num;j--){
                
                dp[j]=dp[j]+dp[j-num];
                
            }
        }
       return dp[(S+sum)/2];
    }
}
// @lc code=end

