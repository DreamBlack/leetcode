import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=46 lang=java
 *
 * [46] Permutations
 *
 * https://leetcode.com/problems/permutations/description/
 *
 * algorithms
 * Medium (57.27%)
 * Likes:    2726
 * Dislikes: 88
 * Total Accepted:    473.8K
 * Total Submissions: 805.6K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a collection of distinct integers, return all possible permutations.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,3]
 * Output:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 * 题目大意：
 * 求数组的全排列
 * 
 * 解题思路：
 * 分析：
 * 1、是否要回溯？
 * 答：要，因为要遍历所有的解
 * 2、每个子节点是什么？
 * 答：所有没有被访问过的点
 * 3、终结点是什么？
 * 答：path中有了n个数
 * 
 * 注意点：
 * 需要记下标记和取消标记；
 * void backTracking(boolean[]visited,int[]nums,int pos,List<List<Integer>>res,List<Integer>path)
 * path是单条路径上的结果；
 * res是整个题目的结果。
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> res;
    boolean[] visited;

    public List<List<Integer>> permute1(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        visited = new boolean[nums.length];
        res = new ArrayList<List<Integer>>();
        helper(nums, -1, new ArrayList<Integer>());
        return res;
    }

    void helper(int[] nums, int index, List<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != index && !visited[i]) {
                path.add(nums[i]);
                visited[i] = true;
                helper(nums, i, path);
                path.remove(path.size() - 1);
                visited[i] = false;
            }

        }
    }

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums.length == 0) {
            return res;
        }
        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        backTracking(visited, nums, 0, res, path);
        return res;
    }

    void backTracking(boolean[] visited, int[] nums, int pos, List<List<Integer>> res, List<Integer> path) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                path.add(nums[i]);
                backTracking(visited, nums, pos + 1, res, path);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }

    }
}
// @lc code=end
