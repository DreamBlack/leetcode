package linearlist;

public class SingleNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int singleNumber(int[] nums) {
		// һ�ּ򵥵ķ�������hashmap��һ��boolean���飬�ܷŽ�ȥ����Ӧλ��Ϊtrue���Ѿ����˼�Ϊfalse��������Boolean����λtrue�±��Ӧ��
		// ����Ψһ���Ǹ���
		/*
		 * ʵ��û����������˴𰸣���Ȼ����򡣡�����ͬ�����Ϊ0,0���κ������Ϊ�䱾��
		 */
		int length = nums.length;
		if (length == 1) {
			return nums[0];
		}
		int a = nums[0];
		for (int i = 1; i < length; i++) {
			a = a ^ nums[i];
		}
		return a;
	}
}
