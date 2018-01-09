package linearlist;

public class MedianOfTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums1 = {1,5,6,7};
		int[] nums2 = { 2,3,4,8 };
		System.out.println(findMedian1(nums1, nums2));
		System.out.println(findMedian2(nums1, nums2));
		
		
	}
	public static double findMedian2(int[] nums1,int[] nums2) {
		
		if ((nums1.length+nums2.length)%2!=0)
			return findMedianSortedArrays(nums1,0,nums1.length-1, nums2,0,nums2.length-1,(nums1.length+nums2.length)/2+1);
		else 
			return (findMedianSortedArrays(nums1,0,nums1.length-1, nums2,0,nums2.length-1,(nums1.length+nums2.length)/2+1)+
					findMedianSortedArrays(nums1,0,nums1.length-1, nums2,0,nums2.length-1,(nums1.length+nums2.length)/2))/2;
		
	}

	public static double findMedianSortedArrays(int[] nums1, int head1, int tail1, int[] nums2, int head2, int tail2,int k) {
		/*
		 * 有一个问题没有搞懂，为什么要判断a和b长度大小呢
		 * 注意第k个大的元素，和在第k位置上元素的区别
		 * 一定不在midmin左半部分(包且不包括midmin)，也不在midmax右半部分（但可能包括midmax）
		 * 但是如果直接切掉min左半部分和max右半部分，剩下中间的不好再用这种方法递归处理找中位数，因为切掉的两部分可能大小不同
		 * 一种思路是每次左右切掉长度相同的元素，然后再对中间部分的进行递归找中位数
		 * 另一种是切掉之后转化为找剩余两个数组中第mid-k大的元素，其中k为删掉的较小部分的元素个数，
		 * 选用第二种方法，接下来的关键就是何时退出循环
		 * 1、当a[n/2]=b[m/2]时，返回
		 * 2、当a空或b空时返回b[m/2]或a[n/2]
		 * 3、当k=1时，返回Min(a[0],b[0])（没有考虑到）
		 * 
		 * 由于对于总数为奇数的数组应返回第（m+n）/2+1个元素，对于偶数个元素的数组是返回第(m+n)/2个和（m+n）/2+1个的平均数
		 * 分开讨论比较复杂，最好转化为找第(m+n)/2个元素
		 */
		int len1=tail1-head1+1;
		int len2=tail2-head2+1;
		if(len1>len2) return findMedianSortedArrays(nums2,head2,tail2, nums1,head1,tail1,k);
		//这样只需考虑nums1短，nums2长的情况
		if(len1==0)//先写这个防止越界访问
	    	return nums2[head2+k-1];
		if(len2==0)//先写这个防止越界访问
	    	return nums1[head1+k-1];
		if(k==1)
			return (nums1[head1]<=nums2[head2])?(nums1[head1]):(nums2[head2]);
	    //防止越界
		/*
		 * 先分区
		 * 各去掉头尾两部分。关键在于确定新的head,tail
		 * 这部分可以说是写了很久了，老是报越界的错
		 */
	    int pa=(k/2-1+head1<head1+len1-1)?(k/2-1+head1):(head1+len1-1);
	    int pb=k-(pa-head1)+head2-2;
	    if(nums1[pa]==nums2[pb])
	    	return nums1[pa];
	    else if(nums1[pa]<nums2[pb]) {
	    	return findMedianSortedArrays(nums1,pa+1,tail1,nums2,head2,pb,k-(pa-head1+1));
	    }
	    else {
	    	return findMedianSortedArrays(nums1,head1,pa,nums2,pb+1,tail2,k-(pb-head2+1));
	    }
	    	

	}

	public static double findMedian1(int[] nums1, int[] nums2) {
		/*
		 * 直接找到第[m+n/2]的元素，只考虑了合计奇数个元素的情况
		 * 可以将其中一个数组处理完的情况也放在while里面
		 * 注意count<mid还是count<=mid
		 */
		int count = 0;
		int ptr1 = 0, ptr2 = 0;
		int mid = (nums1.length + nums2.length) / 2;

		while ((ptr1 < nums1.length || ptr2 < nums2.length)&&count<mid) {

			count++;
			if (ptr1 >= nums1.length)
				ptr2++;
			else if (ptr2 >= nums2.length)
				ptr1++;
			else {
				if (nums1[ptr1] <= nums2[ptr2])

					ptr1++;
				else
					ptr2++;
			}
		}
		if (ptr1 < nums1.length && ptr2 < nums2.length)
			return (nums1[ptr1] <= nums2[ptr2]) ? (nums1[ptr1]) : (nums2[ptr2]);
		else if (ptr1 >= nums1.length)
			return nums2[ptr2];
		else
			return nums1[ptr1];

	}
}
