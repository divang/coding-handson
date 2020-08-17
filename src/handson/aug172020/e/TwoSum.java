package handson.aug172020.e;

import java.util.HashMap;

public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> counterNum = new HashMap<Integer, Integer>();
		int[] pair = new int[2];
		for (int i = 0; i < nums.length; i++) {
			int rem = target - nums[i];

			if (counterNum.containsKey(rem)) {
				pair[0] = counterNum.get(rem);
				pair[1] = i;
				return pair;
			} else {
				counterNum.put(nums[i], i);
			}
		}
		return pair;
	}
}
