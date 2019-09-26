/*
 * @lc app=leetcode id=78 lang=cpp
 *
 * [78] Subsets
 *
 * https://leetcode.com/problems/subsets/description/
 *
 * algorithms
 * Medium (52.83%)
 * Likes:    1948
 * Dislikes: 50
 * Total Accepted:    371.4K
 * Total Submissions: 699.5K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output:
 * [
 * ⁠ [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * 
 */
class Solution
{
public:
    vector<vector<int>> subsets(vector<int> &nums)
    {
        sort(nums.begin(), nums.end());
        vector<vector<int>> ret = {{}};
        
        for (int i = 0; i < nums.size(); i++)
        {
            int cnt=ret.size();
            for (int j = 0; j < cnt; j++)
            {//这里j的范围要注意，因为ret大小时刻在变换所以要先保存一下
                vector<int> tmp=ret[j];
                tmp.push_back(nums[i]);
                
                ret.push_back(tmp);
               
            }
        }
        return ret;
    }

    vector<vector<int>> subsets2(vector<int> &nums)
    {

        vector<vector<int>> ret;
        vector<int> tmp;

        if (nums.empty())
        {
            ret.push_back(tmp);
            return ret;
        }

        sort(nums.begin(), nums.end());
        dfs(tmp, ret, nums, 0);

        return ret;
    }
    void dfs(vector<int> tmp, vector<vector<int>> &ret, vector<int> nums, int index)
    {
        if (index == nums.size())
        {
            ret.push_back(tmp);
            return;
        }

        dfs(tmp, ret, nums, index + 1);
        tmp.push_back(nums[index]);
        dfs(tmp, ret, nums, index + 1);
    }
};
