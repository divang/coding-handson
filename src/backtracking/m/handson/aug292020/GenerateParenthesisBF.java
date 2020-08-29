package backtracking.m.handson.aug292020;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesisBF {

	public static void main(String[] args) {
		GenerateParenthesisBF bf = new GenerateParenthesisBF();
		List<String> r = bf.generateParenthesis(3);
		// System.out.println(r);
	}
	List<String> result = new ArrayList<String>();
	char[] seq;

	public List<String> generateParenthesis(int n) {
		seq = new char[n * 2];
		buildSeq(0);
		return result;
	}

	public void buildSeq(int pos) {
		// System.out.println("Seq: " + Arrays.toString(seq) + " pos: " + pos);
		if (pos == seq.length) {
			if (validate()) {
				result.add(new String(seq));
			}
		} else if (pos < seq.length) {
			seq[pos] = '(';
			buildSeq(pos + 1);
			seq[pos] = ')';
			buildSeq(pos + 1);
		}
	}

	public boolean validate() {
		int openCloseCounter = 0;

		for (char ch : seq) {
			if (ch == '(') {
				openCloseCounter++;
			} else {
				if (openCloseCounter > 0) {
					openCloseCounter--;
				} else {
					return false;
				}
			}
		}
		// System.out.println("Seq: " + Arrays.toString(seq) + " counter: " +
		// openCloseCounter);
		return openCloseCounter == 0;
	}
}
