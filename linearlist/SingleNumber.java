package linearlist;

public class SingleNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int singleNumber(int[] nums) {
		// 一种简单的方法，用hashmap和一个boolean数组，能放进去，对应位记为true，已经有了记为false，最后遍历Boolean数组位true下标对应的
		// 就是唯一的那个数
		/*
		 * 实在没想出来，看了答案，居然用异或。。。相同的异或为0,0和任何数异或为其本身
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
