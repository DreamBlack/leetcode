/*
 * @lc app=leetcode id=74 lang=cpp
 *
 * [74] Search a 2D Matrix
 *
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 *
 * algorithms
 * Medium (35.20%)
 * Likes:    1025
 * Dislikes: 119
 * Total Accepted:    249.8K
 * Total Submissions: 708.2K
 * Testcase Example:  '[[1,3,5,7],[10,11,16,20],[23,30,34,50]]\n3'
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the
 * previous row.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * matrix = [
 * ⁠ [1,   3,  5,  7],
 * ⁠ [10, 11, 16, 20],
 * ⁠ [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * matrix = [
 * ⁠ [1,   3,  5,  7],
 * ⁠ [10, 11, 16, 20],
 * ⁠ [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 * 题目大意：有序的二维数组的搜索
 * 解题思路：
 * 两次二分查找，先在第一列进行，从而确定要进一步查找的row，
 * 之后再该row再进行一次二分查找
 * 
 */
class Solution {
public:

    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        //不要忘了进行空数组的判断
        if(matrix.empty())return false;
        if(matrix[0].empty())return false;
        int m=matrix.size();
        int n=matrix[0].size();
        //加上这个判断可以显著提高速度
        if(matrix[0][0]>target||matrix[m-1][n-1]<target)return false;
        if(matrix[0][0]==target||matrix[m-1][n-1]==target)return true;
        int head=0,tail=m-1;
        while(head<=tail){
            int mid=(head+tail)/2;
            if(matrix[mid][0]==target){
                return true;
            }else if(matrix[mid][0]>target){
                tail=mid-1;
            }else{
                
                head=mid+1;
                
            }
        }
        int row=max(tail,0);//这里要做和0的比较，否则会导致越界
        tail=n-1;
        head=0;
        while(head<=tail){
            int mid=(head+tail)/2;
            if(matrix[row][mid]==target){
                return true;
            }else if(matrix[row][mid]>target){
                tail=mid-1;
            }else{
                
                    head=mid+1;
            }
        }
        return false;
    }
};

