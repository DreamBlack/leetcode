package linearlist;

public class SearchInRotatedSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 4, 5, 6, 7, 8, 1, 2, 3 };
		int[] nums2 = { 1, 1, 3, 1 };
		System.out.println(search(nums, 8));
		System.out.println(search(nums2, 3));
		if (partionDup(nums2, 3))
			System.out.println("find it");
		else
			System.out.println("not found");
	}

	public static int search(int[] nums, int target) {

		if (nums.length < 1)
			return -1;
		return partion(0, nums.length - 1, nums, target);
	}

	public static int partion(int head, int tail, int[] nums, int target) {
		// ÿ�ζ��ֺ���midΪ�߽�������������ֻ��һ���������
		if (head > tail)
			return -1;
		int lOr = 0;// 0��ʾ�������������ң�1��ʾ���ұ����������
		if (head == tail && nums[head] == target)
			return head;
		int mid = (head + tail) / 2;
		if (target == nums[mid])
			return mid;
		else if (target < nums[mid]) {// ���м�Ԫ��ֵС
			if (nums[head] <= nums[mid]) {
				// Ԫ������
				if (nums[head] <= target)
					lOr = 0;
				else
					lOr = 1;
			} else {
				// ���ұ߱�����
				lOr = 0;
			}
		} else if (target > nums[mid]) {// ���м�Ԫ��ֵ��
			if (nums[mid] <= nums[tail]) {
				// Ԫ������
				if (nums[tail] >= target)
					lOr = 1;
				else
					lOr = 0;
			} else {
				// ����߱�����
				lOr = 1;
			}
		}
		if (lOr == 0)
			return partion(head, mid - 1, nums, target);
		else
			return partion(mid + 1, tail, nums, target);
	}

	public static int searchBookVersion(int[] nums, int target) {
		int first = 0, last = 0;
		while (first != last) {
			int mid = (first + last) / 2;
			if (nums[mid] == target)
				return mid;
			if (nums[first] <= nums[mid]) {// ��������
				if (nums[first] <= target && target < nums[mid])// �������
					last = mid;
				else// ���ұ���
					first = mid + 1;
			} else {// �����������Ұ�߱�������
				if (nums[mid] < target && target <= nums[last - 1])// ���ұ���
					first = mid + 1;
				else// �������
					last = mid;
			}

		}
		return -1;
	}

	public static boolean partionDup(int[] nums, int target) {
		/*
		 * �ֶ�����--------------------------------------------------------------------
		 * ÿ�ζ��ֺ���midΪ�߽�������������ֻ��һ��������ģ��������޷��ü򵥵�ͷС��β�϶���Ϊ����.
		 * ������������ڲ�������ķ������ж��ֲ��ң��о�����ֱ�ӱ����ˡ� ���Ӧ����ԭ���������������޸Ľ����Ż���
		 * ��nums[i]<nums[j]�ȺŲ�������������򣬰�ԭ���������ɣ�
		 * ��nums[mid]=nums[tail]�������жϣ���mid+1�������ж�mid+1,tail�Ƿ�����
		 * �źߣ����@��Ч�ʘO��
		 */
		int head=0;
		int tail=nums.length-1; 
		if (head > tail)
			return false;
		while (head <= tail) {
			if (head == tail && nums[head] == target)
				return true;
			int mid = (head + tail) / 2;
			if (target == nums[mid])
				return true;
			else if (nums[head] < nums[mid]) {// �������
				if (nums[head] <= target && target < nums[mid])
					tail = mid - 1;
				else
					head = mid + 1;
			} else if (nums[head] == nums[mid]) {
				int i = mid;
				for (; i >= head; i--) {
					if (nums[head] != nums[i])
						break;
				}

				if (i >= head && nums[head] <= target && target <= nums[i])
					tail = i;
				else
					head = mid + 1;
			} // �����ұ�
			else if (nums[mid] < nums[tail]) {
				if (nums[mid] < target && target <= nums[tail])
					head = mid + 1;
				else
					tail = mid - 1;
			} else if (nums[tail] == nums[mid]) {
				int i = mid;
				for (; i <= tail; i++) {
					if (nums[tail] != nums[i])
						break;
				}

				if (i <= tail && nums[i] <= target && target <= nums[tail])
					head = i;
				else
					tail = mid - 1;
			}

		}
		return false;
	}
	public static boolean partionDupBookVersion(int[] a, int target) {
		/*
		 * Ϊɶ�ҵķ�������������ô����������ô��
		 */
		int first=0,last=a.length;
		while(first!=last) {
			int mid=(first+last)/2;
			if(a[mid]==target)
				return true;
			if(a[first]<a[mid]) {//�������
				if(a[first]<=target&&target<a[mid])
					last=mid;
				else
					first=mid+1;
				
			}else if(a[first]>a[mid]) {//����������ұ�����
				if(a[mid]<=target&&target<=a[last-1])
					first=mid+1;
				else
					last=mid;
			}
			else//�������������򲻶�
				first++;
		}
		return false;
			
		}
			
		
}
