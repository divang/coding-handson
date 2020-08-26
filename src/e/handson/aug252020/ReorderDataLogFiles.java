package e.handson.aug252020;

import java.util.Arrays;
import java.util.Comparator;

public class ReorderDataLogFiles {

	public static void main(String[] args) {

		String[] logs = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
		// ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act
		// zoo","a2 act car"]
		ReorderDataLogFiles dataLogFiles = new ReorderDataLogFiles();
		String[] reord = dataLogFiles.reorderLogFiles(logs);
		System.out.println("reorder->" + Arrays.toString(reord));

	}
	public String[] reorderLogFiles(String[] logs) {

		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// + -> o1>o2
				// - -> o2>o1
				// 0 -> o1==o2

				String[] split1 = o1.split(" ", 2);
				String[] split2 = o2.split(" ", 2);

				boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
				boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

				if (isDigit1 && isDigit2)
					return 0;
				else if (!isDigit1 && isDigit2)
					return -1;
				else if (isDigit1 && !isDigit2)
					return 1;
				else {
					int cmp = split1[1].compareTo(split2[1]);
					if (cmp == 0) {
						cmp = split1[0].compareTo(split2[0]);
					}
					return cmp;
				}
			}
		};
		Arrays.sort(logs, comparator);
		return logs;
	}
}
