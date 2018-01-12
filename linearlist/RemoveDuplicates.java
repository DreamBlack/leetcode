package linearlist;

public class RemoveDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 1, 1, 2, 2, 2 };
		int count = 0;
		count = removeDuplicates(nums);
		System.out.println("length:" + count);
		for (int i = 0; i < count; i++) {
			System.out.println(nums[i]);
		}
		System.out.println("-------------" + "removeDuplicates2--------------");
		int[] nums2 = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4 };
		count = removeDuplicates2(nums2);
		System.out.println("length:" + count);
		for (int i = 0; i < count; i++) {
			System.out.println(nums2[i]);
		}
	}

	public static int removeDuplicates(int[] nums) {
		int count = 0;// 记录修改后数组中应有的数据个数
		if (nums.length <= 1)
			return nums.length;
		for (int i = 1; i < nums.length; i++) {

			if (nums[i] != nums[count])
				nums[++count] = nums[i];
		}
		return ++count;

	}

	public static int removeDuplicates2(int[] nums) {
		int count = 0;// 记录修改后数组中应有的数据个数
		int countnow = 1;// 记录当前元素值出现个数
		if (nums.length <= 1)
			return nums.length;
		int now = nums[0];// 记录当前元素值
		for (int i = 1; i < nums.length; i++) {

			if (nums[i] == now && countnow < 2) {
				countnow++;
				nums[++count] = nums[i];
			} else if (nums[i] != now) {
				now = nums[i];
				countnow = 1;
				nums[++count] = nums[i];
			}
		}
		return ++count;

	}

}
