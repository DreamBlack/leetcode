/*
题目描述：
Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].
解题思路：
这道题还好。两个方法都想出来了，要注意的还是一些边界或者说特色情况时在错误的case情况下才找出来的
1、k=0的情况
2、k<0的情况
解法1：
排序，之后二分查找。注意k=0的情况。
PS：我终于会写二分查找了。。。
解法2:
空间换时间。
*/
#include<vector>
#include<map>
#include<set>
#include<algorithm>
#include<iterator>
using namespace std;
int findPairs(vector<int>& nums, int k) {
	/*
	先对数组排序，之后对于每一个元素向后用二分查找比它大k的数。
	注意二分查找mid的变化是mid-1和mid+1，不然很容易死循环
	*/
	//开始跳过了所有等于的数，导致k=0的时候死循环
	sort(nums.begin(), nums.end());
	int count = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (i > 0 && nums[i] == nums[i - 1])
			continue;
		//二分查找
		int p = i+1,end= nums.size() - 1;
		//while (p < nums.size() && nums[p] == nums[i])p++;//这里跳过了所有等于的数，导致k=0的时候死循环
		int mid = 0;
		while (p <= end) {
			mid = (p + end) / 2;
			if (nums[mid] == nums[i] + k) {
				count++;
				break;
			}
			if (nums[mid] > nums[i] + k)
				end = mid-1;//同样注意这里是mid-1而不是mid
			else 
				p = mid+1;//注意这里是mid+1，不然会死循环
		}

	}
	return count;
}
int findPairs2(vector<int>& nums, int k) {
	/*
	先遍历数组同时构造set1和set2，set1是本身的set，set2是原数组每个元素加k后的
	之后在set1中查找set2中的元素并计数
	*/
	if (k < 0)return 0;//好像要求k不能小于0
	map<int, int>m;
	set<int> s;
	int count = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (m.count(nums[i]) == 1) {
			m[nums[i]]++;
		}
		else {
			m.insert(make_pair(nums[i],1));
		}
		
		s.insert(nums[i]+k);
	}
	for (set<int>::iterator it = s.begin(); it != s.end(); it++) {
		if (k != 0) {
			if (m.count(*it) == 1) {
				count++;
			}
		}
		else {
			if (m.count(*it) == 1&&m[*it]>1) {
				count++;
			}
		}
		
	}
	return count;
}