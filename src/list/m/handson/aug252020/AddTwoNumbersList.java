package list.m.handson.aug252020;

import handson.ListNode;

public class AddTwoNumbersList {

	public static void main(String[] args) {
		// [2,4,3]
		// [5,6,4]
		/*
		 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
		 * 
		 * Output: 7 -> 0 -> 8
		 * 
		 * Explanation: 342 + 465 = 807.
		 */
		ListNode l1 = new ListNode(1);
		ListNode l11 = new ListNode(2);
		ListNode l111 = new ListNode(3);
		ListNode l1111 = new ListNode(4);
		ListNode l11111 = new ListNode(5);
		ListNode l111111 = new ListNode(6);
		l1.next = l11;
		l11.next = l111;
		l111.next = l1111;
		l1111.next = l11111;
		l11111.next = l111111;

		ListNode l2 = new ListNode(1);
		ListNode l22 = new ListNode(2);
		ListNode l222 = new ListNode(3);
		ListNode l2222 = new ListNode(4);
		ListNode l22222 = new ListNode(5);
		l2.next = l22;
		l22.next = l222;
		l222.next = l2222;
		l2222.next = l22222;

		AddTwoNumbersList twoNumbersList = new AddTwoNumbersList();
		ListNode sum = twoNumbersList.addTwoNumbers(l1, l2);
		while (sum != null) {
			System.out.println("Final d->" + sum.val);
			sum = sum.next;
		}
	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;

		if (l1 != null && l2 == null)
			return l1;

		if (l2 != null && l1 == null)
			return l2;

		ListNode root = new ListNode();
		addTwoNumbers(l1, l2, root, 0);
		return root.next;
	}

	public void addTwoNumbers(ListNode l1, ListNode l2, ListNode parent, int cf) {

		if (l1 == null && l2 == null) {
			if (cf == 1) {
				ListNode node = new ListNode();
				node.val = 1;
				parent.next = node;
			}
			return;
		}

		ListNode node = new ListNode();
		if (l1 != null && l2 != null) {
			node.val = cf + l1.val + l2.val;
			cf = node.val > 9 ? 1 : 0;
			node.val = node.val > 9 ? node.val % 10 : node.val;
			parent.next = node;
			addTwoNumbers(l1.next, l2.next, node, cf);
		} else if (l1 != null) {
			node.val = cf + l1.val;
			cf = node.val > 9 ? 1 : 0;
			node.val = node.val > 9 ? node.val % 10 : node.val;
			parent.next = node;
			addTwoNumbers(l1.next, null, node, cf);
		} else if (l2 != null) {
			node.val = cf + l2.val;
			cf = node.val > 9 ? 1 : 0;
			node.val = node.val > 9 ? node.val % 10 : node.val;
			parent.next = node;
			addTwoNumbers(null, l2.next, node, cf);
		}
	}
}
