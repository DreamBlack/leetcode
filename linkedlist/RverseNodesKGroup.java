package linkedlist;

public class RverseNodesKGroup {

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
		ListNode l3 = reverseKGroup(l1, 3);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.val);
			pre = pre.next;
		}
	}

	public static ListNode reverseKGroup(ListNode head, int k) {
		/*
		 * 一次通过棒棒哒
		 * 
		 * Given a linked list, reverse the nodes of a linked list k at a time and
		 * return its modified list.
		 * 
		 * k is a positive integer and is less than or equal to the length of the linked
		 * list. If the number of nodes is not a multiple of k then left-out nodes in
		 * the end should remain as it is.
		 * 
		 * You may not alter the values in the nodes, only nodes itself may be changed.
		 * 
		 * Only constant memory is allowed.
		 * 
		 * For example, Given this linked list: 1->2->3->4->5
		 * 
		 * For k = 2, you should return: 2->1->4->3->5
		 * 
		 * For k = 3, you should return: 3->2->1->4->5
		 * 
		 * 思路： 先扫描链表，记下链表长，递归实现 每次翻转前k个元素，后将n-k个元素继续翻转（当n<=1时退出循环） 需要注意的是
		 * 1、java对象作为函数参数，传递的是引用
		 * 2、翻转过程尤其是分部分递归时要保证链不断，因此要把之前的链最后一个结点传递个后半段作为头结点
		 */
		ListNode ls = new ListNode(0);
		ls.next = head;
		ListNode p = head;
		int length = 0;
		while (p != null) {
			length++;
			p = p.next;
		}
		reverseKGroupSUB(head, ls, k, length);
		return ls.next;
	}

	public static void reverseKGroupSUB(ListNode head, ListNode headpre, int k, int length) {

		headpre.next = head;

		if (length <= 1 || k > length || head == null || k <= 1) {
			return;
		}

		ListNode pre = headpre, p = head, q = head.next, temp = null;
		int i = 0;
		while (q != null && i < k - 1) {
			temp = q.next;
			q.next = p;
			pre.next = q;
			p = q;
			q = temp;
			i++;

		}
		reverseKGroupSUB(q, head, k, length - k);
	}
}
