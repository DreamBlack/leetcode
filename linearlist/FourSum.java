package linearlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { -3, -2, -1, 0, 0, 1, 2, 3 };
		List<List<Integer>> ret = fourSumFirstVersion(nums, 0);
		for (int i = 0; i < ret.size(); i++) {
			System.out.println(
					ret.get(i).get(0) + "," + ret.get(i).get(1) + "," + ret.get(i).get(2) + "," + ret.get(i).get(3));
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

	public static List<List<Integer>> fourSumFirstVersion(int[] nums, int target) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		if (nums.length < 4)
			return ret;
		/*
		 
		 */
		quickSort(nums, 0, nums.length - 1);
		for (int j = 0; j < nums.length; j++) {
			if (j == 0 || (j - 1 >= 0 && nums[j] != nums[j - 1])) {
				for (int i = j + 1; i < nums.length - 1; i++) {// 这个循环里要剔除相同的nums[i]，以防止重复

					while (i - 1 >= j + 1 && i < nums.length && nums[i - 1] == nums[i]) {// 时刻防止越界
						i++;
					}
					if (i > nums.length - 3)
						break;
					int left = i + 1, right = nums.length - 1;
					int sum = target - nums[i] - nums[j];
					// 对于值相同的元素只需处理一次，因为第一次处理的元素必然会包含了所有满足条件的三元素，而4Sum此法仍不可
					// if (i == j+1 || (i - 1 > j+1 && nums[i] != nums[i - 1])) {
					while (left < right) {// 这个循环里要剔除元素和相同的两个元素
						if (nums[left] + nums[right] == sum) {
							// 找到满足的一对
							ret.add(Arrays.asList(nums[j], nums[i], nums[left], nums[right]));
							while (left + 1 < nums.length && nums[left + 1] == nums[left] && left - 1 < nums.length) {// 时刻防止越界
								left++;
							}
							left++;
							while (right - 1 >= 0 && nums[right - 1] == nums[right] && right - 1 > i) {
								right--;
							}
							right--;// 两指针必须同时更新，且要跳过所有和Nums[left],nums[right]相同的元素以避免重复出现，但仍然的继续寻找
							// 居然忘了考虑没有相等元素的情况。。。
						} else if (nums[left] + nums[right] < sum) {
							// 和不够大
							left++;
						} else {
							// 和太大了
							right--;
						}

					}
					// }
				}
			}
		}
		return ret;
	}
}
