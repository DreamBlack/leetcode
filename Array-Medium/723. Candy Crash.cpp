/*
This question is about implementing a basic elimination algorithm for Candy Crush.

Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the current board.

Example 1:

Input:
board =
[[110,5,112,113,114],
 [210,211,5,213,214],
 [310,311,3,313,314],
 [410,411,412,5,414],
 [5,1,512,3,3],
 [610,4,1,613,614],
 [710,1,2,713,714],
 [810,1,2,1,1],
 [1,1,2,2,2],
 [4,1,4,4,1014]]
Output:
[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]
Explanation:
Note:

The length of board will be in the range [3, 50].
The length of board[i] will be in the range [3, 50].
Each board[i][j] will initially start as an integer in the range [1, 2000].
---------------------
解题思路：
注意开始的时候是发现有行列相同直接消除，但是后来看网上的解题方法发现，应该先标记，之后再消除，因为一个grid
的消除应该是并行的。先消除的时候可能导致连接地方一些本来能消除的变得不能消除了
Tips：这题是锁起来的，所以正确性可能有误
*/
#include <vector>
#include <map>
#include <set>
#include <iterator>
#include <queue>
using namespace std;
vector<vector<int>> candyCrush(vector<vector<int>> &board)
{
	bool crash = false;
	int m = board.size(), n = board[0].size();
	do
	{
		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (board[i][j] == 0)
					continue;
				int up = i, cup = 0;
				while (up > 0 && board[up--][j] == board[i][j])
					cup++;
				int down = i, cdown = 0;
				while (down < m - 1 && board[down++][j] == board[i][j])
					cdown++;

				int left = j, cleft = 0;
				while (left > 0 && board[i][left--] == board[i][j])
					cleft++;
				int right = i, cright = 0;
				while (right < n - 1 && board[i][right++] == board[i][j])
					cright++;

				if (cup + cdown - 1 >= 3)
				{
					//记录竖排
					int num = cup + cdown - 1;
					for (int p = down - 1; p > up; p--)
					{
						board[p][j] = -board[p][j];
						crash = true;
					}
				}
				if (cleft + cright - 1 >= 3)
				{
					//记录横排
					int num = cleft + cright - 1;
					for (int q = right - 1; q > left; q--)
					{
						board[i][q] = -board[i][q];
						crash = true;
					}
				}
				//[i][j]可能会被标记两遍
				if(cleft + cright - 1 >= 3||cup + cdown - 1 >= 3){
					board[i][j]=(board[i][j]>0)?board[i][j]:-board[i][j];
				}
			}
		}
		//标记好之后开始消除，从左到右，从下到上
		for(int j=0;j<n;j++){
			for(int i=m-1;i>=0;i--){
				if(board[i][j]==0){
					int temp=i;
					int count=0;
					while(board[i][j]<0&&i>=0){
						i--;
						count++;
					}
					i=temp;
					while(count>0){
						board[i][j]=board[i-count][j];
						i--;
					}
					i++;
				}
				
			}
		}
	} while (crash);
	return board;
}