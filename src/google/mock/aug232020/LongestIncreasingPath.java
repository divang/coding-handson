package google.mock.aug232020;

public class LongestIncreasingPath {

	class Coordinate {
		int i;
		int j;
		int v = Integer.MAX_VALUE;
	}

	public int longestIncreasingPath(int[][] matrix) {

		Coordinate minCoordinate = new Coordinate();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (minCoordinate.v > matrix[i][j]) {
					minCoordinate.i = i;
					minCoordinate.j = j;
					minCoordinate.v = matrix[i][j];
				}
			}
		}

		return 0;
	}
}
