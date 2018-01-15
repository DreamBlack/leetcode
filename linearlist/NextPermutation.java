package linearlist;

public class NextPermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 5, 4, 7, 5, 3, 2 };
		nextPermutation(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

	public static int partion(int[] nums, int head, int tail) {

		int pivot = nums[head];
		int left = head, right = tail;
		while (left < right) {// 先从后面
			while (nums[right] >= pivot && right > left)
				right--;

			nums[left] = nums[right];// 不需要交换，直接赋值

			while (nums[left] <= pivot && left < right)
				left++;
			nums[right] = nums[left];

		}
		nums[left] = pivot;
		return left;

	}

	public static void quickSort(int[] nums, int head, int tail) {
		if (head >= tail)
			return;
		int left = partion(nums, head, tail);
		quickSort(nums, head, left - 1);
		quickSort(nums, left + 1, tail);
	}

	public static int findMinTarget(int[] nums, int target, int i, int j) {
		if (i > j) {
			return -1;
		}
		int min = i;
		for (int m = i + 1; m <= j; m++) {
			if (nums[m] < nums[min] && nums[m] > target) {
				min = m;
			}
		}
		return min;

	}

	public static void nextPermutation(int[] nums) {
		/*
		 * 话说这个题目都没看懂。。。
		 * 将一个数组中元素重新排序，使其重新排序后的数组是原数组中元素所有可能的排序中下一个比原来的排序大的（字典序）数组，若不存在这样的数组，则使原数组按増序排序
		 * 如果原数组已经是降序排序，则不存在这样一个重排序，应使数组重排列为升序； 否则 首先应知道必须要想找到下一个（紧邻原来的排序的新序列），应尽可能少的动刀，
		 * 因此，首先从后向前找到第一个非降序的元素i， 后，在从后往前找到第一个比Nums[i]大的元素(之前漏了这一点导致错了)，使其与i交换位置
		 * 再然后，将原i后所有元素重新排位升序
		 */
		int i = nums.length - 1;
		if (i < 1) {
			return;
		}

		for (; i > 0; i--) {
			if (nums[i - 1] < nums[i]) {
				i--;
				break;
			}
		}
		if (i == 0 && nums[0] >= nums[1]) {
			// 原数组已是降序排列，直接转为升序即可
			quickSort(nums, 0, nums.length - 1);

		} else {
			// i个元素开始为非降序
			int min = findMinTarget(nums, nums[i], i + 1, nums.length - 1);
			if (min != -1) {
				int temp = nums[min];
				nums[min] = nums[i];
				nums[i] = temp;

				quickSort(nums, i + 1, nums.length - 1);

			}

		}

	}

}
