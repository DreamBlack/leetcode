/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
����˼·��
1���������
2��ʹ��set
3��XOR����
��all=n��֮��������齫all��Index��valueͬʱ��򡣸�����ͬΪ0���෴Ϊ1��������ֻ���ֹ�һ�Σ�����ԭ���飩
δ���ֵ�����������all
4����˹��ʽ
*/
#include<vector>
using namespace std;
int missingNumber(vector<int>& nums) {
	int sum = nums.size()*(nums.size() + 1) / 2;
	for (int i = 0; i < nums.size(); i++) {
		sum -= nums[i];
	}
	return sum;
}