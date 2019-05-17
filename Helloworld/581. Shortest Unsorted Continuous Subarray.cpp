/*
��Ŀ������
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
����˼·��һ���ܺõ���
1�������ⷨO(N^3)
2�������ⷨO(N^2)
3��������
��ʵ������Ӧ�þ�֪���ؼ��������ڳ������ҵ�����������Сֵ�����ֵ�������ڵ�λ�ã����������ǳ������ҵ�λ�ã���������Ϊ�������ҵ�������
�кܿ�����ֵ��δ���ҵ������е�ֵ�����С��
4����stack
��stack��˼·�Ǽ�¼�����г������ҵ�������С����һ����Ӧ���ڵ�λ�õ�min���ͳ������ҵ�����������һ�������ڵ�λ�õ�max��Ȼ��max-min+1
5��O(N)+O(1)
�ȱ����ҳ����ҵ�λ�á��������ҵĲ����ҳ�min��max���ٱ���һ���ҵ�min��max��ԭ�����е���ȷλ��min,max������max-min+1
*/
#include<vector>
#include<map>
#include<set>
#include<algorithm>
#include<stack>
using namespace std;
int findUnsortedSubarray(vector<int>& nums) {
	//3����
	int p = -1, q = -1;
	vector<int>tmp(nums.size(), 0);
	tmp.assign(nums.begin(), nums.begin() + nums.size());
	sort(tmp.begin(), tmp.end());
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] != tmp[i])
		{
			p = i;
			break;
		}
	}
	if (p == -1)return 0;
	for (int i = nums.size() - 1; i >= 0; i--) {
		if (nums[i] != tmp[i])
		{
			q = i;
			break;
		}
	}
	return q - p + 1;
}

int findUnsortedSubarray4(vector<int>& nums) {
	//ע��ջ�������Ǹ���Ԫ����ʵ���±�ֵ����ΪҪ��ջ����±�ȥ����Ԫ��
	stack<int>s;
	int p = nums.size() - 1, q = 0;
	for (int i = 0; i < nums.size(); i++) {
		while (!s.empty() && nums[s.top()] > nums[i])
		{
			p = min(p, s.top());//���������min�����������ʱ�ʾӦ�ĳɸ���Ԫ�ض�Ӧ����ʵ���ڵ�����λ��ô����ʵ��ʾ
			//ջ����ŵ�һֱ������ģ�������ֵ�����һ��֤��ջ���ȵ�ǰԪ��С����ǰԪ��Ӧ����ջ��Ԫ�������ڵ�λ�á�
			s.pop();
		}

		s.push(i);
	}
	stack<int>s2;
	for (int i = nums.size() - 1; i >= 0; i--) {
		while (!s2.empty() && nums[s2.top()] < nums[i])
		{
			q = max(q, s2.top());
			s2.pop();
		}

		s2.push(i);
	}
	return (q - p) > 0 ? q - p + 1 : 0;//������ܻ���q<p�����
}
}
int findUnsortedSubarray5(vector<int>& nums) {
	//5�ȱ����ҳ����ҵ�λ�á�
	int p=nums.size()-1, q=0;
	for (int i = 0; i < nums.size()-1; i++) {
		if (nums[i] > nums[i + 1]) {
			p = i;
			break;
		}

	}
	if (p == nums.size() - 1)return 0;
	for (int i = nums.size()-1; i > 0; i--) {
		if (nums[i] < nums[i - 1]) {
			q = i;
			break;
		}
	}
	//�������ҵĲ����ҳ�min��max��
	int mi = INT_MAX, ma = INT_MIN;
	for (int i = p; i <= q; i++) {
		mi = min(mi,nums[i]);
		ma = max(ma, nums[i]);
	}
	//�ٱ���һ���ҵ�min��max��ԭ�����е���ȷλ��min,max������max-min+1
	int m = -1, n = -1;
	for (int i = 0; i <= p; i++) {//����Ҫ�ж��������飬��Ϊmin,max�϶�����������ͷ��֮ǰ�Ĳ��֣�����֮ǰ�Ĳ����������
		if (nums[i] > mi) {
			m = i;
			break;
		}
	}
	for (int i = nums.size() - 1; i >= q; i--) {
		if (nums[i] < ma) {
			n = i;
			break;
		}
	}
	return n - m + 1;
}