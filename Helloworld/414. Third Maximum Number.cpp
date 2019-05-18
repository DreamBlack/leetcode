/*
��Ŀ����
Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
����˼·
1���������飬��¼�����ֵ���ڶ���͵������ֵ
����������һЩ����
    a.�ж��ֵ��ȵ����������Ҫ���жϵ�ʱ��ܿ����ڵ����
	b.ֵ��С������-2147483648����32λint����Сֵ�������int_32����int_min�����룬Ҫ��64λlong long ��INT64_MIN
INT_MIN��INT32_MIN����-2147483648
long��long long����һ��������64λ��
*/
#include<vector>
#include<map>
#include<set>
using namespace std;
int thirdMax(vector<int>& nums) {
	long long max3 = INT64_MIN, max2 = INT64_MIN, max1 = nums[0];
	for (int i = 1; i < nums.size(); i++) {
		if (nums[i] > max1) {
			max3 = max2;
			max2 = max1;
			max1 = nums[i];
		}
		else if (nums[i] < max1&&nums[i] > max2) {//a.�ж��ֵ��ȵ����������Ҫ���жϵ�ʱ��ܿ����ڵ����
			max3 = max2;
			max2 = nums[i];
		}
		else if (nums[i] < max2&&nums[i] > max3) {//a.�ж��ֵ��ȵ����������Ҫ���жϵ�ʱ��ܿ����ڵ����
			max3 = nums[i];
		}
	}
	if (max3 == INT64_MIN) {//�������������պ���-2147483648������ͻ����max1�ˣ����Գ�ʼ����ʱ��Ҫ��INT64_MIN
		return max1;
	}
	else
		return max3;

	
}