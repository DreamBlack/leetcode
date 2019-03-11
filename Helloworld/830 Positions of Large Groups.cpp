/*
In a string S of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".

Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.



Example 1:

Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
Example 2:

Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.
Example 3:

Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]
解题思路：
简单题。双指针。注意特殊情况。整个数组中只有一个group的时候，不要忘了输出。
*/
#include<vector>
using namespace std;
vector<vector<int>> largeGroupPositions(string S) {
	vector<vector<int>> res;
	int start = 0, end = 0;
	char last = S[0];
	for (int i = 1; i < S.length(); i++) {
		if (last == S[i])end = i;
		else {
			if (end - start + 1 >= 3) {
				vector<int> tmp = { start,end };
				res.push_back({ start,end });
			}
			start = i;
			end = i;
			last = S[i];
		}
	}
	if (end - start + 1 >= 3) {
		res.push_back({ start,end });//最后的一个group不要忘了
	}
	return res;
}