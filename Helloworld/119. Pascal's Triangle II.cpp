/*
使用O(K)空间复杂度打印杨辉三角形
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

解题思路：找规律

*/
#include<vector>
#include<algorithm>
using namespace std;
vector<int> generate(int numRows) {
	vector<int> ret(numRows+1,0);
	ret[0] = 1;
	for (int i = 0; i <= numRows; i++) {
		
		for (int j = i; j >=1; j--) {
			ret[j] += ret[j - 1] ;
		}
	}
	return ret;
}