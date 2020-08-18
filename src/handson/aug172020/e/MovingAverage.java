package handson.aug172020.e;

import java.util.ArrayList;

public class MovingAverage {

	public static void main(String[] args) {

		MovingAverage average = new MovingAverage(3);
		System.out.println("-->avg: " + average.next(1));
		System.out.println("-->avg: " + average.next(10));
		System.out.println("-->avg: " + average.next(3));
		System.out.println("-->avg: " + average.next(5));
	}

	ArrayList<Integer> list;
	int size;
	int lastSum;
	/** Initialize your data structure here. */
	public MovingAverage(int size) {
		list = new ArrayList<Integer>();
		this.size = size;
	}

	public double next(int val) {

		if (list.size() == size) {
			int removedItem = list.remove(0);
			lastSum -= removedItem;
		}
		list.add(val);
		lastSum += val;
		return (double) lastSum / (double) list.size();
	}
}
