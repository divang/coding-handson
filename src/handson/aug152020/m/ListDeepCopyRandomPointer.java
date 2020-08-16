package handson.aug152020.m;

import java.util.HashMap;

public class ListDeepCopyRandomPointer {

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;

		n1.random = n3;
		n2.random = n4;
		n3.random = n1;
		n4.random = n2;

		ListDeepCopyRandomPointer copyRandomPointer = new ListDeepCopyRandomPointer();
		Node newListHead = copyRandomPointer.copyRandomList(n1);
		System.out.println("->" + newListHead);
		while (newListHead != null) {
			System.out.println("v->" + newListHead.val);
			newListHead = newListHead.next;
		}
	}

	static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	HashMap<Node, Node> oldNewNodeMap = new HashMap<Node, Node>();

	public Node copyRandomList(Node head) {
		if (head == null)
			return null;
		if (oldNewNodeMap.containsKey(head))
			return oldNewNodeMap.get(head);

		Node newNode = new Node(head.val);
		oldNewNodeMap.put(head, newNode);
		newNode.next = copyRandomList(head.next);
		newNode.random = copyRandomList(head.random);

		return newNode;
	}
}
