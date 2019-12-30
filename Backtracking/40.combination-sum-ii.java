import java.util.HashSet;
import java.util.List;

/*
 * @lc app=leetcode id=40 lang=java
 *
 * [40] Combination Sum II
 *
 * https://leetcode.com/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (43.35%)
 * Likes:    1050
 * Dislikes: 50
 * Total Accepted:    247.9K
 * Total Submissions: 571.8K
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * Given a collection of candidate numbers (candidates) and a target number
 * (target), find all unique combinations in candidates where the candidate
 * numbers sums to target.
 * 
 * Each number in candidates may only be used once in the combination.
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
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * ⁠ [1, 7],
 * ⁠ [1, 2, 5],
 * ⁠ [2, 6],
 * ⁠ [1, 1, 6]
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 * [1,2,2],
 * [5]
 * ]
 * 题目大意：
 * 求组合数。数组中有重复元素，每个元素只能用一次，输出结果中不可以有重复
 * 
 * 解题思路
 * 1、回溯
 * 花了很久
 * 主要有三个问题：1是编码导致下标超出范围，2是编码导致的漏掉了一些结果
 * 3是去重问题考虑了很久。
 * 
 * 2、动态规划
 * 从某种角度看，这就是个普通的背包问题。
 * 但是此题的关键点在于如何保证结果中没有重复元素,这里好像只能用set
 * 
 * tips:
 * 1、遇到这种题目一定要把数画出来，自己走一遍分析一遍
 * 2、判断逻辑写的简单一点，清晰一定，这样才不容易漏掉
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // dp法
        // 关键问题在于，如何去重,使用set
        List<List<Integer>>[] dp = new List[target + 1];

        Arrays.sort(candidates);

        for (int i = 1; i <= candidates.length; i++) {
            dp[0] = new ArrayList<List<Integer>>();
            dp[0].add(new ArrayList<Integer>());
            for (int j = target; j >= 1; j--) {
                if (j - candidates[i - 1] >= 0 && dp[j - candidates[i - 1]] != null) {

                    if (dp[j] == null) {
                        dp[j] = new ArrayList<List<Integer>>();
                    }
                    int index = dp[j].size();
                    for (List<Integer> ls : dp[j - candidates[i - 1]]) {
                        dp[j].add(new ArrayList<>(ls));
                        dp[j].get(index++).add(candidates[i - 1]);
                    }
                }
            }
        }
        if (dp[target] == null) {
            return new ArrayList<List<Integer>>();
        }
        HashSet<List<Integer>> set = new HashSet<>(dp[target]);

        return new ArrayList<List<Integer>>(set);
    }

    public List<List<Integer>> combinationSum21(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backTracking2(candidates, res, path, target, 0, 0);
        return res;
    }

    void backTracking(int[] candidates, List<List<Integer>> res, List<Integer> path, int target, int index, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            // System.out.println("add new path "+path);
        } else if (index < candidates.length && candidates[index] + sum <= target) {
            // 问题1，下标超出范围
            // 问题3：candidates[index]+sum<=target，没有加等于，只是小于，错过了一些答案
            for (int i = index; i < candidates.length; i++) {

                path.add(candidates[i]);
                // System.out.println("index="+index+",i="+i+",add "+candidates[i]);
                backTracking(candidates, res, path, target, i + 1, sum + candidates[i]);

                path.remove(path.size() - 1);
                int tmp = i;
                while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                    i++;
                }
                if (i != tmp) {
                    // System.out.println("found same num ,skip from "+tmp+" to "+i);
                }
            }
        }
    }

    void backTracking2(int[] candidates, List<List<Integer>> res, List<Integer> path, int target, int index, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
        } else if (sum > target) {// 剪枝
            return;
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (i > index && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                path.add(candidates[i]);
                backTracking2(candidates, res, path, target, i + 1, sum + candidates[i]);
                path.remove(path.size() - 1);

            }
        }
    }
}
// @lc code=end
