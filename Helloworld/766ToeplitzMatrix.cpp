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
1����ʼ�����ӵڶ������ұ�Ԫ�ؿ�ʼ�������Ͻ�Ԫ�ؽ��бȽϣ���ͬ�򷵻�false����ͬ�������һ���ж���ɣ��ٵ���һ�н����ж�
���У���һ��Ԫ���ǲ���Ҫ��Ԫ�ضԱȵ�
2��������洢�ڴ����ϵ�ÿ��ֻ�ܶ�һ��ʱ���Ϳ���ʹ�÷���һ
3��������ܴ�ÿ��ֻ�ܶ�һ���в��ֶ�����ʱ�򣬿����Ƚ���һ�ж��ִζ���
4������������ǿ�ж�̬�滮
ǿ�ж�̬�滮�ⷨ�������� m * n �� boolean ���顣
ֻ��һ�л���ֻ��һ�е�ʱ�����϶�Ϊ Toeplitz Matrix��
boolean ������� row = i, column = j �� matrix �Ƿ�Ϊ true��
������ֻҪ�� false ������ֱ�ӷ��ء�
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