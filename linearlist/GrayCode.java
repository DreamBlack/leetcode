package linearlist;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> ls = new ArrayList<Integer>();
		ls = grayCode(3);
		for (int i = 0; i < ls.size(); i++) {
			System.out.println(ls.get(i));
		}

	}

	public static List<Integer> grayCode(int n) {
		/*
		 * 这题花了很长时间。刚开始没有摸清规律，瞎做，做不对也做不出来 后来突然发现这东西是对称的。。。然后就简单了 其次求2的指数，左移远比调用函数来的快的多
		 * 还有一种利用的是格雷码和二进制直接的转换公式，那就相当快了 G(n) = B(n) XOR B(n+1) 当然不知道这个的还是用对称比较简单的多
		 */
		List<Integer> ls = new ArrayList<Integer>();
		int a = 0;
		if (n < 0) {
			return ls;
		} else if (n == 0) {
			ls.add(0);
			return ls;
		} else {
			ls.add(0);
			ls.add(1);
			int j = 1;
			while (j < n) {
				for (int i = (1 << j) - 1; i >= 0; i--) {
					a = ls.get(i);
					a = a + (1 << j);
					ls.add(a);
				}
				j++;
			}

		}
		return ls;
	}

}
