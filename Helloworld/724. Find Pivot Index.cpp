/*
Given an array of integers nums, write a method that returns the "pivot" index of this array.

We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.

If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.

Example 1:

Input:
nums = [1, 7, 3, 6, 5, 6]
Output: 3
Explanation:
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.


Example 2:

Input:
nums = [1, 2, 3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.
解题思路：
1、遍历数组，记录数组和sum[i]表示nums[0]到Nums[i]的和，二次遍历数组比较sum[i]和sum[n-1]-sum[i-1]
2、1中空间复杂度为O(N)，仔细想想其实不用记录每个i处的sum[i]，每次只有总的和sum，和leftsum（0到i）
的被用到，因此只记录sum和leftsum，每次比较时只要leftsum(不包含nums[i])和sum-leftsum-num[i]相同即可
*/

#include<vector>
#include<algorithm>
using namespace std;
int pivotIndex(vector<int>& nums) {
	if (nums.size() == 0)return -1;
	vector<int>sum(nums.size()+1,0);
	sum[1] = nums[0];
	for (int i = 1; i < nums.size(); i++) {
		sum[i+1] = sum[i] + nums[i];
	}
	for (int i = 1; i < sum.size(); i++) {
		if (sum[i] == sum[nums.size()] - sum[i - 1])
			return i - 1;
	}
	return -1;
}
int pivotIndex2(vector<int>& nums) {
	if (nums.size() == 0)return -1;
	int sum = 0;
	for (int i = 0; i < nums.size(); i++) {
		sum += nums[i];
	}
	int leftsum = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (leftsum == sum- leftsum-nums[i])
			return i ;
		leftsum += nums[i];
	}
	return -1;
}