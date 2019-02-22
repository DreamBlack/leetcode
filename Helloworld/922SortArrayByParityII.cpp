/*
Problem:Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.



Example 1:

Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.


Note:

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000

���������
1��ordinary
��һ���µĴ�С��ͬ�����飬����ԭ���齫����Ԫ�ذ���ż�Էŵ���Ӧλ��
ʱ��O(N),�ռ�O(N)
2��sortArrayByParityII
��˫ָ�룬�ֱ�ָ��ͷβ��һ�����ֵ�A[i]��i��ż�Բ�ͬ��Ԫ�أ����������
Question:�п����ҵ�i,j��ż����ͬ����ʹswap֮��Ҳ���ܱ�֤����ĿҪ�������
Answer:�������������ʱ���ҵ�j��ߵ�һ����j��ż�Բ�ͬ��Ԫ�ؽ�����֮���ٽ�����һ��i,j����
ʱ��O(N),�ռ�O(1)
3��sortArrayByParityII2
����Ŀ��˼�����Խ�A��Ϊ�����к�ż�����������֣��ֱ�ΪA[1],A[3],������A[0],A[2],����
i,j�ֱ��¼ż/�������е�һ������������±꣬���н����������Ͳ��ÿ�����һ�ֽⷨ��Question���ֵ����⣬��ʱ�����
ʱ��O(N),�ռ�O(1)
*/
#include<vector>
using namespace std;
vector<int> sortArrayByParityII(vector<int>& A) {
	int i = 0, j = A.size() - 1;
	while (i < j) {
		while (i < j&&A[i] % 2 == i % 2)i++;
		while (i < j&&A[j] % 2 == j % 2)j--;
		if (i == j)break;
		if (i % 2 == j % 2) {//ָ����ָ��ż����ͬ�����ҵ���j�������ż���෴��Ԫ�أ����н���
			int jeo = j - 1;
			while (jeo > i&&A[jeo] % 2 == A[j] % 2)jeo--;//�ҵ�j��ߵ�һ����j��ż�Բ�ͬ�Ľ��н���
			swap(A[j], A[jeo]);
			j--;
		}
		else {
			swap(A[i], A[j]);
			i++;
			j--;
		}

	}
	return A;

}
vector<int> sortArrayByParityII2(vector<int>& A) {
	int i = 0, j = 1, length = A.size();
	do {
		while (i < length&&A[i] % 2 == 0)i = i + 2;
		while (j < length&&A[j] % 2 != 0)j = j + 2;
		if (i > length || j > length)break;
		swap(A[i], A[j]);
		i = i + 2;
		j = j + 2;


	} while (i < length&&j < length);
	return A;

}
vector<int> ordinary(vector<int>& A) {
	vector<int> result(A.size());
	int m = 0, n = 1;
	for (int i = 0; i < A.size(); i++) {
		if (A[i] % 2 == 0) {
			result[m] = A[i];
			m = m + 2;
		}
		else {
			result[n] = A[i];
			n = n + 2;
		}
	}
	return result;

}