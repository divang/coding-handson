package handson.aug162020.e;

public class SymmetricTree {

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

	public boolean isSymmetric(TreeNode root) {
		return isMirror(root, root);
	}

	public boolean isMirror(TreeNode l, TreeNode r) {

		if (l == null && r == null)
			return true;
		if (l == null || r == null)
			return false;
		if (l.val != r.val)
			return false;

		return isMirror(l.right, r.left) && isMirror(l.left, r.right);
	}
}
