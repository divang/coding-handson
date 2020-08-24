package backtracking.h.handson.aug232020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PuzzleSendMoreMoney {

	public static void main(String[] args) {
		PuzzleSendMoreMoney money = new PuzzleSendMoreMoney();
		// String[] words = {"SEND", "MORE"};

		/*
		 * ["LEET","CODE"] "POINT"
		 */

		String[] words = {"LEET", "IS", "TOO"};
		String result = "FUNNY";
		// List<HashMap<Character, Integer>> solutions = money.decrypt(words,
		// "MONEY");

		long sT = System.currentTimeMillis();
		List<HashMap<Character, Integer>> solutions = money.decrypt(words, result);
		// List<HashMap<Character, Integer>> solutions = money.decrypt("PM",
		// "TP", "YT");
		// List<HashMap<Character, Integer>> solutions = money.decrypt("M", "N",
		// "O");

		System.out.println("Execution time:" + (System.currentTimeMillis() - sT));

		System.out.println(solutions);

	}
	/*
	 * Solution Algorithm: Backtracking is an algorithmic-technique for solving
	 * problems recursively by trying to build a solution incrementally, one
	 * piece at a time, removing those solutions that fail to satisfy the
	 * constraints of the problem at any point of time.
	 * 
	 * So try to apply all 0-9 digit to each character one by one.
	 */

	public boolean isSolvable(String[] words, String result) {
		List<HashMap<Character, Integer>> solutions = decrypt(words, result);
		return solutions.size() > 0 ? true : false;
	}

	public List<HashMap<Character, Integer>> decrypt(String[] words, String result) {

		Set<Character> chars = new HashSet<Character>();
		HashMap<Character, Integer> charDigitMap = new HashMap<Character, Integer>();

		for (String word : words) {
			fillChar(word, chars);
		}
		fillChar(result, chars);
		System.out.println("Characters->" + chars);
		List<HashMap<Character, Integer>> solutions = new ArrayList<HashMap<Character, Integer>>();
		backTrack(0, new ArrayList<Character>(chars), charDigitMap, words, result, solutions);
		return solutions;
	}

	private void backTrack(int index, List<Character> chars, HashMap<Character, Integer> charDigitMap, String[] words, String result,
			List<HashMap<Character, Integer>> solutions) {
		if (charDigitMap.size() == chars.size()) {
			validate(words, result, charDigitMap, solutions);
			return;
		}
		if (index >= chars.size())
			return;
		char ch = chars.get(index);
		for (int digit = 0; digit < 10; digit++) {
			if (!charDigitMap.values().contains(digit)) {
				charDigitMap.put(ch, digit);
				backTrack(index + 1, chars, charDigitMap, words, result, solutions);
				if (solutions.size() > 0)
					return;
				charDigitMap.remove(ch);
			}
		}
	}

	private void fillChar(String word, Set<Character> chars) {
		for (char c : word.toCharArray()) {
			chars.add(c);
		}
	}

	private void validate(String[] words, String result, HashMap<Character, Integer> charDigitMap, List<HashMap<Character, Integer>> solutions) {
		for (String word : words) {
			if (leadingZeros(word, charDigitMap))
				return;
		}
		int sum = 0;
		for (String word : words) {
			sum += wordToNumber(word, charDigitMap);
		}
		int validateResult = wordToNumber(result, charDigitMap);
		if (sum == validateResult) {
			solutions.add(deepCopy(charDigitMap));
		}
	}

	private boolean leadingZeros(String word, HashMap<Character, Integer> charDigitMap) {
		if (charDigitMap.get(word.charAt(0)) == 0)
			return true;
		return false;
	}
	private HashMap<Character, Integer> deepCopy(HashMap<Character, Integer> charDigitMap) {
		HashMap<Character, Integer> copy = new HashMap<Character, Integer>();
		for (Character c : charDigitMap.keySet()) {
			copy.put(c, charDigitMap.get(c));
		}
		return copy;
	}

	private int wordToNumber(String word, HashMap<Character, Integer> charDigitMap) {

		int placeValue = 0;
		for (char c : word.toCharArray()) {
			int faceValue = charDigitMap.get(c);
			placeValue = placeValue * 10 + faceValue;
		}
		return placeValue;
	}
}
