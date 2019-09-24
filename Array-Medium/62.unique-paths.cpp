/*
 * @lc app=leetcode id=62 lang=cpp
 *
 * [62] Unique Paths
 *
 * https://leetcode.com/problems/unique-paths/description/
 *
 * algorithms
 * Medium (49.07%)
 * Likes:    1909
 * Dislikes: 136
 * Total Accepted:    333.9K
 * Total Submissions: 677.7K
 * Testcase Example:  '3\n2'
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * 
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 * 
 * Note: m and n will be at most 100.
 * 
 * Example 1:
 * 
 * 
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the
 * bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: m = 7, n = 3
 * Output: 28
 * 解题思路：
 * 1、遍历
 * 2、动态规划保存一个二维数组
 * 3、保存一个一维数组
 * 
 * tips:
 * 不要随便写for(;i<n;i++)省略初始化的这种形式
 * 尤其是在双重循环中，基本上就废了，还让人找不到原因
 */
class Solution {
public:
 int uniquePaths(int m, int n) {
     //观察发现可以只用一维数组
      if(m==1||n==1)
            return 1;
        vector<int>dp(m,1);
        int last=0;
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                if(j-1>=0){
                    dp[j]+=last;
                }
                last=dp[j];
            }
            last=0;
        }
        return dp[m-1];
 }
    int uniquePaths2(int m, int n) {
        if(m==1||n==1)
            return 1;
        vector<vector<int>>dp(n,vector<int>(m));
        int i=0,j=0;
        dp[i][j]=1;
        for(i=0;i<n;i++){
            for(j=0;j<m;j++){
                //开始忘了在for条件里写i=0,j=0导致，j无法再
                //从0开始计数
                if(i-1>=0){
                    dp[i][j]+=dp[i-1][j];
                }
                if(j-1>=0){
                    dp[i][j]+=dp[i][j-1];
                }
            }

        }
         
        return dp[n-1][m-1];
    }
};

