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
����˼·��
�����Լ�û���������������O(N^2)�Ľⷨ��������Ȼ����˫ָ�룬Ҳ���У���Ϊָ������Ҫ��ͷ����������ĵ���N^2
���������Ӧ���뵽�ÿռ任ʱ�䣬��¼�ؼ���Ϣ������ؼ���Ϣ����x%60��ֵ�ĸ���
1�����ѽⷨ
������ʱ����� cnt[(60-time[i]%60)%60]��Ϊ0������ôһ�Ժ�Ϊ0�ģ�����֮����Ҫ�ټӸ�%60��Ϊ��ʹ���ս����0-59
֮�䡣����ע��0,60��Ӧ�Ľ������0��������x������Ϊ0����˿��Ժ��ԡ�֮������cnt�м�¼x%60�ĸ���
2��
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
	//����ֻҪ����cnt��һ��
	for (int i = 1; i < 30; i++) {
		pairs += cnt[i] * cnt[60 - i];
	}
	//0��30���⴦�����Ƕ���(0,0),(30,30)��ӣ���˽����
	pairs += cnt[0] * (cnt[0] - 1) / 2;
	pairs += cnt[30] * (cnt[30] - 1) / 2;
	return pairs;
}