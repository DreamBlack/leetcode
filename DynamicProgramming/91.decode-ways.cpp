/*
 * @lc app=leetcode id=91 lang=cpp
 *
 * [91] Decode Ways
 *
 * https://leetcode.com/problems/decode-ways/description/
 *
 * algorithms
 * Medium (22.83%)
 * Likes:    1736
 * Dislikes: 1982
 * Total Accepted:    302.4K
 * Total Submissions: 1.3M
 * Testcase Example:  '"12"'
 *
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * 
 * Given a non-empty string containing only digits, determine the total number
 * of ways to decode it.
 * 
 * Example 1:
 * 
 * 
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2
 * 6).
 * 
 */

// @lc code=start
class Solution
{
public:
    int num;
    int numDecodings(string s)
    {
        int p = 1, pp; //p=[i+1],pp[i+2]
        for (int i = s.size() - 1; i >= 0; i--)
        {
            int cur = s[i] == '0' ? 0 : p;
            if (i < s.size() - 1 && (s[i] == '1' || (s[i] == '2' && s[i + 1] < '7')))
            {
                cur += pp;
            }
            pp = p;
            p = cur;
        }
        return s.empty() ? 0 : p;
    }
    int numDecodings2(string s)
    {
        vector<int> dp(s.size() + 1, 0);
        dp[s.size()] = 1;
        for (int i = s.size() - 1; i >= 0; i--)
        {
            if (s[i] == '0')
                dp[i] = 0;
            else
            {
                dp[i] = dp[i + 1];
                if (i < s.size() - 1 && (s[i] == '1' || (s[i] == '2' && s[i + 1] < '7')))
                {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return s.empty() ? 0 : dp[0];
    }

    int numDecodings1(string s)
    {
        num = 0;
        vector<int> dp(s.size(), -1);
        dfs(s, 0, dp);
        return dp[0];
    }
    int dfs(string s, int index, vector<int> &dp)
    {
        if (s[index] == '0')
        {
            //如果出现孤单0，表示这种解析法不可取，直接返回
            dp[index] = 0;
            return 0;
        }
        //这种末端也要返回值并且设置dp值，否则对于s="1"这种情况就会返回-1
        if (index == s.size() - 1)
        {
            dp[index] = 1;
            return 1;
        }
        else if (index > s.size() - 1)
        {
            dp[index - 1] = 1;
            return 1;
        }

        //以下if的先后判断顺序要注意，>2要放在前面，如果先判断+1是否为0,30,40的
        //情况就不会被考虑
        if (dp[index] == -1)
        {
            if (s[index] > '2')
            {
                dp[index] = dfs(s, index + 1, dp);
            }
            else if (s[index + 1] == '0')
            {
                dp[index] = dfs(s, index + 2, dp);
            }
            else if (s[index] == '1')
            {
                dp[index] = dfs(s, index + 2, dp) + dfs(s, index + 1, dp);
            }
            else
            {
                //s[index]='2'
                if (s[index + 1] > '6')
                {
                    dp[index] = dfs(s, index + 1, dp);
                }
                else
                {
                    dp[index] = dfs(s, index + 2, dp) + dfs(s, index + 1, dp);
                }
            }
        }
        if (dp[index] == 0)
        {
            return 0;
        }

        return dp[index]; //注意返回值
    }
};
// @lc code=end
