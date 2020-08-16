package handson.aug132020;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SamllestSubStringWindow {

	public static void main(String[] args) {

		String s = "abefabgcxyzabc";
		String t = "abc";
		System.out.println("->" + getSubString(s, t));
	}

	static class Pair {
		char key;
		int index;

		public Pair(char key, int index) {
			this.index = index;
			this.key = key;
		}
		public char getKey() {
			return key;
		}
		public int getIndex() {
			return index;
		}

	}

	static HashMap<Character, Integer> getDesiredWindow(String t) {
		char[] arrCharT = t.toCharArray();
		HashMap<Character, Integer> desiredWindow = new HashMap<Character, Integer>();
		for (char charT : arrCharT) {
			int count = desiredWindow.getOrDefault(charT, 0);
			desiredWindow.put(charT, count + 1);
		}
		return desiredWindow;
	}

	static List<Pair> getFilteredSource(String s, String t,
			HashMap<Character, Integer> desiredWindow) {
		List<Pair> filtered = new ArrayList<SamllestSubStringWindow.Pair>();
		char[] arrCharT = t.toCharArray();
		for (int i = 0; i < arrCharT.length; i++) {
			if (desiredWindow.containsKey(arrCharT[i])) {
				filtered.add(new Pair(arrCharT[i], i));
			}
		}
		return filtered;
	}

	static String getSubString(String s, String t) {

		HashMap<Character, Integer> desiredWindow = getDesiredWindow(t);
		HashMap<Character, Integer> currentWindow = getDesiredWindow(t);
		List<Pair> filteredSource = getFilteredSource(s, t, desiredWindow);

		int desiredCharsCount = desiredWindow.size();
		int currentCharsCount = 0;
		int minWinLeftIndex = -1;
		int minWinRightIndex = -1;
		int curLeft = 0;
		int curRight = 0;

		while (curRight < filteredSource.size()) {
			char curChar = filteredSource.get(curRight).getKey();
			int count = currentWindow.getOrDefault(curChar, 0);
			currentWindow.put(curChar, count + 1);

			if (desiredWindow.containsKey(curChar) && desiredWindow
					.get(curChar) == currentWindow.get(curChar)) {
				currentCharsCount++;
			}

			while (curLeft < curRight
					&& currentCharsCount == desiredCharsCount) {

				int curWinLeftIndex = filteredSource.get(curLeft).getIndex();
				int curWinRightIndex = filteredSource.get(curRight).getIndex();

				if (minWinLeftIndex == -1
						|| minWinRightIndex - minWinLeftIndex > curWinRightIndex
								- curWinLeftIndex) {
					minWinLeftIndex = curWinLeftIndex;
					minWinRightIndex = curWinRightIndex;
				}

				curChar = filteredSource.get(curLeft).getKey();
				if (currentWindow.containsKey(curChar)) {
					currentWindow.put(curChar, currentWindow.get(curChar) - 1);
				}

				if (desiredWindow.containsKey(curChar) && desiredWindow
						.get(curChar) > currentWindow.get(curChar)) {
					currentCharsCount--;
				}
				curLeft++;
			}
			curRight++;
		}

		return minWinLeftIndex == -1
				? ""
				: s.substring(minWinLeftIndex, minWinRightIndex + 1);
	}

}
