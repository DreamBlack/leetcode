/*
In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.

The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

Example 1:
Input:
nums =
[[1,2],
 [3,4]]
r = 1, c = 4
Output:
[[1,2,3,4]]
Explanation:
The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
Example 2:
Input:
nums =
[[1,2],
 [3,4]]
r = 2, c = 4
Output:
[[1,2],
 [3,4]]
Explanation:
There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
Note:
The height and width of the given matrix is in range [1, 100].
The given r and c are all positive.

解决思路：
1、使用队列
先遍历原矩阵，按序入队列，之后将队列中元素按序输出到新矩阵中
2、matrixReshape
在1基础上，不使用额外的队列空间。在遍历原矩阵的时候，用两个变量p,q记录应该输入到新矩阵中
的下标。当q等于c时，转入下一行
3、matrixReshape2
在2的基础上，去掉p,q即p,q的更新，根据二维矩阵在物理上时按一维矩阵存储的，根据元素在原矩阵中的位置，
计算出该元素是row-traversing order 中第几个元素count，再根据这个count直接计算元素在新矩阵中的下标
*/
#include<vector>
using namespace std;
vector<vector<int>> matrixReshape(vector<vector<int>>& nums, int r, int c) {
	int r0 = nums.size();
	int c0 = nums[0].size();
	if (r0*c0 != r * c)return nums;
	vector< vector<int> > result(r, vector<int>(c));//这里不用new，找了半天错    

	int p = 0, q = 0;
	for (int i = 0; i < r0; i++) {
		for (int j = 0; j < c0; j++) {
			result[p][q] = nums[i][j];
			q++;
			if (q == c) {
				q = 0;
				p++;
			}
		}
	}
	return result;
}
vector<vector<int>> matrixReshape2(vector<vector<int>>& nums, int r, int c) {
	int r0 = nums.size();
	int c0 = nums[0].size();
	if (r0*c0 != r * c)return nums;
	vector< vector<int> > result(r, vector<int>(c));//这里不用new，找了半天错    

	int count = 0;
	for (int i = 0; i < r0; i++) {
		for (int j = 0; j < c0; j++) {
			count = c0 * i + j;
			result[count / c][count%c] = nums[i][j];
		}
	}
	return result;
}