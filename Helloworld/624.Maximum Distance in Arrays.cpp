/*
��Ŀ������
Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:

Input:
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation:
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.


Note:

Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].
����˼·��
1��ÿ�����鶼������ģ������ǴӲ�ͬ�������и�ȡһ�����֣�ʹ�����������ֵĲ�ľ���ֵ�����������������ֵ����ô�����룬��Ȼ���鶼������ģ�
��ô��ľ���ֵ�����������ֿ϶��Ƿֱ�λ��������׺�β��ע����Ŀ��˵Ҫ�Ӳ�ͬ��������ȡ������ô��ʹĳ���������β���ܴ�Ҳ���С�
��ʱҪô���صڶ������ֺ���С���־��Բ��������ֺ͵ڶ�С���־��Բ��еĽϴ�ֵ

ע�⣡������ôҪ���صڶ������ֺ���С���־��Բ��������ֺ͵ڶ�С���־��Բ��еĽϴ�ֵ��û���ǵ��ڶ������
2���������ѵ�˼·��û�����:��β�ʹ��map���ݽṹ����O(1)�ռ临�Ӷ������ɽ��
����Ҫ��map������ÿ���������min��max��ֻ��Ҫ����res=���ܵĽ��ֵ,start=���е���������С����,end=���е�������������
�ӵڶ��������鿪ʼ���Ƚ�abs(end-array[i][0])��abs(array[i][last]-start)�Լ���һ��res��ȡ���Ĳ�����res��
ͬʱ����start��end

*/
#include<vector>
#include<map>
#include<algorithm>
#include<iterator>
using namespace std;
int maxDistance(vector<vector<int>>& arrays) {
	map<int,int>mins, maxs;
	for (int i = 0; i< arrays.size(); i++) {
		mins[arrays[0][0]] = i;
		maxs[arrays[0][arrays[0].size() - 1]] =i;
	}
	map<int, int>::iterator minit = mins.begin();//��Ӧ��start
	map<int, int>::iterator maxit = maxs.end();//��Ӧ��end
	maxit--;
	if (maxit->second != minit->second)
		return maxit->first - minit->first;
	else//��Ӧ��ÿ�αȽ���������ֵȡ����
		return max(abs((--maxit)->first-minit->first),abs(maxit->first - (++minit)->first));//����û���ǵ�
}
int maxDistance2(vector<vector<int>>& arrays) {
	int res=0, start = arrays[0][0], end = arrays[0][arrays[0].size() - 1];
	for (int i = 1; i < arrays.size(); i++) {
		//���ﲢû�н�resֱ����end-start�ȣ����Բ�����ּ����res��ͬһ�������еõ����������
		res = max(res, abs(end - arrays[i][0]), abs(arrays[i][arrays[i].size() - 1]- start));
		end = max(end, arrays[i][arrays[i].size() - 1]);
		start = min(start, arrays[i][0]);
	}
	return res;
}