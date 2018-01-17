package linearlist;

public class SetMatrixZero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void setZeroes(int[][] matrix) {
		/*
		 * 这题乍一看很简单 法一：spaceO(m*n) 用一个同样大的矩阵先遍历一遍矩阵记录对应的某位元素是否为0，
		 * 后再次遍历矩阵的同时根据记录矩阵中的值删除对应的行和列 法二：spaceO(m+n) 分别用一个长为m和长为n的数组，一遍遍历矩阵，记录要删除的行号和列号
		 * 后遍历这两个记录数组删除原矩阵中对应的行和列 法三：spaceO(1)
		 * 所以怎样才能达到原地操作呢。唔。。这个真想不到，之前也没做过类似的题，只能先看看人家怎么做了 嗯。。好吧。
		 * 因为每次扫描到0时不能简单的将这个元素所在行或者列全都简单置为0（否则会覆盖掉某些0），因此这题无论如何都得用辅助空间。
		 * 但人家又说要原地操作，那就只好利用矩阵本身的第一行和第一列来记录要删除的行和列了，此为不用多余空间（腻害，有种这也能行的感觉）
		 * 这样的话需要着重考虑的就是第一行和第一列了
		 */
		int m = matrix.length;
		int n = matrix[0].length;
		if (m <= 0 || n <= 0) {
			return;
		}
		boolean firstColZero = false;
		boolean firstRowZero = false;
		if (matrix[0][0] == 0) {
			// 表示第0行和第0列都要删
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

			// 先处理处第一行外每一行，先记录
			for (int i = 1; i < m; i++) {
				for (int j = 1; j < n; j++) {
					if (matrix[i][j] == 0) {
						matrix[i][0] = 0;
						matrix[0][j] = 0;

					}
				}
			}

			// 再置行
			for (int i = 1; i < m; i++) {
				if (matrix[i][0] == 0) {
					for (int j = 1; j < n; j++) {
						matrix[i][j] = 0;
					}
				}
			}
			// 再置列
			for (int i = 1; i < n; i++) {
				if (matrix[0][i] == 0) {
					for (int j = 1; j < m; j++) {
						matrix[j][i] = 0;
					}
				}
			}

		}
		// 处理第一列和第一列
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
