/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
����˼·��
1��moveZeroes
ɨ������ʱ��¼0Ԫ�ظ���Ϊcount�����ڷ���Ԫ������λ�ü�Ϊi-count��������count��λ�õ�Ԫ����Ϊ0
2��moveZeroes2
�跨��������ٱ���һ����0
��λ��i���ַ�0Ԫ��ʱ���������ȷ��λ�ã��������ȷ��Ҫ�����е�����£�λ��i��Ԫ��Ϊ0Ҫô��Ϊ0.
���λ��i��Ԫ�����λ��ҲΪiʱ�򲻱䣬����Nums[i]��Ϊ0���Ͳ�������ٱ���
3��moveZeroes3
ָ�뷨����¼���һ����Ϊ0��λ��ΪlastNonZeroFoundAt.
����ʹ�õ���swap
*/
#include<vector>
using namespace std;
void moveZeroes(vector<int>& nums) {
	int count = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] == 0)count++;
		else {
			nums[i - count] = nums[i];
		}
	}
	for (int i = nums.size() - count; i < nums.size(); i++) {
		nums[i] = 0;
	}
}
void moveZeroes2(vector<int>& nums) {
	int count = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] == 0)count++;
		else {
			nums[i - count] = nums[i];
			if (count != 0)nums[i] = 0;
		}
	}

}
void moveZeroes3(vector<int>& nums) {
	for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.size(); cur++) {
		if (nums[cur] != 0) {
			swap(nums[lastNonZeroFoundAt++], nums[cur]);
		}
	}
}