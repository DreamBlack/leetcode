
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

法一：
求出每个元素的平方后排序。时间O(NlogN),空间O(N)
法二：
直觉：正数平方的排序和原来相同，负数平方的排序和原来相反，但都是局部有序的。
解法：用双指针分别指向正数中最小的平方和负数中最小的平方
时间O(N),空间O(N)

出现的问题
1、处理多于情况时，两个while条件写漏掉了i=0和j=0的情况
2、寻找原数组中第一个非负数写错导致数组越界，主要有两处
A.for循环中n的取值范围应该去掉n=length-1
B.i,j的初值应分别设置为0,1，否则如果数组只有两个元素，i=j=0会只能访问到一个元素，在判断平方大小时失误，且越界（p值超过数组B的大小）
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