package google.mock.aug232020;

import java.util.HashMap;

public class ExpressiveWords {
	public int expressiveWords(String S, String[] words) {

		int wCount = 0;
		HashMap<Character, Integer> sMap = getCharCount(S);

		for (String t : words) {
			HashMap<Character, Integer> tMap = getCharCount(t);
			if (tMap.size() == sMap.size()) {
				boolean found = false;
				for (Character ch : tMap.keySet()) {
					if (!sMap.containsKey(ch))
						break;
					found = true;
					int tCount = tMap.get(ch);
					int sCount = sMap.get(ch);
					if (tCount != sCount) {
						// System.out.println("t-"+t+" ch-"+ch +" tCount-" +
						// tCount + " sCount-"+sCount);
						// int tRuleCount = tCount ==1 ? tCount + 2: tCount + 3;
						int tRuleCount = tCount + 2;

						// System.out.println("t-"+t+" ch-"+ch +" tCount-" +
						// tCount + " sCount-"+sCount +" tRuleCount-" +
						// tRuleCount);
						if (!(tRuleCount <= sCount)) {
							// System.out.println("-->t-"+t+" ch-"+ch +"
							// tCount-" + tCount + " sCount-"+sCount +"
							// tRuleCount-" + tRuleCount);
							found = false;
							break;
						}
					}
				}
				if (found) {
					wCount++;
					// System.out.println("Matched. t->" +t);
				}
			}
		}

		return wCount;
	}

	public HashMap<Character, Integer> getCharCount(String S) {
		char[] seq = S.toCharArray();
		HashMap<Character, Integer> chCount = new HashMap<Character, Integer>();
		for (char c : seq) {
			chCount.put(c, chCount.getOrDefault(c, 0) + 1);
		}
		return chCount;
	}
}
