/*
Problem:
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
Now given an M x N matrix, return True if and only if the matrix is Toeplitz.

Input:
matrix = [
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
]
Output: True
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.

Note:

matrix will be a 2D array of integers.
matrix will have a number of rows and columns in range [1, 20].
matrix[i][j] will be integers in range [0, 99].

Follow up:

What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
What if the matrix is so large that you can only load up a partial row into the memory at once?

Solution:
1、初始方法从第二行最右边元素开始与其左上角元素进行比较，不同则返回false，相同则继续；一行判断完成，再到下一行进行判断
其中，第一列元素是不需要和元素对比的
2、当矩阵存储在磁盘上的每次只能读一行时，就可以使用方法一
3、当矩阵很大每次只能读一行中部分东西的时候，可以先将第一行都分次读完
4、网友做法：强行动态规划
强行动态规划解法……另开了 m * n 的 boolean 数组。
只有一行或者只有一列的时候结果肯定为 Toeplitz Matrix。
boolean 数组代表 row = i, column = j 的 matrix 是否为 true。
过程中只要有 false 产生就直接返回。
*/
#include<vector>
using namespace std;
bool isToeplitzMatrix(vector<vector<int>>& matrix) {
	int m = matrix.size();
	int n = matrix[0].size();
	for (int i = 1; i < m; i++) {
		for (int j = n - 1; j >= 1; j--) {
			if (matrix[i][j] != matrix[i - 1][j - 1])
				return false;
		}

	}
	return true;
}