package linkedlist;

public class PartitionList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(2);
		ListNode pre = l1;
		pre.next = new ListNode(1);
		pre = l1;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
		System.out.println("----------------------------------");
		ListNode l3 = partition(l1, 2);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
	}

	public static ListNode partition(ListNode head, int x) {
		/*
		 * 链表操作就那么点，不过跟数组outofboundary类似要谨防出现null pointer的问题 
		 * Given a linked list and a
		 * value x, partition it such that all nodes less than x come before nodes
		 * greater than or equal to x.
		 * 
		 * You should preserve the original relative order of the nodes in each of the
		 * two partitions.
		 * 
		 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
		 */
		ListNode less = new ListNode(0);
		less.next = head;
		if (head == null) {
			return null;
		}
		ListNode lessTail = less, moreTail = null, more = new ListNode(0);
		ListNode p = less.next;
		while (p != null && p.val < x) {
			lessTail = p;
			p = p.next;

		}
		more.next = p;
		moreTail = p;
		if (p != null) {
			p = p.next;
		}
		while (p != null) {
			if (p.val >= x) {
				moreTail.next = p;
				moreTail = p;

			} else {
				lessTail.next = p;
				lessTail = p;
			}
			p = p.next;
		}
		if (lessTail != null) {
			lessTail.next = more.next;
		}
		if (moreTail != null) {
			moreTail.next = null;
		}

		return less.next;
	}

}
