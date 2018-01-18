package linkedlist;

import java.util.ArrayList;

public class AddTwoNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(9);
		ListNode pre = l1;
		pre.next = new ListNode(1);
		pre = pre.next;
		pre.next = new ListNode(6);
		ListNode l2 = new ListNode(0);
		ListNode l3 = addTwoNumbersTwo(l1, l2);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
	}

	public static ListNode addTwoNumbersTwo(ListNode l1, ListNode l2) {
		/*
		 * ���������룬��ʵ��������Ҫ�����鱣�档����
		 */
		ListNode now1 = l1;
		ListNode now2 = l2;
		int inc = 0;
		int sum = now1.val + now2.val;
		if (sum > 9) {
			sum = 0;
			inc = 1;
		}
		now1 = now1.next;
		now2 = now2.next;
		ListNode head = new ListNode(sum);
		ListNode ls = head;
		while (now1 != null & now2 != null) {
			sum = now1.val + now2.val + inc;
			if (sum > 9) {
				sum = sum % 10;
				inc = 1;
			} else {
				inc = 0;
			}
			ListNode next = new ListNode(sum);
			ls.next = next;
			ls = next;
			now1 = now1.next;
			now2 = now2.next;
		}
		while (now1 != null) {
			sum = now1.val + inc;
			if (sum > 9) {
				sum = sum % 10;
				inc = 1;
			} else {
				inc = 0;
			}
			ListNode next = new ListNode(sum);
			ls.next = next;
			ls = next;
			now1 = now1.next;
		}
		while (now2 != null) {
			sum = now2.val + inc;
			if (sum > 9) {
				sum = sum % 10;
				inc = 1;
			} else {
				inc = 0;
			}
			ListNode next = new ListNode(sum);
			ls.next = next;
			ls = next;
			now2 = now2.next;
		}
		if (inc == 1) {
			ls.next = new ListNode(1);
		}
		return head;
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		/*
		 * ����������ֱ𱣴������������ ֮��ʹ��β�巨���Ӹ�λ��ʼ���������
		 */

		ArrayList<Integer> a1 = new ArrayList<Integer>();
		ListNode now = l1;
		while (now != null) {
			a1.add(now.val);
			now = now.next;
		}
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		now = l2;
		while (now != null) {
			a2.add(now.val);
			now = now.next;
		}

		int inc = 0;
		int sum = a1.get(0) + a2.get(0);
		if (sum > 9) {
			sum = sum % 10;
			inc = 1;
		}
		ListNode head = new ListNode(sum);
		ListNode ls = head;
		int i = 1;
		while (i < a1.size() && i < a2.size()) {
			sum = a1.get(i) + a2.get(i) + inc;
			if (sum > 9) {
				sum = sum % 10;
				inc = 1;
			} else {
				inc = 0;
			}
			ListNode next = new ListNode(sum);
			ls.next = next;
			ls = next;
			i++;
		}
		while (i < a1.size()) {
			// l1�ϳ�û����
			sum = a1.get(i) + inc;
			if (sum > 9) {
				sum = sum % 10;
				inc = 1;
			} else {
				inc = 0;
			}
			ListNode next = new ListNode(sum);
			ls.next = next;
			ls = next;
			i++;
		}
		while (i < a2.size()) {
			// l1�ϳ�û����
			sum = a2.get(i) + inc;
			if (sum > 9) {
				sum = sum % 10;
				inc = 1;
			} else {
				inc = 0;
			}
			ListNode next = new ListNode(sum);
			ls.next = next;
			ls = next;
			i++;
		}
		if (inc == 1) {
			ls.next = new ListNode(1);
		}
		return head;
	}

}
