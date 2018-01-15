package linearlist;

public class PermutationSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(permutationSeqFirstVersion(3, 3));
		// 哈哈，居然一次提交就通过了。虽然效率很烂
	}

	public static String retPthSequence(String s, int n, int p) {

		if (p == 0) {
			return s;
		} else {
			int fac = factorialN(n - 1);
			int m = p / fac;
			String begin = String.valueOf(s.charAt(m));// chartostring的方法，以及取string中第i个字符的方法
			s = s.replace(begin, "");// 去掉string中某个字符

			int q = p % fac;
			return begin + retPthSequence(s, n - 1, q);
		}

	}

	public static int factorialN(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return n * factorialN(n - 1);
		}
	}

	public static String permutationSeqFirstVersion(int n, int k) {
		// 从n个数的n!个序列中返回第k个序列
		String ret = "";
		/*
		 * n个数构成的数列中，以i为开头的数列有(n-1)!个
		 * 用k/(n-1)!得到的商m，余数为p,所求序列开头的数字即为数列中第m+1大的数，后面的序列即为1...n中出去m+1后构成的序列中的第p+1（从1开始
		 * ）个
		 */
		if (n == 1) {
			return "1";
		}
		if (k <= 0 || k > factorialN(n))
			return "";
		for (int i = 0; i < n; i++) {
			ret += i + 1;
		}

		return retPthSequence(ret, n, k - 1);

	}
}
