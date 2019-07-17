/*
 * @lc app=leetcode id=1035 lang=cpp
 *
 * [1035] Uncrossed Lines
 *
 * https://leetcode.com/problems/uncrossed-lines/description/
 *
 * algorithms
 * Medium (51.20%)
 * Likes:    187
 * Dislikes: 5
 * Total Accepted:    6K
 * Total Submissions: 11.6K
 * Testcase Example:  '[1,4,2]\n[1,2,4]'
 *
 * We write the integers of A and B (in the order they are given) on two
 * separate horizontal lines.
 * 
 * Now, we may draw connecting lines: a straight line connecting two numbers
 * A[i] and B[j] such that:
 * 
 * 
 * A[i] == B[j];
 * The line we draw does not intersect any other connecting (non-horizontal)
 * line.
 * 
 * 
 * Note that a connecting lines cannot intersect even at the endpoints: each
 * number can only belong to one connecting line.
 * 
 * Return the maximum number of connecting lines we can draw in this way.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: A = [1,4,2], B = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4
 * will intersect the line from A[2]=2 to B[1]=2.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
 * Output: 3
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
 * Output: 2
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 500
 * 1 <= B.length <= 500
 * 1 <= A[i], B[i] <= 2000
 * 
 * 
 */
class Solution
{
public:
    int maxUncrossedLines(vector<int> &A, vector<int> &B)
    {
        /*
        最长公共子序列，
        1、记住2个特征，
        1.1对于设A=“a0，a1，…，am”，
        B=“b0，b1，…，bn”，
        且Z=“z0，z1，…，zk”为它们的最长公共子序列。
        如果am=bn，则zk=am=bn，且z0,……,zk-1为
        a0，a1，…，a(m-1)”和“b0，b1，…，b(n-1)”的一个最长公共子序列
        1.2 如果am!=bn，则z为“a0，a1，…，a(m-1)”和“b0，b1，…，bn”的一个最长公共子序列
        和a0，a1，…，am”和“b0，b1，…，b(n-1)”的最长公共子序列中较大的一个
        2、记住递推公式
        假设我们用c[i,j]表示Xi 和 Yj 的LCS的长度（直接保存最长公共子序列的中间
        结果不现实，需要先借助LCS的长度）。其中X = {x1 ... xm}，
        Y ={y1...yn}，Xi = {x1 ... xi}，Yj={y1... yj}，
        则当i=0或者j=0时,c[i,j]=0；
        当xi=yj时，c[i,j]=c[i-1,j-1]+1;
        当xi!=yj时，c[i,j]=max(c[i-1,j],c[i,j-1])
         */
        int n = A.size(), m = B.size();
        vector<vector<int>> grid(n+1,vector<int>(m+1,0));
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(A[i-1]==B[j-1]){//这里记得-1
                //因为grid在n,m基础上加了1
                //这个错误找了好久
                    grid[i][j]=grid[i-1][j-1]+1;
                }else{
                    grid[i][j]=max(grid[i-1][j],grid[i][j-1]);
                }
                
            }
            
        }
       
        return grid[n][m];
    }
    
};
