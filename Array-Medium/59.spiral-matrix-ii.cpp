/*
 * @lc app=leetcode id=59 lang=cpp
 *
 * [59] Spiral Matrix II
 *
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 *
 * algorithms
 * Medium (48.18%)
 * Likes:    572
 * Dislikes: 87
 * Total Accepted:    150.7K
 * Total Submissions: 310.5K
 * Testcase Example:  '3'
 *
 * Given a positive integer n, generate a square matrix filled with elements
 * from 1 to n^2 in spiral order.
 * 
 * Example:
 * 
 * 
 * Input: 3
 * Output:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 8, 9, 4 ],
 * ⁠[ 7, 6, 5 ]
 * ]
 * 
 * 
 */
class Solution {
public:
    vector<vector<int>> generateMatrix(int n) {
        int cnt=n*n,start=1;
        vector<vector<int>>ret(n,vector<int>(n));
        int sc=0,ec=n-1;
        int sr=0,er=n-1;
        while(start<=cnt){
            for(int i=sc;i<=ec;i++){
                ret[sr][i]=start++;
            }
            sr++;
            if(start>cnt)break;
            for(int i=sr;i<=er;i++){
                ret[i][ec]=start++;
            }
            ec--;
            if(start>cnt)break;
            for(int i=ec;i>=sc;i--){
                ret[er][i]=start++;
            }
            er--;
            if(start>cnt)break;
            for(int i=er;i>=sr;i--){
                ret[i][sc]=start++;
            }
            sc++;

        }
        return ret;
    }
};

