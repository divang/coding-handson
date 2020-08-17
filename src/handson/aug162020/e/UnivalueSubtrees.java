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

	int count = 0;
	boolean is_valid_part(TreeNode node, int val) {

		if (node == null)
			return true;
		if (!is_valid_part(node.left, node.val)
				| !is_valid_part(node.right, node.val))
			return false;
		count++;
		return node.val == val;
	}
	public int countUnivalSubtrees(TreeNode root) {
		is_valid_part(root, 0);
		return count;
	}

}
