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
		int[] temp = new int[gas.length];
		for (int i = 0; i < gas.length; i++) {
			temp[i] = gas[i] - cost[i];
		}
		int total = 0;
		for (int i = 0; i < gas.length; i++) {
			total += temp[i];
			if (total >= 0) {
				for (int j = (i == gas.length - 1) ? 0 : i + 1; ; j=(j == gas.length - 1) ? 0 : j + 1) {
					total+=temp[j];
					if(total<0) {
						break;
					}
					if(((j == gas.length - 1) ? 0 : j + 1) == i){
						return i;
					}

				}
			}
			total=0;
		}
		return -1;
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
		boolean a=true;
		while (a){
			
			totalGas = totalGas + gas[front] - cost[front];
			if (totalGas < 0) {
				i=(i==gas.length-1)?0:i+1;
				front = i;
				j = (i == gas.length - 1) ? 0 : i + 1;
				totalGas = 0;
				if(i==0) {
					a=false;
				}
			}else if (i == j) {
				return i;
			}else {
				front = j;
				j = (j == gas.length - 1) ? 0 : j + 1;
			}
		}
		return -1;
	}

}
