package linearlist;

public class TrappingRainWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] height = { 4, 2, 3 };
		System.out.println(trapExcellentVersion(height));
	}

	public static int total(int[] height, int i, int j, int n) {
		int total = 0;
		if (i > j) {
			return 0;
		}
		while (i <= j) {
			total += (height[i] > n) ? n : height[i];
			i++;
		}
		return total;
	}

	public static int trapExcellentVersion(int[] height) {
		/*
		 * EhanceVersion中使用了辅助数组，且复杂度为3N，设法不使用辅助空间且再时间O(N)内完成算法
		 * 记住两个柱子中较短的那个决定了整个数组的储水量，后计算每一个元素在该位置上所能容纳的水量。
		 */
		if (height.length < 2)
			return 0;
		int i = 0, j = height.length - 1;
		int min = 0, cap = 0;
		while (i < j) {
			min = Math.min(height[i], height[j]);
			if (height[i] < height[j]) {
				i++;
				while (i < j && height[i] < min) {
					cap += min - height[i];
					i++;
				}
			} else {
				j--;
				while (i < j && height[j] < min) {
					cap += min - height[j];
					j--;
				}
			}
		}
		return cap;
	}

	public static int trapEhanceVersion(int[] height) {
		/*
		 * 即该题型为每一个元素对应结果取决于该元素两边的其他元素，对于该题型可从两边各扫描一次得到需要的变量，再通过第三次遍历即可得到结果
		 * 另一种思路是每个柱子所能承受的容量决定于它左边最大的柱子和右边最大的柱子只差，为min(left,right)-height 发现这个规律就很好解决了
		 */
		if (height.length < 2)
			return 0;
		int cap = 0;
		int[] leftmax = new int[height.length];
		int maxleft = height[0];
		for (int i = 0; i < height.length; i++) {
			if (height[i] > maxleft) {
				maxleft = height[i];

			}
			leftmax[i] = maxleft;
		}
		int[] rightmax = new int[height.length];
		int maxright = height[height.length - 1];
		for (int i = height.length - 1; i >= 0; i--) {
			if (height[i] > maxright) {
				maxright = height[i];

			}
			rightmax[i] = maxright;
		}
		int max = 0;
		for (int i = 0; i < height.length; i++) {
			max = Math.min(leftmax[i], rightmax[i]);
			if (max > height[i]) {
				cap = cap + max - height[i];
			}
		}
		return cap;
	}

	public static int trap(int[] height) {
		/*
		 * 又是一道看不懂描述的题。 题意是求数组形成的凹槽能盛水的容积 1、从左向右找到第一个不为0的，下标即为left
		 * 2、从left往右找到最大的一个，且比右边大的记为right
		 * cap=cap+(right-left+1)*min(height[left],height[right])-total(left+1,right-1);
		 * 3、left=right
		 */
		int left = 0, right = 0, cap = 0;
		int len = height.length;
		if (len <= 1)
			return 0;
		for (int i = 0; i < len; i++) {
			if (height[i] != 0) {
				left = i;
				break;
			}
		}
		while (left < len - 2) {
			right = left;
			for (int i = left + 1; i < len - 1; i++) {
				if ((height[right] <= height[i] && height[i] > height[i + 1]) || i == len - 2) {// 找到i后面最大的
					right = i;
					if (i == len - 2) {
						i++;
						while (i > left) {
							if (height[i] >= height[right]) {
								right = i;
							}
							i--;
						}

					}

					cap += (right - left - 1) * Math.min(height[left], height[right])
							- total(height, left + 1, right - 1, Math.min(height[left], height[right]));
					left = right;
					break;
				}

			}

		}
		return cap;
	}

}
