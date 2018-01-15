package linearlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> ret = threeSumLastVersion(nums);
		for (int i = 0; i < ret.size(); i++) {
			System.out.println(ret.get(i).get(0) + "," + ret.get(i).get(1) + "," + ret.get(i).get(2));
		}
		System.out.println("--------------------------------------");
		List<List<Integer>> ret2 = threeSumBinary(nums);
		for (int i = 0; i < ret2.size(); i++) {
			System.out.println(ret2.get(i).get(0) + "," + ret2.get(i).get(1) + "," + ret2.get(i).get(2));
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

	public static int binarySearch(int[] nums, int n, int head, int tail) {
		if ((head == tail && nums[head] != n) || head > tail) {
			return -1;
		}
		int mid = (head + tail) / 2;
		if (nums[mid] == n)
			return mid;
		else if (nums[mid] < n) {
			return binarySearch(nums, n, mid + 1, tail);
		} else {
			return binarySearch(nums, n, head, mid - 1);
		}
	}

	public static List<List<Integer>> threeSumLastVersion(int[] nums) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		if (nums.length < 3)
			return ret;
		/*
		 * 之前的思路一直是先找两个元素，计算和，再找另一个元素使三者之和为0，这应该算是常规思路
		 * 但其实可以从3者之和这个问题本身来考虑，对于每一个元素a,要找出满足a+b+c=0的另外两个元素，
		 * b+c必须满足b+c=-a这个条件，问题就变为在已知元素a值的情况下，再在数组中找出满足b+c=-a的另外两个元素
		 * 此外为了防止出现重复，显然应该从前往后找，不能再看当前元素之前的元素，且不需要对和当前元素值相同的元素进行二次处理
		 */
		quickSort(nums, 0, nums.length - 1);
		for (int i = 0; i < nums.length; i++) {// 这个循环里要剔除相同的nums[i]，以防止重复
			int left = i + 1, right = nums.length - 1;
			int sum = 0 - nums[i];
			// 对于值相同的元素只需处理一次，因为第一次处理的元素必然会包含了所有满足条件的三元素，而4Sum此法仍不可
			if (i == 0 || (i - 1 >= 0 && nums[i] != nums[i - 1])) {
				while (left < right) {// 这个循环里要剔除元素和相同的两个元素
					if (nums[left] + nums[right] == sum) {
						// 找到满足的一对
						ret.add(Arrays.asList(nums[i], nums[left], nums[right]));
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
			}

		}
		return ret;
	}

	public static List<List<Integer>> threeSumViolent(int[] nums) {
		// 关键在于如何去除重复元素，不好在插入List时挨个比较，因为三个元素的排列组合有6个之多
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		if (nums.length < 3)
			return ret;

		int sum = 0, a = 0, b = 0, c = 0;
		for (int i = 0; i < nums.length; i++) {
			a = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				b = nums[j];
				sum = a + b;
				for (int k = j + 1; k < nums.length; k++) {

					c = nums[k];
					if (sum + nums[k] == 0) {
						List<Integer> l = new ArrayList<Integer>();
						l.add(a);
						l.add(b);
						l.add(c);
						ret.add(l);
					}
				}

			}
		}
		return ret;
	}

	public static List<List<Integer>> threeSumBinary(int[] nums) {
		// 还是会有重复元素
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		if (nums.length < 3)
			return ret;
		quickSort(nums, 0, nums.length - 1);// 不排序没法用二分查找
		for (int i = 0; i < nums.length; i++) {

			for (int j = i + 1; j < nums.length; j++) {
				if (j - 1 > 0 && j - 1 < nums.length && nums[j - 1] == nums[j]) {// 防止重复的出现

				} else {
					int pos = binarySearch(nums, -(nums[i] + nums[j]), (i > j) ? i + 1 : j + 1, nums.length - 1);
					if (pos != -1 && pos != i && pos != j) {
						List<Integer> l = new ArrayList<Integer>();
						l.add(nums[i]);
						l.add(nums[j]);
						l.add(nums[pos]);
						ret.add(l);
					}

				}
			}

		}
		return ret;
	}

}
