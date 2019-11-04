/*
 * @lc app=leetcode id=416 lang=java
 *
 * [416] Partition Equal Subset Sum
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (41.55%)
 * Likes:    1612
 * Dislikes: 50
 * Total Accepted:    117.2K
 * Total Submissions: 280.9K
 * Testcase Example:  '[1,5,11,5]'
 *
 * Given a non-empty array containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both
 * subsets is equal.
 * 
 * Note:
 * 
 * 
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [1, 5, 11, 5]
 * 
 * Output: true
 * 
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1, 2, 3, 5]
 * 
 * Output: false
 * 
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 * 
 * 
 * 解题思路：
 * 1、在已知为0-1背包问题的条件下来看
 * 0-1：子集，所以不能有重复，所以是0-1，key:子集
 * 背包：是将原数组中元素划分，所以是背包，key:划分
 * 想半天没搞错来跟背包有啥关系，其实要分成和相同的一半，就是说有没有办法
 * 从里面拿出i个使得其装满体积为sum/2的背包,这里Nums[i]即表示价值，也表示体积
 */

// @lc code=start
class Solution {
    public boolean canPartition1(int[] nums) {
        int sum=0;
        
        for(int num:nums){
            sum+=num;
        }
        if(sum%2!=0)//能分两半，肯定和能被2整除
            return false;
        int[]dp=new int[sum/2+1];
        boolean ret=false;
        for(int i=1;i<nums.length+1;i++){//每个物品
            for(int j=sum/2;j>=1;j--){
                if(j>=nums[i-1]){
                    dp[j]=Math.max(dp[j], dp[j-nums[i-1]]+nums[i-1]);
                }
                if(dp[j]==sum/2){
                    ret=true;
                    return ret;
                }
            }
        }
        return ret;
    }
    public boolean canPartition(int[] nums) {
        int sum=0;
        
        for(int num:nums){
            sum+=num;
        }
        if(sum%2!=0)//能分两半，肯定和能被2整除
            return false;
        boolean[]dp=new boolean[sum/2+1];
        dp[0]=true;
        for(int i=1;i<nums.length+1;i++){//每个物品
            for(int j=sum/2;j>=nums[i-1];j--){
                dp[j]=dp[j]||dp[j-nums[i-1]];
            }
        }
        return dp[sum/2];
    }
}
// @lc code=end

