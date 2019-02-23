
/*
The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.
Given N, calculate F(N).



Example 1:

Input: 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
Example 2:

Input: 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.

Example 3:

Input: 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.


Note:

0 �� N �� 30.
����˼·
1��fib
�ݹ�
2���ռ任ʱ��
����һ����ΪN�����飬��0��N��¼ÿ���±��Ӧ��Fֵ
3��fib2
��2�Ļ����ϣ����ֲ�����Ҫ����0-N-1���е�Fֵ��Ҫ��F(N)ֻ��Ҫ����F(N-1)��F(N-2)
*/
#include<vector>
using namespace std;
int fib(int N) {
	if (N == 0)return 0;
	else if (N == 1) return 1;
	else return fib(N - 1) + fib(N - 2);
}
int fib2(int N) {
	if (N == 0)return 0;
	else if (N == 1) return 1;
	int pre1 = 0, pre2 = 1, tmp = 0;
	for (int i = 2; i <= N; i++) {
		tmp = pre1 + pre2;
		pre1 = pre2;
		pre2 = tmp;
	}
	return tmp;
}