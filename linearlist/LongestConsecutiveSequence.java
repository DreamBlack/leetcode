package linearlist;

import java.util.HashMap;

public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * �Կռ任ʱ�䣬���Ǹ���զ�졣��������������� �ؼ���������δ����
		 */
		int[] nums = { 100, 4, 200, 1, 3, 2 };
		System.out.println(LongestConsecutiveSeqHashMap(nums));

	}

	public static int LongestConsecutiveSeqHashMap(int[] nums) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		if (nums.length == 0)
			return 0;
		/*
		 * �������ȱ���һ�飬��Ԫ�ض�����map������α�������ÿ��Ԫ���ж�ǰ��Ԫ���Ƿ���ڲ����³��ȣ��������ᵼ�³��ȷ������Ǵ���� ����Ӧ��һ�����������Ͷ��ȡ��
		 * һ�α�������ÿ�������е�ͷβ��value�ﱣ�������еĳ��ȣ�����ÿһ��Ԫ�� 1���Ѿ���map������κβ���
		 * 2������map��鿴��ǰһ��Ԫ�غͺ�һ��Ԫ���Ƿ���map��������ʾ������Ҫ���±䳤��
		 * 3�����������и��£���Ҫ��������ͷβ�������г�����������ͷβ��λ����ȫ���Ը��ݵ�ǰԪ���Ʋ�õ�����һ����û���뵽��
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
				// ���뼰ʱ���£�ͷβ�Ĳ��ܱ�֤����������������г���
				hm.put(nums[i] - front, length);
				hm.put(nums[i] + behind, length);

			}
		}

		// ���ܼ򵥵�ֻ����Ԫ��ǰһ�����֣���Ϊ�����鲻������ģ��ڴ���4��ʱ���п���3��û�в���

		return maxlength;
		// hashmap�ı���
		/*
		 * Iterator iter=hm.entrySet().iterator(); while(iter.hasNext()) { Map.Entry
		 * entry=(Map.Entry)iter.next(); Object key=entry.getKey(); Object
		 * value=entry.getValue(); }
		 */

	}

	public static int LongestConsecutiveSeq(int[] nums) {
		int length = 0;
		// �ҳ��������������+1��Ϊ��¼����ĳ���
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= length)
				length = nums[i];
		}
		// �ü�¼�����¼ÿ��Ԫ�ص�����
		int[] temp = new int[length + 1];// �������������ˡ�������
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
