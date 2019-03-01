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
����˼·
1��isMonotonic
�����뷨������һ�����飬��һ�η����ڽ�Ԫ�ش�С��ͬʱ��¼flag=1/-1(��/��)��֮��Ƚ��ڽ�Ԫ�ص�ͬʱ
��������������¼flag�Ƿ���ͬ
2��isMonotonic2
������鵥�����Ͳ�����A[i]>A[i-1]����������鵥����������A[i]<A[i-1]���������ʼ��¼decreasing=increasing=true
���������������Ϊfalse��de/in������һ��Ϊtrue��Ϊtrue����Ϊfalse��Ϊfalse�������ǵ�������ȷ�������������ǵ�������Ҳ������
���Լ���������Ҳ����������
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