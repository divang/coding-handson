package basic.algorithms;

/**
 * Based on Hashing.
 * 
 * @author sdivang
 *
 */
public class RobinKarp_StringMatch {

	public static void main(String[] args) {
		RobinKarp_StringMatch stringMatch = new RobinKarp_StringMatch();
		System.out.println("M->" + (stringMatch.subString("abcde", "acb") >= 0));
	}

	public int subString(String line, String subStr) {
		int subStrHashCode = getHashCode(subStr);
		int subStrLen = subStr.length();
		int lineSubStrHashCode = 0;
		for (int i = 0; i <= line.length() - subStrLen; i++) {
			lineSubStrHashCode = regenerateHashCode(line, i, subStrLen, lineSubStrHashCode);
			System.out.println("lineSubStrHashCode-" + lineSubStrHashCode);
			if (lineSubStrHashCode == subStrHashCode)
				return i;
		}
		return -1;
	}

	final int primeNum = 3;
	private int getHashCode(String str) {
		int hashCode = 0;
		int pow = 0;
		for (Character c : str.toCharArray()) {
			hashCode += getInt(c) * Math.pow(primeNum, pow);
			pow++;
		}
		return hashCode;
	}

	private int getInt(char c) {
		return c - 'a' + 1;
	}
	private int regenerateHashCode(String line, int startIndex, int len, int preHashCode) {
		if (startIndex == 0)
			return getHashCode(line.substring(startIndex, startIndex + len));
		char preChar = line.charAt(startIndex - 1);
		char nextChar = line.charAt(startIndex + len - 1);
		int remainHasValue = (preHashCode - getInt(preChar));
		int hasValueAdd = getInt(nextChar) * (int) Math.pow(primeNum, len - 1);
		return remainHasValue / primeNum + hasValueAdd;
	}
}
