package graph.m.handson.aug182020;

public class TotalIslands {

	public static void main(String[] args) {
		TotalIslands totalIslands = new TotalIslands();
		char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'},
				{'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};

		totalIslands.printGrid(grid);
		int n = totalIslands.numIslands(grid);
		System.out.println("total islands->" + n);
	}

	public void printGrid(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			System.out.println("");
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j] + " | ");
			}
			System.out.println("");
		}
		System.out.println("=====================");
	}

	public int numIslands(char[][] grid) {

		int islands = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {

				if (grid[i][j] == '1') {
					islands++;
					System.out.println("Mark island. r->" + i + " c->" + j);
					markIslandArea(grid, i, j);
				}
			}
		}
		return islands;
	}

	public void markIslandArea(char[][] grid, int row, int column) {
		System.out.println("DFS call. r->" + row + " c->" + column);
		if (grid == null || grid.length == 0)
			return;

		if (row >= 0 && column >= 0 && row < grid.length
				&& column < grid[0].length) {
			if (grid[row][column] == '0')
				return;
			grid[row][column] = '0';
			// Now mark all near by cells too, '0' as part of island via DFS
			markIslandArea(grid, row, column - 1);
			markIslandArea(grid, row, column + 1);
			markIslandArea(grid, row - 1, column);
			markIslandArea(grid, row + 1, column);
		} else {
			System.out.println(
					"Base condition failed. r->" + row + " c->" + column);
		}
	}
}
