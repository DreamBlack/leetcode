package linkedlist;

public class RemoveDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		ListNode pre = l1;
		pre.next = new ListNode(1);
		pre = pre.next;
		pre.next = new ListNode(2);
		pre = pre.next;
		pre.next = new ListNode(3);
		pre = pre.next;
		pre.next = new ListNode(3);
		pre = l1;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
		System.out.println("----------------------------------");
		ListNode l3 = deleteDuplicates(l1);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
	}

	public static ListNode deleteDuplicates(ListNode head) {
		/*
		 * Given a sorted linked list, delete all duplicates such that each element
		 * appear only once.
		 * 
		 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
		 */
		ListNode ls = new ListNode(0);
		ls.next = head;
		if (head == null) {
			return null;
		}
		ListNode pre = ls.next;
		ListNode p = pre.next;
		while (p != null) {
			if (p.val == pre.val) {
				pre.next = p.next;
				p = pre.next;
			} else {
				pre = p;
				p = p.next;
			}
		}
		return ls.next;
	}
}
