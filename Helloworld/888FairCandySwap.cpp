/*
Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of candy that Bob has.

Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)

Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the candy bar that Bob must exchange.

If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.



Example 1:

Input: A = [1,1], B = [2,2]
Output: [1,2]
Example 2:

Input: A = [1,2], B = [2,3]
Output: [1,2]
Example 3:

Input: A = [2], B = [1,3]
Output: [2,3]
Example 4:

Input: A = [1,2,5], B = [2,4]
Output: [5,4]


Note:

1 <= A.length <= 10000
1 <= B.length <= 10000
1 <= A[i] <= 100000
1 <= B[i] <= 100000
It is guaranteed that Alice and Bob have different total amounts of candy.
It is guaranteed there exists an answer.
解决方案：
1、暴力法
2、这道题做的时候可能脑子不太清除没想起来。仔细考虑会发现这是一个二元一次方程求解问题。
A、B和分别为n,m。如果交换A中a和B中b即可，则有公式m-b+a=n+b-a即b=m-n+a/2。下面可以扫描A
中的每一个元素计算b，并再B中找是否有值为b的元素有则返回。为了加快查找可以用一个set存储B
的值
3、加速法fairCandySwap2
使用bitset：类似数组的结构，它的每一个元素只能是０或１，每个元素仅用１bit空间
*/
#include<vector>
#include<set>
#include<bitset>
using namespace std;
vector<int> fairCandySwap(vector<int>& A, vector<int>& B) {
	int m = 0, n = 0;
	for (int i = 0; i < A.size(); i++)n += A[i];
	set<int>s;
	for (int i = 0; i < B.size(); i++) {
		m += B[i];
		s.insert(B[i]);
	}
	int find = (m - n) / 2;
	for (int i = 0; i < A.size(); i++) {
		if (s.count(find + A[i]) == 1) {
			return { A[i],find + A[i] };
		}
	}
	return {};


}
vector<int> fairCandySwap2(vector<int>& A, vector<int>& B) {

	bitset<200002> bf;

	int sumA = 0, sumB = 0;
	for (auto n : A) {
		sumA += n;
	}
	for (auto n : B) {
		sumB += n;
		bf.set(2 * n);//set函数只有一个参数时，将参数下标处置为１
	}

	int diff = sumA - sumB;

	for (auto n : A) {
		int det = 2 * n - diff;
		if (det > 0 && det < 200002 && bf.test(det)) {//test函数用来查下标处的元素是０还是１，并返回false或true
			return { n, (2 * n - diff) / 2 };
		}
	}
	return {};
}