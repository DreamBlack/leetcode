/*
Problem:Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.

To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].

To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].

Answer:
1、我用的是双指针。对矩阵每一行用双指针交换同时invert
2、其实，给的是vector，可以利用vector，const_reverse_iterator来用rbegin,rend反向得到数组中的数之后push_back，就可以达到flip的目的
3、vector<int>::const_reverse_iterator 逆循环因子
出现的问题：
使用双指针后却没有即使更新*/
#include<iostream>
#include<vector>
using namespace std;
vector<vector<int>> flipAndInvertImage(vector<vector<int>>& A) {
	
	int m = A.size();
	int n = (A[0].empty()) ? 0 : A[0].size();
	for (int i = 0; i < m; i++) {
		int p = 0, q = n - 1;
		if (n != 0) {
			while (p <= q && p < n&&q >= 0) {
				if (p == q) {
					A[i][p] = (A[i][q] == 1) ? 0 : 1;
				}
				else {
					int temp = A[i][p];
					A[i][p] = (A[i][q] == 1) ? 0 : 1;
					A[i][q] = (temp == 1) ? 0 : 1;
					
				}
				p++, q--;
			}

		}
	}
		return A;
	
}


	int main() {
		vector<vector<int>> A = { {1} };
		vector<vector<int>> B=flipAndInvertImage(A);
		printf("");
		return 0;
	}