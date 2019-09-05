/*
 * @lc app=leetcode id=15 lang=cpp
 *
 * [15] 3Sum
 *
 * https://leetcode.com/problems/3sum/description/
 *
 * algorithms
 * Medium (24.68%)
 * Likes:    4415
 * Dislikes: 502
 * Total Accepted:    631.5K
 * Total Submissions: 2.6M
 * Testcase Example:  '[-1,0,1,2,-1,-4]'
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such
 * that a + b + c = 0? Find all unique triplets in the array which gives the
 * sum of zero.
 * 
 * Note:
 * 
 * The solution set must not contain duplicate triplets.
 * 
 * Example:
 * 
 * 
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is:
 * [
 * ⁠ [-1, 0, 1],
 * ⁠ [-1, -1, 2]
 * ]
 * 
 * 解题思路
 * 先考虑不排序能不能做，答不能。
 * 所以先排序.
 * 如果十分钟之内看不出O(N)的解法，就先从最简单的开始想
 * 
 * 1、然后是最简单的暴力
 * 时间O(N^3)
 * 2、之后考虑怎么减少时间
 * hash:有重复数，且有负数，pass
 * 二分查找：对于三个元素都不确定的情况下二分查找很难办的。
 * 所以最好先固定a,b,c中的一个。那么问题就成了a+b=-c。把这里的
 * c(固定的)看成2Sum里的target。那么接下来的问题就可以用双指针
 * 解决了。时间O(NlogN+N*N)
 * 
 * 注意点：
 * 重复元素的处理
 */
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>>ret;
        sort(nums.begin(),nums.end());
        int c=nums.size()-1;
        while(c>=0){
            int target=-nums[c];
            //2Sum
            int i=0,j=c-1;
            while(i<j){
                if(nums[i]+nums[j]==target){
                    vector<int>tmp({nums[i],nums[j],-target});
                    ret.push_back(tmp);
                    i++;
                    j--;
                    //跳过重复元素，但是要在i++,j--之后，否则
                    //会死循环啊
                    while(i<j&&nums[i]==nums[i-1])i++;
                    while(j>i&&nums[j]==nums[j+1])j--;
                }else if(nums[i]+nums[j]<target){
                    i++;
                }else{
                    j--;
                }
            }
            c--;
            //这里忘记跳过重复元素了
             while(c>=0&&nums[c]==nums[c+1])c--;
        }
        return ret;
    }
};

