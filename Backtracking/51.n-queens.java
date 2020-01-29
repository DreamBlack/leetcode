import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 *
 * https://leetcode.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (41.26%)
 * Likes:    1397
 * Dislikes: 60
 * Total Accepted:    175.4K
 * Total Submissions: 405K
 * Testcase Example:  '4'
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * 
 * 
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.
 * 
 * Example:
 * 
 * 
 * Input: 4
 * Output: [
 * ⁠[".Q..",  // Solution 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 * 
 * ⁠["..Q.",  // Solution 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as
 * shown above.
 * 
 * 解题思路
 * 1、暴力搜索，但是写出来的结果有重复的。不知道怎么去重
 * 第一次的暴力搜索，复杂度为O((N*N)^N)，每次在整个棋盘中选择，复杂度太高
 * 后来想到，既然要摆成八皇后，那么每一行肯定有且只有一个棋子。也就是说
 * 搜索树有N层，每层代表棋盘中的一行。每一行的每个树节点可以有N个子节点，因为
 * 可以在这一行的N个地方中选择下子的点。
 * 复杂度就变成了O(N+N^2+...+N^N)
 * 这样还可以做到去重，因为搜索树上从根到页结点的路径肯定都是不一样的。
 * 但是还是在去重上面犯了错：每个会多加n次
 * 
 * 
 * 看了答案之后，对于我写的代码有两个改进的方向
 * 一是，isvalid，由于是从上往下的，可以只判断上半部分，不用判断整个表格
 * 二是，可以利用空间记录n列,2*n的\对角线，2*n的/对角线
 * 观察到同一个对角线上i-j的值是一样的，而一个对角线、一行、一列只能有
 * 一个皇后。这种方法让速度提高了很多。以空间换时间换的值。
 * 复杂度
 * 1、时间O(N*(N^N)
 * 2、空间O(保存的)
 * 边界
 * 编码
 * 用例
 * Tips:
 * 1、数组访问要用.get(i)而不是[i]。同样的设置的使用要用set(i)而不是[i]
 * 2、String没法直接改变某个下标对应的字符，要StringBuilder或者tochararray后再改
 * 3、char数组转字符串，要用String.valueof(),而不是tostring
 */

// @lc code=start
class Solution {
    public List<List<String>> solveNQueens(int n) {
        //用空间记录信息的版本
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }

        
        backTracking(n,0, new ArrayList<String>(), result,new boolean[n],new boolean[2*n],new boolean[2*n]);

        return result;
    }

    void backTracking(int n,int x, List<String> board, List<List<String>> result,boolean[]cols,boolean[]d1,boolean[]d2) {
        //注意这里board是[]为空的，所以下面都不能使用board.size(),要把n当作参数传进来
        if (x == n) {
            result.add(new ArrayList<String>(board));
        } else {
            for (int i = 0; i < n; i++) {
                int id1=n+i-x;
                int id2=i+x;
                if (!cols[i]&&!d1[id1]&&!d2[id2]) {
                    cols[i]=true;
                    d1[id1]=true;
                    d2[id2]=true;
                    char[] tmp = new char[n];
                    Arrays.fill(tmp, '.');
                    tmp[i] = 'Q';
                    board.add(new String(tmp));
                    backTracking(n,x + 1, board, result,cols,d1,d2);
                    cols[i]=false;
                    d1[id1]=false;
                     d2[id2]=false;
                    board.remove(board.size()-1);
                }
            }

        }
    }
    public List<List<String>> solveNQueens1(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }

        String tmp = "";
        List<String> board = new ArrayList<String>();
        for (int p = 0; p < n; p++) {
            tmp += ".";
        }
        for (int p = 0; p < n; p++) {
            board.add(new String(tmp));
        }

        backTracking1(0, board, result);

        return result;
    }

    void backTracking1(int x, List<String> board, List<List<String>> result) {
        if (x == board.size()) {
            result.add(new ArrayList<String>(board));
        } else {
            for (int i = 0; i < board.size(); i++) {
                if (isValid(x, i, board)) {
                    char[] tmp = board.get(x).toCharArray();
                    tmp[i] = 'Q';
                    board.set(x, String.valueOf(tmp));
                    backTracking1(x + 1, board, result);
                    tmp[i] = '.';
                    board.set(x, String.valueOf(tmp));
                }
            }

        }
    }

    boolean isValid(int x, int y, List<String> board) {
        // isvalid的更简洁的写法
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.get(i).charAt(j) == 'Q' && (j == y || i - j == x - y || j + i == y + x)) {
                    /*
                     * j==y,同一列 i-j==x-y，\对角线 j+i==y+x,/对角线
                     */
                    return false;
                }
            }
        }
        return true;
    }

    boolean isValid1(int x, int y, List<String> board) {
        // 横着的
        for (int i = 0; i < board.size(); i++) {
            if (board.get(x).charAt(i) == 'Q' && i != y) {
                return false;
            }
        }
        // 竖着的
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).charAt(y) == 'Q' && i != x) {
                return false;
            }
        }
        // 斜着1
        for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
            if (board.get(x - i).charAt(y - i) == 'Q') {
                return false;
            }
        }
        for (int i = 1; x + i < board.size() && y + i < board.size(); i++) {
            if (board.get(x + i).charAt(y + i) == 'Q') {
                return false;
            }
        }
        // 斜着2
        for (int i = 1; x + i < board.size() && y - i >= 0; i++) {
            if (board.get(x + i).charAt(y - i) == 'Q') {
                return false;
            }
        }
        for (int i = 1; x - i >= 0 && y + i < board.size(); i++) {
            if (board.get(x - i).charAt(y + i) == 'Q') {
                return false;
            }
        } /*
           * System.out.println("isValid x="+x+",y="+y+",board[x]=");
           * System.out.println(board);
           */

        return true;
    }
}
// @lc code=end
