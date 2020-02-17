/*
 * @lc app=leetcode id=58 lang=java
 *
 * [58] Length of Last Word
 *
 * https://leetcode.com/problems/length-of-last-word/description/
 *
 * algorithms
 * Easy (32.28%)
 * Likes:    523
 * Dislikes: 2097
 * Total Accepted:    333.4K
 * Total Submissions: 1M
 * Testcase Example:  '"Hello World"'
 *
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word (last word means the last
 * appearing word if we loop from left to right) in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a maximal substring consisting of non-space
 * characters only.
 * 
 * Example:
 * 
 * 
 * Input: "Hello World"
 * Output: 5
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 网友骚操作
     * 
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        s = s.trim();
        return s.length() - s.lastIndexOf(' ') - 1;
    }

    /**
     * 我写的方法
     * 
     * @param s
     * @return
     */
    public int lengthOfLastWordMySolution(String s) {
        if (s == null || s == "") {
            return 0;
        }
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (end < 0) {
            return 0;
        }
        int begin = end - 1;
        while (begin >= 0 && s.charAt(begin) != ' ') {
            begin--;
        }
        return end - begin;
    }
}
// @lc code=end
