import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=12 lang=java
 *
 * [12] Integer to Roman
 *
 * https://leetcode.com/problems/integer-to-roman/description/
 *
 * algorithms
 * Medium (52.04%)
 * Likes:    841
 * Dislikes: 2389
 * Total Accepted:    311K
 * Total Submissions: 580.5K
 * Testcase Example:  '3'
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
 * and M.
 * 
 * 
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 
 * For example, two is written as II in Roman numeral, just two one's added
 * together. Twelve is written as, XII, which is simply X + II. The number
 * twenty seven is written as XXVII, which is XX + V + II.
 * 
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making
 * four. The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 * 
 * 
 * I can be placed before V (5) and X (10) to make 4 and 9. 
 * X can be placed before L (50) and C (100) to make 40 and 90. 
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * 
 * 
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be
 * within the range from 1 to 3999.
 * 
 * Example 1:
 * 
 * 
 * Input: 3
 * Output: "III"
 * 
 * Example 2:
 * 
 * 
 * Input: 4
 * Output: "IV"
 * 
 * Example 3:
 * 
 * 
 * Input: 9
 * Output: "IX"
 * 
 * Example 4:
 * 
 * 
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * 
 * 解题思路：
 * Tips:
 * 对于num所处范围的判断，可以不用num>a&&m<b，直接将
 * 所有要判断的区间范围用有序递减数组表示，逐个与数组中数字比较即可
 */

// @lc code=start
class Solution {
    /**
     * 网友骚操作
     * 
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        String M[] = { "", "M", "MM", "MMM" };
        String C[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        String X[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        String I[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    /**
     * 对于num所处范围的判断，可以不用num>a&&m<b，直接将 所有要判断的区间范围用有序递减数组表示，逐个与数组中数字比较即可
     * 
     * @param num
     * @return
     */
    public String intToRomanModified(int num) {
        if (num < 1 || num > 3999) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        String[] roman = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        int i = 0;
        while (num > 0) {
            while (num >= values[i]) {
                num -= values[i];
                result.append(roman[i]);

            }
            i++;// i++写在的是这里，而不是上面那个while循环里面
        }
        return result.toString();
    }

    public String intToRomanMy(int num) {
        Map<Integer, String> mappings = new HashMap<>() {
            {
                this.put(1, "I");
                this.put(5, "V");
                this.put(10, "X");
                this.put(50, "L");
                this.put(100, "C");
                this.put(500, "D");
                this.put(1000, "M");
                this.put(4, "IV");
                this.put(9, "IX");
                this.put(40, "XL");
                this.put(90, "XC");
                this.put(400, "CD");
                this.put(900, "CM");
            }
        };
        if (num <= 0) {
            return "";
        }
        if (mappings.containsKey(num)) {
            return mappings.get(num);
        }
        String res = "";
        if (num > 1 && num < 5) {
            for (int i = num; i > 0; i--) {
                res += mappings.get(1);
            }
            return res;
        }
        if (num > 5 && num < 10) {
            return mappings.get(5) + intToRoman(num - 5);
        }
        if (num > 10 && num < 50) {
            if (num > 40) {
                return "XL" + intToRoman(num - 40);
            } else {
                return "X" + intToRoman(num - 10);
            }
        }
        if (num > 50 && num < 100) {
            if (num > 90) {
                return "XC" + intToRoman(num - 90);
            } else {
                return "L" + intToRoman(num - 50);
            }
        }
        if (num > 100 && num < 500) {
            if (num > 400) {
                return "CD" + intToRoman(num - 400);
            } else {
                return "C" + intToRoman(num - 100);
            }
        }
        if (num > 500 && num < 1000) {
            if (num > 900) {
                return "CM" + intToRoman(num - 900);
            } else {
                return "D" + intToRoman(num - 500);
            }
        }
        if (num > 1000) {
            return "M" + intToRoman(num - 1000);
        }
        return res;
    }
}
// @lc code=end
