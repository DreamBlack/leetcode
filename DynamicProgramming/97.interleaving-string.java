/*
 * @lc app=leetcode id=97 lang=java
 *
 * [97] Interleaving String
 *
 * https://leetcode.com/problems/interleaving-string/description/
 *
 * algorithms
 * Hard (28.83%)
 * Likes:    1025
 * Dislikes: 61
 * Total Accepted:    130K
 * Total Submissions: 439.3K
 * Testcase Example:  '"aabcc"\n"dbbca"\n"aadbbcbcac"'
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and
 * s2.
 * 
 * Example 1:
 * 
 * 
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * 
 * 解题思路：
 * 1、先考虑暴力解题方法
 * 最好把暴力的时间复杂度求出来，如果能很清楚的指导暴力复杂度的求法，一定程度上
 * 也会帮助自己容易写出暴力解法（递归||DFS）
 * 2、从暴力解法出发，想办法用dp，并用记录中间结果重复多次利用的方法
 */

// @lc code=start
class Solution {
    public boolean bruteForceisInterleave(String s1, String s2, String s3) {

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // 法一：暴力递归解法
        // 复杂度O(2^(M*N))，空间O(M+N)
        /*
         * 对于结果串s3，长为m+n，在每一个字符位置上都有两种选择， 要么选择s1当前第一个字符，要么选择s2当前第一个字符，所以
         * 用递归的算法，时间复杂度为O(2^(M*N)) 递归栈的最大深度为m+n，所以空间复杂度为O(M+N)
         */
        return recursiveHelp(s1, 0, s2, 0, "", s3);
    }

    public boolean twoDDpisInterleave(String s1, String s2, String s3) {

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // 法二：二维dp
        /*
         * 时间O(m*n)，空间O(m*n) 开始想从递归解法考虑自底向上或者自顶向下，其实在这里都一样，所以这个不是考虑的方向；
         * 
         * dp[i][j]表示s1(0,i)，s2(0,j)能否交错构成s3(0,i+j)； 确定dp[i][j]方法后，纠结于其对应的s3串的长度问题
         * 
         * 如果s1[i]=s3[i+j]&&dp[i-1][j]，可以构成； 如果s2[j]=s3[i+j]&&dp[i][j-1]，可以构成；
         * 其他情况都不可以构成
         */
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s2.length(); i++) {
            dp[0][i] = dp[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = (dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i - 1 + j))
                        || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1 + j)));
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public boolean isInterleave(String s1, String s2, String s3) {

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        // 法三：一维dp
        /*
         * 时间O(m*n)，空间O(n) 这种选择上下的dp，换成一维，甚至都不需要保存中间变量
         */
        boolean[] dp = new boolean[s2.length() + 1];
        dp[0] = true;

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[0] = true;
                } else if (i == 0) {
                    dp[j] = (s2.charAt(j - 1) == s3.charAt(j - 1)) && dp[j - 1];
                } else if (j == 0) {
                    dp[j] = (s1.charAt(i - 1) == s3.charAt(i - 1)) && dp[j];
                } else {
                    dp[j] = (dp[j - 1] && (s2.charAt(j - 1) == s3.charAt(i - 1 + j))
                            || (dp[j] && s1.charAt(i - 1) == s3.charAt(i - 1 + j)));
                }
            }
        }
        return dp[s2.length()];
    }

    boolean recursiveHelp1(String s1, int i, String s2, int j, String res, String s3) {
        // 自己写的递归
        if (i == s1.length()) {
            res += s2.substring(j);
            return res.equals(s3);
        }
        if (j == s2.length()) {
            res += s1.substring(i);
            return res.equals(s3);
        }
        if (res.equals(s3.substring(0, res.length()))) {
            return recursiveHelp(s1, i + 1, s2, j, res + s1.charAt(i), s3)
                    || recursiveHelp(s1, i, s2, j + 1, res + s2.charAt(j), s3);
        } else {
            return false;
        }

    }

    boolean recursiveHelp(String s1, int i, String s2, int j, String res, String s3) {
        // 解答写的递归
        if (res.equals(s3) && i == s1.length() && j == s2.length()) {
            return true;
        }
        boolean flag = false;
        if (i < s1.length() && res.equals(s3.substring(0, res.length()))) {
            flag = flag || recursiveHelp(s1, i + 1, s2, j, res + s1.charAt(i), s3);
        }
        if (j < s2.length() && res.equals(s3.substring(0, res.length()))) {
            flag = flag || recursiveHelp(s1, i, s2, j + 1, res + s2.charAt(j), s3);
        }
        return flag;
    }
}
// @lc code=end
