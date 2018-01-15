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
		 * ��˵�����Ŀ��û����������
		 * ��һ��������Ԫ����������ʹ������������������ԭ������Ԫ�����п��ܵ���������һ����ԭ���������ģ��ֵ������飬�����������������飬��ʹԭ���鰴��������
		 * ���ԭ�����Ѿ��ǽ��������򲻴�������һ��������Ӧʹ����������Ϊ���� ���� ����Ӧ֪������Ҫ���ҵ���һ��������ԭ��������������У���Ӧ�������ٵĶ�����
		 * ��ˣ����ȴӺ���ǰ�ҵ���һ���ǽ����Ԫ��i�� ���ڴӺ���ǰ�ҵ���һ����Nums[i]���Ԫ��(֮ǰ©����һ�㵼�´���)��ʹ����i����λ��
		 * ��Ȼ�󣬽�ԭi������Ԫ��������λ����
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
			// ԭ�������ǽ������У�ֱ��תΪ���򼴿�
			quickSort(nums, 0, nums.length - 1);

		} else {
			// i��Ԫ�ؿ�ʼΪ�ǽ���
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
