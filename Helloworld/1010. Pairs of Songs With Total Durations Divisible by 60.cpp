/*
In a list of songs, the i-th song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.



Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.


Note:

1 <= time.length <= 60000
1 <= time[i] <= 500
解题思路：
这题自己没想起来，如果不用O(N^2)的解法，先排序，然后用双指针，也不行，因为指针总是要从头遍历，变相的等于N^2
这种情况下应该想到用空间换时间，记录关键信息，这里关键信息就是x%60的值的个数
1、网友解法
遍历的时候，如果 cnt[(60-time[i]%60)%60]不为0则有这么一对和为0的，其中之所以要再加个%60是为了使最终结果在0-59
之间。这里注意0,60对应的结果都是0，但条件x不可能为0，因此可以忽略。之后再在cnt中记录x%60的个数
2、
*/
#include<vector>
#include<algorithm>
using namespace std;
int numPairsDivisibleBy60(vector<int>& time) {
	int pairs=0;
	vector<int> cnt(60,0);
	for (int i = 0; i < time.size(); i++) {
		pairs += cnt[(60-time[i]%60)%60];//1-60
		cnt[time[i] % 60]++;//0-59
	}
	return pairs;
}
int numPairsDivisibleBy602(vector<int>& time) {
	int pairs = 0;
	vector<int> cnt(60, 0);
	for (int i = 0; i < time.size(); i++) {
		cnt[time[i] % 60]++;//0-59
	}
	//下面只要遍历cnt的一半
	for (int i = 1; i < 30; i++) {
		pairs += cnt[i] * cnt[60 - i];
	}
	//0和30特殊处理，他们都是(0,0),(30,30)组队，因此结果是
	pairs += cnt[0] * (cnt[0] - 1) / 2;
	pairs += cnt[30] * (cnt[30] - 1) / 2;
	return pairs;
}