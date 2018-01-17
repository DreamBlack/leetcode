package linearlist;

public class ClimbingStairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(climbStairsEhancedVersion(44));
		System.out.println(climbStairsDinamicPlaceReduce(44));
	}
	public static int climbStairsDinamicPlaceReduce(int n) {
		/*
		 * 在上一种动态规划的解法上，虽然加快了速度，但同时增加了空间开销，其实我们只需要steps[n]的值，之前0-n-1的中间根本不需要
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
		 * 初始递归问题再于一个问题，如爬3层楼梯会重复计算很多次，没有充分利用每次计算出的中间问题，造成了浪费
		 * 动态规划
		 * 有两种方式可以走到底n阶，一种是从第n-1阶爬一步，一种是从n-2阶爬两步
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
		 * 之前的递归方法按本质上来说是每次往后退一步，将原来的楼梯分成了1阶和N-1阶，为了提高时间效率，可采用二分法的思想每次分为2/n和2/n
		 * 但是这种方法要注意N为偶数和奇数时不同的处理方法
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
		 * 共有n层楼梯，每次只能走一步或两步，问有几种方法爬上去 此题必然是递归，结果居然time limited。。。 效率太低了，看来得用另外的方法
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
