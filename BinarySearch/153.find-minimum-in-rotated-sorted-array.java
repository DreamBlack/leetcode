/*
 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (43.55%)
 * Likes:    1276
 * Dislikes: 184
 * Total Accepted:    331.5K
 * Total Submissions: 758.3K
 * Testcase Example:  '[3,4,5,1,2]'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,4,5,1,2] 
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findMin(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<right){
            int mid=(left+right)>>>1;
            System.out.print("left="+left+",right="+right+",mid="+mid);
            //在看了第二题154的答案后发现居然这么简单
            if(nums[mid]>nums[right]){
               
                left=mid+1;
                System.out.println("进入分支：left=mid+1");
            }else{
                
                right=mid;
                System.out.println("进入分支：right=mid");
            }
            
        }
        return nums[left];
    }
    public int findMin2(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<right){
            int mid=(left+right)>>>1;
            System.out.print("left="+left+",right="+right+",mid="+mid);
            
            if(nums[mid]>=nums[left]&&nums[mid]>=nums[right]){
                /**
                 * 这里搞了好久
                 * 1、先是想nums[mid]和左右元素比发现不行
                 * 2、后来终于想到应该要跟两头的元素同时比
                 * 3、但是2的时候没有写上等于号，因为mid是有可能等于left和right的，所以要写上等于号
                 */
                left=mid+1;
                System.out.println("进入分支：left=mid+1");
            }else{
                
                right=mid;
                System.out.println("进入分支：right=mid");
            }
            
        }
        return nums[left];
    }
}
// @lc code=end

