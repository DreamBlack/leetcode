/*
Given an array A of integers, return true if and only if it is a valid mountain array.

Recall that A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[B.length - 1]


Example 1:

Input: [2,1]
Output: false
Example 2:

Input: [3,5,5]
Output: false
Example 3:

Input: [0,3,2,1]
Output: true


Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000
����˼·��
���⣬������һЩע��㣬��Ȼ����Щ����������
1��ע�ⲻ���е��ڵ����
2��ע��mountain array�Ķ��壬�տ�ʼ������
3��ע�ⲻ��һֱ�����������ȵ�����ݼ�
*/
#include<vector>
#include<algorithm>
using namespace std;
bool validMountainArray(vector<int>& A) {
	int flag = 0;
	if (A.size() < 3)return false;
	for (int i = 0; i < A.size() - 1; i++) {
		if (i == 0 && A[i] >= A[i + 1])return false;
		if (flag == 0) {

			if (A[i] > A[i + 1])flag = 1;
			if (A[i] == A[i + 1])return false;//ע���1
		}
		if (flag == 1) {
			if (A[i] <= A[i + 1])return false;//ע���1
		}

	}
	if (flag == 0)return false;//ע���3
	return true;
}
bool validMountainArray2(vector<int>& A) {
	//�ⷨ2�����壬����ɽ����ɽ
	if (A.size() < 3)return false;
	int i = 0;
	while (i < A.size() - 1 && A[i] < A[i + 1])i++;//ע���±꣬�˳���ʱ��i+1��
	if (i == 0 || i == A.size() - 1)return false;
	while (i < A.size() - 1 && A[i] > A[i + 1])i++;
	return i == A.size() - 1;

}