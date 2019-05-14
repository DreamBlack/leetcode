/*
����������
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
���������
���⣬��map����
ע��map��ʹ��
1�����룺mp.insert(make_pair(nums[i],i));
2�����ʣ�mp[nums[i]] = i;
3���ж�����Ԫ��if (mp.count(nums[i]) == 0)
*/
#include<vector>
#include<algorithm>
#include<map>
using namespace std;
bool containsNearbyDuplicate(vector<int>& nums, int k) {
	map<int, int>mp;
	for (int i = 0; i < nums.size(); i++) {
		if (mp.count(nums[i]) == 0) {
			mp.insert(make_pair(nums[i],i));
		}
		else {
			if (i - mp[nums[i]] <= k)
				return true;
			else {
				mp[nums[i]] = i;
			}
		}
	}
	return false;
}