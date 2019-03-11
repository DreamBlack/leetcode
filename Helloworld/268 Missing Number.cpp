/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
解题思路：
1、排序后找
2、使用set
3、XOR操作
令all=n，之后遍历数组将all和Index和value同时异或。根据相同为0，相反为1的异或规则，只出现过一次（即在原数组）
未出现的数便是最后的all
4、高斯公式
*/
#include<vector>
using namespace std;
int missingNumber(vector<int>& nums) {
	int sum = nums.size()*(nums.size() + 1) / 2;
	for (int i = 0; i < nums.size(); i++) {
		sum -= nums[i];
	}
	return sum;
}