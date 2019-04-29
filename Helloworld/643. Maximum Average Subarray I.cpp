/*
题目描述：
643. Maximum Average Subarray I
Easy

441

82

Favorite

Share
Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

Example 1:

Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75


Note:

1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].
解题思路：
简单题
1、滑动窗口
2、累和
*/
#include<vector>
#include<algorithm>
using namespace std;
double findMaxAverage(vector<int>& nums, int k) {
	int sum = 0, maxsum = 0, startindex = 0;
	for (int i = 0; i < k; i++) {
		sum += nums[i];
	}
	maxsum = sum;
	for (int i = k; i < nums.size(); i++) {
		sum = sum - nums[startindex++] + nums[i];
		if (sum > maxsum)maxsum = sum;
	}
	return (double)maxsum / k;

}
double findMaxAverage2(vector<int>& nums, int k) {
	int nowsum = 0, maxsum = 0, startindex = 0;
	vector<int>culm(nums.size(),0);
	culm[0] = nums[0];
	for (int i = 1; i < nums.size(); i++) {
		culm[i] = nums[i]+ culm[i-1];
	}
	maxsum = culm[k-1];
	for (int i = k; i < nums.size(); i++) {
		nowsum = nums[i] - nums[i - k];
		if (nowsum > maxsum)maxsum = nowsum;
	}
	return (double)maxsum / k;

}