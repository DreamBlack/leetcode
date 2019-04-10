/*
Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1.
Note: Length of the array will not exceed 10,000.
简单题
注意数组为空的情况
*/
#include<vector>
#include<algorithm>
using namespace std;
int findLengthOfLCIS(vector<int>& nums) {
	int findLengthOfLCIS(vector<int>& nums) {
		int maxret = 0, ret = 0;
		for (int i = 1; i < nums.size(); i++) {
			if (nums[i] > nums[i - 1]) {
				ret++;
			}
			else {
				ret = 0;
			}
			if (ret > maxret) {
				maxret = ret;
			}
		}
		return (nums.empty()) ? 0 : maxret + 1;
	}
}