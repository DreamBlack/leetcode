/*
 * @lc app=leetcode id=198 lang=java
 *
 * [198] House Robber
 *
 * https://leetcode.com/problems/house-robber/description/
 *
 * algorithms
 * Easy (41.29%)
 * Likes:    3158
 * Dislikes: 103
 * Total Accepted:    386.1K
 * Total Submissions: 932.4K
 * Testcase Example:  '[1,2,3,1]'
 *
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping
 * you from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent
 * houses were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight
 * without alerting the police.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money =
 * 3).
 * Total amount you can rob = 1 + 3 = 4.
 * 
 * Example 2:
 * 
 * 
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house
 * 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * 
 * 题目大意：一个晚上可以偷到的最多多少钱
 * 一个非负数组中非邻接子序列的和的最大值
 * 解题思路：
 * 1、本来打算只用一个dp，后来发现不行，尤其是对于[2,1,1,1,2]这种情况
 * 因此想到，对于第i个数，可以选择偷和不偷两种情况，
 * 如果偷，dp1[i]=dp2[i-1]+nums[i](因为如果偷i的，第i-1个就不能偷)；
 * 如果不偷，dp2[i]=max(dp1[i-1],dp2[i-1])
 * 最返回dp1[l-1]和dp2[l-1]中较大的即可
 * 
 * 2、看了答案之后发现，其实法1中的dp2就是在i-1处的解
 * 所以可以改成只使用一个dp，但是dp长度为dp[l+1],
 * dp[0]=0,dp[1]=nums[0],那么对于数组中第1个数来说，
 * dp[2]=max(dp[0]+nums[1],dp[1])//要么从previous的previous过来
 * 就可以偷当前这个房子，要么从previous过来，就不能偷当前的房子。
 * 
 * ★★★★★★★有的时候动态规划的数组长度可以比原数组长度大一★★★
 * ☆×★☆×★尤其是这种当前状态与之前两个状态有关的时候★★★★
 */

// @lc code=start
class Solution {
    public int rob(int[] nums) {
        //不用数组，只用两个变量的方法
        int l=nums.length;//java获取数组长度不需要判断数组是否为空
        if(l==0)return 0;
        int pp=0,p=nums[0];
        for(int i=2;i<l+1;i++){
            int tmp=p;
            p=Math.max(pp+nums[i-1], p);//这里是Nums[i-1]小心越界
            pp=tmp;
        }
        return p;
    }
    public int rob2(int[] nums) {
        int l=nums.length;//java获取数组长度不需要判断数组是否为空
        if(l==0)return 0;
        int[]dp=new int[l+1];//数组长度比原数组长度+1
        dp[0]=0;
        dp[1]=nums[0];
        for(int i=2;i<l+1;i++){
            dp[i]=Math.max(dp[i-2]+nums[i-1], dp[i-1]);//这里是Nums[i-1]小心越界
        }
        return dp[l];
    }
    public int rob1(int[] nums) {
        
        
        int l=nums.length;//java获取数组长度不需要判断数组是否为空
        if(l==0)return 0;

        int[]dp1=new int[l];
        int[]dp2=new int[l];
        dp1[0]=nums[0];

        for(int i=1;i<l;i++){
            dp1[i]=dp2[i-1]+nums[i];
            dp2[i]=Math.max(dp1[i-1],dp2[i-1]);
        }
        return Math.max(dp1[l-1],dp2[l-1]);//注意这里要返回大的那个
    }
}
// @lc code=end

