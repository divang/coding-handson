package graph.m.handson.aug182020;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import handson.TreeNode;

public class BinaryTreeRightSideView {

	public static void main(String[] args) {
		BinaryTreeRightSideView binaryTreeRightSideView = new BinaryTreeRightSideView();
		// [1,2,3,null,5,null,4]
		TreeNode root = new TreeNode(1);
		TreeNode ll2 = new TreeNode(2);
		TreeNode rl2 = new TreeNode(3);

		binaryTreeRightSideView.rightSideView(null);
	}
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> rList = new ArrayList<Integer>();
		levelOrderTraversal(root, rList);
		return rList;
	}

	public void levelOrderTraversal(TreeNode root, List<Integer> rList) {
		if (root == null)
			return;

		Queue<TreeNode> levelQueue = new LinkedList<TreeNode>();
		levelQueue.add(null);
		levelQueue.add(root);

		while (!levelQueue.isEmpty()) {
			TreeNode curNode = levelQueue.poll();
			if (curNode == null) {
				if (levelQueue.isEmpty())
					return;
				curNode = levelQueue.poll();
				rList.add(curNode.val);
				levelQueue.add(null);
			}
			if (curNode.right != null)
				levelQueue.add(curNode.right);
			if (curNode.left != null)
				levelQueue.add(curNode.left);
		}
	}

}
