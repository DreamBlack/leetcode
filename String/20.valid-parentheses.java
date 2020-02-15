import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 *
 * https://leetcode.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (37.20%)
 * Likes:    4094
 * Dislikes: 194
 * Total Accepted:    846.8K
 * Total Submissions: 2.2M
 * Testcase Example:  '"()"'
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * 
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * 
 * 
 * Note that an empty string is also considered valid.
 * 
 * Example 1:
 * 
 * 
 * Input: "()"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "()[]{}"
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "(]"
 * Output: false
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: "([)]"
 * Output: false
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: "{[]}"
 * Output: true
 * 
 * 解题思路：
 * 用栈
 * 用栈的时候要考虑的事情是什么时候入栈，规定入栈的情况，这样思路会比较清晰
 * 对栈的情况会比较清楚
 * 
 * 
 * 复杂度
 * T: O(N),S：O(N)
 * 
 * 边界
 * 
 * 编码
 * 
 * 用例
 * 
 */

// @lc code=start
class Solution {
    /**
     * 网友的巧妙解法
     * 
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 答案写法，写的更清晰一点。 如果是右括号就要和栈顶元素比较， 如果是左括号则入栈
     * 
     * @param s
     * @return
     */
    public boolean isValidSolution(String s) {
        HashMap<Character, Character> mappings = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };
        if (s == null || s.length() == 0) {
            return true;
        }
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {
                // 是右括号
                char tchar = stack.isEmpty() ? '#' : stack.pop();
                if (mappings.get(c) != tchar) {
                    return false;
                }
            } else {
                // 是左括号
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public boolean isValidMySolution(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        ArrayDeque<Character> stack = new ArrayDeque<>();
        stack.push(s.charAt(0));
        int index = 1;
        while (index < s.length()) {
            if (stack.isEmpty()) {
                // 需要注意的点:入栈的时间问题，只有左括号才让入栈
                stack.push(s.charAt(index));
            } else {
                char last = stack.peek();
                char next = s.charAt(index);
                if ((last == '(' && next == ')') || (last == '{' && next == '}') || (last == '[' && next == ']')) {
                    stack.pop();
                } else {
                    stack.push(next);
                }
            }
            index++;
        }
        return stack.isEmpty();
    }
}
// @lc code=end
