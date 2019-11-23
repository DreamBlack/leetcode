/*
 * @lc app=leetcode id=72 lang=java
 *
 * [72] Edit Distance
 *
 * https://leetcode.com/problems/edit-distance/description/
 *
 * algorithms
 * Hard (39.29%)
 * Likes:    2715
 * Dislikes: 42
 * Total Accepted:    208.7K
 * Total Submissions: 518.5K
 * Testcase Example:  '"horse"\n"ros"'
 *
 * Given two words word1 and word2, find the minimum number of operations
 * required to convert word1 to word2.
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * 
 * Insert a character
 * Delete a character
 * Replace a character
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation: 
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation: 
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * 
 * 题目类型：字符串编辑
 * 难点：二维dp好像没法弄，树DFS的角度好像也搞不出来
 * 字符串A变到B的最短距离，和字符串B变到A的最短距离是一样的
 * 
 * 解题思路：
 * 1、二维DP
 * 开心，哈哈哈，居然一遍AC
 * 就从二维dp的角度出发，无非是根据以前状态判断当前状态
 * dp[i][j]表示，word1中0-i个字符最少要经过几次编辑才能编成word2中0-j字符串
 * 如果word1[j]==word2[i]表示，word1中第j个不用变化，直接从dp[i-1][j-1]拿过来即可；
 * 如果word1[j]！=word2[i]，有三种选择，删掉第j个字符，dp[i][j-1]+1
 * 在insert一个word2[i],dp[i-1][j]+1，或者将word1中第j个字符改为word2中第i个字符
 * dp[i-1][j-1]+1
 * 
 * 2、一维DP
 * 看答案的
 * 
 * Tips:
 * 1、加上 if(word1.length()<word2.length())return minDistance(word2, word1);
 * 可以加快速度
 * 2、改用一维数组的时候注意word1[i]==word2[j]的时候，应该取pre了
 */

// @lc code=start
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.length() < word2.length())
            return minDistance(word2, word1);
        int[] dp = new int[word2.length() + 1];
        for (int i = 0; i <= word2.length(); i++) {
            dp[i] = i;
        }
        int pre = 0;// pre应该用来保存dp[i-1][j-1]，因为i-1,j-1会被覆盖
        for (int i = 1; i <= word1.length(); i++) {
            pre = dp[0];
            dp[0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                int tmp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = pre;// 注意这里也要改
                } else {
                    dp[j] = Math.min(pre, Math.min(dp[j], dp[j - 1])) + 1;
                }
                pre = tmp;
                // System.out.print(dp[j]+" ");
            }
            // System.out.println();
        }
        // for(int i=0;i<=word2.length();i++){
        // for(int j=0;j<=word1.length();j++){
        // System.out.print(dp[i][j]+" ");
        // }
        // System.out.println();
        // }
        return dp[word2.length()];
    }

    public int minDistance1(String word1, String word2) {
        if (word1.length() < word2.length())
            return minDistance(word2, word1);
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        for (int i = 0; i <= word2.length(); i++) {
            for (int j = 0; j <= word1.length(); j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[word1.length()][word2.length()];
    }
}
// @lc code=end
