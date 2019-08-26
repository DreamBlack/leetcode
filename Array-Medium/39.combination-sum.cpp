/*
 * @lc app=leetcode id=39 lang=cpp
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
 * 深度优先遍历所有可能的解，并且记录符合要求的解
 * 写深度优先要注意的点：
 * 1、跳出条件
 * 2、到达某个节点之后只有两种情况A.选择B.不选择
 * 3、带环的深度优先遍历和普通的遍历其实没什么不一样。写多了
 * 反而会出现一些重复的数据
 * 4、数据的修改要放在dfs最进行的地方。target=target-candidates[index];
 * 这句必须放在dfs的邻边
 */
class Solution {
public:
    vector<vector<int>> result;
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
    //一定得先排序
       sort(candidates.begin(),candidates.end());
       vector<int>path;
       dfs(target,0,path,candidates);
        return result;
    }
    void dfs(int target,int index,vector<int>path,vector<int>candidates){
        if(index>=candidates.size())
            return;

        if(target==0)
        {
            result.push_back(path);
            return;
        }
        

        if(target<candidates[index])
            return;
        else{
            //这里有两种方案，一是放弃当前节点，直接到下一节点
            //二是，再走一次当前节点
            //直接下一步
            dfs(target,index+1,path,candidates);
            
           
            //这句要放在else里面，否则target每次都会--，导致
            //一些树枝就不能访问到
            target=target-candidates[index];
             path.push_back(candidates[index]);
            //记录当前数据，再循环
            dfs(target,index,path,candidates);
            //记录当前数据，不循环。这里和上面那个可能会导致重复
            //dfs(target,index+1,path,candidates);
        }
    }
};

