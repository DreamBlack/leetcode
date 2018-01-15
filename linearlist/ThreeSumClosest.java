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

	public static int ThreeSClosest(int[] nums, int target) {
		if (nums.length < 3)
			return 0;
		int min = nums[0] + nums[1] + nums[2];

		/*
		 * �ٺ٣��о��Լ������գ���֮ǰһ��Ļ���֮���Լ�����һ��Ķ���û�뵽һ�γɹ�����Ч��Ҳ���൱�ĸ�
		 * 
		 */
		quickSort(nums, 0, nums.length - 1);
		for (int i = 0; i < nums.length; i++) {// ���ѭ����Ҫ�޳���ͬ��nums[i]���Է�ֹ�ظ�
			int left = i + 1, right = nums.length - 1;

			// ����ֵ��ͬ��Ԫ��ֻ�账��һ�Σ���Ϊ��һ�δ����Ԫ�ر�Ȼ�����������������������Ԫ�أ���4Sum�˷��Բ���
			if (i == 0 || (i - 1 >= 0 && nums[i] != nums[i - 1])) {
				while (left < right) {// ���ѭ����Ҫ�޳�Ԫ�غ���ͬ������Ԫ��
					int sum = nums[i] + nums[left] + nums[right];
					if (((sum - target > 0) ? sum - target : (target - sum)) <= ((min - target > 0) ? min - target
							: (target - min))) {
						// �ҵ������һ��
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
