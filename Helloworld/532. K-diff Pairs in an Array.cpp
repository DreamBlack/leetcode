/*
��Ŀ������
Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].
����˼·��
����⻹�á�����������������ˣ�Ҫע��Ļ���һЩ�߽����˵��ɫ���ʱ�ڴ����case����²��ҳ�����
1��k=0�����
2��k<0�����
�ⷨ1��
����֮����ֲ��ҡ�ע��k=0�������
PS�������ڻ�д���ֲ����ˡ�����
�ⷨ2:
�ռ任ʱ�䡣
*/
#include<vector>
#include<map>
#include<set>
#include<algorithm>
#include<iterator>
using namespace std;
int findPairs(vector<int>& nums, int k) {
	/*
	�ȶ���������֮�����ÿһ��Ԫ������ö��ֲ��ұ�����k������
	ע����ֲ���mid�ı仯��mid-1��mid+1����Ȼ��������ѭ��
	*/
	//��ʼ���������е��ڵ���������k=0��ʱ����ѭ��
	sort(nums.begin(), nums.end());
	int count = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (i > 0 && nums[i] == nums[i - 1])
			continue;
		//���ֲ���
		int p = i+1,end= nums.size() - 1;
		//while (p < nums.size() && nums[p] == nums[i])p++;//�������������е��ڵ���������k=0��ʱ����ѭ��
		int mid = 0;
		while (p <= end) {
			mid = (p + end) / 2;
			if (nums[mid] == nums[i] + k) {
				count++;
				break;
			}
			if (nums[mid] > nums[i] + k)
				end = mid-1;//ͬ��ע��������mid-1������mid
			else 
				p = mid+1;//ע��������mid+1����Ȼ����ѭ��
		}

	}
	return count;
}
int findPairs2(vector<int>& nums, int k) {
	/*
	�ȱ�������ͬʱ����set1��set2��set1�Ǳ����set��set2��ԭ����ÿ��Ԫ�ؼ�k���
	֮����set1�в���set2�е�Ԫ�ز�����
	*/
	if (k < 0)return 0;//����Ҫ��k����С��0
	map<int, int>m;
	set<int> s;
	int count = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (m.count(nums[i]) == 1) {
			m[nums[i]]++;
		}
		else {
			m.insert(make_pair(nums[i],1));
		}
		
		s.insert(nums[i]+k);
	}
	for (set<int>::iterator it = s.begin(); it != s.end(); it++) {
		if (k != 0) {
			if (m.count(*it) == 1) {
				count++;
			}
		}
		else {
			if (m.count(*it) == 1&&m[*it]>1) {
				count++;
			}
		}
		
	}
	return count;
}