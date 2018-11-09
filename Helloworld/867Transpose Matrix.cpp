/*
Problem:Given a matrix A, return the transpose of A.

The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.

Example 1:
Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]
Example 2:

Input: [[1,2,3],[4,5,6]]
Output: [[1,4],[2,5],[3,6]]
解法：
1、二维矩阵按固定大小初始化
vector<vector<int>> ret(n, vector<int>(m, 0));//直接初始化指定大小的二维vector,cde(10，1)初始化了10个值为1的元素
2、二维矩阵取大小
int m = A.size();
int n = A[0].size();
*/
#include<vector>
using namespace std;
vector<vector<int>> transpose(vector<vector<int>>& A) {
	int m = A.size();
	int n = A[0].size();
	vector<vector<int>> ret(n, vector<int>(m, 0));//直接初始化指定大小的二维vector,cde(10，1)初始化了10个值为1的元素

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			//这里搞了半天想着要控制下标范围不能越界，其实想好生成矩阵的每一个元素逐个用原矩阵对应元素生成，根本不会使下标越界的呀
				//而且，i=j的地方直接赋值也是完全没问题的呀。说到底还是自己代码写的太少了，而且没有理清思路，没有用程序的运行过程去想问题怎么解决
			ret[i][j] = A[j][i];

		}

	}
	return ret;
}