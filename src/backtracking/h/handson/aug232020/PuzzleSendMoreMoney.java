package backtracking.h.handson.aug232020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PuzzleSendMoreMoney {

	public static void main(String[] args) {
		PuzzleSendMoreMoney money = new PuzzleSendMoreMoney();
		List<HashMap<Character, Integer>> solutions = money.decrypt("SEND", "MORE", "MONEY");
		// List<HashMap<Character, Integer>> solutions = money.decrypt("PM",
		// "TP", "YT");
		// List<HashMap<Character, Integer>> solutions = money.decrypt("M", "N",
		// "O");
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
	public List<HashMap<Character, Integer>> decrypt(String word1, String word2, String word3) {

		Set<Character> chars = new HashSet<Character>();
		HashMap<Character, Integer> charDigitMap = new HashMap<Character, Integer>();

		fillChar(word1, chars);
		fillChar(word2, chars);
		fillChar(word3, chars);
		System.out.println("Characters->" + chars);
		List<HashMap<Character, Integer>> solutions = new ArrayList<HashMap<Character, Integer>>();
		backTrack(0, new ArrayList<Character>(chars), charDigitMap, word1, word2, word3, solutions);
		return solutions;
	}

	private void backTrack(int index, List<Character> chars, HashMap<Character, Integer> charDigitMap, String word1, String word2, String word3,
			List<HashMap<Character, Integer>> solutions) {
		if (charDigitMap.size() == chars.size()) {
			validate(word1, word2, word3, charDigitMap, solutions);
			return;
		}
		if (index >= chars.size())
			return;
		char ch = chars.get(index);
		for (int digit = 0; digit < 10; digit++) {
			if (!charDigitMap.values().contains(digit)) {
				charDigitMap.put(ch, digit);
				backTrack(index + 1, chars, charDigitMap, word1, word2, word3, solutions);
				charDigitMap.remove(ch);
			}
		}
	}

	private void fillChar(String word, Set<Character> chars) {
		for (char c : word.toCharArray()) {
			chars.add(c);
		}
	}

	private void validate(String word1, String word2, String word3, HashMap<Character, Integer> charDigitMap,
			List<HashMap<Character, Integer>> solutions) {
		if (leadingZeros(word1, charDigitMap) || leadingZeros(word2, charDigitMap) || leadingZeros(word3, charDigitMap))
			return;
		int n1 = wordToNumber(word1, charDigitMap);
		int n2 = wordToNumber(word2, charDigitMap);
		int n3 = wordToNumber(word3, charDigitMap);
		if (n1 + n2 == n3) {
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
