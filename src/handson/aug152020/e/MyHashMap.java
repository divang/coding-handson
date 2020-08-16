package handson.aug152020.e;

import java.util.ArrayList;

public class MyHashMap {

	public static void main(String[] args) {
		MyHashMap myHashMap = new MyHashMap();
		myHashMap.put(2, 1);
		System.out.println("HashMap->" + myHashMap);
		System.out.println("v->" + myHashMap.get(2));
	}
	class Entity {
		int k;
		int v;

		public Entity(int k, int v) {
			this.k = k;
			this.v = v;
		}

		@Override
		public String toString() {
			return "Entity [k=" + k + ", v=" + v + "]";
		}
	}

	ArrayList<ArrayList<Entity>> buckets = new ArrayList<ArrayList<Entity>>();
	int bucketSize = 10;

	/** Initialize your data structure here. */
	public MyHashMap() {
		for (int i = 0; i < bucketSize; i++) {
			buckets.add(new ArrayList<MyHashMap.Entity>());
		}
	}

	/** value will always be non-negative. */
	public void put(int key, int value) {
		int hasValue = key % bucketSize;
		if (get(key) == -1) {
			buckets.get(hasValue).add(new Entity(key, value));
		} else {
			ArrayList<Entity> bucket = buckets.get(hasValue);

			for (int i = 0; i < bucket.size(); i++) {
				if (bucket.get(i).k == key) {
					bucket.get(i).v = value;
					break;
				}
			}
		}
	}

	/**
	 * Returns the value to which the specified key is mapped, or -1 if this map
	 * contains no mapping for the key
	 */
	public int get(int key) {
		int hasValue = key % bucketSize;
		for (Entity entity : buckets.get(hasValue)) {
			if (entity.k == key)
				return entity.v;
		}
		return -1;
	}

	/**
	 * Removes the mapping of the specified value key if this map contains a
	 * mapping for the key
	 */
	public void remove(int key) {
		int hasValue = key % bucketSize;
		ArrayList<Entity> bucket = buckets.get(hasValue);

		for (int i = 0; i < bucket.size(); i++) {
			if (bucket.get(i).k == key) {
				buckets.get(hasValue).remove(i);
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "MyHashMap [buckets=" + buckets + ", bucketSize=" + bucketSize
				+ "]";
	}

}

/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj
 * = new MyHashMap(); obj.put(key,value); int param_2 = obj.get(key);
 * obj.remove(key);
 */