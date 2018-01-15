package linearlist;

public class ValidSudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean isValidSudoku(char[][] board) {
		/*
		 * ��Ŀ��˼��9*9�ľ����У�ÿһ��ÿһ���Լ�ÿһС�飨3*3�ľ�����1-9��Ÿ�����ֻ����һ��
		 * �������ؼ������ж��Ƿ����ظ����֣������ж�ÿһ��ÿһ���Լ�ÿһ��С�������Ƿ����ظ����ּ���
		 */
		boolean ret = false;
		// ÿһ��
		for (int n = 0; n < 9; n++) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[n][i] == board[n][j] && i != j&&board[n][i]!='.') {
						return false;
					}
				}
			}
		}
		// ÿһ��
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
