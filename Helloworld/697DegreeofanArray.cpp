/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
����˼·��
�տ�ʼ��ʱ��һֱ������ô�����ܼ�����Ԫ���������г��ֵĴ����ȷ�������ʵ˼·�ܼ򵥣���Щ���鶼
��mapȥ��������ֻҪ�뵽���Ƕ���ÿһ��������������ȵ�Ԫ�أ���¼����ʼλ��i,j����j-i+1����һ����������
�������顣����С��j-i+1����
��һ��
�ȱ������飬��¼ÿ��������ֹ��ͳ��ִ������ֱ���first,end��count��
�ڶ������count���������
�����飬�������飬����ÿһ��Ԫ�ؿ���count�ǲ��ǵ���degree����������end-start+1�Ƿ�ȵ�ǰminС
������
��һ�Ļ����ϣ�������һ�α�����
��Ҫע�����1��degree=0 2��degree���޸Ĳ�Ӧ�ð����ڵ�һ����else���棬�����ᵼ�����硾1,2�������ֶ�Ϊ1
������ļ������
*/
#include<vector>
#include<map>
using namespace std;
int findShortestSubArray(vector<int>& nums) {
	map<int, int>first, end, count;
	//��¼first,end,count
	for (int i = 0; i < nums.size(); i++) {
		//��һ�γ��֣���¼first,count,end
		if (first.count(nums[i]) == 0) {
			first.insert(pair<int, int>(nums[i], i));
			count.insert(pair<int, int>(nums[i], 1));
			end.insert(pair<int, int>(nums[i], i));
		}
		else {
			//���ֹ�������count,end
			count[nums[i]]++;
			end[nums[i]] = i;
		}
	}
	//��count�����ֵ
	int degree = 0;
	for (map<int,int>::iterator it = count.begin(); it != count.end(); it++) {
		if (it->second > degree) {
			degree = it->second;
		}
	}
	int min = nums.size();
	for (int i = 0; i < nums.size(); i++) {
		if (count[nums[i]]== degree) {
			if (end[nums[i]] - first[nums[i]] + 1 < min) {
				min = end[nums[i]] - first[nums[i]] + 1;
			}
		}
	}
	return min;

}
int findShortestSubArray2(vector<int>& nums) {
	map<int, int>first, count;
	int degree = 0, min = nums.size();
	//��¼first,count
	for (int i = 0; i < nums.size(); i++) {
		//��һ�γ��֣���¼first,count,end
		if (first.count(nums[i]) == 0) {
			first.insert(pair<int, int>(nums[i], i));
			count.insert(pair<int, int>(nums[i], 1));
		}
		else {
			count[nums[i]]++;
		}
			//���ֹ�������count,end
		
			if (count[nums[i]] > degree) {
				degree = count[nums[i]];
				min = i - first[nums[i]] + 1;
			}
			else if (count[nums[i]] == degree&&i - first[nums[i]] + 1 < min) {
				min = i - first[nums[i]] + 1;
				}
		
		
	}
	return min;
}