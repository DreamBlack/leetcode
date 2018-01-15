package linearlist;

public class ValidSudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean isValidSudoku(char[][] board) {
		/*
		 * 题目意思是9*9的矩阵中，每一行每一列以及每一小块（3*3的矩阵）中1-9这九个数字只出现一次
		 * 因此问题关键在于判断是否有重复数字，依次判断每一行每一列以及每一个小矩阵中是否有重复数字即可
		 */
		boolean ret = false;
		// 每一行
		for (int n = 0; n < 9; n++) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[n][i] == board[n][j] && i != j&&board[n][i]!='.') {
						return false;
					}
				}
			}
		}
		// 每一列
		for (int n = 0; n < 9; n++) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[i][n] == board[j][n] && i != j&&board[i][n]!='.') {
						return false;
					}
				}
			}
		}
		
		return ret;
	}
}
