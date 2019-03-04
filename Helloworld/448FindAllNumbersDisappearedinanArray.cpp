/*
Share
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
解题思路：
误区：由于题目要求在O(n)时间O(1)空间内完成。一直试图通过根据条件解方程的方法求解，结果发现无解。
看了别人的三种做法后，都是原地操作，即不是不利用数组记录信息，而是在原数组上记录信息的同时，保留
必要的原数组元素的信息。
1、findDisappearedNumbers
origial idea是将nums[i]-1的元素标记，之后遍历数组，nums[i]-1未标记的即为没有的。法1选用的标记方法是
元素的正负，负表示标记，正数表示未标记。遍历0-n-1，将nnums[ums[i]-1]标记为负
2、findDisappearedNumbers2
交换法。将nums[i]放到nums[nums[i]-1]上，则Nums[i]不是i+1的表示i+1没出现过
3、findDisappearedNumbers3
遍历数组将各个nums[(nums[i]-1)%n]+=n（注意取模，防止越界）,则所有出现过一次或者更多的数nums[nums[i]-1]必然大于0,小于等于n的i+1
未出现过
*/
#include<vector>
using namespace std;
vector<int> findDisappearedNumbers(vector<int>& nums) {
	vector<int>res;
	for (int i = 0; i < nums.size(); i++) {
		int idx = abs(nums[i]) - 1;
		nums[idx] = (nums[idx] > 0) ? -nums[idx] : nums[idx];
	}
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] > 0) {
			res.push_back(i + 1);
		}
	}
	return res;
}
vector<int> findDisappearedNumbers2(vector<int>& nums) {
	vector<int>res;
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] != nums[nums[i] - 1]) {
			swap(nums[i], nums[nums[i] - 1]);
			i--;
		}
	}
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] != i + 1) {
			res.push_back(i + 1);
		}
	}
	return res;
}
vector<int> findDisappearedNumbers3(vector<int>& nums) {
	vector<int>res;
	for (int i = 0; i < nums.size(); i++) {
		nums[(nums[i] - 1) % nums.size()] += nums.size();
	}
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] <= nums.size()) {
			res.push_back(i + 1);
		}
	}
	return res;
}