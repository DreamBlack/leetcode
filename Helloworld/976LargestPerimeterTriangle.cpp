/*
Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.

If it is impossible to form any triangle of non-zero area, return 0.



Example 1:

Input: [2,1,2]
Output: 5
Example 2:

Input: [1,2,1]
Output: 0
Example 3:

Input: [3,2,3,4]
Output: 10
Example 4:

Input: [3,6,2,3]
Output: 8


Note:

3 <= A.length <= 10000
1 <= A[i] <= 10^6
解题思路：
将数组升序排序，从最后一个元素开始，寻找能构成三角形的最大的另外两个元素。
如有这样的两个元素则返回周长，否则返回0
这个方法基于：数组是升序排序的，如果元素i左边两个元素不能使这三条边构成三角形，则
i左边的其他元素要更小，更不可能构成。因此，只需看i左边两个元素即可
*/
#include<vector>
#include<algorithm>
using namespace std;
int largestPerimeter(vector<int>& A) {
	int result = 0;
	sort(A.begin(), A.end());
	for (int i = A.size() - 1; i >= 2; i--) {
		if (A[i] < A[i - 1] + A[i - 2])
		{
			return A[i] + A[i - 1] + A[i - 2];
		}
	}
	return result;
}