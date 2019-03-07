/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
解题思路：
刚开始的时候一直在想怎么操作能减少求元素在数组中出现的次数等方法。其实思路很简单，这些事情都
让map去做，我们只要想到的是对于每一个次数等于数组度的元素，记录其起始位置i,j，则j-i+1就是一个满足条件
的子数组。找最小的j-i+1即可
法一：
先遍历数组，记录每个数的起止点和出现次数，分别在first,end和count里
第二遍遍历count计算数组度
第三遍，遍历数组，对于每一个元素看起count是不是等于degree，等于则看起end-start+1是否比当前min小
法二：
在一的基础上，减少了一次遍历。
需要注意的是1、degree=0 2、degree的修改不应该包含在第一个的else里面，这样会导致形如【1,2】，这种度为1
的数组的计算错误。
*/
#include<vector>
#include<map>
using namespace std;
int findShortestSubArray(vector<int>& nums) {
	map<int, int>first, end, count;
	//记录first,end,count
	for (int i = 0; i < nums.size(); i++) {
		//第一次出现，记录first,count,end
		if (first.count(nums[i]) == 0) {
			first.insert(pair<int, int>(nums[i], i));
			count.insert(pair<int, int>(nums[i], 1));
			end.insert(pair<int, int>(nums[i], i));
		}
		else {
			//出现过，更新count,end
			count[nums[i]]++;
			end[nums[i]] = i;
		}
	}
	//找count中最大值
	int degree = 0;
	for (map<int,int>::iterator it = count.begin(); it != count.end(); it++) {
		if (it->second > degree) {
			degree = it->second;
		}
	}
	int min = nums.size();
	for (int i = 0; i < nums.size(); i++) {
		if (count[nums[i]]== degree) {
			if (end[nums[i]] - first[nums[i]] + 1 < min) {
				min = end[nums[i]] - first[nums[i]] + 1;
			}
		}
	}
	return min;

}
int findShortestSubArray2(vector<int>& nums) {
	map<int, int>first, count;
	int degree = 0, min = nums.size();
	//记录first,count
	for (int i = 0; i < nums.size(); i++) {
		//第一次出现，记录first,count,end
		if (first.count(nums[i]) == 0) {
			first.insert(pair<int, int>(nums[i], i));
			count.insert(pair<int, int>(nums[i], 1));
		}
		else {
			count[nums[i]]++;
		}
			//出现过，更新count,end
		
			if (count[nums[i]] > degree) {
				degree = count[nums[i]];
				min = i - first[nums[i]] + 1;
			}
			else if (count[nums[i]] == degree&&i - first[nums[i]] + 1 < min) {
				min = i - first[nums[i]] + 1;
				}
		
		
	}
	return min;
}