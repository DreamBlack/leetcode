/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
����˼·��
1����ʼ���뷨�ǴӺ���ǰÿ��ȡ��i,i-1)�н�С���Ǹ���
���Ƕ���[0,2,2,1]���������˵����ǲ��Ե�
2����ʵ������˵��¥��ÿ��Ҫô��һ����Ҫô���������������һ������������cost�����������
���������һ������dp[]����ŵ���ÿһ������Ҫ�Ļ���ֵ���������ս������dp[cost.length]��ֵ��
��Ϊÿ�ο�����1�����2�㣬���ҿ��Դ�0���ߴ�1��ʼ�����Կ��Եõ�dp[0]Ϊ0��dp[1]Ϊ0����2��ʼ��
dp[i]������ͨ��dp[i-2]��2�����ͨ��dp[i-1]��һ�㵽�����i-2��i-1����Ҫ���ѵ�ֵ�ֱ�Ϊcost[i-2]��cost[i-1]��
���ԣ�dp[i] = min(dp[i-2] + cost[i-2], dp[i-1] + cost[i-1])�����㷨�Ŀռ临�Ӷ�ΪO(n)��ʱ�临�Ӷ�ΪO(n)��
*/
#include<vector>
using namespace std;
int minCostClimbingStairs(vector<int>& cost) {
	int f1 = 0, f2 = 0;
	for (int i = cost.size() - 1; i >= 0; i--) {
		int m = (f1 > f2) ? f2 : f1;
		int nowcost = cost[i] + m;
		f2 = f1;
		f1 = nowcost;
	}
	return (f1 > f2) ? f2 : f1;
}
int main() {
	 vector<int> A = { 1,1,0,0 };
	int c = minCostClimbingStairs(A);
	//int m = A.size(), n = A[0].size();
	//for (int i = 0; i < m; i++) {
		//for (int j = 0; j < n; j++) {
			printf("%d  ",c);
		//}
	//}


	return 0;
}