package linearlist;

public class SetMatrixZero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void setZeroes(int[][] matrix) {
		/*
		 * ����էһ���ܼ� ��һ��spaceO(m*n) ��һ��ͬ����ľ����ȱ���һ������¼��Ӧ��ĳλԪ���Ƿ�Ϊ0��
		 * ���ٴα��������ͬʱ���ݼ�¼�����е�ֵɾ����Ӧ���к��� ������spaceO(m+n) �ֱ���һ����Ϊm�ͳ�Ϊn�����飬һ��������󣬼�¼Ҫɾ�����кź��к�
		 * �������������¼����ɾ��ԭ�����ж�Ӧ���к��� ������spaceO(1)
		 * �����������ܴﵽԭ�ز����ء�����������벻����֮ǰҲû�������Ƶ��⣬ֻ���ȿ����˼���ô���� �š����ðɡ�
		 * ��Ϊÿ��ɨ�赽0ʱ���ܼ򵥵Ľ����Ԫ�������л�����ȫ������Ϊ0������Ḳ�ǵ�ĳЩ0�����������������ζ����ø����ռ䡣
		 * ���˼���˵Ҫԭ�ز������Ǿ�ֻ�����þ�����ĵ�һ�к͵�һ������¼Ҫɾ�����к����ˣ���Ϊ���ö���ռ䣨�度��������Ҳ���еĸо���
		 * �����Ļ���Ҫ���ؿ��ǵľ��ǵ�һ�к͵�һ����
		 */
		int m = matrix.length;
		int n = matrix[0].length;
		if (m <= 0 || n <= 0) {
			return;
		}
		boolean firstColZero = false;
		boolean firstRowZero = false;
		if (matrix[0][0] == 0) {
			// ��ʾ��0�к͵�0�ж�Ҫɾ
			firstRowZero = true;
			firstColZero = true;
		}
		for (int i = 0; i < m; i++) {

			if (matrix[i][0] == 0) {
				firstColZero = true;
			}

		}
		for (int i = 0; i < n; i++) {

			if (matrix[0][i] == 0) {
				firstRowZero = true;
			}

		}
		if (m > 1 && n > 1) {

			// �ȴ�����һ����ÿһ�У��ȼ�¼
			for (int i = 1; i < m; i++) {
				for (int j = 1; j < n; j++) {
					if (matrix[i][j] == 0) {
						matrix[i][0] = 0;
						matrix[0][j] = 0;

					}
				}
			}

			// ������
			for (int i = 1; i < m; i++) {
				if (matrix[i][0] == 0) {
					for (int j = 1; j < n; j++) {
						matrix[i][j] = 0;
					}
				}
			}
			// ������
			for (int i = 1; i < n; i++) {
				if (matrix[0][i] == 0) {
					for (int j = 1; j < m; j++) {
						matrix[j][i] = 0;
					}
				}
			}

		}
		// �����һ�к͵�һ��
		if (firstRowZero) {
			for (int i = 0; i < n; i++) {

				matrix[0][i] = 0;

			}

		}
		if (firstColZero) {
			for (int i = 0; i < m; i++) {

				matrix[i][0] = 0;

			}
		}

	}

}
