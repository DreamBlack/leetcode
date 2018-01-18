package linearlist;

public class Candy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ratings = { 1, 3, 4, 3, 2, 1 };
		System.out.println(candy(ratings));
		System.out.println(candyEnhanced(ratings));
	}

	public static int candyEnhanced(int[] ratings) {
		/*
		 * ����һ���㷨��ʱ�临�Ӷ��Ѿ���С�ˣ���������N�Ŀռ䣬�����跨ʹ�㷨ԭ�ع���
		 * Ҫ��ԭ�ع����Ͳ�������������¼ÿһ��С���ѷֵ��ǹ�������������ʵ�ڵ�һ�������ʱ������ֻ�õ��˺͵�ǰС�������ڵ�ǰһ��С���ѵ��ǹ�����
		 * ��˿���ֻ��һ���������浱ǰС����֮ǰ�Ǹ�С���ѷֵ����ǹ�������һ��sum��ʾ�ܵķֵ����ǹ�����
		 * ��������һ�����⣬��һ���㷨���ڴ������ұ���һ��֮���ִ������������һ�Σ�������Ϊ����ratingsΪ1,2,5,4,3,2,1�������һ�������С����
		 * ��õ��ǹ�����Ϊ1,2,3,1,1,1,1������Ȼ�ǲ��Եģ���Ҫ���������������һ�Σ��ɴ˿��Է���һ������Ҫ�Ĺ���
		 * ������ratings���������ǲ���Ҫ���ҵ��������ģ���1,2,5,5
		 * ������ratings��ʼ���������ˣ���1,5,4,3,2,1�������ұ��������С����Ҫ�ֵ�2���ǹ�������ʵӦ�÷�1,5,4,3,2,1���ԭ����1,2,
		 * 1,1,1,1
		 * �ǹ�����Ӧ������3+3+2+1�����ӵݼ����г���>2��ʼ����Ϊ�Ȳ����У��ݼ����ⲿ�ֿ��õȲ�������ͼ��㣬�ټ��ϵݼ����еĵ�һ��Ԫ�ص�׷��ֵ
		 */
		int sum = 1;
		int before = 1;

		int length = ratings.length;
		if (length == 1)
			return 1;
		if (length - 1 >= 1) {
			if (ratings[0] > ratings[1]) {
				before = 2;
				sum = 2;
			}
		}
		int sublength = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				if (sublength > 1) {// �ҵ��˵ݼ����еĳ���
					sum = sum + (1 + sublength - 1) * (sublength - 1) / 2;
					// �ݼ����еĵ�һ��Ҫ׷��
					sum = sum + ((before > sublength) ? 0 : sublength - before);
					before = 1;
					sublength = 1;
				}

				sum = sum + before + 1;
				before++;
			} else if (ratings[i] == ratings[i - 1]) {
				if (sublength > 1) {
					sum = sum + (1 + sublength - 1) * (sublength - 1) / 2;
					// �ݼ����еĵ�һ��Ҫ׷��
					sum = sum + ((before > sublength) ? 0 : sublength - before);
					before = 1;
					sublength = 1;
				}
				sum = sum + 1;
				before = 1;
			} else {
				// �ݼ�
				sublength++;
			}
		}
		if (sublength > 1) {
			sum = sum + (1 + sublength - 1) * (sublength - 1) / 2;
			// �ݼ����еĵ�һ��Ҫ׷��
			sum = sum + ((before > sublength) ? 0 : sublength - before);
		}
		return sum;
	}

	public static int candy(int[] ratings) {
		/*
		 * ���hard����㷨��Ȼ�Լ������ײŪ�����˲���ֻ������ʮ����
		 */
		int length = ratings.length;
		int[] candy = new int[length];
		for (int i = 0; i < length; i++) {
			candy[i] = 1;
		}
		if (length - 1 >= 1) {
			if (ratings[0] > ratings[1]) {
				candy[0] = 2;
			}
		}
		// ����ÿ��Ԫ�غ�֮ǰ��Ԫ�رȽϣ���ǰ��Ĵ�ͱ�ǰ��Ķ��һ��
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candy[i] = candy[i - 1] + 1;
			}
		}
		// ���ôӺ���ǰ����ÿ��Ԫ�غͺ������ڵ�Ԫ�رȽϣ��Ⱥ���Ĵ���Ŀǰ�ֵ�û�к���Ķ࣬�ͱȺ�����һ��
		for (int i = length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) {
				candy[i] = candy[i + 1] + 1;
			}
		}
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += candy[i];
		}
		return sum;
	}

}
