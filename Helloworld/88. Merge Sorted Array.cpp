/*
题目描述
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
解题思路：
1、双指针
2、空间O(1)下的双指针（没想出来）
1的思路是从0开始到n-1进行比较，如果直接在nums1上进行原地操作会覆盖掉Nums1上的值
但是如果从n-1到1开始比较的话，由于nums1后面填充的是无意义的0，因此不会覆盖的nums1中的一些值
由于index肯定始终会比i大的，所有不会将nums1中未处理的值覆盖掉。
*/
#include<vector>
#include<algorithm>
using namespace std;
void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
	vector<int> num(m, 0);
	num.assign(nums1.begin(), nums1.begin() + m);
	int i = 0, j = 0,index=0;
	while (i < m&&j < n) {
		if (num[i] < nums2[j]) {
			nums1[index++]=num[i++];
		}
		else {
			nums1[index++] = nums2[j++];
		}
	}
	while (i < m) {
		nums1[index++] = num[i++];
	}
	while (j < n) {
		nums1[index++] = nums2[j++];
	}
}
void merge2(vector<int>& nums1, int m, vector<int>& nums2, int n) {
	int i = m-1, j = n-1, index = m+n-1;
	while (i >=0&&j >=0) {
		if (nums1[i] > nums2[j]) {
			nums1[index--] = nums1[i--];
		}
		else {
			nums1[index--] = nums2[j--];
		}
	}
	while (i >=0) {
		nums1[index--] = nums1[i--];
	}
	while (j < n) {
		nums1[index--] = nums2[j--];
	}
}