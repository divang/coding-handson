package dp.m.handson.sep042020;

public class LongestPalindrome {

	public String longestPalindrome(String s) {

		int[][] palindromeArr = new int[s.length()][s.length()];

		for (int i = 0; i < s.length(); i++) {
			palindromeArr[i][i] = 1;
		}

		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				if (palindromeArr[i][j] == palindromeArr[i][0]) {
					palindromeArr[i][j] = 1;
				}
			}

		}
		int longestStartIndex = 0;
		int longestEndIndex = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				if (palindromeArr[i][j] == 1) {
					// startIndex = i;
					// endIndex = j;
					// TODO need to finish
				}
			}
		}

		return "";
	}
}
