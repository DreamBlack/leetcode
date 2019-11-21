/*
 * @lc app=leetcode id=152 lang=java
 *
 * [152] Maximum Product Subarray
 *
 * https://leetcode.com/problems/maximum-product-subarray/description/
 *
 * algorithms
 * Medium (29.97%)
 * Likes:    2741
 * Dislikes: 120
 * Total Accepted:    259.8K
 * Total Submissions: 855.2K
 * Testcase Example:  '[2,3,-2,4]'
 *
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 * 
 * Example 1:
 * 
 * 
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * 
 * 解题思路：
 * 本题的拦路虎在于连续，思考的时候将很多时间放在了如何处理连续的问题上
 * 其实换个角度看对于每个数组无非是二者选择，1是继续累积，2重头开始
 * 
 * 1、本来打算从dfs的角度考虑，后来发现每个分支都要计算多次，遂放弃
 * 
 * 2、dp
 * 后来从最长公共子串得到启发，在已知前i-1个数包含第i-1个数的max product
 * 已知的情况下求第i个的max product，
 * 考虑到前i-1个数的积为正，而第i个数为负的时候，以第i个数为结尾的子数组可能
 * 会超过i-1个数为积的子数组，或者个数小于i-1为积的子数组。
 * 所以将数组reverse了一遍，取两者结果的最大值。
 * 居然通过了，而且速度还挺快
 * 
 * 3、标准解法
 * 
 * tips:
 * java对于int[]没有reverse函数，但是对于arraylist有reverse函数。
 */

// @lc code=start

class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0)
            return 0;
        int ret = nums[0];
        // minproduct和maxproduct分别保存到第i个元素时，构成的子串
        // 最小积和最大积
        int minproduct = ret, maxproduct = ret;
        for (int i = 1; i < nums.length; i++) {
            minproduct *= nums[i];
            maxproduct *= nums[i];
            if (nums[i] < 0) {
                int tmp = minproduct;
                minproduct = maxproduct;
                maxproduct = tmp;
            }
            // 对于当前元素nums[i]，可以选择继续累积，也可以选择重头开始
            if (maxproduct < nums[i]) {
                maxproduct = nums[i];
            }
            if (minproduct > nums[i]) {
                minproduct = nums[i];
            }
            ret = Math.max(ret, maxproduct);
        }
        return ret;
    }

    public int maxProduct2(int[] nums) {
        if (nums.length == 0)
            return 0;

        return Math.max(help(nums), help(reverseInt(nums)));
    }

    public int[] reverseInt(int[] nums) {
        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ret[i] = nums[nums.length - 1 - i];
        }
        return ret;
    }

    public int help(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] dp1 = new int[nums.length];
        dp1[0] = nums[0];
        int[] dp2 = new int[nums.length];
        dp2[0] = nums[0];
        int ret = dp1[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                dp1[i] = Math.max(nums[i], nums[i] * dp1[i - 1]);
            } else {
                dp1[i] = Math.max(Math.max(nums[i] * dp2[i - 1], nums[i] * dp1[i - 1]), nums[i] * nums[i - 1]);
            }
            if (nums[i] == 0) {
                dp2[i] = 0;
            } else {
                dp2[i] = (dp2[i - 1] == 0) ? nums[i] : nums[i] * dp2[i - 1];
            }

            if (dp1[i] > ret) {
                ret = dp1[i];
            }
        }
        return ret;
    }
}
// @lc code=end
