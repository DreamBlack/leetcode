/*
Problem:Given a matrix A, return the transpose of A.

The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.

Example 1:
Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]
Example 2:

Input: [[1,2,3],[4,5,6]]
Output: [[1,4],[2,5],[3,6]]
�ⷨ��
1����ά���󰴹̶���С��ʼ��
vector<vector<int>> ret(n, vector<int>(m, 0));//ֱ�ӳ�ʼ��ָ����С�Ķ�άvector,cde(10��1)��ʼ����10��ֵΪ1��Ԫ��
2����ά����ȡ��С
int m = A.size();
int n = A[0].size();
*/
#include<vector>
using namespace std;
vector<vector<int>> transpose(vector<vector<int>>& A) {
	int m = A.size();
	int n = A[0].size();
	vector<vector<int>> ret(n, vector<int>(m, 0));//ֱ�ӳ�ʼ��ָ����С�Ķ�άvector,cde(10��1)��ʼ����10��ֵΪ1��Ԫ��

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			//������˰�������Ҫ�����±귶Χ����Խ�磬��ʵ������ɾ����ÿһ��Ԫ�������ԭ�����ӦԪ�����ɣ���������ʹ�±�Խ���ѽ
				//���ң�i=j�ĵط�ֱ�Ӹ�ֵҲ����ȫû�����ѽ��˵���׻����Լ�����д��̫���ˣ�����û������˼·��û���ó�������й���ȥ��������ô���
			ret[i][j] = A[j][i];

		}

	}
	return ret;
}