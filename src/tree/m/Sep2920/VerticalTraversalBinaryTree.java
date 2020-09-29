package tree.m.Sep2920;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import handson.TreeNode;

public class VerticalTraversalBinaryTree {

	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode() {}
	 *     TreeNode(int val) { this.val = val; }
	 *     TreeNode(int val, TreeNode left, TreeNode right) {
	 *         this.val = val;
	 *         this.left = left;
	 *         this.right = right;
	 *     }
	 * }
	 */
	class Solution {
	       class NodePosition {
	      int v;
	      int c;
	      int r;
	   }

	   public List<List<Integer>> verticalTraversal(TreeNode root) {

	      DFS(root, 0,0);
	      Collections.sort(nodesWithPos, new Comparator<NodePosition>() {
	         @Override public int compare(NodePosition o1, NodePosition o2) {
	            if (o1.c != o2.c) return o1.c - o2.c;
	            else if (o1.r != o2.r)  return o1.r - o2.r;
	            else return o1.v - o2.v;
	         }
	      });

	      TreeMap<Integer, List<Integer>> verticalTraverse = new TreeMap<>();

	      for(NodePosition n:nodesWithPos) {
	         if(!verticalTraverse.containsKey(n.c)) {
	            verticalTraverse.put(n.c, new LinkedList<>());
	         }
	         List<Integer> values = verticalTraverse.get(n.c);
	         values.add(n.v);
	      }

	      List<List<Integer>> traverseList = new LinkedList<>();

	      for (int k: verticalTraverse.keySet())  {
	         traverseList.add(verticalTraverse.get(k));
	      }
	      return traverseList;
	   }

	   List<NodePosition> nodesWithPos = new LinkedList<>();
	   public void DFS(TreeNode node, int c, int r) {

	      if(node == null) return;

	      NodePosition nodePos = new NodePosition();
	      nodePos.v = node.val;
	      nodePos.c = c;
	      nodePos.r = r;
	      nodesWithPos.add(nodePos);

	      DFS(node.left, c -1, r+1);
	      DFS(node.right, c +1, r+1);
	   }
	}
}
