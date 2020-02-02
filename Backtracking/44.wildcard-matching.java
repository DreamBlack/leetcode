/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 *
 * https://leetcode.com/problems/wildcard-matching/description/
 *
 * algorithms
 * Hard (23.33%)
 * Likes:    1540
 * Dislikes: 88
 * Total Accepted:    216.5K
 * Total Submissions: 904.2K
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern
 * matching with support for '?' and '*'.
 * 
 * 
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * 
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * Note:
 * 
 * 
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters
 * like ? or *.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not
 * match 'b'.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*'
 * matches the substring "dce".
 * 
 * 
 * Example 5:
 * 
 * 
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 * 
 * 解题思路
 * 
 * 复杂度
 * 
 * 边界
 * 
 * 
 * 编码
 * 
 * 用例
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        // 看了答案后，网友的所谓O(N+M)的线性版本，其实并不是
        int i = 0, j = 0, statid = -1, match = -1;// 分别表示当前处理的s,p的元素，statid记录上一次*出现的位置
        // match表示上一次出现*的时候，处理的s中第match个元素
        while (i < s.length()) {
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                statid = j;
                match = i;
                j++;// 不匹配。*此处作用是啥都不匹配
            } else if (statid != -1) {
                match++;
                i = match;
                j = statid + 1;
            } else {
                return false;
            }
        }
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }

    public boolean isMatchDP(String s, String p) {
        // dp版本
        boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];
        memo[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    memo[i][j] = memo[i][j + 1] || (s.length() != i && memo[i + 1][j]);
                } else {
                    memo[i][j] = i != s.length()
                            && ((p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) && memo[i + 1][j + 1]);
                }
            }
        }
        return memo[0][0];
    }

    int[][] memo;

    public boolean isMatchMemo(String s, String p) {
        // 带memo的递归
        memo = new int[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p);
    }

    public boolean dp(int i, int j, String s, String p) {
        if (memo[i][j] != 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean flag = false;
        if (p.length() == j) {
            flag = (s.length() == i);
        } else {
            if (p.charAt(j) == '*') {
                // *匹配空，或者匹配序列
                flag = dp(i, j + 1, s, p) || (s.length() != i && dp(i + 1, j, s, p));
            } else {
                flag = (s.length() != i) && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))
                        && dp(i + 1, j + 1, s, p);
            }
        }
        memo[i][j] = (flag) ? 1 : -1;
        return flag;

    }

    public boolean isMatchRecursive(String s, String p) {
        // 递归版本，超时了
        if (p.length() == 0) {
            return s.length() == 0;
        }

        if (p.charAt(0) == '*') {
            // *匹配空，或者匹配序列
            return isMatch(s, p.substring(1)) || (s.length() != 0 && isMatch(s.substring(1), p));
        } else {

            return (s.length() != 0) && (p.charAt(0) == '?' || p.charAt(0) == s.charAt(0))
                    && isMatch(s.substring(1), p.substring(1));
        }

    }
}
// @lc code=end
