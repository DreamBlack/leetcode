/*
 * @lc app=leetcode id=40 lang=cpp
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
 * 
 * 解题思路
 * 1、代码的顺序问题一定要考虑到，不然很可能输出和你想象的不一样
 * 2、第二个问题是怎么去重复
 * 想的方法比较烦。其实可以不用map记录个数的，只要两个相等的时候不处理
 * 就行了。原来没这么做是怕失去[1,1,6]这样的结果，但其实并不会，
 * 因为在第一个1的时候会得到这个结果的，跳过第二个1的时候这个结果
 * 已经得到了
 * 
 * 
 * 
 * ！！！和39的比较，其实只有两个地方需要改动
 */
class Solution
{
public:
    vector<vector<int>> ret;
    vector<vector<int>> combinationSum2(vector<int> &candidates, int target)
    {

        sort(candidates.begin(), candidates.end());
        vector<int> tmp;
        dfs(tmp, target, 0, candidates);
        return ret;
    }
    void dfs(vector<int> tmp, int target, int index, vector<int> candidates)
    {
        if (target == 0)
        {
            ret.push_back(tmp);
            return;
        }
        if (index == candidates.size())
        {
            return;
        }

        //!!!!这句一定要放在后面，否则会没有输出的
        if (candidates[index] > target)
        {
            return;
        }

        //作为根结点进行遍历
        for (int i = index; i < candidates.size(); i++)
        {
            //这里是不等于Index而不是0
            //注意这里下面用的下标都是i而不是index了
            //如果当前根结点下有和根相同的就跳过，因为是有序的，重复点肯定是和根结点相邻的，
            //如果使用i!=0的话,当index=1遍历到这里的时候就会漏掉[1,1,6]这种情况了
            if (i !=index && candidates[i] == candidates[i - 1])//不要这句了，因为39里面的元素都是唯一的
                continue;

            tmp.push_back(candidates[i]);
            dfs(tmp, target - candidates[i], i + 1, candidates);//i+1边i就有自环的可能了
            tmp.pop_back();
        }
    }
  
};
