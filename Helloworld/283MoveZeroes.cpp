/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
解题思路：
1、moveZeroes
扫描数组时记录0元素个数为count，对于非零元素所在位置即为i-count。最后将最后count个位置的元素置为0
2、moveZeroes2
设法让最后不用再遍历一遍设0
当位置i发现非0元素时，会调到正确的位置，而最后正确按要求排列的情况下，位置i的元素为0要么不为0.
如果位置i的元素最后位置也为i时则不变，否则将Nums[i]设为0，就不用最后再遍历
3、moveZeroes3
指针法，记录最后一个不为0的位置为lastNonZeroFoundAt.
这里使用的是swap
*/
#include<vector>
using namespace std;
void moveZeroes(vector<int>& nums) {
	int count = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] == 0)count++;
		else {
			nums[i - count] = nums[i];
		}
	}
	for (int i = nums.size() - count; i < nums.size(); i++) {
		nums[i] = 0;
	}
}
void moveZeroes2(vector<int>& nums) {
	int count = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] == 0)count++;
		else {
			nums[i - count] = nums[i];
			if (count != 0)nums[i] = 0;
		}
	}

}
void moveZeroes3(vector<int>& nums) {
	for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.size(); cur++) {
		if (nums[cur] != 0) {
			swap(nums[lastNonZeroFoundAt++], nums[cur]);
		}
	}
}