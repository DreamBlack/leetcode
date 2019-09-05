/*
 * @lc app=leetcode id=16 lang=cpp
 *
 * [16] 3Sum Closest
 *
 * https://leetcode.com/problems/3sum-closest/description/
 *
 * algorithms
 * Medium (45.76%)
 * Likes:    1297
 * Dislikes: 92
 * Total Accepted:    376.6K
 * Total Submissions: 823K
 * Testcase Example:  '[-1,2,1,-4]\n1'
 *
 * Given an array nums of n integers and an integer target, find three integers
 * in nums such that the sum is closest to target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 * 
 * Example:
 * 
 * 
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * 
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * 
 */
class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        //忘了先排序搞得我找了好长时间的错
        sort(nums.begin(),nums.end());
        int c=nums.size()-1;
        int ret=INT_MAX,retv=0;;
        while(c>=0){
            
            int i=0,j=c-1;
            while(i<j){
                if(abs(nums[i]+nums[j]+nums[c]-target)<ret){
                    ret=abs(nums[i]+nums[j]+nums[c]-target);
                    retv=nums[i]+nums[j]+nums[c];
                }
                if(nums[i]+nums[j]+nums[c]>target){
                    j--;
                    while(j>i&&nums[j]==nums[j+1])j--;
                }else if(nums[i]+nums[j]+nums[c]<target){
                     i++;
                    while(i<j&&nums[i]==nums[i-1])i++;
                }else{
                    retv=target;
                    return retv;
                }
            }
           c--;
            while(c>=0&&nums[c]==nums[c+1])c--;
             
        }
        return retv;
    }
};

