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
         * 使二位数组顺时针旋转90度
         * 关键点应该在于减少交换的次数
         * 1、先将对称元素位置交换，注意交换位置时要注意不要换过头等于没换
         * 2、后将改变后数组第一列和最后一列……进行交换
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
