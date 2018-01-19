package linkedlist;

public class RotateList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		ListNode pre = l1;
		pre.next = new ListNode(2);
		pre = pre.next;
		pre.next = new ListNode(3);
		pre = pre.next;
		pre.next = new ListNode(4);
		pre = pre.next;
		pre.next = new ListNode(5);
		pre = l1;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
		System.out.println("----------------------------------");
		ListNode l3 = rotateRightTwo(l1, 2);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
	}

	public static ListNode rotateRightTwo(ListNode head, int k) {
		/*
		 * �ڶ��ַ��� ���ִ������β����k�������⣬����Ҫ���ҵ�������k����㣩
		 * �뵽������ָ�룬����ָ�����k-1����㣬�������Ǹ�ָ�뵽βʱ��ǰ���Ǹ�ָ����ָ�ľ��ǵ�����k�������
		 * ������Ͳ�д�ˣ�ֻ������һ����ҵ�������k����� �����ַ��� �ȴ�ͷ��β������������¼����Ԫ�صĸ��� ֮��β��ͷ����������
		 * ��Ȼ���βԪ�ؿ�ʼ��ǰ����c-k��Ԫ�أ���ʱ�����Ͽ�����
		 */
		ListNode ls = new ListNode(0);
		ls.next = head;
		if (head == null) {
			return null;
		}
		if (k == 0) {
			return head;
		}

		ListNode p = ls;
		int c = 0;
		while (p.next != null) {
			c++;
			p = p.next;
		}

		if (k >= c) {
			k = k % c;
		}
		if (k == 0) {
			return head;
		}
		p.next = ls.next;
		int j = c - k;
		while (j > 0) {
			p = p.next;
			j--;
		}
		ls.next = p.next;
		p.next = null;
		return ls.next;
	}

	public static ListNode rotateRight(ListNode head, int k) {
		/*
		 * Given a list, rotate the list to the right by k places, where k is
		 * non-negative.
		 * 
		 * Example: Given 1->2->3->4->5->NULL and k = 2, return 4->5->1->2->3->NULL.
		 * ������һ����Ҫע�⣬k���ܻ�ȱ���Ҫ����ʱ��Ҫȡģ �Լ�k=0ʱ�൱�ڲ����ı�
		 */
		ListNode ls = new ListNode(0);
		ls.next = head;
		ListNode p = ls.next;
		if (head == null) {

			return null;
		}
		if (k == 0) {
			return head;
		}

		int c = 0;
		while (p != null) {
			c++;
			p = p.next;
		}
		if (k >= c) {
			k = k % c;
		}
		if (k == 0) {
			return head;
		}
		p = ls;
		int i = 0;
		for (; i < c - k; i++) {
			p = p.next;
		}
		ListNode newhead = p.next;
		p.next = null;
		ListNode oldhead = ls.next;
		ls.next = newhead;
		p = newhead;
		while (p.next != null) {
			p = p.next;
		}
		p.next = oldhead;
		return ls.next;
	}
}
