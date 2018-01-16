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
		 * EhanceVersion��ʹ���˸������飬�Ҹ��Ӷ�Ϊ3N���跨��ʹ�ø����ռ�����ʱ��O(N)������㷨
		 * ��ס���������н϶̵��Ǹ���������������Ĵ�ˮ���������ÿһ��Ԫ���ڸ�λ�����������ɵ�ˮ����
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
		 * ��������Ϊÿһ��Ԫ�ض�Ӧ���ȡ���ڸ�Ԫ�����ߵ�����Ԫ�أ����ڸ����Ϳɴ����߸�ɨ��һ�εõ���Ҫ�ı�������ͨ�������α������ɵõ����
		 * ��һ��˼·��ÿ���������ܳ��ܵ�����������������������Ӻ��ұ���������ֻ�Ϊmin(left,right)-height ����������ɾͺܺý����
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
		 * ����һ���������������⡣ �������������γɵİ�����ʢˮ���ݻ� 1�����������ҵ���һ����Ϊ0�ģ��±꼴Ϊleft
		 * 2����left�����ҵ�����һ�����ұ��ұߴ�ļ�Ϊright
		 * cap=cap+(right-left+1)*min(height[left],height[right])-total(left+1,right-1);
		 * 3��left=right
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
				if ((height[right] <= height[i] && height[i] > height[i + 1]) || i == len - 2) {// �ҵ�i��������
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
