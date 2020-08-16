package handson.aug162020.h;

import java.util.Arrays;
import java.util.TreeSet;

public class FirstMissingPositive {

	public static void main(String[] args) {
		FirstMissingPositive cl = new FirstMissingPositive();
		int missInt = cl.firstMissingPositive(new int[]{3, 4, -1, 1});
		System.out.println("->" + missInt);
	}

	// Steps
	// - Check for 1 is present.
	// - If not then return 1
	// - replace all negative with 1
	// - replace numbers which greater then the size of array
	// -- why? suppose all numbers are in sequence from 1 to n, then
	// smallest positive
	// number will be n+1
	// -- so, it means missing number should be within 1 to n.
	// - Now iterate over array and pick the num and set minus flag [use
	// absolute value to handle duplicate value] in that value's index in
	// array.
	// -- which indicates that number is present in array.
	// -- return the index +1 which does not minus flag. That is the missing
	// one.
	public int firstMissingPositive(int[] nums) {

		if (nums == null)
			return 1;
		boolean hasOne = false;
		for (int n : nums) {
			if (n == 1) {
				hasOne = true;
				break;
			}
		}
		if (!hasOne)
			return 1;

		// Reset values to 1 for handling index out of bound error. Only n
		// elements are sufficient to calculate the smallest positive num.
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 1 || nums[i] > nums.length)
				nums[i] = 1;
		}

		// Now use supplied array for first n number existence check
		// Set the minus flag to that values' index in array.
		for (int i = 0; i < nums.length; i++) {
			// -1, array index start with index 0 and we need to find the first
			// positive number.
			int indexToFlag = Math.abs(nums[i]) - 1;
			nums[indexToFlag] = -Math.abs(nums[indexToFlag]);
		}
		// Now iterate over array and pick the index which is not in array.
		int i = 0;
		for (; i < nums.length; i++) {
			if (nums[i] > 0)
				break;
		}
		return i + 1;
	}

	public int firstMissingPositiveTreeSet(int[] nums) {

		TreeSet<Integer> visited = new TreeSet<Integer>();
		for (int num : nums) {
			if (num < 1)
				continue;
			visited.add(num);
		}

		int current;
		int i = 1;
		while (!visited.isEmpty()) {
			current = visited.pollFirst();

			if (i == current) {
				i++;
			} else {
				break;
			}
		}

		return i;
	}

	public int firstMissingPositiveAfterSort(int[] nums) {
		Arrays.sort(nums);
		int i = 1;
		int preNum = 0;
		for (int num : nums) {
			if (num < 1)
				continue;
			if (preNum == num)
				continue;

			if (i == num) {
				i++;
				preNum = num;
				continue;
			}
			return i;
		}
		return i;
	}
}
