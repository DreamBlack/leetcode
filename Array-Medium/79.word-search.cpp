/*
 * @lc app=leetcode id=79 lang=cpp
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
 * 题目大意：在一个二维char数组中判断是否存在给定的字符串
 * 解题思路：
 * dfs寻找。注意要用一个标记数组来记录走过的路径，防止一个字符
 * 被多次使用
 * 
 * 
 * tips:
 * dfs这种东西，不要随便return，因为return 表示到这儿就会退出
 * 函数了，很可能漏掉一些情况，自己还不容易发现。
 */
class Solution {
public:

    bool exist(vector<vector<char>>& board, string word) {
        int m=board.size();
        int n=board[0].size();
        
        bool flag=false;
       
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==word[0]){
                    
        vector<vector<bool>> tag(m, vector<bool>(n, true));
                     //要用标记，因为每个元素只能用一次
                     
                    flag=dfs(1,word,board,i,j,tag);
                    if(flag)
                        return flag;
                }
            }
        }
        return flag;
    }
     bool dfs(int cnt,string word,vector<vector<char>>& board,int i,int j,vector<vector<bool>>& tag){
        
         if(board[i][j]!=word[cnt-1])
            return false;
         tag[i][j]=false;
         if(cnt==word.size()){
            return true;
        }
        vector<vector<int>>step={{-1,0},{1,0},{0,-1},{0,1}};
        for(int p=0;p<step.size();p++){
            int row=i+step[p][0];
            int col=j+step[p][1];
            if(row>=0&&row<board.size()&&
            col>=0&&col<board[0].size()&&tag[row][col]){
                bool flag=dfs(cnt+1,word,board,row,col,tag);
                if(flag)
                    return flag;
            }
        }
        tag[i][j]=true;
        return false;
     }
    bool dfs2(int cnt,string word,vector<vector<char>>& board,int i,int j,vector<vector<bool>>& tag){
        if(cnt==word.size()&&board[i][j]==word[cnt-1]){
            return true;
        }
        if(i-1>=0&&tag[i-1][j]&&board[i-1][j]==word[cnt]){
            tag[i-1][j]=false;
            //这些if里面不能直接return dfs()，因为这样如果返回的是
            //true没问题，但如果返回的是false，那么下面其他可能的结果
            //并不会被访问到
            if( dfs(cnt+1,word,board,i-1,j,tag))
                return true;
        }
        //下面这里写的太乱了，完全可以用一个方向数组加一个循环来写
        if(i+1<board.size()&&tag[i+1][j]&&board[i+1][j]==word[cnt]){
            tag[i+1][j]=false;
            if( dfs(cnt+1,word,board,i+1,j,tag))
                return true;
        }
        if(j-1>=0&&tag[i][j-1]&&board[i][j-1]==word[cnt]){
            tag[i][j-1]=false;
            if( dfs(cnt+1,word,board,i,j-1,tag))
                return true;
        }
        if(j+1<board[0].size()&&tag[i][j+1]&&board[i][j+1]==word[cnt]){
            tag[i][j+1]=false;
            if( dfs(cnt+1,word,board,i,j+1,tag))
                return true;
        }
        //居然把tag写成了board。。。半天没找出错
        tag[i][j]=true;//此路不通的时候要把标记清空，防止干扰其他路径
        return false;
    }
};

