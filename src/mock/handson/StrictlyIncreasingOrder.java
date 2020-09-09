package mock.handson;

public class StrictlyIncreasingOrder {

	/*
	 * Given an array arr of positive integers sorted in a strictly increasing
	 * order, and an integer k.
	 * 
	 * Find the kth positive integer that is missing from this array.
	 * 
	 * Ex:
	 * 
	 * Input: arr = [2,3,4,7,11], k = 5 Output: 9 Explanation: The missing
	 * positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive
	 * integer is 9.
	 * 
	 * Input: arr = [1,2,3,4], k = 2 Output: 6 Explanation: The missing positive
	 * integers are [5,6,7,...]. The 2nd missing positive integer is 6.
	 * 
	 */
	public static void main(String[] args) {
		/*
		 * [2,3,4,7,11] 5
		 */
		StrictlyIncreasingOrder order = new StrictlyIncreasingOrder();
		int[] arr = {1, 2, 3, 4};
		int k = order.findKthPositive(arr, 2);
		System.out.println("k-" + k);
	}
	public int findKthPositive(int[] arr, int k) {

		int lastNum = 0;
		for (int curNum : arr) {
			if (lastNum + 1 != curNum) {
				for (int j = lastNum + 1; j < curNum; j++) {
					if (--k == 0) {
						return j;
					}
				}
			}
			lastNum = curNum;
		}
		for (; k > 0; lastNum++, k--);
		return lastNum;
	}
}
