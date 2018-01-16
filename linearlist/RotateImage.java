package linearlist;

public class RotateImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}
	public static void swap(int a,int b) {
		int temp=a;
		a=b;
		b=temp;
	}
	public static void rotate(int[][] matrix) {
        /*
         * ʹ��λ����˳ʱ����ת90��
         * �ؼ���Ӧ�����ڼ��ٽ����Ĵ���
         * 1���Ƚ��Գ�Ԫ��λ�ý�����ע�⽻��λ��ʱҪע�ⲻҪ����ͷ����û��
         * 2���󽫸ı�������һ�к����һ�С������н���
         */
		if(matrix[0].length<=1) {
			return;
		}
		int length=matrix[0].length;
		for(int i=0;i<length;i++) {
			for(int j=i+1;j<length;j++) {
				int temp=matrix[i][j];
				matrix[i][j]=matrix[j][i];
				matrix[j][i]=temp;
				
			}
		}
		int i=0,j=length-1;
		while(i<j) {
			for(int m=0;m<length;m++) {
				int temp=matrix[m][i];
				matrix[m][i]=matrix[m][j];
				matrix[m][j]=temp;
						
				
			}
			i++;
			j--;
		}
		
    }
}
