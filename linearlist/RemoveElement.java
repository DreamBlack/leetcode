package linearlist;

public class RemoveElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 3, 2, 2, 3 };
		System.out.println(removeElementFirstVersion(nums, 2));
	}

	public static int removeElementFirstVersion(int[] nums, int val) {
		int ret = nums.length;
		if (nums.length <= 0)
			return 0;
		int tail = nums.length - 1, head = 0;
		while (head <= tail) {
			if (nums[head] == val) {
				nums[head] = nums[tail];
				tail--;
				ret--;
			} else {
				head++;
			}
		}
		return ret;
	}

}
