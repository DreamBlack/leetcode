/*
题目描述：
Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False
Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.
解题思路：
写不好，简单题写了很久，主要原因在于if条件没有写清楚，漏掉了一些情况导致很多用例出错
*/
#include<vector>
#include<map>
#include<set>
using namespace std;
bool canPlaceFlowers(vector<int>& flowerbed, int n) {
	if(n == 0)return true;//边界条件，这里要注意
	flowerbed.insert(flowerbed.begin(), 0);
	flowerbed.push_back(0);
	for (int i = 1; i <= flowerbed.size() - 2; i++) {
		if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0)
		{//最好不要把判断写成条件太多的形式，很容易漏掉某些情况
			n--;
			if (n == 0)return true;
		}
		if (flowerbed[i] == 1 || flowerbed[i + 1] == 0) {//这里判断条件写错了，导致用例【0,1,0,1,0,1,0,0,0】过不了
			i++;
		}



	}
	return false;
}
bool canPlaceFlowers2(vector<int>& flowerbed, int n) {
	if (n == 0)return true;//边界条件，这里要注意
	flowerbed.push_back(0);
	for (int i = 1; i <= flowerbed.size() - 2; i++) {
		if (flowerbed[i] == 1)
		{
			i++;
		}
		else {
			if (flowerbed[i + 1] == 0) {
				n--;
				i++;
				if (n == 0)return true;
			}
			else {
				i += 2;
			}
			
		}

	}
	return false;
}