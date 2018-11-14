/*
Problem: Assume you have an array of length n initialized with all 0's and are given k update operations.
Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray 
A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:
Given:
    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]
Output:
    [-2, 0, 3, 5, 3]
Explanation:
Initial state:
[ 0, 0, 0, 0, 0 ]
After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]
After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]
After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]
Hint:
Thinking of using advanced data structures? You are thinking it too complicated.
For each update operation, do you really need to update all elements between i and j?
Update only the first and end element is sufficient.
The optimal time complexity is O(k + n) and uses O(1) extra space.

Solution:
�����Ƚ�����Ժ���Ҫ�ٿ�
1�������������Ӷ�ΪO(K*N)����ʾҪ��O(K+N)����Ȼ���ȱ���������¼ÿ�ε�inc��Ȼ���ٱ���
һ�����飬ʹÿ��Ԫ�صõ����յ�ֵ��
2����ʼ��ʱ������Ǳ�����������ͷβԪ���ۼ�Inc���������֪����λָ�����û��¼incԪ�ص�ֵ
3���ο������ѵİ취��ͷԪ�ؼ�¼inc��βԪ��֮����Ǹ�Ԫ�ؼ�¼-inc����������ʱ�򣬴�ͷ�����ۼӡ�
ÿ��Ԫ�ص�ֵ�������ڵ�ǰֵ����ǰ���Ǹ�Ԫ�ص�ֵ�������Ļ���ͷԪ��֮��βԪ��+1֮ǰ��Ԫ�ؾͶ����ۼӵ�inc��
����βԪ��+1��ʼ��Ԫ�ؾͲ����ۼ��ˡ���Ҳ���û��ɻ�ӵĹ�����
*/
#include<vector>
using namespace std;
vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
	vector<int> res(length+1,0);
	for (int i = 0; i < updates.size(); i++) {
		res[updates[i][0]] += updates[i][2];
		res[updates[i][1] + 1] += -updates[i][2];
	}
	for (int i = 1; i < length; i++) {
		res[i] += res[i - 1];
	}
	res.pop_back();
	return res;
}