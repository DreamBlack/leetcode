import jdk.internal.org.objectweb.asm.tree.analysis.Frame;

/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 *
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (25.65%)
 * Likes:    3501
 * Dislikes: 625
 * Total Accepted:    378.9K
 * Total Submissions: 1.5M
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string (s) and a pattern (p), implement regular expression
 * matching with support for '.' and '*'.
 * 
 * 
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * 
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * Note:
 * 
 * 
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters
 * like . or *.
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
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'.
 * Therefore, by repeating 'a' once, it becomes "aa".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * 
 * 
 * Example 4:
 * 
 * 
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore,
 * it matches "aab".
 * 
 * 
 * Example 5:
 * 
 * 
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 * 
 * 这题搞不定不会
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        // 自底向上的dp
        /*
         * memo[i][j]表示s.substring(i)和p.substring(j)能否匹配 自顶向下
         */
        boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];
        memo[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean firstMatch = i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j));

                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    // 0个或者多个
                    memo[i][j] = memo[i][j + 2] || (firstMatch && memo[i + 1][j]);
                } else {
                    memo[i][j] = firstMatch && memo[i + 1][j + 1];
                }
            }
        }

        return memo[0][0];

    }

    int[][] memo;

    public boolean isMatchUpDown(String s, String p) {
        // 带memo的递归
        /*
         * memo[i][j]表示s.substring(i)和p.substring(j)能否匹配 自顶向下
         */
        memo = new int[s.length() + 1][p.length() + 1];

        return dp(0, 0, s, p);

    }

    public boolean dp(int i, int j, String s, String p) {
        if (memo[i][j] != 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean flag = false;
        if (j == p.length()) {
            flag = s.length() == i;
        } else {
            boolean firstMatch = (i < s.length()) && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j));

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // 0个或者多个
                flag = dp(i, j + 2, s, p) || (firstMatch && dp(i + 1, j, s, p));
            } else {
                flag = firstMatch && dp(i + 1, j + 1, s, p);
            }
        }

        memo[i][j] = (flag) ? 1 : -1;
        return flag;
    }

    public boolean isMatchRecurisive(String s, String p) {
        // 递归解法
        /*
         * 如果第二位不是*,那么就是按位匹配； 如果第二位是*，要么使用0次要么使用多次
         */
        if (p.length() == 0) {
            return s.length() == 0;
        }
        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            // 0个或者多个
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }

    }

    public boolean isMatch1(String s, String p) {
        return backTracking(s, p);
    }

    boolean backTracking(String s, String p) {
        if (s.length() == 0) {
            return true;
        }
        if (p.length() == 0) {
            return false;
        }
        int id = p.indexOf("*");// 找不到返回-1
        if (id == -1) {
            return isValid(s, p);
        }
        String left = p.substring(0, id);
        String com = "";
        for (int i = 0; i <= left.length(); i++) {
            if (i == 0 || isValid(s.substring(0, i), com)) {
                if (backTracking(s.substring(i), p.substring(id + 1))) {
                    return true;
                }
            } else {
                return backTracking(s, p.substring(id + 1));
            }
            com = com + left;
        }
        return false;

    }

    boolean isValid(String s, String p) {
        if (s.length() != p.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (p.charAt(i) == '.' || p.charAt(i) == s.charAt(i)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
