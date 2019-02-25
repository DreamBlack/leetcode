/*
Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.

If it is impossible to form any triangle of non-zero area, return 0.



Example 1:

Input: [2,1,2]
Output: 5
Example 2:

Input: [1,2,1]
Output: 0
Example 3:

Input: [3,2,3,4]
Output: 10
Example 4:

Input: [3,6,2,3]
Output: 8


Note:

3 <= A.length <= 10000
1 <= A[i] <= 10^6
����˼·��
�������������򣬴����һ��Ԫ�ؿ�ʼ��Ѱ���ܹ��������ε�������������Ԫ�ء�
��������������Ԫ���򷵻��ܳ������򷵻�0
����������ڣ���������������ģ����Ԫ��i�������Ԫ�ز���ʹ�������߹��������Σ���
i��ߵ�����Ԫ��Ҫ��С���������ܹ��ɡ���ˣ�ֻ�迴i�������Ԫ�ؼ���
*/
#include<vector>
#include<algorithm>
using namespace std;
int largestPerimeter(vector<int>& A) {
	int result = 0;
	sort(A.begin(), A.end());
	for (int i = A.size() - 1; i >= 2; i--) {
		if (A[i] < A[i - 1] + A[i - 2])
		{
			return A[i] + A[i - 1] + A[i - 2];
		}
	}
	return result;
}