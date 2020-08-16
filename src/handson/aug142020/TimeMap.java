package handson.aug142020;

import java.util.HashMap;
import java.util.TreeMap;

public class TimeMap {

	public static void main(String[] args) {

		TimeMap timeMap = new TimeMap();
		timeMap.set("foo", "bar", 1);
		System.out.println("->" + timeMap.get("foo", 3));
	}

	HashMap<String, TreeMap<Integer, String>> keyTimeValue;

	/** Initialize your data structure here. */
	public TimeMap() {
		keyTimeValue = new HashMap<String, TreeMap<Integer, String>>();
	}

	public void set(String key, String value, int timestamp) {
		if (keyTimeValue.containsKey(key)) {
			keyTimeValue.get(key).put(timestamp, value);
		} else {
			TreeMap<Integer, String> timeValue = new TreeMap<Integer, String>();
			timeValue.put(timestamp, value);
			keyTimeValue.put(key, timeValue);
		}
		System.out.println("set map->" + keyTimeValue);
	}

	public String get(String key, int timestamp) {
		System.out.println("get map->" + keyTimeValue);
		try {
			if (keyTimeValue.containsKey(key)) {
				int floorKey = keyTimeValue.get(key).floorKey(timestamp);
				return keyTimeValue.get(key).get(floorKey);
			}
		} catch (Exception e) {

		}
		return null;
	}
}

/**
 * Your TimeMap object will be instantiated and called as such: TimeMap obj =
 * new TimeMap(); obj.set(key,value,timestamp); String param_2 =
 * obj.get(key,timestamp);
 */