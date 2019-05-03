import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClosestValService {
	private static final int MAX_ENTRIES = 3;
	private static LinkedHashMap
			<String , String> cachedClosestStrings
			= new LinkedHashMap<String , String >() {
		protected boolean removeEldestEntry (Map.Entry eldest) {
			return size() > MAX_ENTRIES ;
		}
	};
	private static Map<String, String> store = new HashMap<String, String>(){{
		put("non", "none");
		put("sone", "some");
		put("trut", "truth");
		put("flwer", "flower");
		put("sooon", "soon");
		put("mun", "moon");
		put("sune", "sun");
		put("kimi", "kim");
		put("citty", "city");
		put("neighbur", "neighbor");
		put("phon", "phone");
		put("don", "done");
		put("cay", "cat");
	}};



	public String getClosestVal (String key) {
		synchronized (cachedClosestStrings) {
			if (cachedClosestStrings.containsKey(key)) {
				System.out.println("Thread "+ Thread.currentThread().getName()+" Getting from cache "+key);
				return cachedClosestStrings.get(key);

			} //if not in store for thread 1, it will release lock and proceed to get it from store and add to cache
			//rest of the waiting threads (for the same word) will do the same
			//which will mean all the threads will add the same word to the cache one after other
		}
		System.out.println("Thread "+ Thread.currentThread().getName()+" Getting from store "+key);
		String closestToLastWord = store.get(key);
		synchronized (cachedClosestStrings) {
			System.out.println("Thread "+ Thread.currentThread().getName()+" putting to cache "+key);
			cachedClosestStrings.put(key, closestToLastWord);// all the threads will add the same word to the cache one after other
		}
		return  closestToLastWord;
	}
}
