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
这个题比较巧妙，以后需要再看
1、暴力方法复杂度为O(K*N)，提示要求O(K+N)，显然是先遍历操作记录每次的inc，然后再遍历
一遍数组，使每个元素得到最终的值。
2、开始的时候想的是遍历操作，在头尾元素累加Inc，但是最后不知道如何恢复其它没记录inc元素的值
3、参考了网友的办法。头元素记录inc，尾元素之后的那个元素记录-inc。最后遍历的时候，从头往后累加。
每个元素的值，都等于当前值加上前面那个元素的值。这样的话，头元素之后，尾元素+1之前的元素就都能累加到inc，
而从尾元素+1开始的元素就不会累加了。但也不用怀疑会加的过多了
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