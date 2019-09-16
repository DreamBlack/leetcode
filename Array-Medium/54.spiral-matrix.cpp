/*
 * @lc app=leetcode id=54 lang=cpp
 *
 * [54] Spiral Matrix
 *
 * https://leetcode.com/problems/spiral-matrix/description/
 *
 * algorithms
 * Medium (31.35%)
 * Likes:    1351
 * Dislikes: 453
 * Total Accepted:    265.2K
 * Total Submissions: 844.9K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 4, 5, 6 ],
 * ⁠[ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * 
 * 
 * Example 2:
 * 
 * Input:
 * [
 * ⁠ [1, 2, 3, 4],
 * ⁠ [5, 6, 7, 8],
 * ⁠ [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 */
class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int>ret;
        if(matrix.empty())
            return ret;
        int em=matrix.size()-1,en=matrix[0].size()-1;
        int sm=0,sn=0;
        //这里一开始写成了em*em，导致，还没有把全部元素写到ret
        //就退出了找了了半天才看到这个错，苍天。
        int count=(em+1)*(en+1);
        
        while(ret.empty()||(!(ret.empty())&&ret.size()<count)){
            
            for(int j=sn;j<=en;j++){
                ret.push_back(matrix[sm][j]);
                
            }
            //注意中间情况，不用四个方向就退出的时候
            if(ret.size()==count)
                break;
            sm++;

            for(int i=sm;i<=em;i++){
                ret.push_back(matrix[i][en]);
            }
            if(ret.size()==count)
                break;
            en--;

            for(int j=en;j>=sn;j--){
                ret.push_back(matrix[em][j]);
            }
            if(ret.size()==count)
                break;
            em--;

            for(int i=em;i>=sm;i--){
                ret.push_back(matrix[i][sn]);
            }
            sn++;
        }
        //printf("sn=%d,en=%d,sm=%d,em=%d\n",sm,en,sm,em);
        return ret;
    }
};

