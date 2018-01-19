package linkedlist;

public class RverseLinkedListTwo {

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
		ListNode l3 = reverseBetween(l1, 1, 4);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
	}

	public static ListNode reverseBetween(ListNode head, int m, int n) {
		/*
		 * 需要注意的是，这是不带头结点的单链表，所有对于从1开始和从m>1开始的链表操作有所区别 为了不去关注这种操作上的区别，可以人为设置一个头结点
		 */
		ListNode ls = new ListNode(0);
		int i = 1;
		ListNode p = head;
		ListNode pre = head;
		ls.next = pre;
		pre = ls;

		while (p != null && i < m) {
			pre = p;
			p = p.next;

			i++;
		}
		ListNode q = p.next;
		ListNode tail = p;
		while (p != null & i < n) {
			ListNode temp = q.next;
			;
			pre.next = q;

			q.next = p;
			q = temp;
			i++;

			p = pre.next;

		}
		tail.next = q;
		return ls.next;
	}
}
