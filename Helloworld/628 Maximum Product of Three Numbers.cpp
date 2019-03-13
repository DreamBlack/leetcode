/*
Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6


Example 2:

Input: [1,2,3,4]
Output: 24


Note:

The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
在长度大于等于3的数组中找到三个数乘积最大的结果
解决思路
1、扫描一次
刚开始的思路是扫描一遍找到最大、第二大、第三大的正数和第一小、第二小的负数，然后根据结果判定。
要么是三个最大的正数的乘积最大，要么是两个最小负数加最大正数的乘积最大。因为有正负数的判定，
所以导致程序写的很复杂，且最后判定的时候陷入了讨论正负数个数中。
其实不需要区分正负数，直接记录最大的三个和最小的两个（可能有重复），比较最小两个乘最大和最大三个的。
乘积即可。
之所以可以这样是因为，如果数组中只有正数，必然是最大的三个的乘积最大；如果有负数的话，两个最小负数乘以
最大正数有可能是最大的，因此记录最小的两个，如果最小的不全是负数，结果必在前三个最大的中产生。
2、排序法
先排序，后按1的思路做
*/
#include<vector>
#include<algorithm>
using namespace std;
int maximumProduct(vector<int>& nums) {
	sort(nums.begin(), nums.end());
	int n = nums.size();
	int a = nums[n - 1] * nums[n - 2] * nums[n - 3],
		b = nums[0] * nums[1] * nums[n - 1];
	return (a > b) ? a : b;


}
int maximumProduct2(vector<int>& nums) {
	int max1= INT32_MIN, max2 = INT32_MIN, max3 = INT32_MIN;
	int min1= INT32_MAX, min2 = INT32_MAX;
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] >= max1) {
			max3 = max2;
			max2 = max1;
			max1 = nums[i];
		}
		else if (nums[i] >= max2) {
			max3 = max2;
			max2 = nums[i];
		}
		else if (nums[i] >= max3) {
			max3 = nums[i];
		}
		if (nums[i] <= min1) {
			min2 = min1;
			min1 = nums[i];
		}
		else if (nums[i] <= min2) {
			min2 = nums[i];
		}
	}
	int a = max1 * max2*max3;
	int b = min1 * min2*max1;
	return (a > b) ? a : b;
}