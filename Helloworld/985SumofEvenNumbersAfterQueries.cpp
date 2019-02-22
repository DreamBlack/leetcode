/*
Problem:We have an array A of integers, and an array queries of queries.
For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].  Then, the answer to 
the i-th query is the sum of the even values of A.(Here, the given index = queries[i][1] is a 0-based index, 
and each query permanently modifies the array A.)Return the answer to all queries.  Your answer array should
have answer[i] as the answer to the i-th query.
Example 1:

Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
Output: [8,6,2,4]
Explanation:
At the beginning, the array is [1,2,3,4].
After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.


Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
1 <= queries.length <= 10000
-10000 <= queries[i][0] <= 10000
0 <= queries[i][1] < A.length

解法：
先遍历A记录数组中所有偶数的和sum，之后每一次query均只改变A中某一个元素的值，根据原来元素的奇偶性和改变后元素
的奇偶性修改sum，并记录到输出数组中
注意点：
1、bval + val % 2 == 0写法错误，应改为(bval + val) % 2 == 0
2、each query permanently modifies the array A。没注意这句，查询结果会改变原数组中元素的值
*/
#include<vector>
using namespace std;
vector<int> sumEvenAfterQueries(vector<int>& A, vector<vector<int>>& queries) {
	int sum = 0;
	vector<int>result(queries.size());
	for (int i = 0; i < A.size(); i++) {
		if (A[i] % 2 == 0) {
			sum += A[i];
		}
	}
	for (int i = 0; i < queries.size(); i++) {
		int index = queries[i][1], val = queries[i][0];
		int bval = A[index];
		if (bval % 2 == 0) {
			sum -= bval;
		}
		if ((bval + val) % 2 == 0) {
			sum = sum + bval + val;
		}
		A[index] = bval + val;
		result[i] = sum;
	}
	return result;
}
int main() {
	vector<int>A = { 1,2,3,4 };
	vector<vector<int>>B = { {1, 0},{-3, 1},{-4, 0},{2, 3} };
	vector<int>result = sumEvenAfterQueries(A,B);
	for (int i = 0; i < result.size(); i++) {
		printf("%d,", result[i]);
	}
	return 0;
}