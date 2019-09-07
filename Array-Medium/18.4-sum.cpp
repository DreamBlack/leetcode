/*
 * @lc app=leetcode id=18 lang=cpp
 *
 * [18] 4Sum
 *
 * https://leetcode.com/problems/4sum/description/
 *
 * algorithms
 * Medium (31.40%)
 * Likes:    1231
 * Dislikes: 247
 * Total Accepted:    259.3K
 * Total Submissions: 825.4K
 * Testcase Example:  '[1,0,-1,0,-2,2]\n0'
 *
 * Given an array nums of n integers and an integer target, are there elements
 * a, b, c, and d in nums such that a + b + c + d = target? Find all unique
 * quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * 
 * The solution set must not contain duplicate quadruplets.
 * 
 * Example:
 * 
 * 
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * 
 * A solution set is:
 * [
 * ⁠ [-1,  0, 0, 1],
 * ⁠ [-2, -1, 1, 2],
 * ⁠ [-2,  0, 0, 2]
 * ]
 * 
 * 解题思路：
 * 把4Sum分解成3Sum
 * 也有说把4Sum分解成两个2Sum的。
 * 先把两两之和用map记录下来，同时要记录两个加数；
 * 之后对于map遍历，用双指针找到另外两个数。但是要考虑重问题，太烦了
 * 
 */
class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        sort(nums.begin(),nums.end());
        int d=nums.size()-1;
        vector<vector<int>>ret;
        while(d>=3){
            int c=d-1;
            while(c>=2){
                int twosum=nums[d]+nums[c];
                int i=0,j=c-1;
                while(i<j){
                    if(nums[i]+nums[j]==target-twosum){
                        vector<int>tmp({nums[i],nums[j],nums[c],nums[d]});
                        ret.push_back(tmp);
                        //这里忘记--，++结果死循环了
                        j--;
                        while(j>i&&nums[j]==nums[j+1])j--;
                        i++;
                        while(i<j&&nums[i]==nums[i-1])i++;
                    }else if(nums[i]+nums[j]>target-twosum){
                        j--;
                        
                    }else{
                        i++;
                    }
                }
                c--;
                while(c>=2&&nums[c]==nums[c+1])c--;
            }
            d--;
            while(d>=3&&nums[d]==nums[d+1])d--;
        }
        return ret;
    }
};

