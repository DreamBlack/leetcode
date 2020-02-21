/*
 * @lc app=leetcode id=8 lang=java
 *
 * [8] String to Integer (atoi)
 *
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 *
 * algorithms
 * Medium (14.80%)
 * Likes:    1310
 * Dislikes: 7924
 * Total Accepted:    485.4K
 * Total Submissions: 3.2M
 * Testcase Example:  '"42"'
 *
 * Implement atoi which converts a string to an integer.
 * 
 * The function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this
 * character, takes an optional initial plus or minus sign followed by as many
 * numerical digits as possible, and interprets them as a numerical value.
 * 
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of
 * this function.
 * 
 * If the first sequence of non-whitespace characters in str is not a valid
 * integral number, or if no such sequence exists because either str is empty
 * or it contains only whitespace characters, no conversion is performed.
 * 
 * If no valid conversion could be performed, a zero value is returned.
 * 
 * Note:
 * 
 * 
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−2^31,  2^31 − 1]. If the numerical
 * value is out of the range of representable values, INT_MAX (2^31 − 1) or
 * INT_MIN (−2^31) is returned.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "42"
 * Output: 42
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus
 * sign.
 * Then take as many numerical digits as possible, which gets 42.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a
 * numerical digit.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a
 * numerical 
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 * 
 * Example 5:
 * 
 * 
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit
 * signed integer.
 * Thefore INT_MIN (−2^31) is returned.
 * 
 * 解题思路：
 * 在如何判断溢出上出错了，搞不定，看了Discussion
 * Tips:
 * 1、先判断是否会造成溢出，然后再运算，不要运算之后根据结果判断
 * 2、字符转数字，用sign表示符号，正数是1，负数是-1；total表示当前结果
 * 每次左移的时候total*10即可
 */

// @lc code=start
class Solution {
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;
        // 1\处理空串
        if (str.length() == 0) {
            return 0;
        }

        // 2、去掉开头的空格
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }
        if (index == str.length()) {
            return 0;
        }
        // 3、处理符号

        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }
        // 4、转换数字，并且防止溢出
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) {
                break;// 非法字符
            }
            // 先判断是否会造成溢出，然后再运算，不要运算之后根据结果判断
            if (Integer.MAX_VALUE / 10 < total || (Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            total = 10 * total + digit;
            index++;
        }
        return total * sign;

    }

    /**
     * 我的垃圾不AC写法
     * 
     * @param str
     * @return
     */
    public int myAtoi1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int i = 0;
        for (; i < str.length(); i++) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-' || Character.isDigit(str.charAt(i))) {
                break;
            }
            if (str.charAt(i) != ' ') {
                return 0;
            }
        }
        boolean minus = false;
        int left = i;
        if (i < str.length()) {
            if (!Character.isDigit(str.charAt(i))) {
                // 是正号或者负号
                minus = str.charAt(i) == '-' ? true : false;
                left = i + 1;
            }
        } else {
            return 0;
        }
        if (left == str.length() || !Character.isDigit(str.charAt(left))) {
            return 0;
        }
        int right = left + 1;
        while (right < str.length() && Character.isDigit(str.charAt(right))) {
            right++;
        }

        int sum = 0, base = 1, last = 0;
        for (int j = right - 1; j >= left; j--) {
            last = sum;
            sum = sum + (str.charAt(j) - '0') * base;

            System.out.println("base=" + base + "sum=" + sum);
            if (sum < last || (str.charAt(j) - '0') * base < 0 || (base > base * 10 && j > left)) {
                return minus ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            base *= 10;
        }
        return minus ? -sum : sum;
    }
}
// @lc code=end
