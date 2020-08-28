package backtracking.h.handson.aug282020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {
	public static void main(String[] args) {
		NQueen nQueen = new NQueen();

		List<List<String>> solutions = nQueen.solveNQueens(5);
		// System.out.println("Solutions:" + solutions);

	}

	public void printBoard() {
		for (int i = 0; i < N; i++) {
			// System.out.println();
			for (int j = 0; j < N; j++) {
				// System.out.print(board[i][j] + " ");
			}
			// System.out.println();
		}
	}

	int[][] board;
	List<List<String>> solutions = new ArrayList<List<String>>();
	int N;
	public List<List<String>> solveNQueens(int n) {
		board = new int[n][n];
		N = n;
		// //printBoard();
		cleanBorad();
		backTrack(0);
		// //printBoard();
		return solutions;
	}

	public void cleanBorad() {
		// System.out.println("Cleaning the borad");
		for (int i = 0; i < N; i++) {
			Arrays.fill(board[i], 0);
		}
		// printBoard();
	}

	public boolean canPlace(int i, int j) {
		if (i < N && j < N) {
			return board[i][j] == 0;
		}
		return false;
	}

	public void placeQueen(int i, int j) {
		// System.out.println("Placing the Queen-" + i + "," + j);
		for (int k = 0; k < N; k++) {
			board[i][k]++;
			board[k][j]++;
		}
		for (int i1 = i, j1 = j; i1 < N && j1 < N; i1++, j1++) {
			board[i1][j1]++;
		}
		for (int i1 = i, j1 = j; i1 >= 0 && j1 >= 0; i1--, j1--) {
			board[i1][j1]++;
		}
		for (int i1 = i, j1 = j; i1 >= 0 && j1 < N; i1--, j1++) {
			board[i1][j1]++;
		}
		for (int i1 = i, j1 = j; i1 < N && j1 >= 0; i1++, j1--) {
			board[i1][j1]++;
		}
		// Set Queen
		board[i][j] = -1;
	}

	public void removeQueen(int i, int j) {
		// System.out.println("Placing the Queen-" + i + "," + j);
		if (!(i >= 0 && i < N && j >= 0 && j < N))
			return;
		for (int k = 0; k < N; k++) {
			board[i][k]--;
			board[k][j]--;
		}
		for (int i1 = i, j1 = j; i1 < N && j1 < N; i1++, j1++) {
			board[i1][j1]--;
		}
		for (int i1 = i, j1 = j; i1 >= 0 && j1 >= 0; i1--, j1--) {
			board[i1][j1]--;
		}
		for (int i1 = i, j1 = j; i1 >= 0 && j1 < N; i1--, j1++) {
			board[i1][j1]--;
		}
		for (int i1 = i, j1 = j; i1 < N && j1 >= 0; i1++, j1--) {
			board[i1][j1]--;
		}
		// Set Queen
		board[i][j] = 0;
	}

	public void addSolution() {
		// System.out.println("Found solution");
		List<String> solution = new ArrayList<String>();
		for (int i = 0; i < N; i++) {

			StringBuffer buffer = new StringBuffer();
			for (int j = 0; j < N; j++) {

				if (board[i][j] > 0) {
					buffer.append(".");
				} else {
					buffer.append("Q");
				}
			}
			solution.add(buffer.toString());
		}
		// System.out.println("Current->" + solution);
		solutions.add(solution);
	}

	public void backTrack(int row) {
		// System.out.println("back track-" + row);
		for (int j = 0; j < N; j++) {
			if (canPlace(row, j)) {
				placeQueen(row, j);
				// printBoard();
				// Solution found
				if (row + 1 == N) {
					addSolution();
				} else {
					backTrack(row + 1);
				}
				removeQueen(row, j);
			}
		}
	}

}
