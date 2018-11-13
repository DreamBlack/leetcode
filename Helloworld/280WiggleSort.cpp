/*
Problem: Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].������˵����һ�����ܵĽ����
������Ψһ�����
Solution:
1���������
Ҫ����A<=B>=C<=D���ҿ�ʼ���뷨Ҫ���һ��������С���ڶ��������Ҵ�����ѭ����ȥ��
�����ż����A>B�ͽ���������������A<=B����������A<B�ͽ���������������A>=B��������
��ʵ������������������ͬʱ�Ƚ�ǰһ���ͺ�һ������A<=B>=C��ֻҪ������ǰ��Ĳ���ʽ��Ȼ��ӵ�һ�������
һ��ѭ���Ϳ����ˡ�
Ҳ����iΪ������������nums[i]>=nums[i-1];
	  iΪż����������nums[i]<=nums[i-1]
�÷���ʱ�临�Ӷ�ΪO(N),�ռ�O(1)
2������
������Ϊ1,2,3,4,5,6��Ȼ�󽻻���2,3������һ���ٽ�����4,5��
*/
#include<vector>
using namespace std;
void swap(int&x, int&y) {
	int temp = x;
	x = y;
	y = temp;
}
void wiggleSort(vector<int>& nums) {
	int n = nums.size();
	for (int i = 1; i < n; i++) {
		if (i % 2 != 0 && nums[i] < nums[i - 1]) {
			swap(nums[i], nums[i - 1]);
		}
		if (i % 2 == 0 && nums[i] > nums[i - 1]) {
			swap(nums[i], nums[i - 1]);
		}
	}
}
