/*
 * @lc app=leetcode id=37 lang=java
 *
 * [37] Sudoku Solver
 *
 * https://leetcode.com/problems/sudoku-solver/description/
 *
 * algorithms
 * Hard (38.65%)
 * Likes:    1320
 * Dislikes: 81
 * Total Accepted:    160.7K
 * Total Submissions: 396.9K
 * Testcase Example:  '[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]'
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * A sudoku solution must satisfy all of the following rules:
 * 
 * 
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3
 * sub-boxes of the grid.
 * 
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * 
 * A sudoku puzzle...
 * 
 * 
 * ...and its solution numbers marked in red.
 * 
 * Note:
 * 
 * 
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique
 * solution.
 * The given board size is always 9x9.
 * 
 * 思路
 * 9*9的空格中每个都有9种可能的填写方法，尝试每一种填写方法，在本格数字确定后再从左到右
 * 从上到下依次填写数字。如果到某一个格子，没有可能的填写数字了，就back到上一个格子，在
 * 这个格子尝试其他的可能的数字。当所有格子都填完的时候返回。
 * 
 * 出现的问题：
 * 1、判断=='.'的时候，逻辑没确定好!='.'的时候不能直接返回，而是应该往下一个格子继续回溯
 * 2、对board的修改没有持续到判断答案是否正确的地方。
 * java中数组作为形参，如果在函数里改的是数组的内容，修改会持续到函数结束，但如果
 * 修改的是形参指向的地址，那啥事儿的不会发生，因为作为形参传递过来的数组地址是个
 * 复制来的副本而已。
 * 最后选择当答案出来的时候，不修改标志。
 * 3、用三个二维数组分别记录每一行、每一列、每个3*3格子里1-9的出现情况
 * 
 * 看了答案后发现可以
 * 1、不使用标记数组
 * 2、使用带返回值的backtracking
 * 
 * 复杂度
 * 时间O（9^81）？
 * 边界条件
 * 
 * 编码
 * 
 * 测试用例
 * 
 * Tips:
 * 1、数组作为形参
 */

// @lc code=start
class Solution {
    boolean flag;

    public void solveSudoku(char[][] board) {
        if (board == null) {
            return;
        }

        backTracking(0, 0, board);
        return;

    }

    boolean isValid(int i, int j, char c, char[][] board) {
        int tmp1 = 3 * (i / 3);
        int tmp2 = 3 * (j / 3);
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == c) {
                return false;
            }
            if (board[k][j] == c) {
                return false;
            }
            if (board[tmp1 + k / 3][tmp2 + k % 3] == c) {
                return false;
            }
        }
        return true;
    }

    boolean backTracking(int i, int j, char[][] board) {
        if (j == board.length) {
            i++;
            j = 0;
        }
        if (i == board.length) {
            // 填完了
            return true;
        }
        if (board[i][j] != '.') {
            // 这里不能直接返回，否则会啥事儿也不做，而是应该移到下一个需要填值的地方
            return backTracking(i, j + 1, board);// 不需要填值
        }
        for (char num = '1'; num <= '9'; num++) {

            if (isValid(i, j, num, board)) {
                board[i][j] = num;
                if (backTracking(i, j + 1, board)) {
                    return true;
                }
                board[i][j] = '.';
            }
        }

        return false;
    }

    public void solveSudoku1(char[][] board) {
        if (board == null) {
            return;
        }
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] grid = new boolean[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int tmp1 = i / 3;
                    int tmp2 = j / 3;
                    int k = tmp1 * 3 + tmp2;
                    row[i][board[i][j] - '1'] = true;
                    col[j][board[i][j] - '1'] = true;
                    grid[k][board[i][j] - '1'] = true;
                }

            }
        }

        backTracking1(0, 0, board, row, col, grid);
        return;

    }

    void backTracking1(int i, int j, char[][] board, boolean[][] row, boolean[][] col, boolean[][] grid) {
        if (j == board.length) {
            i++;
            j = 0;
        }
        if (i == board.length) {
            // 填完了
            flag = true;
            return;
        }
        if (board[i][j] != '.') {
            // 这里不能直接返回，否则会啥事儿也不做，而是应该移到下一个需要填值的地方
            backTracking1(i, j + 1, board, row, col, grid);// 不需要填值
        } else {
            for (int num = 0; num < 9; num++) {
                int tmp1 = i / 3;
                int tmp2 = j / 3;
                int k = tmp1 * 3 + tmp2;
                if (!row[i][num] && !col[j][num] && !grid[k][num]) {
                    row[i][num] = true;
                    col[j][num] = true;
                    grid[k][num] = true;
                    board[i][j] = (char) (num + '1');
                    backTracking1(i, j + 1, board, row, col, grid);
                    if (flag) {

                    } else {

                        board[i][j] = '.';
                        row[i][num] = false;
                        col[j][num] = false;
                        grid[k][num] = false;
                    }
                }
            }
        }

    }
}
// @lc code=end
