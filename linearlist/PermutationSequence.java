package linearlist;

public class PermutationSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(permutationSeqFirstVersion(3, 3));
		// ��������Ȼһ���ύ��ͨ���ˡ���ȻЧ�ʺ���
	}

	public static String retPthSequence(String s, int n, int p) {

		if (p == 0) {
			return s;
		} else {
			int fac = factorialN(n - 1);
			int m = p / fac;
			String begin = String.valueOf(s.charAt(m));// chartostring�ķ������Լ�ȡstring�е�i���ַ��ķ���
			s = s.replace(begin, "");// ȥ��string��ĳ���ַ�

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
		// ��n������n!�������з��ص�k������
		String ret = "";
		/*
		 * n�������ɵ������У���iΪ��ͷ��������(n-1)!��
		 * ��k/(n-1)!�õ�����m������Ϊp,�������п�ͷ�����ּ�Ϊ�����е�m+1���������������м�Ϊ1...n�г�ȥm+1�󹹳ɵ������еĵ�p+1����1��ʼ
		 * ����
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
