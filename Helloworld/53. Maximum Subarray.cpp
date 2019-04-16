/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
解题思路：
1、动态规划
将原问题分解为A[0:i]的小问题，则可得dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
关键在于仔细分析问题本身，本来以为当前数字为负数时重新计算子数组的和，实践证明是不对的；从而考虑判断什么情况下从新开始，
发现是当累积和小于0时就不加了，开始重新计算（因为前面的和小于0，对之后的计算不会产生正面效果，完全可以不要）；对比原来
思路（因为当前值小于0，但是当前值之前的和可能加上当前值之后大于0，对之后的仍然是有正面效果的）
2、分治（我分治经常性写不好）
将数组从中间一分为二（通常都是从中间分，如果要根据数据大小分那这个分治基本上也没用了），则可以根据左边的max，右边的max，
和当前值a分类；一是，结果在左边中，返回左边max；二是，结果在右边，返回右边max；三是结果包含a横跨了左右。一二情况好说，但
三这个情况下，就得从a开始左右遍历直到头尾累加和计算max，相当于遍历了数组。网上人家的思路是在此基础上的改进，每次分治同时
记录从左边开始的和最大数组的和为maxl，从右边开始的和最大数组的和为maxr，数组总和sum，数组目标max；这样下面的问题就是如何
在l!=r的情况下，根据左右的值恢复当前更长的数组的各个临时变量了。

*/
#include<vector>
#include<algorithm>
using namespace std;
int maxSubArray(vector<int>& nums) {
	//dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
	int sum = nums[0];
	int maxsum = sum;
	for (int i = 1; i < nums.size(); i++) {
		if (sum > 0) {
			sum += nums[i];
		}
		else {
			sum = nums[i];
		}
		if (sum > maxsum) {
			maxsum = sum;
		}
	}
	return maxsum;
}
//分治法
int maxSubArray2(vector<int>& nums) {

	int l = 0, r = nums.size() - 1;
	int maxl = 0, maxr = 0,sum=0;
	return maxSub(nums,l,r,maxl,maxr,sum);
}
int maxSub(vector<int>& nums, int l, int r, int& maxl, int& maxr,int&sum) {
	int mx = 0;
	if (l == r) {
		mx = maxl = maxr = sum=nums[l];
	}
	else {
		int mid = (l + r) / 2;
		int maxl1 = 0, maxr1 = 0,sum1=0;
		int maxl2 = 0, maxr2 = 0,sum2=0;
		int mx1 = maxSub(nums,l,mid,maxl1,maxr1,sum1);
		int mx2 = maxSub(nums, mid+1, r, maxl2, maxr2,sum2);
		maxl = max(maxl1, sum1 + maxl2);
		maxr = max(maxr2, sum2 + maxr1);
		mx = max(max(mx1,mx2),maxr1+maxl2);
		sum = sum1 + sum2;
	}
	return mx;
}
