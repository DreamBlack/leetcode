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
		 * ��Ҫע����ǣ����ǲ���ͷ���ĵ��������ж��ڴ�1��ʼ�ʹ�m>1��ʼ����������������� Ϊ�˲�ȥ��ע���ֲ����ϵ����𣬿�����Ϊ����һ��ͷ���
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
