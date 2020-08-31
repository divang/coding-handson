package design.m.handson.aug312020;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		System.out.println("v->" + cache.get(2));
		cache.put(2, 6);
		System.out.println("v->" + cache.get(1));
		cache.put(1, 5);
		cache.put(1, 2);
		System.out.println("v->" + cache.get(1));
		System.out.println("v->" + cache.get(2));

		/*
		 * ["LRUCache","put","put","put","put","get","get"]
		 * 
		 * 
		 * [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
		 * 
		 * [null,-1,null,-1,null,null,2,6]
		 */
		cache.printLRUCache();
		System.out.println("v->" + cache.get(1));
		cache.printLRUCache();
		System.out.println("v->" + cache.get(2));
		cache.printLRUCache();
	}

	public void printLRUCache() {

		// DLLNode node = head;
		// System.out.println("");
		// while (node != null) {
		// System.out.print("(" + node.k + "," + node.v + ") ");
		// node = node.r;
		// }
		// System.out.println("");
	}

	class DLinkedNode {
		int key;
		int value;
		DLinkedNode prev;
		DLinkedNode next;
	}

	private void addNode(DLinkedNode node) {
		/**
		 * Always add the new node right after head.
		 */
		node.prev = head;
		node.next = head.next;

		head.next.prev = node;
		head.next = node;
	}

	private void removeNode(DLinkedNode node) {
		/**
		 * Remove an existing node from the linked list.
		 */
		DLinkedNode prev = node.prev;
		DLinkedNode next = node.next;

		prev.next = next;
		next.prev = prev;
	}

	private void moveToHead(DLinkedNode node) {
		/**
		 * Move certain node in between to the head.
		 */
		removeNode(node);
		addNode(node);
	}

	private DLinkedNode popTail() {
		/**
		 * Pop the current tail.
		 */
		DLinkedNode res = tail.prev;
		removeNode(res);
		return res;
	}

	private Map<Integer, DLinkedNode> cache = new HashMap<>();
	private int size;
	private int capacity;
	private DLinkedNode head, tail;

	public LRUCache(int capacity) {
		this.size = 0;
		this.capacity = capacity;

		head = new DLinkedNode();
		// head.prev = null;

		tail = new DLinkedNode();
		// tail.next = null;

		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		DLinkedNode node = cache.get(key);
		if (node == null)
			return -1;

		// move the accessed node to the head;
		moveToHead(node);

		return node.value;
	}

	public void put(int key, int value) {
		DLinkedNode node = cache.get(key);

		if (node == null) {
			DLinkedNode newNode = new DLinkedNode();
			newNode.key = key;
			newNode.value = value;

			cache.put(key, newNode);
			addNode(newNode);

			++size;

			if (size > capacity) {
				// pop the tail
				DLinkedNode tail = popTail();
				cache.remove(tail.key);
				--size;
			}
		} else {
			// update the value.
			node.value = value;
			moveToHead(node);
		}
	}
}
