import java.util.List;

/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 *
 * https://leetcode.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (43.61%)
 * Likes:    1077
 * Dislikes: 53
 * Total Accepted:    224.9K
 * Total Submissions: 513.6K
 * Testcase Example:  '[1,2,2]'
 *
 * Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,2]
 * Output:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 
 * 题目大意：
 * 求一个集合的所有子集
 * 这个集合中有重复元素、子集集合中不能有重复元素
 * 类似于40 combination sum2
 * 
 * 注意事项：
 * 1、i+1又写成了index
 * 2、忘了排序
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);// 这里忘了排序
        backTracking(nums, 0, res, new ArrayList<Integer>());
        return res;
    }

    void backTracking(int[] nums, int index, List<List<Integer>> res, List<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i - 1] == nums[i]) {
                continue;
            } else {
                path.add(nums[i]);
                backTracking(nums, i + 1, res, path);// 这里忘了写成了index而不是i+1
                path.remove(path.size() - 1);
            }
        }
    }
}
// @lc code=end
