package challenge.sep012020;

import java.util.Arrays;

public class LargestTimeForGivenDigits {

	public static void main(String[] args) {
		LargestTimeForGivenDigits digits = new LargestTimeForGivenDigits();
		// int[] A = {3, 2, 7, 0};
		int[] A = {0, 1, 1, 0};
		// [5,5,5,5]
		String str = digits.largestTimeFromDigits(A);
		System.out.println("Res->" + str);
	}
	int lastMax = 24;
	int[] digits = new int[10];

	public String largestTimeFromDigits(int[] A) {
		int lastHour = 0, hour = 0;
		int lastMin = 0, min = 0;
		if (Arrays.stream(A).allMatch(e -> e == 0))
			return "00:00";
		do {
			Arrays.fill(digits, -1);
			Arrays.stream(A).forEach(e -> ++digits[e]);

			hour = getNextMaxHour();
			min = getMaxMin();
			if (hour == lastHour && min == lastMin) {
				return isDone() ? "00:00" : "";
			}
			lastHour = hour;
			lastMin = min;
		} while (!isDone());
		StringBuilder builder = new StringBuilder();
		if (hour < 10) {
			builder.append("0");
		}
		builder.append(hour).append(":");
		if (min < 10) {
			builder.append("0");
		}
		builder.append(min);

		return builder.toString();
	}

	public boolean isDone() {
		return Arrays.stream(digits).allMatch(e -> e == -1);
	}

	public int getNextMaxHour() {
		int tenPlace = lastMax / 10;
		int curMax = 0;

		for (int i = tenPlace; i >= 0; i--) {
			if (digits[i] >= 0) {
				curMax = i * 10;
				--digits[i];
				for (int j = 9; j >= 0; j--) {
					if (digits[j] >= 0) {
						if (curMax + j < lastMax) {
							curMax += j;

							--digits[j];
							lastMax = curMax;
							return curMax;
						}
					}
				}
				++digits[i];
			}
		}
		return lastMax;
	}

	public int getMaxMin() {
		int curMin = 0;
		for (int i = 5; i >= 0; i--) {
			if (digits[i] >= 0) {
				curMin = i * 10;
				--digits[i];
				break;
			}
		}
		for (int i = 9; i >= 0; i--) {
			if (digits[i] >= 0) {
				curMin += i;
				--digits[i];
				break;
			}
		}
		return curMin;
	}
}