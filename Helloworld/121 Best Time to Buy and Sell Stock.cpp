/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
			 Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
解题思路：
扫描数组保存min，如果比min大，计算min到i的profit，并进行更新即可。
maxProfit写的比较复杂，maxProfit2是改进版本
*/
#include<vector>
using namespace std;
int maxProfit5(vector<int>& prices) {
	if (prices.size() == 0)return 0;
	int min = prices[0], max = min;
	int profit = 0;
	for (int i = 1; i < prices.size(); i++) {
		if (prices[i] < min) {
			if (max - min > profit) {
				profit = max - min;
			}
			min = prices[i];
			max = min;
		}
		if (prices[i] > max) {
			max = prices[i];
		}
		if (i == prices.size() - 1 && max - min > profit) {

			profit = max - min;

		}
	}
	return profit;

}
int maxProfit6(vector<int>& prices) {
	//写的好看点
	//不需要保存max
	if (prices.size() == 0)return 0;
	int min = prices[0], max = min;
	int profit = 0;
	for (int i = 1; i < prices.size(); i++) {
		if (prices[i] < min) {
			//另起炉灶
			min = prices[i];
		}
		else if(prices[i]-min>profit){//即prices[i]>min
			profit = prices[i] - min;
		}
		
	}
	return profit;

}