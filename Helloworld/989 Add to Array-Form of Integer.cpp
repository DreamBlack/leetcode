/*
For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].

Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.



Example 1:

Input: A = [1,2,0,0], K = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234
Example 2:

Input: A = [2,7,4], K = 181
Output: [4,5,5]
Explanation: 274 + 181 = 455
Example 3:

Input: A = [2,1,5], K = 806
Output: [1,0,2,1]
Explanation: 215 + 806 = 1021
Example 4:

Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
Output: [1,0,0,0,0,0,0,0,0,0,0]
Explanation: 9999999999 + 1 = 10000000000


Note：

1 <= A.length <= 10000
0 <= A[i] <= 9
0 <= K <= 10000
If A.length > 1, then A[0] != 0
解题思路：
*/#include<vector>
#include<algorithm>
using namespace std;
vector<int> addAB(vector<int>A, vector<int>B) {
	int lengthA = A.size(), lengthB = B.size();
	if (lengthA < lengthB)return addAB(B, A);
	int inc = 0, rec = 0, i = 0;
	for (i = 0; i < lengthB; i++) {
		rec = B[i] + A[i] + inc;
		if (rec >= 10) {
			inc = 1;
			rec = rec % 10;
		}
		else {
			inc = 0;
		}
		A[i] = rec;
	}
	if (inc > 0) {
		for (int i = B.size(); i < A.size(); i++) {
			rec = A[i] + inc;
			if (rec >= 10) {
				inc = 1;
				rec = rec % 10;
			}
			else {
				inc = 0;
			}
			A[i] = rec;
		}
		if (inc > 0) {
			A.push_back(1);
		}
	}
	reverse(A.begin(), A.end());//翻转数组
	return A;
}
vector<int> addToArrayForm(vector<int>& A, int K) {
	vector<int>B;
	while (K >= 10) {
		int n = K % 10;
		B.push_back(n);
		K = K / 10;
	} 
	B.push_back(K);
	reverse(A.begin(), A.end());//翻转数组
	int lengthA=A.size(), lengthB = B.size();
		
	return addAB(A, B);
}
vector<int> addToArrayForm(vector<int>& A, int K) {
	reverse(A.begin(), A.end());//翻转数组
	for (int i = 0; i < A.size(); i++) {
		int ret = K + A[i];
		A[i] = ret % 10;
		K = ret / 10;
	}
	if (K != 0) {
		do{
			int n = K % 10;
			A.push_back(n);
			K = K / 10;
		}while (K >= 10)
		A.push_back(K);
	}
	reverse(A.begin(), A.end());//翻转数组
	return A;
}