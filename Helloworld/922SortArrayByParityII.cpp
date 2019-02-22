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

解决方案：
1、ordinary
开一个新的大小相同的数组，遍历原数组将各个元素按奇偶性放到相应位置
时间O(N),空间O(N)
2、sortArrayByParityII
用双指针，分别指向头尾第一个发现的A[i]和i奇偶性不同的元素，交换后继续
Question:有可能找的i,j奇偶性相同，即使swap之后，也不能保证按题目要求的排列
Answer:当出现这种情况时，找到j左边第一个和j奇偶性不同的元素交换，之后再进行下一次i,j更新
时间O(N),空间O(1)
3、sortArrayByParityII2
按题目意思，可以将A分为奇数列和偶数列两个部分，分别为A[1],A[3],……和A[0],A[2],……
i,j分别记录偶/奇数列中第一个不符题意的下标，进行交换。这样就不用考虑上一种解法中Question出现的问题，且时间更快
时间O(N),空间O(1)
*/
#include<vector>
using namespace std;
vector<int> sortArrayByParityII(vector<int>& A) {
	int i = 0, j = A.size() - 1;
	while (i < j) {
		while (i < j&&A[i] % 2 == i % 2)i++;
		while (i < j&&A[j] % 2 == j % 2)j--;
		if (i == j)break;
		if (i % 2 == j % 2) {//指针所指奇偶性相同，需找到离j最近的奇偶性相反的元素，进行交换
			int jeo = j - 1;
			while (jeo > i&&A[jeo] % 2 == A[j] % 2)jeo--;//找到j左边第一个和j奇偶性不同的进行交换
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