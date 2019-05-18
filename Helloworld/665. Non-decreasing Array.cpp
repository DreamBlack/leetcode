/*
题目描述：
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.
Note: The n belongs to [1, 10,000].
解题思路：
仔细分析问题即可
需要考虑到两种情况。不要总是在case中修改代码，最好一次搞清。
*/
#include<vector>
#include<map>
#include<set>
#include<iterator>
using namespace std;
bool checkPossibility(vector<int>& nums) {
	//先找到第一次降序的位置的下标为index
	int index = -1;
	for (int i = 0; i < nums.size() - 1; i++) {
		if (nums[i] > nums[i + 1]) {
			index = i;
			break;
		}
	}
	if (index == -1)return true;
	//看一下index后面的是不是升序，否则return false
	for (int i = index + 1; i < nums.size() - 1; i++) {
		if (nums[i] > nums[i + 1]) {
			return false;
		}
	}
	//如果逆序出现在数组头部，或者倒数第二个位置，没问题，返回true
	if (index == 0||index==nums.size()-2)return true;
	else {
		//如果可以index左边的都比右边的小，那改Index所在的元素即可
		if (nums[index - 1] <= nums[index + 1])
			return true;
		else {//这里漏掉了，出现一次在Index处逆序的时候，有两种选择，1改index所在的元素2改后面的那个元素。这里一开始漏掉了2的这种情况
			//如果index左边的比index右边的大，没关系，只要index左边比右边的第二个大就行，因为可以改index右边第一个元素的位置呀
			if (index+2<nums.size()&&nums[index] <= nums[index + 2]) {
				return true;
			}else
				return false;
		}
			
	}
}
bool checkPossibility2(vector<int>& nums) {
	//这个方法不行，因为nums有可能为负。伤心
	vector<set<int>>mm(10001,set<int>());
	for (int i = 0; i < nums.size(); i++) {
		mm[nums[i]].insert(i);
	}
	int last = -1,count=0;
	for (int i = 0; i < 10001; i++) {
		if (!mm[i].empty()) {
			for (set<int>::iterator it = mm[i].begin(); it != mm[i].end(); it++) {
				if (*it < last) {
					count++;
					if (count > 1)
						return false;
				}
			}
		}
		
	}
	return true;

}