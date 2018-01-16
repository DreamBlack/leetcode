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
		// ����ÿһ�л�ÿһ�У��Լ�ÿһ��С���������г��ֵ�����ֻ������1-9֮�䣬����ʱ�����СҲΪ��
		// ��������Ҫ���������ж��Ƿ����ظ�Ԫ�أ���˿�����������ÿһ��ÿһ��ÿһ��С�����е�Ԫ����Ϊ������±꣬������Ԫ�أ��򷵻�false
		// ���temp�������Ϳɱ���Ϊboolean
		// ÿһ��
		/*
		 * ��Ŀ��˼��9*9�ľ����У�ÿһ��ÿһ���Լ�ÿһС�飨3*3�ľ�����1-9��Ÿ�����ֻ����һ��
		 * �������ؼ������ж��Ƿ����ظ����֣������ж�ÿһ��ÿһ���Լ�ÿһ��С�������Ƿ����ظ����ּ���
		 */
		boolean[] temp = new boolean[9];
		// ÿһ��

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

		// ÿһ��

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				temp[k] = false;
			}
			for (int j = 0; j < 9; j++) {// ע��char���ܼ�תΪInt,��ͨ���Զ�תΪASIIC
				if (board[j][i] != '.' && temp[board[j][i] - '1'] != true) {
					temp[board[j][i] - '1'] = true;
				} else if (board[j][i] != '.' && temp[board[j][i] - '1'] == true) {
					return false;
				}
			}
		}

		// �м�9��С������ѭ��̫�鷳��ת������temp���飬�Կռ任ʱ��
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
		 * ��Ŀ��˼��9*9�ľ����У�ÿһ��ÿһ���Լ�ÿһС�飨3*3�ľ�����1-9��Ÿ�����ֻ����һ��
		 * �������ؼ������ж��Ƿ����ظ����֣������ж�ÿһ��ÿһ���Լ�ÿһ��С�������Ƿ����ظ����ּ���
		 */

		// ÿһ��
		for (int n = 0; n < 9; n++) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[n][i] == board[n][j] && i != j && board[n][i] != '.') {
						return false;
					}
				}
			}
		}
		// ÿһ��
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
		// �м�9��С������ѭ��̫�鷳��ת������temp���飬�Կռ任ʱ��
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
