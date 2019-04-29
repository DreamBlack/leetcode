/*
题目描述：
Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:

Input:
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation:
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.


Note:

Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].
解题思路：
1、每个数组都是有序的，让我们从不同的数组中各取一个数字，使得这两个数字的差的绝对值最大，让我们求这个最大值。那么我们想，既然数组都是有序的，
那么差的绝对值最大的两个数字肯定是分别位于数组的首和尾，注意题目中说要从不同的数组中取数，那么即使某个数组的首尾差距很大，也不行。
此时要么返回第二大数字和最小数字绝对差跟最大数字和第二小数字绝对差中的较大值

注意！！！那么要返回第二大数字和最小数字绝对差跟最大数字和第二小数字绝对差中的较大值，没考虑到第二种情况
2、另外网友的思路我没想出来:如何不使用map数据结构，在O(1)空间复杂度下生成结果
不需要用map来保存每个子数组的min和max，只需要计算res=可能的结果值,start=已有的数组中最小的数,end=已有的数组中最大的数
从第二个子数组开始，比较abs(end-array[i][0])和abs(array[i][last]-start)以及上一个res，取最大的并更新res。
同时更新start和end

*/
#include<vector>
#include<map>
#include<algorithm>
#include<iterator>
using namespace std;
int maxDistance(vector<vector<int>>& arrays) {
	map<int,int>mins, maxs;
	for (int i = 0; i< arrays.size(); i++) {
		mins[arrays[0][0]] = i;
		maxs[arrays[0][arrays[0].size() - 1]] =i;
	}
	map<int, int>::iterator minit = mins.begin();//对应了start
	map<int, int>::iterator maxit = maxs.end();//对应了end
	maxit--;
	if (maxit->second != minit->second)
		return maxit->first - minit->first;
	else//对应了每次比较三个数的值取最大的
		return max(abs((--maxit)->first-minit->first),abs(maxit->first - (++minit)->first));//这里没考虑到
}
int maxDistance2(vector<vector<int>>& arrays) {
	int res=0, start = arrays[0][0], end = arrays[0][arrays[0].size() - 1];
	for (int i = 1; i < arrays.size(); i++) {
		//这里并没有将res直接与end-start比，所以不会出现计算的res是同一个数组中得到的这种情况
		res = max(res, abs(end - arrays[i][0]), abs(arrays[i][arrays[i].size() - 1]- start));
		end = max(end, arrays[i][arrays[i].size() - 1]);
		start = min(start, arrays[i][0]);
	}
	return res;
}