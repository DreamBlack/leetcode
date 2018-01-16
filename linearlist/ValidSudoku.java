package linearlist;

public class ValidSudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = { { '.', '.', '.', '.', '5', '.', '.', '1', '.' },
				{ '.', '4', '.', '3', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '3', '.', '.', '1' },
				{ '8', '.', '.', '.', '.', '.', '.', '2', '.' }, { '.', '.', '2', '.', '7', '.', '.', '.', '.' },
				{ '.', '1', '5', '.', '.', '.', '.', '.', '.' }, { '.', '.', '.', '.', '.', '2', '.', '.', '.' },
				{ '.', '2', '.', '9', '.', '.', '.', '.', '.' }, { '.', '.', '4', '.', '.', '.', '.', '.', '.' } };

		System.out.println(isValidSudoku(board));

	}

	public static boolean isArrayValid(char[] n) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (n[i] == n[j] && i != j && n[i] != '.') {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isValidSudokuEhanceVersion(char[][] board) {
		// 由于每一行或每一列，以及每一个小数独中所有出现的数字只可能在1-9之间，且临时数组大小也为九
		// 而本题主要操作在于判断是否有重复元素，因此可以用数独中每一行每一列每一个小数独中的元素作为数组的下标，若已有元素，则返回false
		// 因此temp数组类型可保存为boolean
		// 每一行
		/*
		 * 题目意思是9*9的矩阵中，每一行每一列以及每一小块（3*3的矩阵）中1-9这九个数字只出现一次
		 * 因此问题关键在于判断是否有重复数字，依次判断每一行每一列以及每一个小矩阵中是否有重复数字即可
		 */
		boolean[] temp = new boolean[9];
		// 每一行

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				temp[k] = false;
			}
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.' && temp[board[i][j] - '1'] != true) {
					temp[board[i][j] - '1'] = true;
				} else if (board[i][j] != '.' && temp[board[i][j] - '1'] == true) {
					return false;
				}
			}
		}

		// 每一列

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				temp[k] = false;
			}
			for (int j = 0; j < 9; j++) {// 注意char不能简单转为Int,普通会自动转为ASIIC
				if (board[j][i] != '.' && temp[board[j][i] - '1'] != true) {
					temp[board[j][i] - '1'] = true;
				} else if (board[j][i] != '.' && temp[board[j][i] - '1'] == true) {
					return false;
				}
			}
		}

		// 中间9个小数独用循环太麻烦，转而利用temp数组，以空间换时间
		for (int p = 0; p < 9; p = p + 3) {
			for (int k = 0; k < 9; k = k + 3) {
				for (int n = 0; n < 9; n++) {
					temp[n] = false;
				}
				int count = 0;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {

						if (board[i + k][j + p] != '.' && temp[board[i + k][j + p] - '1'] != true) {
							temp[board[i + k][j + p] - '1'] = true;
						} else if (board[i + k][j + p] != '.' && temp[board[i + k][j + p] - '1'] == true) {
							return false;
						}
					}
				}
			}

		}

		return true;
	}

	public static boolean isValidSudoku(char[][] board) {
		/*
		 * 题目意思是9*9的矩阵中，每一行每一列以及每一小块（3*3的矩阵）中1-9这九个数字只出现一次
		 * 因此问题关键在于判断是否有重复数字，依次判断每一行每一列以及每一个小矩阵中是否有重复数字即可
		 */

		// 每一行
		for (int n = 0; n < 9; n++) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[n][i] == board[n][j] && i != j && board[n][i] != '.') {
						return false;
					}
				}
			}
		}
		// 每一列
		for (int n = 0; n < 9; n++) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[i][n] == board[j][n] && i != j && board[i][n] != '.') {
						return false;
					}
				}
			}
		}
		char[] temp = new char[9];
		// 中间9个小数独用循环太麻烦，转而利用temp数组，以空间换时间
		for (int p = 0; p < 9; p = p + 3) {
			for (int k = 0; k < 9; k = k + 3) {
				int count = 0;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						temp[count++] = board[i + k][j + p];
					}
				}
				if (!isArrayValid(temp)) {
					return false;
				}
			}
		}

		return true;
	}
}
