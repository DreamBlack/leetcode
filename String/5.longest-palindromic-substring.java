import sun.security.util.Length;

/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 *
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (27.87%)
 * Likes:    4495
 * Dislikes: 406
 * Total Accepted:    677K
 * Total Submissions: 2.4M
 * Testcase Example:  '"babad"'
 *
 * Given a string s, find the longest palindromic substring in s. You may
 * assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * 
 * 
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "cbbd"
 * Output: "bb"
 * 
 * 注意最长公共子串（要求更高，转化为最长公共后缀的长度问题）和最长公共子序列的区别
 * 不仅是最长公共子串，还是最长公共回文子串，所以会失效
 * 要考虑：We could see that the longest common substring method fails 
 * when there exists a reversed copy of a non-palindromic substring 
 * in some other part of SS.
 * 
 * 解题思路：
 * 1、求s和s.reverse()的最长的回文的公共子串
 * T:O(n^2)和S:O(N^2)
 * 2、Brute Force
 * T:O(n^3)和S:O(1)
 * 3、中心扩展法
 * 对于每个元素i，像左右两边扩展，计算得到以i为中心的最长回文串，并更新最大
 * 注意对每个元素有两种扩展方法，一种是回文串为偶数长度，一种是为奇数长度
 * T:O(n^2)和S:O(1)
 * 4、动态规划
 * 一般的动态规划是二维数组从左上角开始往左下角填；
 * 本题的动态规划是上三角矩阵，逐条对角线，从左上角往右下角计算
 * dp[i,j]表示sub(i,j+1)是否是回文串，那么
 * dp[i,j]==true，当dp[i+1,j-1]&&s[i]==s[j]
 * 否则dp[i,j]==false,
 * 初始情况是dp[i][i]=true,dp[i][i+1]=(s[i]==s[i+1])
 * T:O(n^2)和S:O(N^2)
 */

// @lc code=start
class Solution {
    /**
     * 动态规划法
     * 
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int n = s.length();
        String ans = "";
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            if (i + 1 < n) {
                dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
                if (dp[i][i + 1] && s.substring(i, i + 2).length() > ans.length()) {
                    ans = s.substring(i, i + 2);
                }
            }
            if (dp[i][i] && s.substring(i, i + 1).length() > ans.length()) {
                ans = s.substring(i, i + 1);
            }

        }
        for (int k = 2; k < n; k++) {
            for (int j = k, i = 0; i < n && j < n; i++, j++) {
                dp[i][j] = (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j));
                if (dp[i][j] && s.substring(i, j + 1).length() > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    /**
     * 中心扩展法
     * 
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            // 循环不变量i表示中心点范围[0,n-1]
            // 奇数，以s[i]为中心
            int left = i - 1, right = i + 1;// left=[-1,i-1],right=[i+1,n-1]
            while (left >= 0 && right < n) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left--;
                right++;
            }
            // 一定有结果至少是单个字符
            if (s.substring(left + 1, right).length() > ans.length()) {
                ans = s.substring(left + 1, right);
            }
            // 偶数，以s[i]s[i+1]为中心
            left = i;// left=[0,i]
            right = i + 1;// right=[i+1,n-1]
            while (left >= 0 && right < n) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left--;
                right++;
            }
            // 不一定有结果的
            if (left != i && s.substring(left + 1, right).length() > ans.length()) {
                // left动了，表示找到了至少一个回文串
                ans = s.substring(left + 1, right);
            }
        }
        return ans;
    }

    /**
     * Brute Force
     * 
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int n = s.length();
        String ans = "";
        // i,j循环不变量，表示回文串s[i,j)，所以i范围是[0,n-1],j范围是[i+1,n]
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = s.substring(i, j);
                if (isPalindrome(sub) && sub.length() > ans.length()) {
                    ans = sub;
                }
            }
        }
        return ans;
    }

    public boolean isPalindrome(String s) {
        if (s == "" || s.length() == 1) {
            return true;
        }
        int i = 0, j = s.length() - 1;// 循环不变量，表示比较的元素
        while (i < j) {// i==j不用判断了
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 写错的使用公共子串方法计算最长回文子串： 应该改为对于求得的最长回文子串还要判断它是否回文，找到回文的最长的 那个才是答案。
     * 
     * 对于求最长公共子串的方法要注意，状态转移方程 当s[i]!=s[j]的时候，dp[j][j]=0，而不是max(,)
     * 
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        ;
        String b = new StringBuilder(s).reverse().toString();
        int maxLen = 0, indi = -1, indj = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;// 这个是最长公共子串的写法
                    // dp[i][j]=Math.max(dp[i][j-1], dp[i-1][j]);这个是最长公共子序列的解法
                }
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    indi = i;
                    indj = j;
                }
            }
        }
        // System.out.print(dp[n][n]);
        StringBuilder ans = new StringBuilder();
        if (maxLen == 0) {
            return "";
        }
        while (ans.length() < maxLen && indi - 1 < n && indj - 1 < n) {

            ans.append(s.charAt(indi - 1));
            indi--;
            indj--;

        }
        return ans.toString();
    }
}
// @lc code=end
