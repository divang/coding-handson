package handson.aug152020.h;

import java.util.TreeSet;

public class LongestConsecutive {

	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		TreeSet<Integer> sortedNum = new TreeSet<Integer>();

		for (int i : nums) {
			sortedNum.add(i);
		}

		int maxNoOfConscutive = 1;
		int curNOfConscutive = 1;
		int previousElement = sortedNum.pollFirst();

		while (!sortedNum.isEmpty()) {
			Integer i = sortedNum.pollFirst();

			if (previousElement + 1 == i) {
				curNOfConscutive += 1;
				if (curNOfConscutive > maxNoOfConscutive) {
					maxNoOfConscutive = curNOfConscutive;
				}
			} else {
				curNOfConscutive = 1;
			}
			previousElement = i;
		}
		return maxNoOfConscutive;
	}
}
