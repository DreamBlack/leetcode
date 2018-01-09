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
		 * ��һ������û�и㶮��ΪʲôҪ�ж�a��b���ȴ�С��
		 * ע���k�����Ԫ�أ����ڵ�kλ����Ԫ�ص�����
		 * һ������midmin��벿��(���Ҳ�����midmin)��Ҳ����midmax�Ұ벿�֣������ܰ���midmax��
		 * �������ֱ���е�min��벿�ֺ�max�Ұ벿�֣�ʣ���м�Ĳ����������ַ����ݹ鴦������λ������Ϊ�е��������ֿ��ܴ�С��ͬ
		 * һ��˼·��ÿ�������е�������ͬ��Ԫ�أ�Ȼ���ٶ��м䲿�ֵĽ��еݹ�����λ��
		 * ��һ�����е�֮��ת��Ϊ��ʣ�����������е�mid-k���Ԫ�أ�����kΪɾ���Ľ�С���ֵ�Ԫ�ظ�����
		 * ѡ�õڶ��ַ������������Ĺؼ����Ǻ�ʱ�˳�ѭ��
		 * 1����a[n/2]=b[m/2]ʱ������
		 * 2����a�ջ�b��ʱ����b[m/2]��a[n/2]
		 * 3����k=1ʱ������Min(a[0],b[0])��û�п��ǵ���
		 * 
		 * ���ڶ�������Ϊ����������Ӧ���صڣ�m+n��/2+1��Ԫ�أ�����ż����Ԫ�ص������Ƿ��ص�(m+n)/2���ͣ�m+n��/2+1����ƽ����
		 * �ֿ����۱Ƚϸ��ӣ����ת��Ϊ�ҵ�(m+n)/2��Ԫ��
		 */
		int len1=tail1-head1+1;
		int len2=tail2-head2+1;
		if(len1>len2) return findMedianSortedArrays(nums2,head2,tail2, nums1,head1,tail1,k);
		//����ֻ�迼��nums1�̣�nums2�������
		if(len1==0)//��д�����ֹԽ�����
	    	return nums2[head2+k-1];
		if(len2==0)//��д�����ֹԽ�����
	    	return nums1[head1+k-1];
		if(k==1)
			return (nums1[head1]<=nums2[head2])?(nums1[head1]):(nums2[head2]);
	    //��ֹԽ��
		/*
		 * �ȷ���
		 * ��ȥ��ͷβ�����֡��ؼ�����ȷ���µ�head,tail
		 * �ⲿ�ֿ���˵��д�˺ܾ��ˣ����Ǳ�Խ��Ĵ�
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
		 * ֱ���ҵ���[m+n/2]��Ԫ�أ�ֻ�����˺ϼ�������Ԫ�ص����
		 * ���Խ�����һ�����鴦��������Ҳ����while����
		 * ע��count<mid����count<=mid
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
