/*
 * @lc app=leetcode id=154 lang=java
 *
 * [154] Find Minimum in Rotated Sorted Array II
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
 *
 * algorithms
 * Hard (39.76%)
 * Likes:    568
 * Dislikes: 158
 * Total Accepted:    146.5K
 * Total Submissions: 367.4K
 * Testcase Example:  '[1,3,5]'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,5]
 * Output: 1
 * 
 * Example 2:
 * 
 * 
 * Input: [2,2,2,0,1]
 * Output: 0
 * 
 * Note:
 * 
 * 
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 * 
 * 解题思路：
 * 这题不会看的答案。
 * 对于nums[mid],nums[left],nums[right],
 * 由于数组本身要么单调增，要么分成了两段单调增，因此
 * 如果nums[mid]>nums[right]，肯定是增(大数据mid部分)/增(小数据部分)，肯定在mid右边
 * 如果nums[mid]<nums[left]，那么肯定是增(大数据部分)/增(小数mid据部分)，肯定在mid左边
 * 否则nums[left]<=nums[mid]<=nums[right]，另right--就可以了，因为肯定不在right上面
 * 而且可能是顺序递增的数组
 */

// @lc code=start
class Solution {
    public int findMin(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<right){
            int mid=(left+right)>>>1;
            System.out.print("left="+left+",right="+right);
            
            System.out.print(",mid="+mid);
            if(nums[mid]>nums[right]){
                
                left=mid+1;
                System.out.println("进入分支：left=mid+1");
            }else if(nums[mid]<nums[left]){
                
                right=mid;
                left++;//这句话可有可无
                System.out.println("进入分支：right=mid");
            }else{
                right--;
            }
            
        }
        
        
            return nums[left];
    }
}
// @lc code=end

