/*
Problem:Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) 
which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].

Solution:
1������
#include<algorithm>
sort(nums.begin(), nums.end());ÿ��һλ��ӣ�����ֱ���ò�����2
2��Hash��
Ҫע����ǿ�����ͬһ�����ֳ��ֶ�Σ�����Ҫ��¼���ֵĴ���
int hash[20001] = {0};hash[ nums[i]+10000 ] += 1;Ҫ�벻�ֳ������������鴦������ʹ�����ַ���
*/
#include<vector>
#include<stdio.h>
#include<algorithm>
using namespace std;
int numpostive[10001] = {0};
int numnegative[10001] = {0};//��ʼ������
int arrayPairSumSort(vector<int>& nums) {
	//����ķ���
	sort(nums.begin(), nums.end());
	int sum = 0;
	int size = nums.size();
	for (int i = 0; i < size; i = i + 2)//ÿ��һλ��ӣ�����ֱ���ò�����2
		sum += nums[i];
	return sum;
}
int arrayPairSum(vector<int>& nums) {
	//hash����Ҫע����ǿ�����ͬһ�����ֳ��ֶ�Σ�����Ҫ��¼���ֵĴ���
	//int hash[20001] = {0};hash[ nums[i]+10000 ] += 1;Ҫ�벻�ֳ������������鴦������ʹ�����ַ���
	int n = nums.size();
	for (int i = 0; i < n; i++) {
		if (nums[i] > 0) {
			numpostive[nums[i]] += 1;
		}
		else {
			numnegative[-nums[i]] += 1;
		}
	}
	int sum = 0;
	int cnt = 1;
	for (int i = 10000; i >= 0; i--) {
		if (numnegative[i] != 0) {
			if (cnt % 2 != 0) {//��ѡ����
				sum -= i;
			}
			if (numnegative[i] > 1) {//��¼�����ִ����ļ��ٺ��Ƿ�ʹ�ø����ּ�����޹أ���������sum-=i��i�йأ�i�ĸ���Ҫ���ں��ʵ�λ��
				numnegative[i] -= 1;
				i++;
			}
			cnt++;
		}
	}
	for (int i = 0; i < 10001; i++) {
		if (numpostive[i] != 0) {
			if (cnt % 2 != 0) {//��ѡ����
				sum += i;
			}
			if (numpostive[i] > 1) {
				numpostive[i] -= 1;
				i--;
			}
			cnt++;
		}
	}
	return sum;
}
