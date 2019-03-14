/*
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
´òÓ¡Ñî»ÔÈý½ÇÐÎ

*/
#include<vector>
#include<algorithm>
using namespace std;
vector<vector<int>> generate(int numRows) {
	vector<vector<int>> ret;
	for (int i = 0; i < numRows; i++) {
		vector<int>tmp(i + 1, 1);
		for (int j = 1; j < i - 1; j++) {
			tmp[j] = ret[i - 1][j - 1] + ret[i - 1][j];
		}
		ret.push_back(tmp);
	}
	return ret;
}