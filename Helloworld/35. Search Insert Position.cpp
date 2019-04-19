/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 4:

Input: [1,3,5,6], 0
Output: 0
解题思路：简单题
1、遍历
2、二分查找
我写的时候漏掉了if (left > right)return -1;导致越界。而且写的很垃圾
3、网友写的二分查找，简单明了
*/

#include<vector>
using namespace std;

int searchInsert(vector<int>& nums, int target) {
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] >= target) return i;
	}
	return nums.size();
}
int searchInsert2(vector<int>& nums, int target) {
	
	
	return midSearch(nums, 0, nums.size()-1,target);
}
int midSearch(vector<int>& nums,int left,int right,int target){
	int mid = (left + right) / 2;
	if (target == nums[mid]) return mid;
	if (left == right) {
		if (target < nums[left])
			return left;
		else
			return left + 1;
	}
	if (left > right)return -1;
	if(target<nums[mid]){
		return midSearch(nums,left,mid-1,target);
	}
	else {
		return midSearch(nums, mid + 1, right, target);
	}
}
int searchInsert3(vector<int>& nums, int target) {
	int low = 0, high = nums.size() - 1;

	// Invariant: the desired index is between [low, high+1]
	while (low <= high) {
		int mid = low + (high - low) / 2;

		if (nums[mid] < target)
			low = mid + 1;
		else
			high = mid - 1;
	}

	// (1) At this point, low > high. That is, low >= high+1
	// (2) From the invariant, we know that the index is between [low, high+1], so low <= high+1. Follwing from (1), now we know low == high+1.
	// (3) Following from (2), the index is between [low, high+1] = [low, low], which means that low is the desired index
	//     Therefore, we return low as the answer. You can also return high+1 as the result, since low == high+1
	return low;
}