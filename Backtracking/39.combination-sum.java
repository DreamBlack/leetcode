import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=39 lang=java
 *
 * [39] Combination Sum
 *
 * https://leetcode.com/problems/combination-sum/description/
 *
 * algorithms
 * Medium (48.52%)
 * Likes:    2310
 * Dislikes: 71
 * Total Accepted:    382.7K
 * Total Submissions: 761.3K
 * Testcase Example:  '[2,3,6,7]\n7'
 *
 * Given a set of candidate numbers (candidates) (without duplicates) and a
 * target number (target), find all unique combinations in candidates where the
 * candidate numbers sums to target.
 * 
 * The same repeated number may be chosen from candidates unlimited number of
 * times.
 * 
 * Note:
 * 
 * 
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * ⁠ [7],
 * ⁠ [2,2,3]
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * 
 * 解题思路：
 * 注意点
 * 元素可以重复，所以这里递归下一层的时候仍然从i开始
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || target <= 0) {
            return res;
        }
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backTracking(0, 0, target, candidates, res, path);
        return res;

    }

    void backTracking(int index, int sum, int target, int[] candidates, List<List<Integer>> res, List<Integer> path) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
        } else if (sum + candidates[index] <= target) {
            for (int i = index; i < candidates.length; i++) {
                path.add(candidates[i]);
                backTracking(i, sum + candidates[i], target, candidates, res, path);// 元素可以重复，所以这里递归下一层的时候仍然从i开始
                path.remove(path.size() - 1);
            }
        }
    }
}
// @lc code=end
