import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=13 lang=java
 *
 * [13] Roman to Integer
 *
 * https://leetcode.com/problems/roman-to-integer/description/
 *
 * algorithms
 * Easy (53.22%)
 * Likes:    1754
 * Dislikes: 3190
 * Total Accepted:    583.8K
 * Total Submissions: 1.1M
 * Testcase Example:  '"III"'
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
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be
 * within the range from 1 to 3999.
 * 
 * Example 1:
 * 
 * 
 * Input: "III"
 * Output: 3
 * 
 * Example 2:
 * 
 * 
 * Input: "IV"
 * Output: 4
 * 
 * Example 3:
 * 
 * 
 * Input: "IX"
 * Output: 9
 * 
 * Example 4:
 * 
 * 
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * 
 * 
 * 解题思路：
 * 用一个hashtable存储字符及对应的数值，
 * 逐个字符进行加法操作，对于I\X\C单独判断，
 * 
 * 复杂度
 * O(n)
 * 
 * 边界：
 * 字符串为空
 * 
 * 编码
 * 
 * 测试用例
 */

// @lc code=start
class Solution {
    public int romanToInt(String s) {
        int result=0;
        if(s==null){
            return 0;
        }
        Map<Character,Integer> table=new HashMap<Character,Integer> (){{
            this.put('I', 1);
            this.put('V', 5);
            this.put('X', 10);
            this.put('L', 50);
            this.put('C', 100);
            this.put('D', 500);
            this.put('M', 1000);
        }};
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            switch(c){
                case 'I':{
                    if(i<s.length()-1&&(s.charAt(i+1)=='V'||s.charAt(i+1)=='X')){
                        result-=1;
                    }else{
                        result+=1;
                    }
                    break;
                }
                case 'X':{
                    if(i<s.length()-1&&(s.charAt(i+1)=='L'||s.charAt(i+1)=='C')){
                        result-=10;
                    }else{
                        result+=10;
                    }
                    break;
                }
                case 'C':{
                    if(i<s.length()-1&&(s.charAt(i+1)=='D'||s.charAt(i+1)=='M')){
                        result-=100;
                    }else{
                        result+=100;
                    }
                    break;
                }
                default:{
                    result+=table.get(c);
                }
            }
        }
        return result;
    }
}
// @lc code=end

