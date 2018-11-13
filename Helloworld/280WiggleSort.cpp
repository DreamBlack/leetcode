/*
Problem: Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].（这里说的是一个可能的结果，
并不是唯一结果）
Solution:
1、问题分析
要求是A<=B>=C<=D，我开始的想法要求第一个比左右小，第二个比左右大这样循环下去。
如果第偶数个A>B就交换，这样就做到A<=B，第奇数个A<B就交换，这样就做到A>=B就满足了
其实这样想下来根本不用同时比较前一个和后一个满足A<=B>=C，只要先满足前面的不等式，然后从第一个到最后
一个循环就可以了。
也就是i为奇数，需满足nums[i]>=nums[i-1];
	  i为偶数，需满足nums[i]<=nums[i-1]
该方法时间复杂度为O(N),空间O(1)
2、法二
先排序为1,2,3,4,5,6，然后交换（2,3），各一个再交换（4,5）
*/
#include<vector>
using namespace std;
void swap(int&x, int&y) {
	int temp = x;
	x = y;
	y = temp;
}
void wiggleSort(vector<int>& nums) {
	int n = nums.size();
	for (int i = 1; i < n; i++) {
		if (i % 2 != 0 && nums[i] < nums[i - 1]) {
			swap(nums[i], nums[i - 1]);
		}
		if (i % 2 == 0 && nums[i] > nums[i - 1]) {
			swap(nums[i], nums[i - 1]);
		}
	}
}
