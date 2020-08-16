package handson.aug162020.e;

import java.util.ArrayList;
import java.util.List;

public class LevelOrderBinaryTreeTraversal {

	public static void main(String[] args) {

		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		n1.left = n2;
		n1.right = n3;
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n2.left = n4;
		n2.right = n5;
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		n3.left = n6;
		n3.right = n7;
		LevelOrderBinaryTreeTraversal binaryTreeTraversal = new LevelOrderBinaryTreeTraversal();
		List<List<Integer>> lo = binaryTreeTraversal.levelOrder(n1);
		System.out.println("level order->" + lo);

	}

	public static class TreeNode {
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

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> levelNodes = new ArrayList<List<Integer>>();
		levelOrder(root, 0, levelNodes);
		return levelNodes;
	}

	public void levelOrder(TreeNode root, int level,
			List<List<Integer>> levelNodes) {
		if (root == null)
			return;

		if (level == levelNodes.size()) {
			levelNodes.add(level, new ArrayList<Integer>());
		}
		levelNodes.get(level).add(root.val);
		levelOrder(root.left, level + 1, levelNodes);
		levelOrder(root.right, level + 1, levelNodes);
	}
}
