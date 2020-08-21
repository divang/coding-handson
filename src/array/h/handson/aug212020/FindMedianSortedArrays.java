package array.h.handson.aug212020;

public class FindMedianSortedArrays {

	/*
	 * 
	 * x-> xLMax | xRMin
	 * 
	 * y-> yLMax | yRMin
	 * 
	 * Median condition:
	 * 
	 * xLMax <= yRMin
	 * 
	 * yLMax <= xRMin
	 * 
	 * median will be total nums are odd then
	 * 
	 * Max.max(xLMax, yLMax)
	 * 
	 * median will be total nums are even then
	 * 
	 * Max.avg(Max.max(xLMax, yLMax), Max.min(xRMin, yRMin))/2
	 * 
	 * Otherwise do the partition again:
	 * 
	 * xLMax > yRMin --> shift r pointer to partition point - 1
	 * 
	 * else
	 * 
	 * yLMax > xRMin --> shift l pointer to partition point + 1
	 */

	public double findMedianSortedArrays(int[] x, int[] y) {

		if (x.length > y.length) {
			return findMedianSortedArrays(y, x);
		}

		int l = 0;
		int r = x.length;
		int nums = x.length + y.length;
		while (l <= r) {

			int pX = r + l / 2;
			// +1 for handing the odd count partitioning.
			int pY = (nums + 1) / 2 - pX;

			int xRMin = pX == x.length ? Integer.MAX_VALUE : x[pX];
			int xLMax = pX == 0 ? Integer.MIN_VALUE : x[pX - 1];

			int yRMin = pY == y.length ? Integer.MAX_VALUE : y[pY];
			int yLMax = pY == 0 ? Integer.MIN_VALUE : y[pY - 1];

			if (xLMax <= yRMin && yLMax <= xRMin) {
				// Median condition
				if (nums % 2 == 0) {
					return ((double) Math.max(xLMax, yLMax) + Math.min(xRMin, yRMin)) / 2;
				} else {
					return (double) Math.max(xLMax, yLMax);
				}
			} else if (xLMax > yRMin) {
				r = pX - 1;
			} else {
				l = pX + 1;
			}
		}
		throw new IllegalArgumentException("Arrays are not sorted");
	}
	public static void main(String[] args) {
		int[] x = {1, 5, 10, 20, 25, 26, 35, 40, 45, 50};
		int[] y = {0, 3, 7, 11, 21, 27, 28, 37, 42, 48, 55, 60};
		FindMedianSortedArrays mm = new FindMedianSortedArrays();
		double median = mm.findMedianSortedArrays(x, y);
		System.out.println("median->" + median);

	}
}
