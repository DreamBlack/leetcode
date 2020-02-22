/*
 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 *
 * https://leetcode.com/problems/move-zeroes/description/
 *
 * algorithms
 * Easy (55.10%)
 * Likes:    2925
 * Dislikes: 97
 * Total Accepted:    616.9K
 * Total Submissions: 1.1M
 * Testcase Example:  '[0,1,0,3,12]'
 *
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * 
 * Example:
 * 
 * 
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * 
 * Note:
 * 
 * 
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 * 
 */

// @lc code=start
class Solution {
    /**
     * 思考方向： 如果数组本身就没有0元素，用One/Two的方法还是要做交换 其实只有i!=k的时候才要交换
     * 
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != k) {

                    int tmp = nums[i];
                    nums[i] = nums[k];
                    nums[k] = tmp;
                }
                k++;

            }
        }
    }

    /**
     * 思考方向，第一种方法最后要把末尾的全部置为0， 能否不进行这种操作？ 答：可以，当nums[i]!=0的时候不是简单的置nums[k]=nums[i],
     * 而是swap(nums[k],nums[i]),
     * 
     * @param nums
     */
    public void moveZeroesTwo(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[k];
                nums[k++] = tmp;
            }
        }
    }

    /**
     * 第一种方法：[0,k]保存所有的非0元素， 初始k=0，,nums[i]！=0时放到第k个元素，同时k++， 最后将[k,n-1]置为0
     * 
     * @param nums
     */

    public void moveZeroesFirst(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
        while (k < nums.length) {
            nums[k++] = 0;
        }
    }
}
// @lc code=end
