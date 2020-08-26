package google.mock.aug232020;

public class LongestIncreasingPathDFS {

	public static void main(String[] args) {
		LongestIncreasingPathDFS increasingPath = new LongestIncreasingPathDFS();
		int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1},};
		int l = increasingPath.longestIncreasingPath(matrix);
		System.out.println("Longest Increasing Path->" + l);
	}

	int[][] rowColDirections = {{0, 1}, {1, 0}, {-1, 0}, {0, -1},};
	int[][] matrix;
	int n, m;

	/*
	 * Complexity Analysis
	 * 
	 * Time complexity : O(2^(m+n)) The search is repeated for each valid
	 * increasing path. In the worst case we can have O(2^{m+n})O(2 m+n ) calls.
	 * 
	 * Space complexity : O(mn). For each DFS we need O(h)O(h) space used by the
	 * system stack, where hh is the maximum depth of the recursion. In the
	 * worst case, O(h) = O(mn)
	 */
	public int longestIncreasingPath(int[][] matrix) {
		this.matrix = matrix;
		this.n = matrix.length;
		this.m = matrix[0].length;

		int moves = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				moves = Math.max(moves, dfs(i, j));
			}
		}
		return moves;
	}

	public int dfs(int row, int col) {

		int moves = 0;
		for (int[] rowCol : rowColDirections) {
			int nextRow = row + rowCol[0];
			int nextCol = col + rowCol[1];
			if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m && matrix[nextRow][nextCol] > matrix[row][col]) {
				moves = Math.max(moves, dfs(nextRow, nextCol));
			}
		}
		return ++moves;
	}

}
