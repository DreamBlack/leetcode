package linearlist;

public class ClimbingStairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(climbStairsEhancedVersion(44));
		System.out.println(climbStairsDinamicPlaceReduce(44));
	}
	public static int climbStairsDinamicPlaceReduce(int n) {
		/*
		 * ����һ�ֶ�̬�滮�Ľⷨ�ϣ���Ȼ�ӿ����ٶȣ���ͬʱ�����˿ռ俪������ʵ����ֻ��Ҫsteps[n]��ֵ��֮ǰ0-n-1���м��������Ҫ
		 */
		if(n<4) {
			return n;
		}
		
		int a=1;
		int b=1;
		int c=2;
		for(int i=2;i<n;i++) {
			int temp=b;
			a=temp;
			b=c;
			c=temp+c;
			
		}
		return c;
	}
	public static int climbStairsDinamicPro(int n) {
		/*
		 * ��ʼ�ݹ���������һ�����⣬����3��¥�ݻ��ظ�����ܶ�Σ�û�г������ÿ�μ�������м����⣬������˷�
		 * ��̬�滮
		 * �����ַ�ʽ�����ߵ���n�ף�һ���Ǵӵ�n-1����һ����һ���Ǵ�n-2��������
		 */
		if(n<4) {
			return n;
		}
		int[] steps=new int[n+1];
		steps[0]=1;
		steps[1]=1;
		for(int i=2;i<n+1;i++) {
			steps[i]=steps[i-1]+steps[i-2];
		}
		return steps[n];
	}
	public static int climbStairsEhancedVersion(int n) {
		/*
		 * ֮ǰ�ĵݹ鷽������������˵��ÿ��������һ������ԭ����¥�ݷֳ���1�׺�N-1�ף�Ϊ�����ʱ��Ч�ʣ��ɲ��ö��ַ���˼��ÿ�η�Ϊ2/n��2/n
		 * �������ַ���Ҫע��NΪż��������ʱ��ͬ�Ĵ�����
		 */
		if (n == 1 || n == 0) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else {
			int mid = n / 2;
			return (n % 2 == 0) ? climbStairsEhancedVersion(mid) * climbStairsEhancedVersion(mid) + climbStairsEhancedVersion(mid - 1) * climbStairsEhancedVersion(mid - 1)
					: climbStairsEhancedVersion(mid + 1) * climbStairsEhancedVersion(mid) + climbStairsEhancedVersion(mid ) * climbStairsEhancedVersion(mid - 1);
		}

	}

	public static int climbStairs(int n) {
		/*
		 * ����n��¥�ݣ�ÿ��ֻ����һ�������������м��ַ�������ȥ �����Ȼ�ǵݹ飬�����Ȼtime limited������ Ч��̫���ˣ�������������ķ���
		 */
		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} else {
			return climbStairs(n - 1) + climbStairs(n - 2);
		}

	}
}
