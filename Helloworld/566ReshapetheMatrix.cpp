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

���˼·��
1��ʹ�ö���
�ȱ���ԭ���󣬰�������У�֮�󽫶�����Ԫ�ذ���������¾�����
2��matrixReshape
��1�����ϣ���ʹ�ö���Ķ��пռ䡣�ڱ���ԭ�����ʱ������������p,q��¼Ӧ�����뵽�¾�����
���±ꡣ��q����cʱ��ת����һ��
3��matrixReshape2
��2�Ļ����ϣ�ȥ��p,q��p,q�ĸ��£����ݶ�ά������������ʱ��һά����洢�ģ�����Ԫ����ԭ�����е�λ�ã�
�������Ԫ����row-traversing order �еڼ���Ԫ��count���ٸ������countֱ�Ӽ���Ԫ�����¾����е��±�
*/
#include<vector>
using namespace std;
vector<vector<int>> matrixReshape(vector<vector<int>>& nums, int r, int c) {
	int r0 = nums.size();
	int c0 = nums[0].size();
	if (r0*c0 != r * c)return nums;
	vector< vector<int> > result(r, vector<int>(c));//���ﲻ��new�����˰����    

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
	vector< vector<int> > result(r, vector<int>(c));//���ﲻ��new�����˰����    

	int count = 0;
	for (int i = 0; i < r0; i++) {
		for (int j = 0; j < c0; j++) {
			count = c0 * i + j;
			result[count / c][count%c] = nums[i][j];
		}
	}
	return result;
}