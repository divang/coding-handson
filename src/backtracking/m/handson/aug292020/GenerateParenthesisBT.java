package backtracking.m.handson.aug292020;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesisBT {

	public static void main(String[] args) {
		GenerateParenthesisBT bf = new GenerateParenthesisBT();
		List<String> r = bf.generateParenthesis(3);
		System.out.println(r);
	}
	List<String> result = new ArrayList<String>();

	int max;
	public List<String> generateParenthesis(int n) {
		max = n;
		backTrack("", 0, 0);
		return result;
	}

	public void backTrack(String seq, int open, int close) {
		System.out.println("Seq: " + seq + " open: " + open + " close: " + close);
		if (seq.length() == max * 2) {
			result.add(seq);
		} else {
			if (open < max) {
				backTrack(seq + "(", open + 1, close);
			}
			if (close < open) {
				backTrack(seq + ")", open, close + 1);
			}
		}
	}
}
