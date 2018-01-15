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
		 * ֮ǰ��˼·һֱ����������Ԫ�أ�����ͣ�������һ��Ԫ��ʹ����֮��Ϊ0����Ӧ�����ǳ���˼·
		 * ����ʵ���Դ�3��֮��������Ȿ�������ǣ�����ÿһ��Ԫ��a,Ҫ�ҳ�����a+b+c=0����������Ԫ�أ�
		 * b+c��������b+c=-a�������������ͱ�Ϊ����֪Ԫ��aֵ������£������������ҳ�����b+c=-a����������Ԫ��
		 * ����Ϊ�˷�ֹ�����ظ�����ȻӦ�ô�ǰ�����ң������ٿ���ǰԪ��֮ǰ��Ԫ�أ��Ҳ���Ҫ�Ժ͵�ǰԪ��ֵ��ͬ��Ԫ�ؽ��ж��δ���
		 */
		quickSort(nums, 0, nums.length - 1);
		for (int i = 0; i < nums.length; i++) {// ���ѭ����Ҫ�޳���ͬ��nums[i]���Է�ֹ�ظ�
			int left = i + 1, right = nums.length - 1;
			int sum = 0 - nums[i];
			// ����ֵ��ͬ��Ԫ��ֻ�账��һ�Σ���Ϊ��һ�δ����Ԫ�ر�Ȼ�����������������������Ԫ�أ���4Sum�˷��Բ���
			if (i == 0 || (i - 1 >= 0 && nums[i] != nums[i - 1])) {
				while (left < right) {// ���ѭ����Ҫ�޳�Ԫ�غ���ͬ������Ԫ��
					if (nums[left] + nums[right] == sum) {
						// �ҵ������һ��
						ret.add(Arrays.asList(nums[i], nums[left], nums[right]));
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
			}

		}
		return ret;
	}

	public static List<List<Integer>> threeSumViolent(int[] nums) {
		// �ؼ��������ȥ���ظ�Ԫ�أ������ڲ���Listʱ�����Ƚϣ���Ϊ����Ԫ�ص����������6��֮��
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
		// ���ǻ����ظ�Ԫ��
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		if (nums.length < 3)
			return ret;
		quickSort(nums, 0, nums.length - 1);// ������û���ö��ֲ���
		for (int i = 0; i < nums.length; i++) {

			for (int j = i + 1; j < nums.length; j++) {
				if (j - 1 > 0 && j - 1 < nums.length && nums[j - 1] == nums[j]) {// ��ֹ�ظ��ĳ���

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
