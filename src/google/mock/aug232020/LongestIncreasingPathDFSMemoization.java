package google.mock.aug232020;

public class LongestIncreasingPathDFSMemoization {

	public static void main(String[] args) {
		LongestIncreasingPathDFSMemoization increasingPath = new LongestIncreasingPathDFSMemoization();
		int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1},};
		int l = increasingPath.longestIncreasingPath(matrix);
		System.out.println("Longest Increasing Path->" + l);
	}

	int[][] rowColDirections = {{0, 1}, {1, 0}, {-1, 0}, {0, -1},};
	int[][] matrix;
	int[][] cache; // Memoization
	int n, m;

	/*
	 * Complexity Analysis
	 * 
	 * Time complexity : O(mn). Each vertex/cell will be calculated once and
	 * only once, and each edge will be visited once and only once.
	 * 
	 * Space complexity : O(mn). The cache dominates the space complexity.
	 */
	public int longestIncreasingPath(int[][] matrix) {
		this.matrix = matrix;
		this.n = matrix.length;
		this.m = matrix[0].length;
		cache = new int[n][m];
		int moves = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				moves = Math.max(moves, dfs(i, j));
			}
		}
		return moves;
	}

	public int dfs(int row, int col) {
		if (cache[row][col] != 0)
			return cache[row][col];
		for (int[] rowCol : rowColDirections) {
			int nextRow = row + rowCol[0];
			int nextCol = col + rowCol[1];
			if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m && matrix[nextRow][nextCol] > matrix[row][col]) {
				cache[row][col] = Math.max(cache[row][col], dfs(nextRow, nextCol));
			}
		}
		return ++cache[row][col];
	}

}
