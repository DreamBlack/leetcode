/*
 * @lc app=leetcode id=55 lang=cpp
 *
 * [55] Jump Game
 *
 * https://leetcode.com/problems/jump-game/description/
 *
 * algorithms
 * Medium (32.54%)
 * Likes:    2375
 * Dislikes: 228
 * Total Accepted:    304.4K
 * Total Submissions: 934.5K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * Example 1:
 * 
 * 
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last
 * index.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its
 * maximum
 * jump length is 0, which makes it impossible to reach the last index.
 * 
 * 这一道非常好的让你了解动态规划的题，很有必要再看n遍
 * 解法1：
 * 对于每一个位置进行判断，内存超出
 * 解法2
 * 解法1中一些判断结果可以记录下来的，但是还是内存超出了
 * 解法3
 * 从自顶向下到自底向上的转换，以此来消除递归.
 * 由自顶向下改为自底向上可以减少递归，有一个当处理到i的时候
 * i+1及以后的都已经搞定的前提
 * 解法4
 * 进一步观察发现，只要保持右边第一个可以达到的位置p即可。因为只要
 * 从当前位置i到其可跳范围内有一个可达位置即可，即i<=p<=i+nums[i]
 * 
 */
class Solution {
public:
   bool canJump(vector<int>& nums) {
        int lastok=nums.size()-1;
        for(int i=nums.size()-2;i>=0;i--){
            if(lastok<=i+nums[i])
                lastok=i;
        }
        return ((lastok==0)?true:false);
    }
    bool canJump3(vector<int>& nums) {
        vector<int>memo(nums.size(),0);
        memo[nums.size()-1]=1;
        for(int i=nums.size()-2;i>=0;i--){
             int j=(nums.size()-1<i+nums[i])?(nums.size()-1):(i+nums[i]);
             int flag=false;
             for(;j>i;j--){
                 if(memo[j]==1){
                     memo[i]=1;
                     flag=true;
                     break;
                 }
             }
            memo[i]=(flag)?1:-1;
        }
        return ((memo[0]==1)?true:false);
    }
    bool help(int n,vector<int>nums,vector<int>&memo){
        if(n==nums.size()-1)
            return true;
        if(memo[n]==-1)
            return -1;
        if(memo[n]==1)
            return true;
        int i=(nums.size()-1<n+nums[n])?(nums.size()-1):(n+nums[n]);
        for(;i>n;i--){
            if(memo[i]==1){
                memo[n]=1;
                return true;
            }
                
        }
        memo[n]=-1;
        return false;
    }
     bool canJump2(vector<int>& nums) {
        vector<int>memo(nums.size(),0);
        
        
        return help2(0,nums,memo);
    }
    bool help2(int n,vector<int>nums,vector<int>&memo){
        if(n==nums.size()-1)
            return true;
        if(memo[n]==-1)
            return false;
        if(memo[n]==1)
            return true;
        int i=(nums.size()-1<n+nums[n])?(nums.size()-1):(n+nums[n]);
        for(;i>n;i--){
            if(help2(i,nums,memo)){
                memo[i]=1;
                return true;
            }
                
        }
        memo[n]=-1;
        return false;
    }
    bool canJump1(vector<int>& nums) {
        return help1(0,nums);
    }
    bool help1(int n,vector<int>nums){
     
        if(n==nums.size()-1)
            return true;
        int i=(nums.size()-1<n+nums[n])?(nums.size()-1):(n+nums[n]);
        for(;i>n;i--){
            if(help1(i,nums))
                return true;
        }
        return false;
    }
    
};

