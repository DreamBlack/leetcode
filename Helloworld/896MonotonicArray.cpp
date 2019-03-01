/*
An array is monotonic if it is either monotone increasing or monotone decreasing.

An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

Return true if and only if the given array A is monotonic.



Example 1:

Input: [1,2,2,3]
Output: true
Example 2:

Input: [6,5,4,4]
Output: true
Example 3:

Input: [1,3,2]
Output: false
Example 4:

Input: [1,2,4,5]
Output: true
Example 5:

Input: [1,1,1]
Output: true


Note:

1 <= A.length <= 50000
-100000 <= A[i] <= 100000
解题思路
1、isMonotonic
常规想法，遍历一遍数组，第一次发现邻近元素大小不同时记录flag=1/-1(增/减)，之后比较邻近元素的同时
看其增减情况与记录flag是否相同
2、isMonotonic2
如果数组单调增就不会有A[i]>A[i-1]的情况，数组单调减不会有A[i]<A[i-1]的情况。初始记录decreasing=increasing=true
出现上述情况反置为false。de/in其中有一个为true则为true，都为false即为false（假设是单调增的确有特例，假设是单调减的也有特例
所以即不单调增也不单调减）
*/
#include<vector>
using namespace std;
bool isMonotonic(vector<int>& A) {

	int flag = 0;

	for (int i = 1; i < A.size(); i++) {
		if (flag == 0) {
			if (A[i] > A[i - 1]) {
				flag = 1;//increasing
			}
			else if (A[i] < A[i - 1]) {
				flag = -1;
			}
		}
		else {
			if (A[i] > A[i - 1] && flag == -1)return false;
			if (A[i] < A[i - 1] && flag == 1)return false;
		}
	}
	return true;
}
bool isMonotonic2(vector<int>& A) {

	bool increasing = true, decreasing = true;

	for (int i = 1; i < A.size(); i++) {

		if (A[i] > A[i - 1])decreasing = false;
		if (A[i] < A[i - 1])increasing = false;

	}
	return increasing || decreasing;
}