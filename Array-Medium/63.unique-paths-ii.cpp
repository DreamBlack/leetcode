/*
 * @lc app=leetcode id=63 lang=cpp
 *
 * [63] Unique Paths II
 *
 * https://leetcode.com/problems/unique-paths-ii/description/
 *
 * algorithms
 * Medium (33.68%)
 * Likes:    1011
 * Dislikes: 169
 * Total Accepted:    225.5K
 * Total Submissions: 669.5K
 * Testcase Example:  '[[0,0,0],[0,1,0],[0,0,0]]'
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?
 * 
 * 
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * Note: m and n will be at most 100.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * 
 * 解题思路1：
 * 继续动态规划，和62一样。只不过要注意bstacleGrid[0][0]==1的情况
 * 以及，第一行出现一个障碍会导致后面所有路都不同，所有不能简单
 * 的用dp=bstacleGrid[0]的方法。
 * 解题思路2：
 * 可以不用vector来保存了，可以直接原地操作喽
 * 但是不用long的话数组会超出范围。
 * 
 * tips:
 * 注意对数组修改的时间，如果要根据当前值做一些判断性的工作
 * 一定要在当前值改变之前
 * 
 */
class Solution {
public:

    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        
        int m=obstacleGrid.size();
        int n=obstacleGrid[0].size(); 
         if(obstacleGrid[0][0]==1) return 0;
        vector<long long>dp(n);//用long long 否则溢出
        bool flag=false;
        for(int i=0;i<n;i++){
            dp[i]=(obstacleGrid[0][i]==0)?1:0;
            if(obstacleGrid[0][i]==1){
                flag=true;
            }
            //处理第一行，如果有一个为1，其它后面的都无法到达
            //而且只有第一行有这个性质
            if(flag){
                dp[i]=0;
            }
        }
        for(int i=1;i<m;i++){
            
            for(int j=0;j<n;j++){
                if(obstacleGrid[i][j]==1){
                    dp[j]=0;
                }else{
                    if(j-1>=0){
                        dp[j]+=dp[j-1];
                    }
                }
            }
        }
        return dp[n-1];
    }
};

