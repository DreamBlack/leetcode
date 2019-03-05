/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
			 Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
			 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
			 engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
解题思路：
1、maxProfit1
先找到波谷，再找到波峰。找多个波谷波峰对
2、maxProfit2
*/
#include<vector>
using namespace std;
int maxProfit1(vector<int>& prices) {
	int profit = 0;
	int s = -1, e = -1, i = 1;
	while (i < prices.size()) {
		while (i < prices.size() && prices[i] < prices[i - 1])i++;
		s = i - 1;
		if (s >= prices.size() - 1)return profit;
		while (i<prices.size() && prices[i]>prices[i - 1])i++;
		e = i - 1;

		profit += prices[e] - prices[s];
		i++;
	}
	return profit;
}
int maxProfit(vector<int>& prices) {
	int profit = 0;
	for (int i = 1; i < prices.size(); i++) {
		if (prices[i] - prices[i - 1] > 0)
			profit += prices[i] - prices[i - 1];
	}
	return profit;
}


int main() {
	vector<int>A = { 7,1,5,3,6,4 };
	printf("%d", maxProfit2(A));
	return 0;
}