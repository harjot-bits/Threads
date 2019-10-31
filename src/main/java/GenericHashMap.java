import java.util.HashMap;
import java.util.Map;

public class GenericHashMap<K,V> {
	Map<K, V> myMap;
	public GenericHashMap() {
		myMap = new HashMap<K, V>();
	}

	public void put(K key, V val) {
		myMap.put(key, val);
	}
	public V get(K key) {
		return myMap.get(key);
	}

	public int size() {
		return myMap.size();
	}

	public void remove(K key) {
		if(!myMap.containsKey(key)) {
			return;
		}
		 myMap.remove(key);
	}

}
