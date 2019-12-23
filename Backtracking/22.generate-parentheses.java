import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 *
 * https://leetcode.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (56.90%)
 * Likes:    3732
 * Dislikes: 215
 * Total Accepted:    436.2K
 * Total Submissions: 744.2K
 * Testcase Example:  '3'
 *
 * 
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * 
 * 
 * For example, given n = 3, a solution set is:
 * 
 * 
 * [
 * ⁠ "((()))",
 * ⁠ "(()())",
 * ⁠ "(())()",
 * ⁠ "()(())",
 * ⁠ "()()()"
 * ]
 * 
 * 题目大意：
 * 生成n对圆括号所有可能的组合方式
 * 
 * 解题思路：
 * 1、是否要回溯
 * 答：要
 * 2、终结点是什么
 * 答：字符串长度达到2*n
 * 3、子节点是什么
 * 答：两种，左括号或者右括号
 * 4、本题关键：剪枝操作
 * a.必须出现过左括号，才能加右括号
 * b.左括号个数小于n;右括号个数小于左括号 
 * 
 * 改进：
 * 一开始用了栈结构用来匹配括号，后来发现根本不需要
 *
 * 复杂度分析：
 * 可能的串有2^(2N)种
 * 
 * 答案：
 * 1、brute force
 * 递归生成2^(2N)种，最后判定是不是符合条件的。
 * 时间O(2^(2N)*N)，空间同
 * 2、写的更简洁的backtracking，不用手动add，用函数调用的时候参数自动增或减
 * 时间O(卡特兰数*N)，空间同+O（N）
 * 3、closure number
 * 既然复杂度是卡特兰数就想到斐波那契数列f(2)=f(1)+f(1);
 * 将结果序列分为两部分A+B都满足要求；
 * 那么长度为3*2的序列的所有可能情况就是f(0)+f(2),f(1)+f(1),f(2)+f(0),
 * 其中f(2)={f(0)+f(1),f(1)+f(0)};={()(),(())}
 *    f(1)={f(0)+f(0)}   ="()"
 *    f(0)=""
 * 复杂度同上
 * 
 * Tips:
 * 几个调试过程中发生错误的地方
 * 1、path.substring(0,path.length()-1)写成了path.substring(0,path.length())
 * 2、只注意了左括号个数必须小于n，没有注意有效表达式中右括号数量也必须小于等于已有的左括号数量
 * 3、java中用ArrayDeque模拟栈，出栈pop，入栈push，取栈顶peek
 */

// @lc code=start
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            res.add("");
        } else {
            for (int c = 0; c < n; c++) {
                for (String left : generateParenthesis(c)) {
                    for (String right : generateParenthesis(n - 1 - c)) {
                        // 以下三句都可以
                        // res.add("("+left+")"+right);
                        // res.add("("+right+")"+left);
                        res.add(left + "(" + right + ")");
                    }
                }
            }
        }

        return res;
    }

    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        backTracking(n, 0, 0, 0, "", res);
        return res;
    }

    void backTracking1(int n, int cntL, int cntR, int len, String path, List<String> res) {
        if (len == n * 2) {
            res.add(new String(path));
            return;
        }

        if (cntL < n) {// 剪枝2：左括号个数小于n
            // stack.push('(');
            path += "(";
            backTracking1(n, cntL + 1, cntR, len + 1, path, res);
            path = path.substring(0, path.length() - 1);// 这里应该是到lenth-1！！！
            // stack.pop();
        }
        // 剪枝1：必须出现过左括号
        // if (!stack.isEmpty() && cntR < cntL) {// 剪枝2：右括号不能多于左括号
        if (cntL > 0 && cntR < cntL) {
            path += ")";
            backTracking1(n, cntL, cntR + 1, len + 1, path, res);
            path = path.substring(0, path.length() - 1);

        }
    }

    void backTracking(int n, int cntL, int cntR, int len, String path, List<String> res) {
        // backTracking更简洁写法
        if (len == n * 2) {
            res.add(new String(path));
            return;
        }

        if (cntL < n) {// 剪枝2：左括号个数小于n
            backTracking(n, cntL + 1, cntR, len + 1, path + "(", res);
        }
        // 剪枝1：必须出现过左括号
        // if (!stack.isEmpty() && cntR < cntL) {// 剪枝2：右括号不能多于左括号
        if (cntR < cntL) {
            backTracking(n, cntL, cntR + 1, len + 1, path + ")", res);

        }
    }
}
// @lc code=end
