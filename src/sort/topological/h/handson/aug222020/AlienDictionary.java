package sort.topological.h.handson.aug222020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AlienDictionary {

	public static void main(String[] args) {
		// String[] words = {"z", "x", "z"};
		// String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
		// String[] words = {"z", "z"};
		String[] words = {"ac", "ab", "b"};
		AlienDictionary dictionary = new AlienDictionary();

		String order = dictionary.alienOrder(words);
		System.out.println("order->" + order);
	}

	public String alienOrderBFS(String[] words) {

		// Step 0: Create data structures and find all unique letters.
		Map<Character, List<Character>> adjList = new HashMap<>();
		Map<Character, Integer> counts = new HashMap<>();
		for (String word : words) {
			for (char c : word.toCharArray()) {
				counts.put(c, 0);
				adjList.put(c, new ArrayList<>());
			}
		}

		// Step 1: Find all edges.
		for (int i = 0; i < words.length - 1; i++) {
			String word1 = words[i];
			String word2 = words[i + 1];
			// Check that word2 is not a prefix of word1.
			if (word1.length() > word2.length() && word1.startsWith(word2)) {
				return "";
			}
			// Find the first non match and insert the corresponding relation.
			for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
				if (word1.charAt(j) != word2.charAt(j)) {
					adjList.get(word1.charAt(j)).add(word2.charAt(j));
					counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
					break;
				}
			}
		}

		// Step 2: Breadth-first search.
		StringBuilder sb = new StringBuilder();
		Queue<Character> queue = new LinkedList<>();
		for (Character c : counts.keySet()) {
			if (counts.get(c).equals(0)) {
				queue.add(c);
			}
		}
		while (!queue.isEmpty()) {
			Character c = queue.remove();
			sb.append(c);
			for (Character next : adjList.get(c)) {
				counts.put(next, counts.get(next) - 1);
				if (counts.get(next).equals(0)) {
					queue.add(next);
				}
			}
		}

		if (sb.length() < counts.size()) {
			return "";
		}
		return sb.toString();
	}

	/*
	 * Sudo code
	 * 
	 * - Build Graph: Node is a char. To find the adjacent character, need to
	 * compare the two near one words.
	 * 
	 * - Source Node and its out-bound edges/adjacent list.
	 * 
	 * - Target Node and its in-bound edges count.
	 * 
	 * - Pick the Node which has zero in-bound edge which means this char has
	 * highest order priority.
	 * 
	 * - Build BFS: add all zero in-bound edges Node in Queue.
	 * 
	 * - Now that Node is part of order list, so need to remove this Node from
	 * all in-bound edge list. Which means traverse all adjacent Node and reduce
	 * the count by one. If Adjacent Node's in-bound count reach to zero, then
	 * it means this will be part of "order", so add into Queue.
	 * 
	 * - Need to check cycle dependency. if any of Node has in-bound edges, then
	 * there is cycle dependency.
	 * 
	 * - Now return the order.
	 * 
	 */
	public String alienOrder(String[] words) {

		HashMap<Character, List<Character>> graph = new HashMap<Character, List<Character>>();
		HashMap<Character, Integer> nodeInBoundCount = new HashMap<Character, Integer>();

		// Fill empty Graph with source char
		for (String w : words) {
			for (char c : w.toCharArray()) {
				graph.put(c, new ArrayList<Character>());
				nodeInBoundCount.put(c, 0);
			}
		}
		// Build Graph via comparing two adjacent words
		for (int i = 0; i < words.length - 1; i++) {
			char[] w1 = words[i].toCharArray();
			char[] w2 = words[i + 1].toCharArray();
			// Substring test, prefix word should be before than other prefix
			// contained word.
			if (words[i].startsWith(words[i + 1]) && w1.length != w2.length) {
				return "";
			}
			for (int j = 0; j < Math.min(w1.length, w2.length); j++) {
				if (w1[j] != w2[j]) {
					// Order. Source -> <Targets>
					graph.get(w1[j]).add(w2[j]);
					// In-bound count for any character
					nodeInBoundCount.put(w2[j], nodeInBoundCount.get(w2[j]) + 1);
					break;
				}
			}
		}
		Queue<Character> bsf = new LinkedList<Character>();
		// Build BSF for generating order
		for (Character c : nodeInBoundCount.keySet()) {
			// None dependent char
			if (nodeInBoundCount.get(c) == 0) {
				bsf.add(c);
			}
		}
		StringBuilder order = new StringBuilder();
		while (!bsf.isEmpty()) {
			char c = bsf.poll();
			order.append(c);
			for (char adjChar : graph.get(c)) {
				nodeInBoundCount.put(adjChar, nodeInBoundCount.get(adjChar) - 1);
				if (nodeInBoundCount.get(adjChar) == 0) {
					bsf.add(adjChar);
				}
			}
		}
		for (char c : nodeInBoundCount.keySet()) {
			// Cycle dependency
			if (nodeInBoundCount.get(c) > 0)
				return "";
		}

		return order.toString();
	}

}
