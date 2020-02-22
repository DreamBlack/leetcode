/*
 * @lc app=leetcode id=43 lang=java
 *
 * [43] Multiply Strings
 *
 * https://leetcode.com/problems/multiply-strings/description/
 *
 * algorithms
 * Medium (31.50%)
 * Likes:    1407
 * Dislikes: 650
 * Total Accepted:    252.7K
 * Total Submissions: 777.8K
 * Testcase Example:  '"2"\n"3"'
 *
 * Given two non-negative integers num1 and num2 represented as strings, return
 * the product of num1 and num2, also represented as a string.
 * 
 * Example 1:
 * 
 * 
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * 
 * Example 2:
 * 
 * 
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * 
 * 
 * Note:
 * 
 * 
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0
 * itself.
 * You must not use any built-in BigInteger library or convert the inputs to
 * integer directly.
 * 
 * 解题思路
 * 复杂度O（N*M）
 * 
 */

// @lc code=start
class Solution {
    /**
     * 网友的写法，直接计算元素相乘了之后的数应该放在结尾中哪个位置
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }
        int m1 = num1.length();
        int m2 = num2.length();
        int[] p = new int[m1 + m2];
        for (int i = m1 - 1; i >= 0; i--) {
            for (int j = m2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + p[i + j + 1];
                p[i + j] += mul / 10;// 注意这里是+=
                p[i + j + 1] = mul % 10;// 注意这里是=

            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m1 + m2; i++) {
            if (!(p[i] == 0 && ans.length() == 0)) {
                ans.append(p[i]);
            }
        }
        return ans.length() == 0 ? "0" : ans.toString();
    }

    /**
     * 我写的方法，使用乘法和加法实现
     * 
     * @param num1
     * @param num2
     * @return
     */
    public String multiplyMy(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }

        StringBuilder ans = new StringBuilder();
        int i = 0;
        for (; i < num1.length(); i++) {
            if (num1.charAt(i) != '0') {
                break;
            }
        }

        StringBuilder s1 = new StringBuilder(num1.substring(i, num1.length())).reverse();
        i = 0;
        for (; i < num2.length(); i++) {
            if (num2.charAt(i) != '0') {
                break;
            }
        }

        StringBuilder s2 = new StringBuilder(num2.substring(i, num2.length())).reverse();
        if (s1.length() == 0 || s2.length() == 0) {
            return "0";
        }

        if (num1.length() < num2.length()) {
            return multiply(num2, num1);
        }
        for (i = 0; i < s2.length(); i++) {
            int multi = s2.charAt(i) - '0';
            int carry = 0;
            StringBuilder tmpM = new StringBuilder();
            for (int j = 0; j < s1.length(); j++) {
                int mm = multi * (s1.charAt(j) - '0') + carry;
                carry = mm / 10;
                mm %= 10;
                tmpM.append(mm);
            }
            if (carry != 0) {
                tmpM.append(carry);
            }
            for (int j = 0; j < i; j++) {
                tmpM.insert(0, "0");
            }
            ans = add(tmpM, ans);
        }
        return ans.reverse().toString();
    }

    /**
     * 123+456做加法，返回的也逆序的
     * 
     * @param num1，如果加数是abc，传入的参数是逆序的cbc
     * @param num2，同上
     * @return
     */
    public StringBuilder add(StringBuilder s1, StringBuilder s2) {
        if (s1 == null || s2 == null) {
            return new StringBuilder();
        }
        if (s1.length() == 0) {
            return s2;
        }
        if (s2.length() == 0) {
            return s1;
        }
        int carry = 0;
        if (s1.length() < s2.length()) {
            return add(s2, s1);
        }
        StringBuilder ans = new StringBuilder();
        while (s1.length() != s2.length()) {
            s2.append("0");
        }
        for (int i = 0; i < s1.length(); i++) {
            int sum = s1.charAt(i) - '0' + s2.charAt(i) - '0' + carry;
            carry = sum / 10;
            sum %= 10;
            ans.append(sum);
        }
        if (carry != 0) {
            ans.append(carry);
        }
        return ans;

    }
}
// @lc code=end
