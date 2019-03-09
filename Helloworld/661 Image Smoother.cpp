/*
Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].
解题思路：
不说了，就是按题意求，写起来很烦的一个。但解题思路上没啥新意
*/
#include<vector>
using namespace std;
vector<vector<int>> imageSmoother(vector<vector<int>>& M) {
	int m = M.size(), n = M[0].size();
	vector <  vector<int> > rec(m, vector<int>(n, 0));
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			int count = 1, sum = M[i][j];
			if (i == 0) {
				//up元素不用加
			}
			else
			{
				//up元素要加
				sum += M[i - 1][j];
				count++;
			}
			if (j == 0) {
				//left元素不用加
			}
			else {
				sum += M[i][j - 1];
				count++;
			}
			if (i == m - 1) {
				//下面元素不用加
			}
			else {
				sum += M[i + 1][j];
				count++;
			}
			if (j == n - 1) {
				//右边元素不用加
			}
			else {
				sum += M[i][j + 1];
				count++;
			}
			if (j == 0 || i == 0) {
				//左上不用
			}
			else {
				sum += M[i - 1][j - 1];
				count++;
			}
			if (j == n - 1 || i == 0) {
				//右上不用
			}
			else {
				sum += M[i - 1][j + 1];
				count++;
			}
			if (j == 0 || i == m - 1) {
				//左下不用
			}
			else {
				sum += M[i + 1][j - 1];
				count++;
			}
			if (j == n - 1 || i == m - 1) {
				//右下不用
			}
			else {
				sum += M[i + 1][j + 1];
				count++;
			}
			rec[i][j] = sum / count;
		}
	}
	return rec;
}

vector<vector<int>> imageSmoother2(vector<vector<int>>& M) {
	int m = M.size(), n = M[0].size();
	vector <  vector<int> > rec(m, vector<int>(n, 0));
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {

			int count = 0, sum = 0;
			int left = (i == 0) ? i : i - 1,right= (i + 1 < m) ? i + 1:i;
			int up = (j == 0) ? j : j - 1, down = (j + 1 < n) ? j + 1 : j;

			for (int p =left; p <= right; p++) {
				for (int q = up; q <= down; q++)
				{
					count++;
					sum += M[p][q];
				}
			}
			rec[i][j] = sum / count;
		}
	}
	return rec;
}
vector<vector<int>> imageSmoother3(vector<vector<int>>& M) {
	int m = M.size(), n = M[0].size();
	vector <  vector<int> > rec(m, vector<int>(n, 0));
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {

			int count = 0, sum = 0;
			

			for (int p = i-1; p <= i+1; p++) {
				for (int q = j-1; q <= j+1; q++)
				{
					if (p >= 0 && p < m&&q >= 0 && q < n) {
						count++;
						sum += M[p][q];
					}
					
				}
			}
			rec[i][j] = sum / count;
		}
	}
	return rec;
}
int main() {
	vector <  vector<int> >A = {{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}, {14, 15, 16}};
	A = imageSmoother3(A);
	int m = A.size(), n = A[0].size();
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			printf("%d  ", A[i][j]);
		}
	}

	
	return 0;
}