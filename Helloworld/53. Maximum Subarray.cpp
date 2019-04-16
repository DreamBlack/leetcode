/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
����˼·��
1����̬�滮
��ԭ����ֽ�ΪA[0:i]��С���⣬��ɵ�dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
�ؼ�������ϸ�������Ȿ��������Ϊ��ǰ����Ϊ����ʱ���¼���������ĺͣ�ʵ��֤���ǲ��Եģ��Ӷ������ж�ʲô����´��¿�ʼ��
�����ǵ��ۻ���С��0ʱ�Ͳ����ˣ���ʼ���¼��㣨��Ϊǰ��ĺ�С��0����֮��ļ��㲻���������Ч������ȫ���Բ�Ҫ�����Ա�ԭ��
˼·����Ϊ��ǰֵС��0�����ǵ�ǰֵ֮ǰ�ĺͿ��ܼ��ϵ�ǰֵ֮�����0����֮�����Ȼ��������Ч���ģ�
2�����Σ��ҷ��ξ�����д���ã�
��������м�һ��Ϊ����ͨ�����Ǵ��м�֣����Ҫ�������ݴ�С����������λ�����Ҳû���ˣ�������Ը�����ߵ�max���ұߵ�max��
�͵�ǰֵa���ࣻһ�ǣ����������У��������max�����ǣ�������ұߣ������ұ�max�����ǽ������a��������ҡ�һ�������˵����
���������£��͵ô�a��ʼ���ұ���ֱ��ͷβ�ۼӺͼ���max���൱�ڱ��������顣�����˼ҵ�˼·���ڴ˻����ϵĸĽ���ÿ�η���ͬʱ
��¼����߿�ʼ�ĺ��������ĺ�Ϊmaxl�����ұ߿�ʼ�ĺ��������ĺ�Ϊmaxr�������ܺ�sum������Ŀ��max���������������������
��l!=r������£��������ҵ�ֵ�ָ���ǰ����������ĸ�����ʱ�����ˡ�

*/
#include<vector>
#include<algorithm>
using namespace std;
int maxSubArray(vector<int>& nums) {
	//dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
	int sum = nums[0];
	int maxsum = sum;
	for (int i = 1; i < nums.size(); i++) {
		if (sum > 0) {
			sum += nums[i];
		}
		else {
			sum = nums[i];
		}
		if (sum > maxsum) {
			maxsum = sum;
		}
	}
	return maxsum;
}
//���η�
int maxSubArray2(vector<int>& nums) {

	int l = 0, r = nums.size() - 1;
	int maxl = 0, maxr = 0,sum=0;
	return maxSub(nums,l,r,maxl,maxr,sum);
}
int maxSub(vector<int>& nums, int l, int r, int& maxl, int& maxr,int&sum) {
	int mx = 0;
	if (l == r) {
		mx = maxl = maxr = sum=nums[l];
	}
	else {
		int mid = (l + r) / 2;
		int maxl1 = 0, maxr1 = 0,sum1=0;
		int maxl2 = 0, maxr2 = 0,sum2=0;
		int mx1 = maxSub(nums,l,mid,maxl1,maxr1,sum1);
		int mx2 = maxSub(nums, mid+1, r, maxl2, maxr2,sum2);
		maxl = max(maxl1, sum1 + maxl2);
		maxr = max(maxr2, sum2 + maxr1);
		mx = max(max(mx1,mx2),maxr1+maxl2);
		sum = sum1 + sum2;
	}
	return mx;
}
