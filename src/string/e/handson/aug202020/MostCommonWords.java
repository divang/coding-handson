package string.e.handson.aug202020;

import java.util.HashMap;

public class MostCommonWords {

	public static void main(String[] args) {
		MostCommonWords mostCommonWords = new MostCommonWords();
		String paragraph = "a, a, a, a, b,b,b,c, c";
		String[] banned = new String[1];
		banned[0] = "a";
		String mcw = mostCommonWords.mostCommonWord(paragraph, banned);
		System.out.println("MostCommonWords: " + mcw);
	}

	public String mostCommonWord(String paragraph, String[] banned) {

		paragraph = paragraph.replaceAll("\\p{Punct}", " ");
		paragraph = paragraph.replaceAll("( +)", " ");
		paragraph = paragraph.toLowerCase();
		HashMap<String, Integer> wF = new HashMap<String, Integer>();
		for (String w : paragraph.split(" ")) {
			wF.put(w, wF.getOrDefault(w, 0) + 1);
		}
		for (String bW : banned) {
			wF.remove(bW);
		}
		String mstW = "";
		int mstF = 0;
		for (String w : wF.keySet()) {
			int curF = wF.get(w);
			mstF = Math.max(mstF, curF);
			if (mstF == curF)
				mstW = w;
		}

		return mstW;
	}
}
