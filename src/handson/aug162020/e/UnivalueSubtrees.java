package handson.aug162020.e;

public class UnivalueSubtrees {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {
		}
		TreeNode(int val) {
			this.val = val;
		}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public int countUnivalSubtrees(TreeNode root) {
		if (root == null)
			return 0;

		if (root.left == null && root.right == null)
			return 1;

		int count = 0;

		if (root.left != null && root.left.val == root.val)
			count += 1;

		if (root.right != null && root.right.val == root.val)
			count += 1;

		return count + countUnivalSubtrees(root.left)
				+ countUnivalSubtrees(root.right);
	}

}
