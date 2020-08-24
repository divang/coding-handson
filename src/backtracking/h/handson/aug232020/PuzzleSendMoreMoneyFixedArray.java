package backtracking.h.handson.aug232020;

import java.io.IOException;
import java.util.Arrays;

public class PuzzleSendMoreMoneyFixedArray {

	public static void main(String[] args) throws IOException {

		int[] arr = {1, 2, 3};
		Arrays.asList(arr);

		/*
		 * ["LEET","CODE"] "POINT"
		 * 
		 * ["AB","CD","EF"] "GHIJ"
		 * 
		 * [A = 2][B = 6][C = 4][D = 8][E = 7][F = 9][G = 0][H = 1][I = 5][J =
		 * 3]
		 * 
		 * 26
		 * 
		 * 48
		 * 
		 * 79
		 * 
		 * 0153
		 * 
		 * 
		 * ["SEIS","CATORCE","SETENTA"]
		 * 
		 * "NOVENTA"
		 * 
		 */

		long st = System.currentTimeMillis();
		// String[] words = {"AB", "CD", "EF"};
		// String result = "GHIJ";

		String[] words = {"SEIS", "CATORCE", "SETENTA"};
		String result = "NOVENTA";

		boolean found = solve(words, result);

		System.out.println("found->" + found + " E time:" + (System.currentTimeMillis() - st));
	}

	static Boolean solve(String[] words, String result) // w1 + w2 = w3
	{
		usedLetter = new boolean[26]; // usedLetter[i] = true iff letter i
										// appears in w1, w2 or w3
		usedDigit = new boolean[26]; // usedDigit[i] = true iff digit i is used
										// by a letter (used in backtracking)
		assignedDigit = new int[26]; // assignedDigiti[i] = digit assigned to

		for (String w : words) {
			markLetters(w);
		}
		markLetters(result);
		return backtrack(0, words, result);
	}

	static boolean[] usedLetter;
	static boolean[] usedDigit;
	static int[] assignedDigit;

	// mark the letters appeared in w1, w2 and w3 to use them in the search.
	static void markLetters(String w) {
		for (int i = 0; i < w.length(); ++i)
			usedLetter[w.charAt(i) - 'A'] = true;
	}

	static boolean check(String[] words, String result) {
		for (String word : words) {
			if (leadingZero(word))
				return false;
		}
		if (leadingZero(result))
			return false;
		int sum = 0;
		for (String word : words) {
			sum += value(word);
		}
		int validateResult = value(result);
		// boolean r = sum == validateResult;
		// if (r) {
		// sum = 0;
		// for (String word : words) {
		// int val = value(word);
		// sum += val;
		// System.out.println("word-" + word + " val-" + val + " sum=" + sum + "
		// result-" + result + " rv-" + validateResult);
		// }
		// }

		return sum == validateResult;
	}

	static boolean leadingZero(String w) {
		return assignedDigit[w.charAt(0) - 'A'] == 0;
	}

	// if w = ABCD, then the function returns A * 1000 + B * 100 + C * 10 + D.
	static int value(String w) {

		int val = 0;
		for (int i = 0; i < w.length(); ++i)
			val = val * 10 + assignedDigit[w.charAt(i) - 'A'];

		return val;
	}

	// do the backtracking (brute force)
	static boolean backtrack(int char_idx, String[] words, String result) {
		if (char_idx == 26) {
			// finished assigning values for the 26 letters
			if (check(words, result)) {
				// System.out.println("Found");
				// for (int i = 0; i < 26; ++i)
				// if (usedLetter[i])
				// System.out.printf("[%c = %d]", (char) (i + 'A'),
				// assignedDigit[i]);
				// System.out.println("\n------");
				return true;
			}
			return false;
		}

		if (!usedLetter[char_idx]) {
			// skip this letter, it was not used in the input.
			return backtrack(char_idx + 1, words, result);
		}
		// try assigning different digits for this letter
		boolean solutionFound = false;
		for (int digit = 0; digit < 10; ++digit)
			if (!usedDigit[digit]) // this condition guarantees that no digit is
									// used for more than one letter
			{
				usedDigit[digit] = true;
				assignedDigit[char_idx] = digit;
				solutionFound = backtrack(char_idx + 1, words, result) || solutionFound;
				if (solutionFound)
					break;
				usedDigit[digit] = false;
			}
		return solutionFound;
	}
}