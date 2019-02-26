/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list. 

For example, 
                         0          1         2          3        4
Assume that words = ["practice", "makes", "perfect", "coding", "makes"]. 

Given word1 = “coding”, word2 = “practice”, return 3. 
Given word1 = "makes", word2 = "coding", return 1. 



题意：给定一个数组和两个单词word1,word2,返回两者最近的距离

word1不等于word2,但是两者可能在数组中出现多次

解题思路：
1、暴力法
这个题乍一看被吓到了，毕竟看到有string，立马吓一跳
先找到最近的word1的位置i，然后从i开始分别往前往后找最近的word2记录距离；
之后再找下一个word1的位置重复上述过程
时间O(N^2)
2、一次遍历
法一的时间主要浪费在同于同一个单词有多次查找的时间消耗。
假设word1在word2之前出现，则从头开始访问数组，记录word1位置flag1，再记录最近的word2位置flag2
即可得到一个距离result，之后即使再出现新的word2，第一次发现的word1到它的距离不可能小于第一个word2；
对于word2而言，如果在下一个word1出现之前有多个word2出现，则离word1最近的word2才能使两词之间距离最小。
所以此时更新flag1=-1,继续访问数据，当出现word1时更新flag1，当出现word2时更新flag2,
当flag1 != -1 && flag2 != -1,计算距离，考虑是否更新。
*/
#include<vector>
using namespace std;
int shortestDistance(vector<string>& words, string word1, string word2) {
	int result = INT_MAX;
	int flag1 = -1, flag2 = -1;
	for (int i = 0; i < words.size(); i++) {
		if (words[i].compare(word1) == 0)
			flag1 = i;
		if (words[i].compare(word2) == 0)
			flag2 = i;
		if (flag1 != -1 && flag2 != -1) {
			if (result > abs(flag1 - flag2)) {
				result = abs(flag1 - flag2);
			}
			flag1 = (flag1 < flag2) ? -1 : flag1;
			flag2 = (flag1 > flag2) ? -1 : flag2;
		}
	}
	return result;
}
int main() {
	vector<string>A = { "practice", "makes", "perfect", "coding", "makes" };
	string word1 = "coding",word2 = "makes";
	printf("%d", shortestDistance(A,word1,word2));
	return 0;
}