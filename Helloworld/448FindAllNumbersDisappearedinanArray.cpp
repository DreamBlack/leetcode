/*
Share
Given an array of integers where 1 �� a[i] �� n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
����˼·��
������������ĿҪ����O(n)ʱ��O(1)�ռ�����ɡ�һֱ��ͼͨ�����������ⷽ�̵ķ�����⣬��������޽⡣
���˱��˵����������󣬶���ԭ�ز����������ǲ����������¼��Ϣ��������ԭ�����ϼ�¼��Ϣ��ͬʱ������
��Ҫ��ԭ����Ԫ�ص���Ϣ��
1��findDisappearedNumbers
origial idea�ǽ�nums[i]-1��Ԫ�ر�ǣ�֮��������飬nums[i]-1δ��ǵļ�Ϊû�еġ���1ѡ�õı�Ƿ�����
Ԫ�ص�����������ʾ��ǣ�������ʾδ��ǡ�����0-n-1����nnums[ums[i]-1]���Ϊ��
2��findDisappearedNumbers2
����������nums[i]�ŵ�nums[nums[i]-1]�ϣ���Nums[i]����i+1�ı�ʾi+1û���ֹ�
3��findDisappearedNumbers3
�������齫����nums[(nums[i]-1)%n]+=n��ע��ȡģ����ֹԽ�磩,�����г��ֹ�һ�λ��߸������nums[nums[i]-1]��Ȼ����0,С�ڵ���n��i+1
δ���ֹ�
*/
#include<vector>
using namespace std;
vector<int> findDisappearedNumbers(vector<int>& nums) {
	vector<int>res;
	for (int i = 0; i < nums.size(); i++) {
		int idx = abs(nums[i]) - 1;
		nums[idx] = (nums[idx] > 0) ? -nums[idx] : nums[idx];
	}
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] > 0) {
			res.push_back(i + 1);
		}
	}
	return res;
}
vector<int> findDisappearedNumbers2(vector<int>& nums) {
	vector<int>res;
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] != nums[nums[i] - 1]) {
			swap(nums[i], nums[nums[i] - 1]);
			i--;
		}
	}
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] != i + 1) {
			res.push_back(i + 1);
		}
	}
	return res;
}
vector<int> findDisappearedNumbers3(vector<int>& nums) {
	vector<int>res;
	for (int i = 0; i < nums.size(); i++) {
		nums[(nums[i] - 1) % nums.size()] += nums.size();
	}
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] <= nums.size()) {
			res.push_back(i + 1);
		}
	}
	return res;
}