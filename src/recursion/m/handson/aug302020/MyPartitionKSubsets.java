package recursion.m.handson.aug302020;

import java.util.Arrays;

public class MyPartitionKSubsets {

	public static void main(String[] args) {
		// [2,9,2,2,4,1,12,1,1]
		// 2
		MyPartitionKSubsets kSubsets = new MyPartitionKSubsets();
		int[] a = {2, 9, 2, 2, 4, 1, 12, 1, 1};
		boolean r = kSubsets.canPartitionKSubsets(a, 2);
		System.out.println("r-" + r);
	}
	boolean[] visited;
	int target;
	int[] arr;
	public boolean canPartitionKSubsets(int[] a, int k) {
		if (a == null)
			return false;
		if (k == 0)
			return false;
		int sum = Arrays.stream(a).sum();
		if (sum % k != 0)
			return false;
		target = sum / k;
		this.arr = a;
		this.visited = new boolean[a.length];

		return canPartition(0, 0, k);
	}

	public boolean canPartition(int startIndex, int currentSum, int currentBucket) {

		// If only one bucket left to fill, it means rest of the bucket has
		// target sum, so consider it as a successful partition.
		if (currentBucket == 1) {
			return true;
		}

		if (currentSum > target)
			return false;

		if (currentSum == target) {
			return canPartition(0, 0, currentBucket - 1);
		} else {
			for (int index = startIndex; index < arr.length; index++) {
				if (!visited[index]) {
					visited[index] = true;
					if (canPartition(index + 1, currentSum + arr[index], currentBucket)) {
						return true;
					}
					visited[index] = false;
				}
			}
		}
		return false;
	}
}
