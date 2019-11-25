/*
 * @lc app=leetcode id=115 lang=java
 *
 * [115] Distinct Subsequences
 *
 * https://leetcode.com/problems/distinct-subsequences/description/
 *
 * algorithms
 * Hard (35.75%)
 * Likes:    859
 * Dislikes: 45
 * Total Accepted:    118.1K
 * Total Submissions: 326.3K
 * Testcase Example:  '"rabbbit"\n"rabbit"'
 *
 * Given a string S and a string T, count the number of distinct subsequences
 * of S which equals T.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Example 1:
 * 
 * 
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 * 
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 * 
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: S = "babgbag", T = "bag"
 * Output: 5
 * Explanation:
 * 
 * As shown below, there are 5 ways you can generate "bag" from S.
 * (The caret symbol ^ means the chosen letters)
 * 
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 * ⁠ ^  ^^
 * babgbag
 * ⁠   ^^^
 * 
 * 解题思路：
 * 1、还是二维dp
 * 对于s[i]==s[j]，可以选择用或者不用，因此dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
 * 对于s[i]!=s[j]，只能选择不用，因此dp[i][j]=dp[i-1][j]
 * 时间空间都很垃圾
 * 
 * 2、改进1
 * 一维滚动数组
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numDistinct(String s, String t) {
        int[] dp = new int[t.length() + 1];
        int corner = 1;
        for (int i = 1; i <= s.length(); i++) {
            dp[0] = 1;
            corner = 1;
            for (int j = 1; j <= i && j <= t.length(); j++) {
                int tmp = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += corner;
                }
                corner = tmp;
            }
        }
        return dp[t.length()];
    }

    public int numDistinct1(String s, String t) {
        // if(s.length()==0)return 0;

        int[][] dp = new int[s.length() + 1][t.length() + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= i && j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[s.length()][t.length()];

    }
}
// @lc code=end
