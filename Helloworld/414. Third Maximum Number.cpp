/*
题目描述
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
解题思路
1、遍历数组，记录下最大值，第二大和第三大的值
其中遇到了一些问题
    a.有多个值相等的情况，所以要在判断的时候避开等于的情况
	b.值最小可能是-2147483648，是32位int中最小值，如果用int_32或者int_min都不想，要用64位long long 即INT64_MIN
INT_MIN和INT32_MIN都是-2147483648
long和long long好像一样，都是64位的
*/
#include<vector>
#include<map>
#include<set>
using namespace std;
int thirdMax(vector<int>& nums) {
	long long max3 = INT64_MIN, max2 = INT64_MIN, max1 = nums[0];
	for (int i = 1; i < nums.size(); i++) {
		if (nums[i] > max1) {
			max3 = max2;
			max2 = max1;
			max1 = nums[i];
		}
		else if (nums[i] < max1&&nums[i] > max2) {//a.有多个值相等的情况，所以要在判断的时候避开等于的情况
			max3 = max2;
			max2 = nums[i];
		}
		else if (nums[i] < max2&&nums[i] > max3) {//a.有多个值相等的情况，所以要在判断的时候避开等于的情况
			max3 = nums[i];
		}
	}
	if (max3 == INT64_MIN) {//如果第三大的数刚好是-2147483648，这里就会输出max1了，所以初始化的时候要用INT64_MIN
		return max1;
	}
	else
		return max3;

	
}