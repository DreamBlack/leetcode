package linearlist;

public class SingleNumberTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int singleNumber(int[] nums) {
		/*
		 * �š�������Ҳ���ᡣ��
		 * Int����32λ����һ�������������Σ��������ÿһλ��1���ֵ�ʱ����ö�Ӧλ+1������������������Σ������ÿһλ��������1��Ϊ0��0��Ϊ1��1����
		 * ����Ϊ0��ֻ������Ψһ���Ǹ���
		 */

		int res = 0;
		for (int i = 0; i < 32; ++i) {
			int sum = 0;
			for (int j = 0; j < nums.length; ++j) {
				sum += (nums[j] >> i) & 1;
			}
			res |= (sum % 3) << i;
		}
		return res;
	}

}
