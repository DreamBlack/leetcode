package linearlist;

public class ThreeSumClosest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 1, 1, 0 };
		System.out.println(nums.toString());
		System.out.println(ThreeSClosest(nums, -100));

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

	public static int ThreeSClosest(int[] nums, int target) {
		if (nums.length < 3)
			return 0;
		int min = nums[0] + nums[1] + nums[2];

		/*
		 * 嘿嘿，感觉自己棒棒哒，在之前一题的基础之上自己做了一点改动，没想到一次成功而且效率也是相当的高
		 * 
		 */
		quickSort(nums, 0, nums.length - 1);
		for (int i = 0; i < nums.length; i++) {// 这个循环里要剔除相同的nums[i]，以防止重复
			int left = i + 1, right = nums.length - 1;

			// 对于值相同的元素只需处理一次，因为第一次处理的元素必然会包含了所有满足条件的三元素，而4Sum此法仍不可
			if (i == 0 || (i - 1 >= 0 && nums[i] != nums[i - 1])) {
				while (left < right) {// 这个循环里要剔除元素和相同的两个元素
					int sum = nums[i] + nums[left] + nums[right];
					if (((sum - target > 0) ? sum - target : (target - sum)) <= ((min - target > 0) ? min - target
							: (target - min))) {
						// 找到满足的一对
						min = sum;

					}

					if (sum > target) {
						right--;
					} else if (sum == target) {
						return target;
					} else {
						left++;
					}

				}
			}

		}
		return min;
	}
}
