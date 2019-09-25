/*
 * @lc app=leetcode id=64 lang=cpp
 *
 * [64] Minimum Path Sum
 *
 * https://leetcode.com/problems/minimum-path-sum/description/
 *
 * algorithms
 * Medium (48.52%)
 * Likes:    1649
 * Dislikes: 43
 * Total Accepted:    263.3K
 * Total Submissions: 540.2K
 * Testcase Example:  '[[1,3,1],[1,5,1],[4,2,1]]'
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * [1,3,1],
 * ⁠ [1,5,1],
 * ⁠ [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * 解题思路：
 * 和，62、63类似的题，就是典型的二维的动态规划
 * 
 */
class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        if(grid.empty())return 0;
        int m=grid.size();
        int n=grid[0].size();
        for(int i=1;i<n;i++){
            grid[0][i]+=grid[0][i-1];
        }
        for(int i=1;i<m;i++){
            for(int j=0;j<n;j++){
                if(j==0)
                    grid[i][j]+=grid[i-1][j];
                else//这里一定要写else
                    grid[i][j]+=min(grid[i-1][j],grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }
};

