/*
题目描述：
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
解题思路：一道很好的题
1、暴力解法O(N^3)
2、暴力解法O(N^2)
3、先排序
其实到这里应该就知道关键问题在于出现紊乱的子数组中最小值和最大值本来该在的位置，而不仅仅是出现紊乱的位置（不够：因为出现紊乱的子数组
中很可能有值比未紊乱的数组中的值大或者小）
4、用stack
用stack的思路是记录数组中出现紊乱的数中最小的那一个所应该在的位置的min，和出现紊乱的数中最大的那一个所该在的位置的max，然后max-min+1
5、O(N)+O(1)
先遍历找出紊乱的位置。后在紊乱的部分找出min和max。再遍历一遍找到min和max在原数组中的正确位置min,max，返回max-min+1
*/
#include<vector>
#include<map>
#include<set>
#include<algorithm>
#include<stack>
using namespace std;
int findUnsortedSubarray(vector<int>& nums) {
	//3排序法
	int p = -1, q = -1;
	vector<int>tmp(nums.size(), 0);
	tmp.assign(nums.begin(), nums.begin() + nums.size());
	sort(tmp.begin(), tmp.end());
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] != tmp[i])
		{
			p = i;
			break;
		}
	}
	if (p == -1)return 0;
	for (int i = nums.size() - 1; i >= 0; i--) {
		if (nums[i] != tmp[i])
		{
			q = i;
			break;
		}
	}
	return q - p + 1;
}

int findUnsortedSubarray4(vector<int>& nums) {
	//注意栈里面存的是各个元素真实的下标值，因为要靠栈里的下标去访问元素
	stack<int>s;
	int p = nums.size() - 1, q = 0;
	for (int i = 0; i < nums.size(); i++) {
		while (!s.empty() && nums[s.top()] > nums[i])
		{
			p = min(p, s.top());//是在这里改min。这里有疑问表示应改成各个元素对应的真实所在的最后的位置么，其实表示
			//栈里面放的一直是有序的，如果出现到了这一步证明栈顶比当前元素小，当前元素应该在栈顶元素所该在的位置。
			s.pop();
		}

		s.push(i);
	}
	stack<int>s2;
	for (int i = nums.size() - 1; i >= 0; i--) {
		while (!s2.empty() && nums[s2.top()] < nums[i])
		{
			q = max(q, s2.top());
			s2.pop();
		}

		s2.push(i);
	}
	return (q - p) > 0 ? q - p + 1 : 0;//这里可能会有q<p的情况
}
}
int findUnsortedSubarray5(vector<int>& nums) {
	//5先遍历找出紊乱的位置。
	int p=nums.size()-1, q=0;
	for (int i = 0; i < nums.size()-1; i++) {
		if (nums[i] > nums[i + 1]) {
			p = i;
			break;
		}

	}
	if (p == nums.size() - 1)return 0;
	for (int i = nums.size()-1; i > 0; i--) {
		if (nums[i] < nums[i - 1]) {
			q = i;
			break;
		}
	}
	//后在紊乱的部分找出min和max。
	int mi = INT_MAX, ma = INT_MIN;
	for (int i = p; i <= q; i++) {
		mi = min(mi,nums[i]);
		ma = max(ma, nums[i]);
	}
	//再遍历一遍找到min和max在原数组中的正确位置min,max，返回max-min+1
	int m = -1, n = -1;
	for (int i = 0; i <= p; i++) {//不需要判断整个数组，因为min,max肯定是紊乱数组头部之前的部分，而且之前的部分是有序的
		if (nums[i] > mi) {
			m = i;
			break;
		}
	}
	for (int i = nums.size() - 1; i >= q; i--) {
		if (nums[i] < ma) {
			n = i;
			break;
		}
	}
	return n - m + 1;
}