package graph.h.handson.aug192020;

import handson.TreeNode;

public class BinaryTreeMaxSumPath {

	int globalMaxSum = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		maxPathSumSubTree(root);
		return globalMaxSum;
	}

	public int maxPathSumSubTree(TreeNode root) {
		if (root == null)
			return 0;
		int leftSum = Math.max(maxPathSumSubTree(root.left), 0);
		int rightSum = Math.max(maxPathSumSubTree(root.right), 0);
		int localMax = root.val + leftSum + rightSum;
		globalMaxSum = Math.max(localMax, globalMaxSum);

		return root.val + Math.max(leftSum, rightSum);

	}

}
