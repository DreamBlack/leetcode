/*
 * @lc app=leetcode id=79 lang=java
 *
 * [79] Word Search
 *
 * https://leetcode.com/problems/word-search/description/
 *
 * algorithms
 * Medium (32.30%)
 * Likes:    2210
 * Dislikes: 112
 * Total Accepted:    331.1K
 * Total Submissions: 1M
 * Testcase Example:  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"ABCCED"'
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once.
 * 
 * Example:
 * 
 * 
 * board =
 * [
 * ⁠ ['A','B','C','E'],
 * ⁠ ['S','F','C','S'],
 * ⁠ ['A','D','E','E']
 * ]
 * 
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * 
 * 1、思路解释，向面试官求证
 * 
 * 2、复杂度分析
 * O(N*N*M*M)，空间可以由O(M*N)优化为O(1)
 * 3、设计边界条件
 * word单词长度为1，board为1*n，1*1的情况
 * 4、编码
 * 5、运行测试用例
 * 
 * Tips:
 * 1、先判断在到下一个函数或者在函数中统一判断对时间和空间效率并没有太大影响
 * 所以还是选择在函数中判断吧，可以省去多余的代码
 * 2、写完之后，注意在时间以及空间两个角度去考虑进行优化
 * 空间上的优化角度：能否原地操作
 */

// @lc code=start
class Solution {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;

        if (m == 0 && n == 0) {
            return false;
        }
        char[] words = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtracking(0, i, j, words, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean backtracking(int index, int i, int j, char[] word, char[][] board) {
        if (index == word.length) {
            return true;
        }

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word[index] != board[i][j]) {
            return false;
        }
        char c = board[i][j];
        board[i][j] = '#';//或者直接board[i][j]^=256;
        boolean flag = backtracking(index + 1, i - 1, j, word, board) || backtracking(index + 1, i + 1, j, word, board)
                || backtracking(index + 1, i, j - 1, word, board) || backtracking(index + 1, i, j + 1, word, board);
        board[i][j] = c;//或者直接board[i][j]^256;

        return flag;
    }
}
// class Solution {
// int m;
// int n;
// boolean[][]visited;
// public boolean exist(char[][] board, String word) {
// if(word==null||word.length()==0){
// return true;
// }
// if(board==null){
// return false;
// }
// m=board.length;
// n=board[0].length;
// visited=new boolean[m][n];

// if(m==0&&n==0){
// return false;
// }

// for(int i=0;i<m;i++){
// for(int j=0;j<n;j++){
// visited[i][j]=true;
// if(word.charAt(0)==board[i][j]&&backtracking(i, j, word.substring(1),board)){
// return true;
// }
// visited[i][j]=false;
// }
// }
// return false;
// }
// boolean backtracking(int i,int j,String word,char[][]board){
// if(word.length()==0){
// return true;
// }
// boolean flag=false;

// //漏掉了word只有一个字母的情况
// // if(word.length()==1){
// // return true;
// // }
// //又忘了设置标记位
// if(i-1>=0&&!visited[i-1][j]&&word.charAt(0)==board[i-1][j]){
// visited[i-1][j]=true;
// flag=backtracking(i-1, j, word.substring(1), board);
// visited[i-1][j]=false;
// }
// if(!flag&&i+1<m&&!visited[i+1][j]&&word.charAt(0)==board[i+1][j]){
// visited[i+1][j]=true;
// flag=backtracking(i+1, j, word.substring(1), board);
// visited[i+1][j]=false;
// }
// if(!flag&&j-1>=0&&!visited[i][j-1]&&word.charAt(0)==board[i][j-1]){
// visited[i][j-1]=true;
// flag=backtracking(i, j-1, word.substring(1), board);
// visited[i][j-1]=false;
// }
// if(!flag&&j+1<n&&!visited[i][j+1]&&word.charAt(0)==board[i][j+1]){
// visited[i][j+1]=true;
// flag=backtracking(i, j+1, word.substring(1), board);
// visited[i][j+1]=false;
// }

// if(flag){
// return true;
// }else{
// return false;
// }
// }
// }
// @lc code=end
