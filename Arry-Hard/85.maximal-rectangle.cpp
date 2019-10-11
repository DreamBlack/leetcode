/*
 * @lc app=leetcode id=85 lang=cpp
 *
 * [85] Maximal Rectangle
 *
 * https://leetcode.com/problems/maximal-rectangle/description/
 *
 * algorithms
 * Hard (34.47%)
 * Likes:    1779
 * Dislikes: 54
 * Total Accepted:    137.8K
 * Total Submissions: 396.4K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * ⁠ ["1","0","1","0","0"],
 * ⁠ ["1","0","1","1","1"],
 * ⁠ ["1","1","1","1","1"],
 * ⁠ ["1","0","0","1","0"]
 * ]
 * Output: 6
 * 
 * 解题思路1：
 * 暴力法O(N^2*N^2)，对于每个点都对其可以构成的最大矩形进行考察，因此最坏情况
 * 下式4次方的
 * 解题思路2：
 * 利用84题rectangle in histogram。把矩阵的每一行看成一个直方图，对于每一个元素
 * matrix[i][j]，如果为1，就要考虑它从上到下可能的高度，和从左到右可能的宽度。
 * 对于高度是从上一行累积而来；对于宽度，要考虑当前可达最左宽度和上一行可达最左
 * 宽度，取最大的那个；对于高度，同样要考虑当前可达最右宽度和上一行可达最右宽度
 * 取小的那个。则该点处最大矩形面积为宽度乘高度。
 * 为到达这个效果，要三个数组保存上一行每个点的高度，最左可达，最右可达（后两个
 * 存的是下标和84题一样）
 * 注意left是从左到右计算，right是从右到左计算
 */

// @lc code=start
class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        int maxarea=0;
        if(matrix.empty())return 0;
        int m=matrix.size();
        int n=matrix[0].size();
        vector<int>height(n,0);
        vector<int>left(n,0);
        vector<int>right(n,n);//初始值是最长度，之所以用n，这样算长度的时候就不用+1了
        for(int i=0;i<m;i++){
            int currleft=0,currright=n;
            
             for(int j=n-1;j>=0;j--){
                if(matrix[i][j]=='0'){
                    right[j]=n;
                    currright=j;//这里是j，不是j-1，理由同初始值为n
                }else{
                    right[j]=min(right[j],currright);
                }
            }
            for(int j=0;j<n;j++){
                if(matrix[i][j]=='0'){
                    height[j]=0;
                    left[j]=0;
                    currleft=j+1;//这里是j+1，因为如果当前为0，下一个元素的可能左值就要从右边开始
                }else{
                    height[j]++;
                    left[j]=max(left[j],currleft);
                }
                maxarea=max(maxarea,height[j]*(right[j]-left[j]));
            }
           
        }
        return maxarea;
    }
};
// @lc code=end

