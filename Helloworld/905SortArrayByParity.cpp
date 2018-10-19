/*
Problem:Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

Answer:
KEY:双指针
1、让不合适的元素交换到后面，这样前半部分就是偶数，后半部分都是奇数。
2、要注意的是停止的时候outofindex||指针相遇
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
