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
		 * ���⻨�˺ܳ�ʱ�䡣�տ�ʼû��������ɣ�Ϲ����������Ҳ�������� ����ͻȻ�����ⶫ���ǶԳƵġ�����Ȼ��ͼ��� �����2��ָ��������Զ�ȵ��ú������Ŀ�Ķ�
		 * ����һ�����õ��Ǹ�����Ͷ�����ֱ�ӵ�ת����ʽ���Ǿ��൱���� G(n) = B(n) XOR B(n+1) ��Ȼ��֪������Ļ����öԳƱȽϼ򵥵Ķ�
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
