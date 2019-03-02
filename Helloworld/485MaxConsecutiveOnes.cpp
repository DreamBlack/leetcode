/*
Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
	The maximum number of consecutive 1s is 3.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
解题思路：
简单题。

*/
#include<vector>
using namespace std;
int findMaxConsecutiveOnes(vector<int>& nums) {
	int count = (nums[0] == 1) ? 1 : 0, max = count;
	for (int i = 1; i < nums.size(); i++) {
		if (nums[i]) {
			count++;
			if (count > max) {
				max = count;
			}
		}
		else {

			count = 0;
		}
	}
	return max;
}