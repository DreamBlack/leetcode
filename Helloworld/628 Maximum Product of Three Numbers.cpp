/*
Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6


Example 2:

Input: [1,2,3,4]
Output: 24


Note:

The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
�ڳ��ȴ��ڵ���3���������ҵ��������˻����Ľ��
���˼·
1��ɨ��һ��
�տ�ʼ��˼·��ɨ��һ���ҵ���󡢵ڶ��󡢵�����������͵�һС���ڶ�С�ĸ�����Ȼ����ݽ���ж���
Ҫô���������������ĳ˻����Ҫô��������С��������������ĳ˻������Ϊ�����������ж���
���Ե��³���д�ĺܸ��ӣ�������ж���ʱ�����������������������С�
��ʵ����Ҫ������������ֱ�Ӽ�¼������������С���������������ظ������Ƚ���С������������������ġ�
�˻����ɡ�
֮���Կ�����������Ϊ�����������ֻ����������Ȼ�����������ĳ˻��������и����Ļ���������С��������
��������п��������ģ���˼�¼��С�������������С�Ĳ�ȫ�Ǹ������������ǰ���������в�����
2������
�����򣬺�1��˼·��
*/
#include<vector>
#include<algorithm>
using namespace std;
int maximumProduct(vector<int>& nums) {
	sort(nums.begin(), nums.end());
	int n = nums.size();
	int a = nums[n - 1] * nums[n - 2] * nums[n - 3],
		b = nums[0] * nums[1] * nums[n - 1];
	return (a > b) ? a : b;


}
int maximumProduct2(vector<int>& nums) {
	int max1= INT32_MIN, max2 = INT32_MIN, max3 = INT32_MIN;
	int min1= INT32_MAX, min2 = INT32_MAX;
	for (int i = 0; i < nums.size(); i++) {
		if (nums[i] >= max1) {
			max3 = max2;
			max2 = max1;
			max1 = nums[i];
		}
		else if (nums[i] >= max2) {
			max3 = max2;
			max2 = nums[i];
		}
		else if (nums[i] >= max3) {
			max3 = nums[i];
		}
		if (nums[i] <= min1) {
			min2 = min1;
			min1 = nums[i];
		}
		else if (nums[i] <= min2) {
			min2 = nums[i];
		}
	}
	int a = max1 * max2*max3;
	int b = min1 * min2*max1;
	return (a > b) ? a : b;
}