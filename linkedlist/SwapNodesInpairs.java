package linkedlist;

public class SwapNodesInpairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		ListNode pre = l1;
		pre.next = new ListNode(2);
		pre = pre.next;
		pre.next = new ListNode(3);
		pre = pre.next;
		pre.next = new ListNode(4);
		pre = l1;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
		System.out.println("----------------------------------");
		ListNode l3 = swapPairs(l1);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
	}

	public static ListNode swapPairs(ListNode head) {
		/*
		 * Given a linked list, swap every two adjacent nodes and return its head.
		 * 
		 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
		 * 
		 * Your algorithm should use only constant space. You may not modify the values
		 * in the list, only nodes itself can be changed.
		 */
		ListNode ls = new ListNode(0);
		ls.next = head;
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
		if (head.next.next == null) {
			ls.next = head.next;
			head.next.next = head;
			head.next = null;
			return ls.next;

		}
		// 表长大于等于3时
		ListNode pre = ls, p = head, q = p.next, next = null;
		while (q != null) {
			next = q.next;
			pre.next = q;
			p.next = next;
			q.next = p;
			pre = p;
			p = next;
			q = (p != null) ? p.next : null;
		}
		return ls.next;
	}
}
