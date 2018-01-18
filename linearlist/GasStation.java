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
		// �ж�ѭ����ͷ���Ǹ㲻��
		/*
		 * ��ʵ��һ���ط������Ż�
		 * �����iվ�߶�jվ���ǿ��ܵģ������ٴ�jվ�ߵ�j+1վȴʧ���ˡ���ͨ�ⷨ�Ǵ�ʱ��i��i+1վ�ٿ�ʼ������һ�顣
		 * ����ʵ�������ֱ����i=j+1����Ϊ��i��j�Ĺ�����totalʼ���Ǵ��ڵ���0�ģ����ߵ�j+1��ʱ��totalͻȻС��0�ġ�
		 * ��Ҫ��ȷ���·��ͨ�ģ���û�߹�һ��վֻ����total����������󣬼�total�ǵ��������ģ�i��j+1�߲��˵�ʱ��i+1��j+1��ȻҲ���߲��˵�
		 * ��Ϊȱ��һ��iվtotalֻ�᲻����߸�С����Ȼ�Ǿ��ܲ��˵�J+1վ�Ŀ���ģ������ַ���ƥ��ģʽ������
		 * ��һ�����棬��һ�ֽⷨ�п�����һ��ѭ�������⣬���³���ĳ���д�Ĳ���ȷ��������ѭ������ʵ�������ÿ���ѭ�������⡣
		 * ����ѭ��һ�鳵վ���ǰ�ÿ��վ���gas[i]-cost[i]��ֵ����������Ҫ���ǵ��Ǵ��ĸ�i��ʼ��ѭ����һ�������û��һ��վ���ʱ��total���ǷǸ�����
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
			total+=gas[i]-cost[i];//��¼�ܵ������������м�������С��0���϶�û�г�·
			sum+=gas[i]-cost[i];
			if(total<0) {
				//totalΪ��������ȡ������һ��վ�㿪ʼ
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
		// �ж�ѭ����ͷ���Ǹ㲻��
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
