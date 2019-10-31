package TimeBaseKeyValStore;

import java.util.HashMap;
import java.util.Map;


public class CustomHashMap {
	Cell[] store = null;//int[] store = null; can not have lock on int objects Integer obj is immutable
	int[] count = null;
	long[] times = null;
	int bufferSize = 0;
	Map<Integer, MessageCus> map = null;
	Map<Integer, Integer> keyObjs = null;

	public CustomHashMap(int bufferSize) {
		store = new Cell[bufferSize];
		times = new long[bufferSize];
		count = new int[bufferSize];
		map = new HashMap();
		keyObjs = new HashMap();
		this.bufferSize = bufferSize;
		for(int i=0; i<store.length; i++){
			Cell cv = new Cell();
			store[i] =cv;
		}
	}


	public Integer get(int key) {  //if write is also happening simultaneously??
		if (!map.containsKey(key)) {
			return null;
		}
		long time = System.currentTimeMillis() - (bufferSize *60* 1000); //millis

		MessageCus msg = map.get(key);
		if (msg.timestamp < time) {
			//map.remove(key); background job will do this, remove key object as well
			return null;
		}
		return msg.val;
	}

	public void put(int key, int val) {
		MessageCus m = null;
		Integer obj = null;
		if(map.containsKey(key)){ //if already created both threads will get same object, which is fine
			m = map.get(key);
			obj = keyObjs.get(key);
		} else { //We synchronize for a key Object. To prevet two threads accessing diff key objects, we need to ensure there is only one Object for a key
			synchronized (keyObjs) { //here make sure for a given key (Integer obj is always same )
				if (!keyObjs.containsKey(key)) {
					obj = new Integer(key);
					keyObjs.put(key, obj);
				} else {
					obj = keyObjs.get(key);
				}
			}
			m = new MessageCus();
		}
		m.key = key;
		m.val = val;
		synchronized (obj){ //synchronize only a key object, make sure for a given key (Integer obj is always same )
			if(map.containsKey(key)){ //if already created both threads will get same object
				map.put(key, m); //modify only if new TS is latest,how to make sure newTS is retained

			} else
				map.put(key, m);  //first time add
		}
		long time = m.timestamp;
		int idx = (int) (time % bufferSize);
		//write synchronize for a given idx ...do we need getAvg read synchronize??
		synchronized (store[idx]){
			if (time <= (times[idx] + bufferSize * 60 * 1000)) {
				store[idx].setValue(store[idx].getValue()+val);
				count[idx] += 1;
			} else {
				store[idx].setValue(val);
				count[idx] = 1;
				times[idx] = time;
			}
		}
	}

	public double getAvg() { //We do not need to synchronize for read, what if write is happening simultaneously
		double runSum = 0;
		double c = 0;
		long time = System.currentTimeMillis() - (bufferSize * 60 * 1000);
		for (int i = 0; i < store.length; i++) {
			if (times[i] >= time) {
				runSum += store[i].getValue(); //write is synchronized for a given idx for add to that index ...shall getAvg be read synchronize??
				c += count[i];
			}
		}
		return c == 0 ? 0 : runSum / c;
	}


}

class Cell {

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
