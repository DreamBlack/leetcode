/*
Problem:Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

Answer:
KEY:˫ָ��
1���ò����ʵ�Ԫ�ؽ��������棬����ǰ�벿�־���ż������벿�ֶ���������
2��Ҫע�����ֹͣ��ʱ��outofindex||ָ������
*/
#include<iostream>
#include<vector>
using namespace std;

vector<int> sortArrayByParity(vector<int>& A) {
	int i = 0,j=A.size()-1;
	while (i < A.size()&&i<j&&j>=0) {
		if (A[i] % 2 == 0) {
			i++;
		}
		else {
			int temp = A[i];
			A[i] = A[j];
			A[j] = temp;
			j--;
		}
	}
	return A;
}
