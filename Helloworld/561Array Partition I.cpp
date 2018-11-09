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
1、排序法
#include<algorithm>
sort(nums.begin(), nums.end());每隔一位相加，可以直接让步长加2
2、Hash法
要注意的是可能有同一个数字出现多次，所以要记录出现的次数
int hash[20001] = {0};hash[ nums[i]+10000 ] += 1;要想不分成两个正负数组处理，可以使用这种方法
*/
#include<vector>
#include<stdio.h>
#include<algorithm>
using namespace std;
int numpostive[10001] = {0};
int numnegative[10001] = {0};//初始化方法
int arrayPairSumSort(vector<int>& nums) {
	//排序的方法
	sort(nums.begin(), nums.end());
	int sum = 0;
	int size = nums.size();
	for (int i = 0; i < size; i = i + 2)//每隔一位相加，可以直接让步长加2
		sum += nums[i];
	return sum;
}
int arrayPairSum(vector<int>& nums) {
	//hash法，要注意的是可能有同一个数字出现多次，所以要记录出现的次数
	//int hash[20001] = {0};hash[ nums[i]+10000 ] += 1;要想不分成两个正负数组处理，可以使用这种方法
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
			if (cnt % 2 != 0) {//该选数了
				sum -= i;
			}
			if (numnegative[i] > 1) {//记录的数字次数的减少和是否使用该数字加入和无关，并且由于sum-=i和i有关，i的更新要放在合适的位置
				numnegative[i] -= 1;
				i++;
			}
			cnt++;
		}
	}
	for (int i = 0; i < 10001; i++) {
		if (numpostive[i] != 0) {
			if (cnt % 2 != 0) {//该选数了
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
