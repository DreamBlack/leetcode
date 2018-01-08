package linearlist;

public class MedianOfTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
public static double findMedianSortedArrays(int[] nums1,int head1,int tail1, int[] nums2,int head2,int tail2) {
		/*
		 * 一定不在midmin左半部分，也不在midmax右半部分
		 */
        int length1=tail1-head1+1;
        int mid1=(length1-1)/2;
        int length2=tail2-head2+1;
        int mid2=(length2-1)/2;
        int median=(length1+length2)/2;
        
        int cleft=0,cright=0;
      
        	
        
        return 0;
    }
}
