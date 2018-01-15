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
		while (left < right) {// �ȴӺ���
			while (nums[right] >= pivot && right > left)
				right--;

			nums[left] = nums[right];// ����Ҫ������ֱ�Ӹ�ֵ

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
				for (int i = j + 1; i < nums.length - 1; i++) {// ���ѭ����Ҫ�޳���ͬ��nums[i]���Է�ֹ�ظ�

					while (i - 1 >= j + 1 && i < nums.length && nums[i - 1] == nums[i]) {// ʱ�̷�ֹԽ��
						i++;
					}
					if (i > nums.length - 3)
						break;
					int left = i + 1, right = nums.length - 1;
					int sum = target - nums[i] - nums[j];
					// ����ֵ��ͬ��Ԫ��ֻ�账��һ�Σ���Ϊ��һ�δ����Ԫ�ر�Ȼ�����������������������Ԫ�أ���4Sum�˷��Բ���
					// if (i == j+1 || (i - 1 > j+1 && nums[i] != nums[i - 1])) {
					while (left < right) {// ���ѭ����Ҫ�޳�Ԫ�غ���ͬ������Ԫ��
						if (nums[left] + nums[right] == sum) {
							// �ҵ������һ��
							ret.add(Arrays.asList(nums[j], nums[i], nums[left], nums[right]));
							while (left + 1 < nums.length && nums[left + 1] == nums[left] && left - 1 < nums.length) {// ʱ�̷�ֹԽ��
								left++;
							}
							left++;
							while (right - 1 >= 0 && nums[right - 1] == nums[right] && right - 1 > i) {
								right--;
							}
							right--;// ��ָ�����ͬʱ���£���Ҫ�������к�Nums[left],nums[right]��ͬ��Ԫ���Ա����ظ����֣�����Ȼ�ļ���Ѱ��
							// ��Ȼ���˿���û�����Ԫ�ص����������
						} else if (nums[left] + nums[right] < sum) {
							// �Ͳ�����
							left++;
						} else {
							// ��̫����
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
