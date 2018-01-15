package linearlist;

import java.util.HashMap;

public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 以空间换时间，可是负数咋办。。。这个方法不好 关键问题在于未排序
		 */
		int[] nums = { 100, 4, 200, 1, 3, 2 };
		System.out.println(LongestConsecutiveSeqHashMap(nums));

	}

	public static int LongestConsecutiveSeqHashMap(int[] nums) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		if (nums.length == 0)
			return 0;
		/*
		 * 考虑了先遍历一遍，把元素都放入map，后二次遍历，对每个元素判断前后元素是否存在并更新长度，但这样会导致长度翻倍，是错误的 还是应该一遍遍历，不能投机取巧
		 * 一次遍历，在每个子序列的头尾的value里保存子序列的长度，对于每一个元素 1、已经在map里，不做任何操作
		 * 2、不在map里，查看其前一个元素和后一个元素是否在map里，若在则表示子序列要更新变长。
		 * 3、若子序列有更新，则要在子序列头尾更新序列长，而子序列头尾的位置完全可以根据当前元素推测得到（这一点我没有想到）
		 */
		int front = 0, behind = 0;
		int maxlength = 0, length = 0;
		// front
		for (int i = 0; i < nums.length; i++) {
			if (hm.get(nums[i]) == null) {
				front = (hm.get(nums[i] - 1) != null) ? hm.get(nums[i] - 1) : 0;
				behind = (hm.get(nums[i] + 1) != null) ? hm.get(nums[i] + 1) : 0;
				length = front + behind + 1;
				hm.put(nums[i], length);
				maxlength = Math.max(maxlength, length);
				// 必须及时更新，头尾的才能保证计算出最大的连续序列长度
				hm.put(nums[i] - front, length);
				hm.put(nums[i] + behind, length);

			}
		}

		// 不能简单的只考虑元素前一个数字，因为，数组不是有序的，在处理4的时候，有可能3还没有插入

		return maxlength;
		// hashmap的遍历
		/*
		 * Iterator iter=hm.entrySet().iterator(); while(iter.hasNext()) { Map.Entry
		 * entry=(Map.Entry)iter.next(); Object key=entry.getKey(); Object
		 * value=entry.getValue(); }
		 */

	}

	public static int LongestConsecutiveSeq(int[] nums) {
		int length = 0;
		// 找出数组中最大整数+1作为记录数组的长度
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= length)
				length = nums[i];
		}
		// 用记录数组记录每个元素的有无
		int[] temp = new int[length + 1];// 不会声明数组了。。。。
		for (int i = 0; i < nums.length; i++) {
			temp[nums[i]] = 1;
		}

		int ret = 1, maxret = 1;
		for (int i = 1; i < temp.length - 1; i++) {
			if (temp[i] == 1 && temp[i + 1] == 1) {
				ret++;
			} else {
				if (ret >= maxret) {
					maxret = ret;
					ret = 1;
				}
			}

		}
		return maxret;
	}

}
