package linkedlist;

public class RemoveDuplicatesTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		ListNode pre = l1;
		pre.next = new ListNode(1);
		pre = pre.next;
		pre.next = new ListNode(1);
		pre = pre.next;
		pre.next = new ListNode(1);
		pre = pre.next;
		pre.next = new ListNode(1);
		pre = l1;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
		System.out.println("----------------------------------");
		ListNode l3 = deleteDuplicatesTwo(l1);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
	}

	public static ListNode deleteDuplicatesTwo(ListNode head) {
		/*
		 * Given a sorted linked list, delete all nodes that have duplicate numbers,
		 * leaving only distinct numbers from the original list.
		 * 
		 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3,
		 * return 2->3
		 */
		ListNode ls = new ListNode(0);
		ls.next = head;
		if (head == null) {
			return null;
		}
		ListNode pre = ls, p = pre.next;
		while (p != null) {
			if ((p.next != null && p.next.val != p.val) || p.next == null) {
				pre.next = p;
				pre = p;

				p = p.next;
			} else {
				while (p.next != null && p.next.val == p.val) {
					p = p.next;
				}

				if (p.next == null) {
					pre.next = null;
				}

				p = p.next;

			}

		}
		return ls.next;
	}
}
