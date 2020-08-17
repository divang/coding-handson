package handson.aug172020.m;

import java.util.ArrayList;
import java.util.Stack;

public class DecodeString {

	public static void main(String[] args) {
		DecodeString decodeString = new DecodeString();

		System.out.println("ch->" + decodeString.decodeString("ab3[a2[c]]ef"));
		// System.out.println("ch->" + decodeString.decodeString("3[a]2[bc]"));
		// decodeString.decodeString("100[leetcode]");
		// System.out.println("ch->" +
		// decodeString.decodeString("100[leetcode]"));
		// 100[leetcode]
		// 3[a]2[bc]
		// "3[a]2[b4[F]c]"
		// System.out.println("ch->" +
		// decodeString.decodeString("3[a]2[b4[F]c]"));

	}

	public String decodeString(String s) {

		if (s == null)
			return null;
		while (s.contains("[")) {
			int innerMostLeftParanthesis = s.lastIndexOf('[');
			int innerMostRightParanthesis = s.indexOf(']',
					innerMostLeftParanthesis);
			int[] repeatCountNIndex = getRepeatCountAndIndex(s,
					innerMostLeftParanthesis);

			String prefix = s.substring(0,
					innerMostLeftParanthesis - repeatCountNIndex[1]);
			String postfix = s.substring(innerMostRightParanthesis + 1,
					s.length());

			String decodedSubStr = decodeSubString(s,
					innerMostLeftParanthesis + 1, innerMostRightParanthesis,
					repeatCountNIndex[0]);
			s = prefix + decodedSubStr + postfix;
		}

		return s.replaceAll("]", "");
	}

	public int[] getRepeatCountAndIndex(String s, int l) {

		int[] countNIndex = new int[2];
		int leftFirstDigitIndex = l;
		while (leftFirstDigitIndex > 0) {
			if (s.charAt(leftFirstDigitIndex - 1) >= 48
					&& s.charAt(leftFirstDigitIndex - 1) <= 57) {
				leftFirstDigitIndex--;
			} else {
				break;
			}
		}
		int repeat = 0;
		for (int i = leftFirstDigitIndex; i < l; i++) {
			int c = s.charAt(i) - 48;
			repeat = repeat * 10 + c;
		}

		countNIndex[0] = repeat;
		countNIndex[1] = l - leftFirstDigitIndex;
		return countNIndex;
	}
	// l-> index of first char
	// r-> index of ']'
	public String decodeSubString(String s, int l, int r, int repeat) {

		String subStr = s.substring(l, r);
		StringBuilder repeatStr = new StringBuilder(subStr);
		for (int i = 1; i < repeat; i++) {
			repeatStr.append(subStr);
		}
		return repeatStr.toString();
	}

	// None functional
	public String decodeStringViaStack(String s) {

		if (s == null)
			return null;
		Stack<Character> stack = new Stack<Character>();
		char[] chars = s.toCharArray();
		for (char ch : chars) {
			stack.push(ch);
		}
		Stack<Character> stackSubStr = new Stack<Character>();

		ArrayList<Character> decodedStr = null;

		while (!stack.isEmpty()) {
			char curChar = stack.pop();
			if (curChar != '[') {
				stackSubStr.push(curChar);
			} else {
				int occurance = stack.pop() - 48;
				decodedStr = new ArrayList<Character>();
				while (!stackSubStr.isEmpty()) {
					char subChar = stackSubStr.pop();
					if (subChar != ']') {
						decodedStr.add(subChar);
					} else {
						break;
					}
				}
				for (int i = 0; i < occurance; i++) {
					for (char dChar : decodedStr) {
						stack.push(dChar);
					}
				}
			}

		}
		return null;
	}
}
