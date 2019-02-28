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
���������
1��������
2�����������ʱ��������Ӳ�̫���û����������ϸ���ǻᷢ������һ����Ԫһ�η���������⡣
A��B�ͷֱ�Ϊn,m���������A��a��B��b���ɣ����й�ʽm-b+a=n+b-a��b=m-n+a/2���������ɨ��A
�е�ÿһ��Ԫ�ؼ���b������B�����Ƿ���ֵΪb��Ԫ�����򷵻ء�Ϊ�˼ӿ���ҿ�����һ��set�洢B
��ֵ
3�����ٷ�fairCandySwap2
ʹ��bitset����������Ľṹ������ÿһ��Ԫ��ֻ���ǣ��򣱣�ÿ��Ԫ�ؽ��ã�bit�ռ�
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
		bf.set(2 * n);//set����ֻ��һ������ʱ���������±괦��Ϊ��
	}

	int diff = sumA - sumB;

	for (auto n : A) {
		int det = 2 * n - diff;
		if (det > 0 && det < 200002 && bf.test(det)) {//test�����������±괦��Ԫ���ǣ����ǣ���������false��true
			return { n, (2 * n - diff) / 2 };
		}
	}
	return {};
}