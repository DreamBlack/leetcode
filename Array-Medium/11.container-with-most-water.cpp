/*
 * @lc app=leetcode id=11 lang=cpp
 *
 * [11] Container With Most Water
 *
 * https://leetcode.com/problems/container-with-most-water/description/
 *
 * algorithms
 * Medium (46.41%)
 * Likes:    3862
 * Dislikes: 467
 * Total Accepted:    438.1K
 * Total Submissions: 942.6K
 * Testcase Example:  '[1,8,6,2,5,4,8,3,7]'
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a
 * point at coordinate (i, ai). n vertical lines are drawn such that the two
 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most
 * water.
 * 
 * Note: You may not slant the container and n is at least 2.
 * 
 * 
 * 
 * 
 * 
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In
 * this case, the max area of water (blue section) the container can contain is
 * 49. 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * 
 * 
 * 解题思路（没想出来）
 * 感觉因为不能改变数组的值和index的关系导致无序就不能用双指针了
 * 谁想到还是可以用双指针的。
 * 而且这个东西还要证明，因为一眼真心看不出来
 * 我觉得是个难题
 * 因为木桶原理，容积取决于行长度和最短高度的积，所以，两个端点
 * 高度较低的需要移动，因为高度较高的移动不可能大于原来的两端点积。
 * 这样，每次都是高度低的移动，直到两指针相邻。
 * 
 * 可以这么理解，这个数组构成的可能的区域就那么多，想办法
 */
class Solution {
public:
    int maxArea(vector<int>& height) {
        int i=0,j=height.size()-1;
        int sum=0;
        while(i<j){
            int now=(j-i)*min(height[i],height[j]);
            if(now>sum){
                sum=now;
            }
            if(height[i]<height[j]){

                i++;
            }else{
                j--;
            }
        }
        return sum;
    }
    
};

