package handson.aug122020;

public class QuickSort {

	public static void main(String[] args) {
		int num[] = {4, 5, 6, 1, 2, 9, 0};
		// quickSort(num, 0, num.length-1);
		quickSortIterative(num, 0, num.length - 1);
		System.out.println("Shorted");
		for (int n : num)
			System.out.print(n + " ");
	}

	public static int partition(int[] num, int begin, int end) {

		int pivot = num[end];
		int partitionIndex = begin - 1;

		for (int i = begin; i < end; i++) {
			if (num[i] <= pivot) {
				partitionIndex++;

				int item = num[partitionIndex];
				num[partitionIndex] = num[i];
				num[i] = item;
			}
		}

		int item = num[partitionIndex + 1];
		num[partitionIndex + 1] = pivot;
		num[end] = item;
		return partitionIndex + 1;
	}

	static void quickSortIterative(int numArray[], int low, int high) {
		// auxillary stack
		int[] intStack = new int[high - low + 1];

		// top of stack initialized to -1
		int top = -1;

		// push initial values of low and high to stack
		intStack[++top] = low;
		intStack[++top] = high;

		// Keep popping from stack while is not empty
		while (top >= 0) {
			// Pop h and l
			high = intStack[top--];
			low = intStack[top--];

			// Set pivot element at its correct position
			// in sorted array
			int pivot = partition(numArray, low, high);

			// If there are elements on left side of pivot,
			// then push left side to stack
			if (pivot - 1 > low) {
				intStack[++top] = low;
				intStack[++top] = pivot - 1;
			}

			// If there are elements on right side of pivot,
			// then push right side to stack
			if (pivot + 1 < high) {
				intStack[++top] = pivot + 1;
				intStack[++top] = high;
			}
		}
	}
	public static void quickSort(int arr[], int start, int end) {

		if (start >= end)
			return;

		int partitionIndex = partition(arr, start, end);

		quickSort(arr, start, partitionIndex - 1);
		quickSort(arr, partitionIndex + 1, end);
	}

}
