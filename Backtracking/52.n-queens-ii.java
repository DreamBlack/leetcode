import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=52 lang=java
 *
 * [52] N-Queens II
 *
 * https://leetcode.com/problems/n-queens-ii/description/
 *
 * algorithms
 * Hard (53.60%)
 * Likes:    382
 * Dislikes: 133
 * Total Accepted:    118.3K
 * Total Submissions: 214.2K
 * Testcase Example:  '4'
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * 
 * 
 * 
 * Given an integer n, return the number of distinct solutions to the n-queens
 * puzzle.
 * 
 * Example:
 * 
 * 
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as
 * shown below.
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * 
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 
 * 思路
 * 1、和51题一样，只不过由记录结果们，变成了记录结果的个数
 * 2、看了答案后发现，其实也并不需要记录board 了
 * 
 * 复杂度
 * 
 * 边界
 * 
 * 编码
 * 
 * 用例测试
 * 
 * Tips:
 * 其实回溯题的框架都是一样的，关键在于怎么挖深理解题目意思，然后用算法框架去解决问题
 */

// @lc code=start
class Solution {
    int total;

    public int totalNQueens(int n) {
        if (n <= 0 || n == 2 || n == 3) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        backTracking(n, 0, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
        return total;
    }

    void backTracking(int n, int row, boolean[] cols, boolean[] d1, boolean[] d2) {
        if (row == n) {
            total += 1;
        } else {
            for (int col = 0; col < n; col++) {
                int ind1 = n + col - row;
                int ind2 = col + row;
                if (!cols[col] && !d1[ind1] && !d2[ind2]) {
                    cols[col] = true;
                    d1[ind1] = true;
                    d2[ind2] = true;
                    backTracking(n, row + 1, cols, d1, d2);
                    cols[col] = false;
                    d1[ind1] = false;
                    d2[ind2] = false;
                }

            }
        }
    }
}
// @lc code=end
