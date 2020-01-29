import java.util.List;

/*
 * @lc app=leetcode id=93 lang=java
 *
 * [93] Restore IP Addresses
 *
 * https://leetcode.com/problems/restore-ip-addresses/description/
 *
 * algorithms
 * Medium (32.37%)
 * Likes:    931
 * Dislikes: 407
 * Total Accepted:    166K
 * Total Submissions: 497K
 * Testcase Example:  '"25525511135"'
 *
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * 
 * Example:
 * 
 * 
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 * 
 * 1、思路
 * 第一种：
 * 需要剪枝的dfs，剪枝的条件来自题目，本题的剪枝条件有如下几个：
 * 如果以0开头，且length大于1；
 * 数字大于255；
 * 第二种：
 * 三层的循环
 * 2、复杂度
 * 空间：用来保存的
 * 时间：O（N*3*3*3）循环是3*3*3，再加上每次循环内substring复杂度为O（N）
 * 3、边界
 * 0.0.0.0  1001 1.1.1.1
 * 4、编码
 * 5、用例
 * 
 * 
 * tips:
 * 1、所有的剪枝可以尽量放在一起，看起来比较简洁
 */

// @lc code=start
class Solution {
    public List<String> restoreIpAddresses2(String s) {
        //看答案后写的循环。简洁容易理解的解法
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4) {
            return result;
        }

        for (int i = 1; i < 4 && i < s.length() - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < s.length() - 1; j++) {// 注意是<i+4而不是<4
                for (int k = j + 1; k < j + 4 && k < s.length(); k++) {// 三个&&加上防止访问越界
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k);
                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        result.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return result;
    }

    boolean isValid(String s) {
        if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4) {
            return result;
        }

        backtracking(s, 0, 0, "", result);
        return result;
    }

    void backtracking(String s, int index, int left, String path, List<String> result) {
        //看答案后改的回溯
        if (index == s.length() && left == 4) {// 退出的时候必须是包含了四位地址段
            result.add(path.substring(0, path.length() - 1));
        } else {
            if (s.length() - index > 3 * (4 - left) || s.length() - index < 4 - left) {
                return;// 剪掉剩余部分不足够构成ip地址的情况
            }
            for (int i = 0; i < 3 && i + index < s.length(); i++) {// 注意i+index的范围控制
                String p = s.substring(index, index + i + 1);
                if (Integer.parseInt(p) > 255 || (p.charAt(0) == '0' && p.length() > 1)) {
                    continue;
                }

                backtracking(s, index + i + 1, left + 1, path + p + ".", result);

            }

        }

    }

    void backtracking1(char[] s, int index, int left, String path, List<String> result) {
        //自己一开始改了很多遍的回溯
        if (index == s.length && left == 4) {// 退出的时候必须是包含了四位地址段
            result.add(path.substring(0, path.length() - 1));
        } else {
            if (s.length - index > 3 * (4 - left) || s.length - index < 4 - left) {
                return;// 剪掉剩余部分不足够构成ip地址的情况
            }
            for (int i = 0; i < 3 && i + index < s.length; i++) {// 注意i+index的范围控制
                path += s[i + index];
                path += ".";
                int ip = 0;
                if (i == 2) {// 忘了，剪枝：剪掉地址段不在在0-255之间的
                    ip = Integer.parseInt(path.substring(path.length() - i - 2, path.length() - 1));

                }
                if (ip < 256) {
                    backtracking1(s, index + i + 1, left + 1, path, result);
                }
                path = path.substring(0, path.length() - 1);
                if (i == 0 && s[index] == '0') {
                    // 剪枝：剪掉两个0或者三个0连着的情况
                    break;
                }
            }

        }

    }
}
// @lc code=end
