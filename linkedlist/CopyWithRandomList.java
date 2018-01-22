package linkedlist;

public class CopyWithRandomList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomListNode l1 = new RandomListNode(1);
		RandomListNode pre = l1;
		pre.next = new RandomListNode(2);
		pre = pre.next;
		pre.next = new RandomListNode(3);
		pre = pre.next;
		pre.next = new RandomListNode(4);
		pre = pre.next;
		pre.next = new RandomListNode(5);
		pre = l1;
		while (pre != null) {
			System.out.println(pre.label);
			pre = pre.next;
		}
		System.out.println("----------------------------------");
		RandomListNode l3 = copyRandomList(l1);
		pre = l3;
		while (pre != null) {
			System.out.println(pre.label);
			pre = pre.next;
		}
	}

	public static RandomListNode copyRandomList(RandomListNode head) {
		/*
		 * A linked list is given such that each node contains an additional random
		 * pointer which could point to any node in the list or null.
		 * 
		 * Return a deep copy of the list.
		 * 这题自己想了个用辅助数组的算法，还要再数组中查找，太麻烦，显然不是最优解，看了下答案，相当巧妙
		 * 可以直接将新结点插入到数组中，然后，利用这个新的链表的关系，来copy 当然，应该先弄random的链，再弄next的链 要注意的时不能改变原来的链表
		 */
		RandomListNode ls = new RandomListNode(0);
		if (head == null) {
			return null;
		}
		ls.next = head;
		RandomListNode p = head;
		while (p != null) {
			RandomListNode temp = new RandomListNode(p.label);
			temp.next = p.next;
			temp.random = p.random;
			p.next = temp;
			p = p.next.next;

		}

		p = head.next;
		ls.next = p;
		while (p != null) {
			p.random = (p.random == null) ? null : p.random.next;
			p = (p.next == null) ? null : p.next.next;
		}
		RandomListNode p1 = head, p2 = head.next;
		while (p1 != null && p2 != null) {
			p1.next = p2.next;
			p2.next = (p1.next == null) ? null : p1.next.next;
			p1 = p1.next;
			p2 = p2.next;
		}
		return ls.next;
	}
}
