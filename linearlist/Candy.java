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
		 * 在上一个算法中时间复杂度已经很小了，但是用了N的空间，下面设法使算法原地工作
		 * 要想原地工作就不能用数组来记录每一个小朋友分的糖果数量，但是其实在第一遍遍历的时候，我们只用到了和当前小朋友相邻的前一个小朋友的糖果数量
		 * 因此可以只用一个变量保存当前小朋友之前那个小朋友分到的糖果，再用一个sum表示总的分掉的糖果数量
		 * 下面有另一个问题，上一个算法中在从左往右遍历一次之后又从右往左遍历了一次，这是因为对于ratings为1,2,5,4,3,2,1这种情况一遍遍历后小朋友
		 * 获得的糖果数量为1,2,3,1,1,1,1，这显然是不对的，需要从右往左遍历修正一次，由此可以发现一个很重要的规律
		 * 即，若ratings单调不减是不需要从右到左修正的，如1,2,5,5
		 * 但是若ratings开始单调减少了，如1,5,4,3,2,1，从左到右遍历后二号小朋友要分到2个糖果，但其实应该分1,5,4,3,2,1相比原来的1,2,
		 * 1,1,1,1
		 * 糖果总数应该增加3+3+2+1，即从递减序列长度>2开始，即为等差数列，递减的这部分可用等差数列求和计算，再加上递减序列的第一个元素的追加值
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
				if (sublength > 1) {// 找到了递减序列的长度
					sum = sum + (1 + sublength - 1) * (sublength - 1) / 2;
					// 递减序列的第一个要追加
					sum = sum + ((before > sublength) ? 0 : sublength - before);
					before = 1;
					sublength = 1;
				}

				sum = sum + before + 1;
				before++;
			} else if (ratings[i] == ratings[i - 1]) {
				if (sublength > 1) {
					sum = sum + (1 + sublength - 1) * (sublength - 1) / 2;
					// 递减序列的第一个要追加
					sum = sum + ((before > sublength) ? 0 : sublength - before);
					before = 1;
					sublength = 1;
				}
				sum = sum + 1;
				before = 1;
			} else {
				// 递减
				sublength++;
			}
		}
		if (sublength > 1) {
			sum = sum + (1 + sublength - 1) * (sublength - 1) / 2;
			// 递减序列的第一个要追加
			sum = sum + ((before > sublength) ? 0 : sublength - before);
		}
		return sum;
	}

	public static int candy(int[] ratings) {
		/*
		 * 这道hard题的算法居然自己误打误撞弄出来了并且只用了三十分钟
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
		// 先让每个元素和之前的元素比较，比前面的大就比前面的多分一个
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candy[i] = candy[i - 1] + 1;
			}
		}
		// 再让从后向前，让每个元素和后面相邻的元素比较，比后面的大，且目前分的没有后面的多，就比后面多分一个
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
