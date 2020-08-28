package backtracking.h.handson.aug252020;

public class MySudokuSolver {

	public static void print(char[][] board) {
		int N = 9;
		// we got the answer, just print it
		for (int r = 0; r < N; r++) {
			for (int d = 0; d < N; d++) {
				System.out.print(board[r][d]);
				System.out.print(" ");
			}
			System.out.print("\n");

			if ((r + 1) % (int) Math.sqrt(N) == 0) {
				System.out.print("");
			}
		}
	}

	public static void main(String[] args) {

		MySudokuSolver mySudokuSolver = new MySudokuSolver();
		char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
				{'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
				{'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
				{'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
				{'.', '.', '.', '.', '8', '.', '.', '7', '9'},};
		print(board);
		mySudokuSolver.solveSudoku(board);
		System.out.println("Sol:");
		print(board);

	}
	char[][] board;
	boolean found = false;

	public void solveSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9)
			return;
		this.board = board;
		backTrack(0, 0);
	}

	public void backTrackFromLToRCellOrNextRowCell(int row, int col) {
		if (row == 8 && col == 8) {
			found = true;
		} else {
			if (col == 8) {
				// Move
				backTrack(row + 1, 0);
			} else
				backTrack(row, col + 1);
		}
	}

	public void backTrack(int row, int col) {

		if (board[row][col] == '.') {
			for (int v = 1; v <= 9; v++) {
				System.out.println(row + "," + col + "," + v);
				if (validateAll(row, col, (char) (v + '0'))) {
					board[row][col] = (char) (v + '0');
					System.out.println("Found digit: " + row + "," + col + "," + v);
					print(board);
					backTrackFromLToRCellOrNextRowCell(row, col);
					if (!found) { // back trace
						// Looking for only one successful 8*8 cells.
						// If it is not, then back track to the origin, means
						// THE FRIST '.' and try new value and go forward track.
						System.out.println("back track: " + row + "," + col + ",.");
						board[row][col] = '.';
					}
				} else {
					System.out.println("Not valid: " + row + "," + col + "," + v + " so it will trigger back track... hhmmm");
				}
			}
		} else {
			backTrackFromLToRCellOrNextRowCell(row, col);
		}

	}

	public boolean validateAll(int r, int c, char v) {
		boolean validate = validateRow(r, v);
		if (!validate)
			return false;
		validate = validateColumn(c, v);
		if (!validate)
			return false;
		return validateBox(r, c, v);
	}

	public boolean validateArray(char[] arr, char v) {
		for (char c : arr) {
			if (c == v)
				return false;
		}
		return true;
	}

	public boolean validateRow(int r, char v) {
		char[] rA = board[r];
		// System.out.println("row->" + Arrays.toString(rA));
		return validateArray(rA, v);
	}

	public boolean validateColumn(int c, char v) {
		char[] cA = new char[9];

		for (int r = 0; r < 9; r++) {
			cA[r] = board[r][c];
		}
		// System.out.println("col->" + Arrays.toString(cA));
		return validateArray(cA, v);
	}

	public boolean validateBox(int r, int c, char v) {
		char[] arr = new char[9];
		int r1 = r / 3 * 3;
		int c1 = c / 3 * 3;
		int r2 = r1 + 3;
		int c2 = c1 + 3;
		int iArr = 0;
		for (int i = r1; i < r2; i++) {
			for (int j = c1; j < c2; j++) {
				arr[iArr++] = board[i][j];
			}
		}
		return validateArray(arr, v);
	}
}
