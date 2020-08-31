package trie.h.handson.aug312020;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MyAutocompleteSystem {

	public static void main(String[] args) {
		/*
		 * ["AutocompleteSystem","input","input","input","input"]
		 * [[["i love you","island","iroman","i love leetcode"],[5,3,2,2]],["i"]
		 * ,[" "],["a"],["#"]]
		 * 
		 * ["AutocompleteSystem","input","input","input","input","input","input"
		 * ,"input","input","input","input","input","input"]
		 * 
		 * [[["i love you","island","iroman","i love leetcode"],[5,3,2,2]],
		 * 
		 * ["i"] ,[" "],["a"],["#"],
		 * 
		 * ["i"],[" "],["a"],["#"],
		 * 
		 * ["i"],[" "],["a"],["#"]]
		 * 
		 */
		String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
		int[] times = {5, 3, 2, 2};
		AutocompleteSystem autocompleteSystem = new AutocompleteSystem(sentences, times);
		List<String> hotlist = autocompleteSystem.input('i');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input(' ');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('a');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('#');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('i');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input(' ');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('a');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('#');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('i');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input(' ');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('a');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('#');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('i');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input(' ');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('a');
		System.out.println("lotlist->" + hotlist);
		hotlist = autocompleteSystem.input('#');
		System.out.println("lotlist->" + hotlist);

	}
	static class AutocompleteSystem {

		class TrieNode implements Comparable<TrieNode> {
			String sentance;
			int times;
			TrieNode[] links = new TrieNode[128]; // One more for space
			TreeSet<TrieNode> hotSearchList = new TreeSet<TrieNode>();
			@Override
			public int compareTo(TrieNode o) {
				if (o.times == times) {
					return sentance.compareTo(o.sentance);
				}
				return o.times - times;
			}

			public TrieNode getLink(char c) {
				if (links[c] == null) {
					links[c] = new TrieNode();
				}
				return links[c];
			}
			public void addHotSearch(TrieNode hotSearch) {
				hotSearchList.add(hotSearch);
				if (hotSearchList.size() > 3) {
					hotSearchList.pollLast();
				}
			}

			public List<String> getHotSearch() {
				List<String> hotSearch = new ArrayList<String>();

				for (TrieNode n : hotSearchList) {
					hotSearch.add(n.sentance);
				}
				return hotSearch;
			}
		}

		TrieNode root = new TrieNode();

		public void addSentence(String sentence, int time) {
			TrieNode newSearchNode = root;
			List<TrieNode> parentNodes = new ArrayList<TrieNode>();
			for (char ch : sentence.toCharArray()) {
				parentNodes.add(newSearchNode);
				newSearchNode = newSearchNode.getLink(ch);
			}
			newSearchNode.sentance = sentence;
			newSearchNode.times += time;
			for (TrieNode node : parentNodes) {
				node.addHotSearch(newSearchNode);
			}
		}

		public AutocompleteSystem(String[] sentences, int[] times) {
			for (int i = 0; i < sentences.length; i++) {
				addSentence(sentences[i], times[i]);
			}
		}

		StringBuilder currentSearchStr = new StringBuilder();
		TrieNode currentSearchNode = root;
		public List<String> input(char c) {
			switch (c) {
				case ' ' :
					currentSearchStr.append(c);
					currentSearchNode = currentSearchNode.getLink(c);
					return currentSearchNode.getHotSearch();
				case '#' :
					addSentence(currentSearchStr.toString(), currentSearchNode.times + 1);
					currentSearchStr = new StringBuilder();
					currentSearchNode = root;
					return new ArrayList<String>();
				default :
					currentSearchStr.append(c);
					currentSearchNode = currentSearchNode.getLink(c);
					return currentSearchNode.getHotSearch();
			}
		}
	}
}
