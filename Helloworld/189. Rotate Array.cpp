/*
题目描述：
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
解题思路：
1、第一个想法当然就是另开一个数组按顺序一个个写值进去
2、第二个想法是在1的基础上直接计算新数组某元素在旧数组中对应的位置
3、空间O(1)
本来想把a1,a2,……,an-1.头尾互换k个，然后分别reverse。后来发现如果k>n-k的话没法进行互换了。
陷入了思路绝境。
后来发现最终结果就是要让前n-k个移到尾部,后k个移到头部，而其顺序仍然和原来的一样；那我先反转前n-k个，反转后k个，再整体反转
一下不就可以了么，毕竟负负得正，两次反转得到的顺序还和原来一样

4、Approach #4 Using Cyclic Replacements [Accepted]这个没有想出来
从2中我们可以知道，nums2[i + k] = nums[i]在旧数组中第i个位置在新数组中应该在第(i+k)%n个位置，因此可以通过循环替换的方法
进行k次替换得到结果.同样是O(N)+O(1)

要注意边界条件：
if (k > n)k = k % n;//k有可能比n大
*/
#include<vector>
#include<map>
#include<set>
#include<algorithm>
using namespace std;
void rotate(vector<int>& nums, int k) {
	//先把末尾k个元素放到新数组头部，然后reverse新数组k个头部元素，再把原数组剩下的元素插在新数组后面
	int n = nums.size();
	if (k > n)k = k % n;//k有可能比n大
	vector<int>nums2(n, 0);
	for (int i = n - 1; i >= n - k; i--) {
		nums2[n - 1 - i] = nums[i];
	}
	reverse(nums2.begin(),nums2.begin()+k);
	for (int i = k; i < n; i++) {
		nums2[i] = nums[i - k];
	}
	nums.assign(nums2.begin(),nums2.begin()+n);
}
void rotate2(vector<int>& nums, int k) {
	//直接计算每个点应该在的最终位置
	//坐标的点最终位置为i+k；右边要选择的点最终位置为i-(n-k)
	int n = nums.size();
	if (k > n)k = k % n;//k有可能比n大
	vector<int>nums2(n, 0);
	for (int i = 0; i < n - k; i++) {
		nums2[i + k] = nums[i];
	}
	for (int i = n - k; i < n; i++) {
		nums2[i - (n - k)] = nums[i];//=(i-(n-k)+n)%n
	}
	nums.assign(nums2.begin(), nums2.begin() + n);
}
void rotate3(vector<int>& nums, int k) {
	//将数组分为两个部分0--n-k-1和n-k+1-_n-1,分别reverse，再整体reverse即可
	int n = nums.size();
	if (k >= n)k = k % n;//k有可能比n大

	reverse(nums.begin(), nums.begin() + n - k);
	reverse(nums.begin() + n - k, nums.begin() + n);
	reverse(nums.begin(), nums.begin() + n);
	
	
}
void rotate4(vector<int>& nums, int k) {
	//将数组分为两个部分0--n-k-1和n-k+1-_n-1,分别reverse，再整体reverse即可
	int n = nums.size();
	if (k >= n)k = k % n;//k有可能比n大
	int count = 0;
	for (int i = 0; count<n; i++) {//注意退出条件是与count大小有关，而不是i
		int current = i;
		int pre = nums[current];
		do {
			int nxt = (current + k) % n;
			int tmp = nums[nxt];
			nums[nxt] = pre;
			pre = tmp;
			current = nxt;
			count++;
		} while (i != current);
	}
}