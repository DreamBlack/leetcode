/*
Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:

Input: [1,2,3,1]
Output: true
Example 2:

Input: [1,2,3,4]
Output: false
Example 3:

Input: [1,1,1,3,3,4,3,2,4,2]
Output: true
Ω‚∑®£∫
1°¢±©¡¶
2°¢≈≈–Ú
3°¢Hash
*/
#include<vector>
#include<map>
#include<set>
using namespace std;
bool containsDuplicate(vector<int>& nums) {
	map<int, int>mp;
	for (int i = 0; i < nums.size(); i++) {
		if (mp.find(nums[i]) == mp.end()) {
			mp.insert(pair<int, int>(nums[i], 1));
		}
		else {
			mp[nums[i]]++;
		}
	}
	for (map<int,int>::iterator it = mp.begin(); it != mp.end(); it++) {
		if (it->second > 1) {
			return true;
		}
	}
	return false;
}
bool containsDuplicate2(vector<int>& nums) {
	set<int>s;
	for (int i = 0; i < nums.size(); i++) {
		if (s.count(nums[i]) == 0) {
			s.insert(nums[i]);
		}
		else {
			return true;
		}
	}
	return false;
}