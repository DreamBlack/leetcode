/*
 * @lc app=leetcode id=45 lang=cpp
 *
 * [45] Jump Game II
 *
 * https://leetcode.com/problems/jump-game-ii/description/
 *
 * algorithms
 * Hard (28.74%)
 * Likes:    1481
 * Dislikes: 86
 * Total Accepted:    194.5K
 * Total Submissions: 673.7K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * Example:
 * 
 * 
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * ⁠   Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Note:
 * 
 * You can assume that you can always reach the last index.
 * 
 * 解题思路1：
 * dp[i]记录从i到最后一个元素的最小步数。
 * 自底向上，如果一步能到，dp[i]=1;
 * 如果一步不能到，则必须在i的可达范围内[i,i+nums[i]]找到最小的
 * 那个k，令dp[i]=dp[k]+1。最后返回dp[0].
 * O(N^2),超时。
 * 
 * 解题思路2：
 * 贪心。
 * 从0到n-1的所有step中，并不是在所有的位置都需要进行jump操作的，
 * 只有当i不在上一次jump可达的范围[0,last]中，即i==last的时候，
 * 才必须要跳，否则进行不下去了。同时，中间那些不需要跳的地方也
 * 不能不管，因为它们关系着下次跳的时候能跳的最远距离，需要用maxlast
 * 记录下来，再下次跳的时候更新最远距离last
 * 这里的贪心就是跳最小的步数，能不跳则不跳。
 */
class Solution
{
public:
    int jump(vector<int> &nums)
    {
        int last = 0, jump = 0, maxlast = 0;
        for (int i = 0; i < nums.size() - 1; i++)
        { //这里i范围要注意，n-1的时候不用处理
            if (i == last)
            {
                jump++;
                last = max(maxlast, nums[i] + i);
            }
            maxlast = max(maxlast, nums[i] + i);
        }
        return jump;
    }
    int jump1(vector<int> &nums)
    {
        vector<int> dp(nums.size(), 0);
        //如果只有一个元素，肯定可达，返回0;或者元素为空
        if (nums.empty() || nums.size() == 1)
            return 0;
        for (int i = nums.size() - 2; i >= 0; i--)
        {
            if (nums[i] >= nums.size() - i - 1)
            {
                dp[i] = 1;
            }
            else
            {
                long long minstep = INT_MAX;
                for (int j = i + 1; j <= nums[i] + i && j < nums.size(); j++)
                {
                    if (dp[j] < minstep)
                    {
                        minstep = dp[j];
                    }
                }
                if (minstep != INT_MAX)
                    dp[i] = minstep + 1;
                else
                {
                    dp[i] = minstep; //从i开始不可达
                }
            }
        }
        return dp[0];
    }
};
