/*
��Ŀ������
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
����˼·��
1����һ���뷨��Ȼ������һ�����鰴˳��һ����дֵ��ȥ
2���ڶ����뷨����1�Ļ�����ֱ�Ӽ���������ĳԪ���ھ������ж�Ӧ��λ��
3���ռ�O(1)
�������a1,a2,����,an-1.ͷβ����k����Ȼ��ֱ�reverse�������������k>n-k�Ļ�û�����л����ˡ�
������˼·������
�����������ս������Ҫ��ǰn-k���Ƶ�β��,��k���Ƶ�ͷ��������˳����Ȼ��ԭ����һ���������ȷ�תǰn-k������ת��k���������巴ת
һ�²��Ϳ�����ô���Ͼ��������������η�ת�õ���˳�򻹺�ԭ��һ��

4��Approach #4 Using Cyclic Replacements [Accepted]���û�������
��2�����ǿ���֪����nums2[i + k] = nums[i]�ھ������е�i��λ������������Ӧ���ڵ�(i+k)%n��λ�ã���˿���ͨ��ѭ���滻�ķ���
����k���滻�õ����.ͬ����O(N)+O(1)

Ҫע��߽�������
if (k > n)k = k % n;//k�п��ܱ�n��
*/
#include<vector>
#include<map>
#include<set>
#include<algorithm>
using namespace std;
void rotate(vector<int>& nums, int k) {
	//�Ȱ�ĩβk��Ԫ�طŵ�������ͷ����Ȼ��reverse������k��ͷ��Ԫ�أ��ٰ�ԭ����ʣ�µ�Ԫ�ز������������
	int n = nums.size();
	if (k > n)k = k % n;//k�п��ܱ�n��
	vector<int>nums2(n, 0);
	for (int i = n - 1; i >= n - k; i--) {
		nums2[n - 1 - i] = nums[i];
	}
	reverse(nums2.begin(),nums2.begin()+k);
	for (int i = k; i < n; i++) {
		nums2[i] = nums[i - k];
	}
	nums.assign(nums2.begin(),nums2.begin()+n);
}
void rotate2(vector<int>& nums, int k) {
	//ֱ�Ӽ���ÿ����Ӧ���ڵ�����λ��
	//����ĵ�����λ��Ϊi+k���ұ�Ҫѡ��ĵ�����λ��Ϊi-(n-k)
	int n = nums.size();
	if (k > n)k = k % n;//k�п��ܱ�n��
	vector<int>nums2(n, 0);
	for (int i = 0; i < n - k; i++) {
		nums2[i + k] = nums[i];
	}
	for (int i = n - k; i < n; i++) {
		nums2[i - (n - k)] = nums[i];//=(i-(n-k)+n)%n
	}
	nums.assign(nums2.begin(), nums2.begin() + n);
}
void rotate3(vector<int>& nums, int k) {
	//�������Ϊ��������0--n-k-1��n-k+1-_n-1,�ֱ�reverse��������reverse����
	int n = nums.size();
	if (k >= n)k = k % n;//k�п��ܱ�n��

	reverse(nums.begin(), nums.begin() + n - k);
	reverse(nums.begin() + n - k, nums.begin() + n);
	reverse(nums.begin(), nums.begin() + n);
	
	
}
void rotate4(vector<int>& nums, int k) {
	//�������Ϊ��������0--n-k-1��n-k+1-_n-1,�ֱ�reverse��������reverse����
	int n = nums.size();
	if (k >= n)k = k % n;//k�п��ܱ�n��
	int count = 0;
	for (int i = 0; count<n; i++) {//ע���˳���������count��С�йأ�������i
		int current = i;
		int pre = nums[current];
		do {
			int nxt = (current + k) % n;
			int tmp = nums[nxt];
			nums[nxt] = pre;
			pre = tmp;
			current = nxt;
			count++;
		} while (i != current);
	}
}