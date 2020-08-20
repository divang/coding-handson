package graph.h.handson.aug192020;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RemoveInvalidParentheses {

	public List<String> removeInvalidParentheses(String s) {
		List<String> validSeq = new ArrayList<String>();
		if (s == null)
			return validSeq;
		char[] chars = s.toCharArray();

		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < chars.length; i++) {
			// if(chars[i] == stack.peek()
		}

		for (int i = chars.length - 1; i >= 0; i++) {

		}
		return validSeq;
	}
}
