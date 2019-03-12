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
解题思路：
1、开始的想法是从后往前每次取（i,i-1)中较小的那个。
但是对于[0,2,2,1]这个数组来说结果是不对的
2、其实本题来说，楼梯每次要么爬一步，要么爬两步，如果把爬一步和爬两步的cost都计算出来，
如果我们用一个数组dp[]来存放到达每一层所需要的花费值。则则最终结果是求dp[cost.length]的值。
因为每次可以走1层或者2层，并且可以从0或者从1开始，所以可以得到dp[0]为0，dp[1]为0。从2开始，
dp[i]可以由通过dp[i-2]走2层或者通过dp[i-1]走一层到达，而这i-2和i-1层所要花费的值分别为cost[i-2]和cost[i-1]，
所以，dp[i] = min(dp[i-2] + cost[i-2], dp[i-1] + cost[i-1])。该算法的空间复杂度为O(n)，时间复杂度为O(n)。
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