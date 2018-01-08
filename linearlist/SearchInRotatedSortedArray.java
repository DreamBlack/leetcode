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
		// 每次二分后以mid为边界的两个数组最多只有一个是无序的
		if (head > tail)
			return -1;
		int lOr = 0;// 0表示在左边子数组查找，1表示在右边子数组查找
		if (head == tail && nums[head] == target)
			return head;
		int mid = (head + tail) / 2;
		if (target == nums[mid])
			return mid;
		else if (target < nums[mid]) {// 比中间元素值小
			if (nums[head] <= nums[mid]) {
				// 元素有序
				if (nums[head] <= target)
					lOr = 0;
				else
					lOr = 1;
			} else {
				// 则右边必有序
				lOr = 0;
			}
		} else if (target > nums[mid]) {// 比中间元素值大
			if (nums[mid] <= nums[tail]) {
				// 元素有序
				if (nums[tail] >= target)
					lOr = 1;
				else
					lOr = 0;
			} else {
				// 则左边必有序
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
			if (nums[first] <= nums[mid]) {// 左半边有序
				if (nums[first] <= target && target < nums[mid])// 在左边找
					last = mid;
				else// 在右边找
					first = mid + 1;
			} else {// 左半边无序，则右半边比如有序
				if (nums[mid] < target && target <= nums[last - 1])// 在右边找
					first = mid + 1;
				else// 在左边找
					last = mid;
			}

		}
		return -1;
	}

	public static boolean partionDup(int[] nums, int target) {
		/*
		 * 手动打星--------------------------------------------------------------------
		 * 每次二分后以mid为边界的两个数组最多只有一个是无序的，但现在无法用简单的头小于尾断定其为有序.
		 * 此外如果不利于部分有序的方法进行二分查找，感觉不如直接遍历了。 因此应该在原本基础上做少量修改进行优化。
		 * 若nums[i]<nums[j]等号不成立，则必有序，按原来方法即可；
		 * 若nums[mid]=nums[tail]，不好判断，让mid+1，进而判断mid+1,tail是否有序
		 * 嗯哼，我這個效率極差
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
			else if (nums[head] < nums[mid]) {// 左边有序
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
			} // 处理右边
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
		 * 为啥我的分类讨论总是那么烦，代码那么长
		 */
		int first=0,last=a.length;
		while(first!=last) {
			int mid=(first+last)/2;
			if(a[mid]==target)
				return true;
			if(a[first]<a[mid]) {//左边有序
				if(a[first]<=target&&target<a[mid])
					last=mid;
				else
					first=mid+1;
				
			}else if(a[first]>a[mid]) {//左边无序，则右边有序
				if(a[mid]<=target&&target<=a[last-1])
					first=mid+1;
				else
					last=mid;
			}
			else//左边有序或者无序不定
				first++;
		}
		return false;
			
		}
			
		
}
