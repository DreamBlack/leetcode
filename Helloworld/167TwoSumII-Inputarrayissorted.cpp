/*
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Note:

Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.
Example:

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
解法1：
两个指针i,j分别指向头尾，对于每一个i，找是否有满足条件的j；有则输出，没有则j--；
解法2：
还是两个指针i,j，判断nums[i]+nums[j]是否满足条件，满足则输出；比target小，则i++;比target大，
则j--；
*/
#include<vector>
using namespace std;
vector<int> twoSum(vector<int>& numbers, int target) {
	vector<int> re;
	int n = numbers.size(), j = n - 1;
	for (int i = 0; i < n; i++) {
		int t = target - numbers[i];
		while (j > i&&numbers[j] > t)j--;
		if (numbers[j] == t) {
			re.push_back(i+1);
			re.push_back(j+1);
			return re;
		}
	}
	return re;
}
vector<int> twoSum2(vector<int>& numbers, int target) {
	vector<int> re;
	int n = numbers.size(),i=0,j = n - 1;
	while (i<j) {
		
		if (numbers[j]+numbers[i] == target) {
			re.push_back(i+1);
			re.push_back(j+1);
			return re;
		}
		else if (numbers[j] + numbers[i] > target)j--;
		else i++;
	}
	return re;
}