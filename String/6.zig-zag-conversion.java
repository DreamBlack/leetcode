import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=6 lang=java
 *
 * [6] ZigZag Conversion
 *
 * https://leetcode.com/problems/zigzag-conversion/description/
 *
 * algorithms
 * Medium (33.16%)
 * Likes:    1397
 * Dislikes: 4086
 * Total Accepted:    410.1K
 * Total Submissions: 1.2M
 * Testcase Example:  '"PAYPALISHIRING"\n3'
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for
 * better legibility)
 * 
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * Write the code that will take a string and make this conversion given a
 * number of rows:
 * 
 * 
 * string convert(string s, int numRows);
 * 
 * Example 1:
 * 
 * 
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * 
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 
 * 解题思路
 * 1、我写的方法
 * 先写好第一列，然后就是循环的填充（斜线，竖线）
 * T:O(N),S:O(N)
 * 2、答案：直接计算每一个在的位置
 */

// @lc code=start
class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 0) {
            return null;
        }
        if (s == null || s.length() == 1 || numRows == 1) {
            return s;
        }
        int cnt = 0, n = s.length();
        String[] anses = new String[(numRows < n) ? numRows : n];

        for (int i = 0; i < numRows && cnt < n; i++) {
            anses[i] = "" + s.charAt(i);
            cnt++;
        }

        int i = numRows - 1;
        while (cnt < n) {
            i = numRows - 2;
            while (cnt < n && i >= 0) {
                anses[i] += s.charAt(cnt++);
                i--;
            }
            i = 1;
            while (cnt < n && i < numRows) {
                anses[i] += s.charAt(cnt++);
                i++;
            }
        }
        String result = "";
        for (String ss : anses) {
            result += ss;
        }
        return result;

    }
}
// @lc code=end
