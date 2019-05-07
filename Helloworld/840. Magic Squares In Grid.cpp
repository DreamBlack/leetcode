/*
A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).



Example 1:

Input: [[4,3,8,4],
		[9,5,1,9],
		[2,7,6,2]]
Output: 1
Explanation:
The following subgrid is a 3 x 3 magic square:
438
951
276

while this one is not:
384
519
762

In total, there is only one magic square inside the given grid.
Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
0 <= grid[i][j] <= 15
解题思路：
简单题，不过是那种我最讨厌的比较麻烦的题 。
可以在麻烦解决的基础之上观察规律，减少判断，从而减少时间
if (grid[i + 1][j + 1] != 5)continue;//这里是观察规律得到的，可以加快速度
*/
#include<vector>
#include<algorithm>
using namespace std;
int numMagicSquaresInside(vector<vector<int>>& grid) {
	int m = grid.size(), n = grid[0].size();
	if (m < 3 || n < 3)return 0;
	int count = 0;
	int row1 = 0, row2 = 0, row3 = 0, col1 = 0, col2 = 0, col3 = 0, diag = 0;
	for (int j = 0; j <= n-3; j++) {
		for (int i = 0; i <= m-3; i++) {
			if (grid[i + 1][j + 1] != 5)continue;//这里是观察规律得到的，可以加快速度
			//先是忘了判断grid里面每个数要在1-9范围内，且各不相同
			vector<int>inc(10, 0);
			if(grid[i][j]<10)
				inc[grid[i][j]]++;
			if (grid[i][j+1] < 10)
				inc[grid[i][j+1]]++;
			if (grid[i][j+2] < 10)
				inc[grid[i][j+2]]++;
			if (grid[i + 1][j] < 10)
				inc[grid[i+1][j]]++;
			if (grid[i + 1][j+1] < 10)
				inc[grid[i+1][j+1]]++;
			if (grid[i + 1][j+2] < 10)
				inc[grid[i+1][j+2]]++;
			if (grid[i + 2][j] < 10)
				inc[grid[i+2][j]]++;
			if (grid[i + 2][j+1] < 10)
				inc[grid[i+2][j+1]]++;
			if (grid[i + 2][j+2] < 10)
				inc[grid[i+2][j+2]]++;
			bool flag = true;
			for (int p = 1; p < 10; p++) {
				if (inc[p] != 1) {
					flag = false;
					break;
				}
			}
			if (!flag)continue;
			row1 = grid[i][j] + grid[i][j+1] + grid[i][j+2];
			
			row2 = grid[i+1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2];
			if (row1 != row2)continue;

			row3 = grid[i + 2][j + 0] + grid[i + 2][j + 1] + grid[i + 2][j + 2];
			if (row1 != row3)continue;

			col1 = grid[i][j] + grid[i+1][j] + grid[i+2][j];
			if (row1 != col1)continue;

			col2 = grid[i][j+1] + grid[i+1][j + 1] + grid[i+2][j + 1];
			if (row1 != col2)continue;

			col3 = grid[i][j + 2] + grid[i+1][j + 2] + grid[i+2][j + 2];
			if (row1 != col3)continue;

			diag = grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2];
			if (row1 != diag)continue;
			else count++;
			
		}
	}
	return count;
}