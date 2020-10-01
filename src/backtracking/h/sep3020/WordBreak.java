package backtracking.h.sep3020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 
 */
public class WordBreak {

	// Memoization
	Map<String, List<String>> memo = new HashMap<>();

	public List<String> wordBreak(String s, List<String> wordDict) {
		if (s.length() == 0) {
			return Arrays.asList(new String[]{""});
		}

		if (memo.containsKey(s))
			return memo.get(s);

		List<String> result = new ArrayList<>();
		for (String word : wordDict) {
			if (s.startsWith(word)) {
				String postfix = s.substring(word.length());
				for (String postfixs : wordBreak(postfix, wordDict)) {
					if (postfixs.length() == 0)
						result.add(word);
					else
						result.add(word + " " + postfixs);
				}
			}
		}

		memo.put(s, result);
		return memo.get(s);
	}
	public static void main(String[] args) {
		System.out.println("Test");
		WordBreak wbreak = new WordBreak();
		List<String> wordDict = new ArrayList<>();
		wordDict.add("and");
		wordDict.add("sand");
		wordDict.add("cat");
		wordDict.add("cats");
		wordDict.add("dog");
		List<String> bw = wbreak.wordBreak("catsanddog", wordDict);
		bw.forEach(x -> System.out.println(x));
	}

}
