
/*
Problem:Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, 
also in sorted non-decreasing order.
Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Note:
1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.

��һ��
���ÿ��Ԫ�ص�ƽ��������ʱ��O(NlogN),�ռ�O(N)
������
ֱ��������ƽ���������ԭ����ͬ������ƽ���������ԭ���෴�������Ǿֲ�����ġ�
�ⷨ����˫ָ��ֱ�ָ����������С��ƽ���͸�������С��ƽ��
ʱ��O(N),�ռ�O(N)

���ֵ�����
1������������ʱ������while����д©����i=0��j=0�����
2��Ѱ��ԭ�����е�һ���Ǹ���д��������Խ�磬��Ҫ������
A.forѭ����n��ȡֵ��ΧӦ��ȥ��n=length-1
B.i,j�ĳ�ֵӦ�ֱ�����Ϊ0,1�������������ֻ������Ԫ�أ�i=j=0��ֻ�ܷ��ʵ�һ��Ԫ�أ����ж�ƽ����Сʱʧ����Խ�磨pֵ��������B�Ĵ�С��
*/
#include<vector>
using namespace std;
vector<int> fun(vector<int>A) {
	vector<int>B(A.size());
	int i = 0, j = 1,p=0;
	if (A[0] < 0) {
		for (int n = 0; n < A.size()-1; n++) {
			if (A[n] < 0 && A[n + 1] >= 0) {
				i = n;
				j = n + 1;
				break;
			}
		}
	}
	else {
		i = -1; j = 0;
	}
	while (i >= 0&&j < A.size()) {
		if (abs(A[i]) < A[j]) {
			B[p++] = A[i] * A[i];
			i--;
		}
		else {
			B[p++] = A[j] * A[j];
			j++;
		}
	}
	while (i >= 0) {
		B[p++] = A[i]* A[i];
		i--;
	}
	while (j < A.size()&&j>=0) {
		B[p++] = A[j]* A[j];
		j++;
	}
	return B;
}
int main() {
	vector<int>A = {-4,-1};
	vector<int>B=fun(A);
	for (int i = 0; i < B.size(); i++) {
		printf("%d,",B[i]);
	}
	return 0;
}