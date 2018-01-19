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
		 * 第二种方法 这种从链表结尾数第k个的问题，（主要是找到倒数第k个结点）
		 * 想到用两个指针，让两指针相距k-1个结点，当后面那个指针到尾时，前面那个指针所指的就是倒数第k个结点了
		 * 这个差不多就不写了，只不过是一遍就找到倒数第k个结点 第三种方法 先从头到尾遍历链表，并记录链表元素的个数 之后将尾和头连接起来，
		 * 再然后从尾元素开始向前遍历c-k个元素，此时将链断开即可
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
		 * 这题有一点需要注意，k可能会比表长还要大，这时候要取模 以及k=0时相当于不做改变
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
