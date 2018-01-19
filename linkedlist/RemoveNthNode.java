package linkedlist;

public class RemoveNthNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		ListNode pre = l1;
		pre.next = new ListNode(2);
		pre = l1;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
		System.out.println("----------------------------------");
		ListNode l3 = removeNthFromEnd(l1, 1);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		/*
		 * Given a linked list, remove the nth node from the end of list and return its
		 * head.
		 * 
		 * For example,
		 * 
		 * Given linked list: 1->2->3->4->5, and n = 2.
		 * 
		 * After removing the second node from the end, the linked list becomes
		 * 1->2->3->5. Note: Given n will always be valid. Try to do this in one pass.
		 */
		ListNode ls = new ListNode(0);
		ls.next = head;
		if (head == null) {
			return null;
		}
		if (head.next == null && n == 1) {
			return null;
		}
		ListNode pre = ls, p = ls.next, q = p;
		int c = n;
		if (c == 1) {
			q = p.next;
			while (q.next != null) {
				pre = p;
				p = p.next;
				q = q.next;
			}
			p.next = null;
			return ls.next;

		}
		while (c > 1 && q != null) {
			q = q.next;
			c--;
		}
		while (q.next != null) {
			pre = p;
			p = p.next;
			q = q.next;
		}
		pre.next = p.next;

		return ls.next;
	}
}
