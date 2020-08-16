package handson.aug152020.e;

public class PalidromeII {

	public static void main(String[] args) {
		testValidPalindrome();
	}

	public static void testValidPalindrome() {
		PalidromeII palidromeII = new PalidromeII();
		String str;
		str = "aac";
		System.out.println(str + " is valid Palindrome? "
				+ palidromeII.validPalindrome(str));
		str = "aacb";
		System.out.println(str + " is valid Palindrome? "
				+ palidromeII.validPalindrome(str));

		str = "ab";
		System.out.println(str + " is valid Palindrome? "
				+ palidromeII.validPalindrome(str));

	}
	public static void testIsPalindrome() {
		PalidromeII palidromeII = new PalidromeII();
		String str;
		str = "aa";
		System.out.println(str + " is Palindrome? " + palidromeII
				.isPalindrome(str.toCharArray(), 0, str.length() - 1));
		str = "ab";
		System.out.println(str + " is Palindrome? " + palidromeII
				.isPalindrome(str.toCharArray(), 0, str.length() - 1));
		str = "aa";
		System.out.println(str + " is Palindrome? " + palidromeII
				.isPalindrome(str.toCharArray(), 0, str.length() - 1));
		str = "abbca";
		System.out.println(str + " is Palindrome? " + palidromeII
				.isPalindrome(str.toCharArray(), 0, str.length() - 1));
		str = "abccba";
		System.out.println(str + " is Palindrome? " + palidromeII
				.isPalindrome(str.toCharArray(), 0, str.length() - 1));
		str = "abccba1";
		System.out.println(str + " is Palindrome? " + palidromeII
				.isPalindrome(str.toCharArray(), 0, str.length() - 1));
	}

	public boolean validPalindrome(String s) {

		char[] chars = s.toCharArray();
		int l = 0;
		int r = s.length() - 1;

		while (l < r) {
			if (chars[l] == chars[r]) {
				l++;
				r--;
			} else {
				// let skip a char from right
				if (isPalindrome(chars, l, r - 1))
					return true;
				// let skip a char from left
				if (isPalindrome(chars, l + 1, r))
					return true;
				return false;
			}
		}

		return true;
	}

	public boolean isPalindrome(char[] chars, int l, int r) {

		for (; l < r; l++, r--) {
			if (chars[l] != chars[r])
				return false;
		}

		return true;
	}
}
