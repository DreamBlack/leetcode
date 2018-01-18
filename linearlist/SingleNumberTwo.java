package linearlist;

public class SingleNumberTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int singleNumber(int[] nums) {
		/*
		 * 嗯。。这题也不会。。
		 * Int共有32位，若一个数出现了三次，将这个数每一位上1出现的时候就让对应位+1，若这个数出现了三次，这个数每一位加了三次1后（为0加0，为1加1），
		 * 最后变为0，只留下了唯一的那个数
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
