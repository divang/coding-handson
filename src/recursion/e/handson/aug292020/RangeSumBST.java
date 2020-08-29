package recursion.e.handson.aug292020;

import handson.TreeNode;

public class RangeSumBST {

	public int rangeSumBST(TreeNode root, int L, int R) {

		if (root == null)
			return 0;

		int sum = 0;
		if (root.val >= L && root.val <= R) {
			sum += root.val;
		}
		sum += rangeSumBST(root.left, L, R);
		sum += rangeSumBST(root.right, L, R);
		return sum;
	}

}
