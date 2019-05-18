/*
��Ŀ������
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.
Note: The n belongs to [1, 10,000].
����˼·��
��ϸ�������⼴��
��Ҫ���ǵ������������Ҫ������case���޸Ĵ��룬���һ�θ��塣
*/
#include<vector>
#include<map>
#include<set>
#include<iterator>
using namespace std;
bool checkPossibility(vector<int>& nums) {
	//���ҵ���һ�ν����λ�õ��±�Ϊindex
	int index = -1;
	for (int i = 0; i < nums.size() - 1; i++) {
		if (nums[i] > nums[i + 1]) {
			index = i;
			break;
		}
	}
	if (index == -1)return true;
	//��һ��index������ǲ������򣬷���return false
	for (int i = index + 1; i < nums.size() - 1; i++) {
		if (nums[i] > nums[i + 1]) {
			return false;
		}
	}
	//����������������ͷ�������ߵ����ڶ���λ�ã�û���⣬����true
	if (index == 0||index==nums.size()-2)return true;
	else {
		//�������index��ߵĶ����ұߵ�С���Ǹ�Index���ڵ�Ԫ�ؼ���
		if (nums[index - 1] <= nums[index + 1])
			return true;
		else {//����©���ˣ�����һ����Index�������ʱ��������ѡ��1��index���ڵ�Ԫ��2�ĺ�����Ǹ�Ԫ�ء�����һ��ʼ©����2���������
			//���index��ߵı�index�ұߵĴ�û��ϵ��ֻҪindex��߱��ұߵĵڶ�������У���Ϊ���Ը�index�ұߵ�һ��Ԫ�ص�λ��ѽ
			if (index+2<nums.size()&&nums[index] <= nums[index + 2]) {
				return true;
			}else
				return false;
		}
			
	}
}
bool checkPossibility2(vector<int>& nums) {
	//����������У���Ϊnums�п���Ϊ��������
	vector<set<int>>mm(10001,set<int>());
	for (int i = 0; i < nums.size(); i++) {
		mm[nums[i]].insert(i);
	}
	int last = -1,count=0;
	for (int i = 0; i < 10001; i++) {
		if (!mm[i].empty()) {
			for (set<int>::iterator it = mm[i].begin(); it != mm[i].end(); it++) {
				if (*it < last) {
					count++;
					if (count > 1)
						return false;
				}
			}
		}
		
	}
	return true;

}