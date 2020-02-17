/*
 * @lc app=leetcode id=125 lang=java
 *
 * [125] Valid Palindrome
 *
 * https://leetcode.com/problems/valid-palindrome/description/
 *
 * algorithms
 * Easy (32.26%)
 * Likes:    898
 * Dislikes: 2482
 * Total Accepted:    486.2K
 * Total Submissions: 1.4M
 * Testcase Example:  '"A man, a plan, a canal: Panama"'
 *
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 * 
 * Note: For the purpose of this problem, we define empty string as valid
 * palindrome.
 * 
 * Example 1:
 * 
 * 
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "race a car"
 * Output: false
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 网友的骚操作
     * 
     * 逆向思维： 把所有非数字字母的去掉，然后reverse之后和原来的字符串比较
     * 
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuilder(actual).reverse().toString();
        return actual.equals(rev);

    }

    /**
     * 我的写法 注意看清楚题目意思，题目要求的是不区分大小写
     * 
     * @param s
     * @return
     */
    public boolean isPalindromeMySolution(String s) {
        if (s == null) {
            return false;
        }
        if (s == "") {
            return true;
        }
        String ls = s.toLowerCase();// 注意不区分大小写
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isAlphanumeric(ls.charAt(i))) {
                i++;
            }
            while (i < j && !isAlphanumeric(ls.charAt(j))) {
                j--;
            }
            if (ls.charAt(i) != ls.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    public boolean isAlphanumeric(char c) {
        if (Character.isAlphabetic(c) || Character.isDigit(c)) {
            return true;
        }
        return false;
    }
}
// @lc code=end
