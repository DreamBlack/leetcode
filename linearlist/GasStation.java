package linearlist;

public class GasStation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] gas = { 2,4};
		int[] cost = { 3,4 };
		System.out.println(canCompleteCircuit(gas, cost));
		System.out.println(canCompleteCircuitEhanced(gas, cost));
	}

	public static int canCompleteCircuitEhanced(int[] gas, int[] cost) {
		// 判断循环到头老是搞不好
		/*
		 * 其实有一个地方可以优化
		 * 如果从i站走都j站都是可能的，而在再从j站走到j+1站却失败了。普通解法是此时让i从i+1站再开始重新走一遍。
		 * 但其实这里可以直接让i=j+1，因为从i到j的过程正total始终是大于等于0的，在走到j+1的时候total突然小于0的。
		 * 需要明确如果路是通的，那没走过一个站只能让total不变或者增大，即total是单调不减的，i到j+1走不了的时候，i+1到j+1必然也是走不了的
		 * 因为缺少一个i站total只会不变或者更小，仍然是经受不了到J+1站的考验的（就像字符串匹配模式那样）
		 * 另一个方面，上一种解法中考虑了一个循环的问题，导致程序的出口写的不明确，经常死循环。其实根本不用考虑循环的问题。
		 * 反正循环一遍车站就是把每个站点的gas[i]-cost[i]的值加起来，需要考虑的是从哪个i开始加循环加一遍可以让没到一个站点的时候total都是非负数。
		 */
		if (gas.length == 1) {
			return (gas[0] >= cost[0]) ? 0 : -1;
		}
		if (gas.length <= 0) {
			return -1;
		}
		int total=0;
		int sum=0;
		int j=-1;
		for(int i=0;i<gas.length;i++) {
			total+=gas[i]-cost[i];//记录总的油量，若所有加起来都小于0，肯定没有出路
			sum+=gas[i]-cost[i];
			if(total<0) {
				//total为负，不可取，从下一个站点开始
				j=i;
				sum=0;
			}
		}
		if(total<0) {
			return -1;
		}else {
			return j+1;
		}
	}

	public static int canCompleteCircuit(int[] gas, int[] cost) {
		// 判断循环到头老是搞不好
		if (gas.length == 1) {
			return (gas[0] >= cost[0]) ? 0 : -1;
		}
		if (gas.length <= 0) {
			return -1;
		}
		int totalGas = 0;

		int i = 0;
		int front = 0;
		int j = 1;
		boolean a = true;
		while (a) {

			totalGas = totalGas + gas[front] - cost[front];
			if (totalGas < 0) {
				i = (i == gas.length - 1) ? 0 : i + 1;
				front = i;
				j = (i == gas.length - 1) ? 0 : i + 1;
				totalGas = 0;
				if (i == 0) {
					a = false;
				}
			} else if (i == j) {
				return i;
			} else {
				front = j;
				j = (j == gas.length - 1) ? 0 : j + 1;
			}
		}
		return -1;
	}

}
