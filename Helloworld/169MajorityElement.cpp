/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
解法1：使用map（majorityElement1）
先扫描数组，将出现的元素及其次数记录到map中；后遍历map找大于n/2的那个
解法2：排序（majorityElement2）
因为主元素占到n/2，可以发现不论主元素是最大的还是最小的，还是中间大小的，
必有一个主元素在n/2的位置上。因此排序后直接返回即可
必有一个主元素在n/2的位置上。因此排序后直接返回即可
解法3：随机化
随机取数组中一个元素看是不是主元素，是则返回；不是则再选一个随机数重复该过程。
可以证明其时间复杂度是线性的
解法4：分治法（majority4）

*/
#include<vector>
#include<unordered_map>
#include<algorithm>
#include<time.h>
using namespace std;
int majorityElement1(vector<int>& nums) {
	unordered_map<int, int> counts;
	int n = nums.size();
	for (int i = 0; i < n; i++)
		if (++counts[nums[i]] > n / 2)
			return nums[i];
}
int majorityElement2(vector<int>& nums) {
	nth_element(nums.begin(), nums.begin() + nums.size() / 2, nums.end());//nth_element仅排序第nth个元素（从0开始的索引）
	return nums[nums.size() / 2];
}
int majorityElement3(vector<int>& nums) {
	int n = nums.size();
	srand(unsigned(time(NULL)));
	while (true) {
		int idx = rand() % n;
		int candidate = nums[idx];
		int counts = 0;
		for (int i = 0; i < n; i++)
			if (nums[i] == candidate)
				counts++;
		if (counts > n / 2) return candidate;
	}
}
int majorityElement(vector<int>& nums) {
	return majority4(nums, 0, nums.size() - 1);
}

int majority4(vector<int>& nums, int left, int right) {
		if (left == right) return nums[left];
		int mid = left + ((right - left) >> 1);
		int lm = majority4(nums, left, mid);
		int rm = majority4(nums, mid + 1, right);
		if (lm == rm) return lm;
		return count(nums.begin() + left, nums.begin() + right + 1, lm) > count(nums.begin() + left, nums.begin() + right + 1, rm) ? lm : rm;
}
int majorityElement(vector<int>& nums) {
	int last = nums[0], count = 1;
	for (int i = 1; i < nums.size(); i++) {
		if (count == 0)last = nums[i];

		if (nums[i] == last)count++;
		else count--;
	}
	return last;
}