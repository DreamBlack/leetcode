/*
 * @lc app=leetcode id=75 lang=cpp
 *
 * [75] Sort Colors
 *
 * https://leetcode.com/problems/sort-colors/description/
 *
 * algorithms
 * Medium (43.18%)
 * Likes:    1989
 * Dislikes: 172
 * Total Accepted:    358.6K
 * Total Submissions: 828.2K
 * Testcase Example:  '[2,0,2,1,1,0]'
 *
 * Given an array with n objects colored red, white or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order
 * red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red,
 * white, and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this
 * problem.
 * 
 * Example:
 * 
 * 
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * 
 * Follow up:
 * 
 * 
 * A rather straight forward solution is a two-pass algorithm using counting
 * sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then
 * overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 * 
 * 题目大意：
 * 只有0,1,2的数组进行原地排序
 * 解题思路：
 * 双指针，既然只有三种，那么记录1和2的个数，一遍pass，把2移到后面
 * 1移到前面就可以了
 * 
 * tips：扫描的范围不再是0-n，而是[0,2所在的位置]
 */
class Solution {
public:
    void sortColors(vector<int>& nums) {
        int count1=0,count2=nums.size()-1;
        for(int i=0;i<=count2;i++){
            //i的范围要注意，如果用i<nums.size()会把后面已经排好序的2们
            //又一道前面
            if(nums[i]==2){
                swap(nums[i],nums[count2]);
                count2--;
                i--;
            }else if(nums[i]==0){
                swap(nums[i],nums[count1]);
                count1++;
            }
        }
        
    }
};

