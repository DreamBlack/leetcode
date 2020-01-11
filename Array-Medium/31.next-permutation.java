/*
 * @lc app=leetcode id=31 lang=java
 *
 * [31] Next Permutation
 *
 * https://leetcode.com/problems/next-permutation/description/
 *
 * algorithms
 * Medium (31.03%)
 * Likes:    2560
 * Dislikes: 863
 * Total Accepted:    305.2K
 * Total Submissions: 966.3K
 * Testcase Example:  '[1,2,3]'
 *
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place and use only constant extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 * 
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 * 解题思路：
 * 要求：原地操作，常数空间
 * 这题不会
 * 1、思路解释，向面试官求证
 * 从右向左，找到第一个非升序的，即nums[i]<nums[i+1];
 * 交换第i个和第i+1个肯定会比当前排列大，但是由于nums[i+1]可能比
 * nums[i]大很多，这样就不止大一个了。所以应该在i-last中选只比i大
 * 最少的那个和i个交换，同时将i+1到last reverse。
 * 2、复杂度分析
 * 时间O(N)，空间O(1)
 * 3、设置边界条件
 * nums为空或者nums.length=1直接返回
 * 4、编码
 * 
 * tips:
 * 注意算法写的简洁点
 */

// @lc code=start
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {// 注意这里不要忘记是等于号哦
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);

        }
        reverse(nums, i + 1);// 不论i>=0还是i<=都需要reverse，所以写成这样
    }

    public void nextPermutation2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {// 注意这里不要忘记是等于号哦
            i--;
        }
        if (i < 0) {
            reverse(nums, 0);
        } else {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
            reverse(nums, i + 1);
        }
    }

    void reverse(int[] nums, int i) {
        // 不要传入j，因为反正是从i到末尾都reverse
        if (i < 0 || i >= nums.length) {
            return;

        }
        int j = nums.length - 1;
        while (i < j) {
            // int tmp=nums[i];
            // nums[i++]=nums[j];
            // nums[j--]=tmp;
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    void swap(int[] nums, int i, int j) {
        if (nums == null || i < 0 || j >= nums.length || i > j) {
            return;
        }
        int tmp = nums[i];
        nums[i++] = nums[j];
        nums[j--] = tmp;
    }
}
// @lc code=end
