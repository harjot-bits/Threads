import jdk.nashorn.internal.ir.ObjectNode;

import java.util.concurrent.atomic.AtomicInteger;

public class ReadersWritesStore {
	private static int storeVal = 0;
	private static AtomicInteger readersCount = new AtomicInteger(0);


	public  void getStoreVal() {
		//what if write is happening, read can not happen. hence readerscount will not update
		synchronized (readersCount) { //for read to happen at same time, release the lock after performing atomic operation
			readersCount.set(readersCount.intValue() + 1); //this should be an atomic operation
			readersCount.notifyAll();
		}
        //while read is happening, no write should happen. Write thread should take care of this, if it knows how many are reading, hence maintain count
		System.out.println("Reading val "+storeVal+" by Threas "+Thread.currentThread().getName()); //can happen simultaneously by multiple threads
		synchronized (readersCount) {
			readersCount.set(readersCount.intValue() - 1); //this should be an atomic operation
			readersCount.notifyAll();
		}
	}





		/**int retVal = storeVal;
		synchronized (writersLock) { // if read is happening, write will not happen. But next read will also not happen
			while (writersLock.intValue()>0) {
				try {
					//System.out.println("Waiting "+Thread.currentThread().getName());
					writersLock.wait();
					//System.out.println("Getting up "+Thread.currentThread().getName());
				} catch (InterruptedException e) {

				}
			}
			System.out.println("Awake "+Thread.currentThread().getName());
			readersCount.set(readersCount.intValue()+1);
			System.out.println("Reading val "+storeVal+" by Threas "+Thread.currentThread().getName());
			retVal =  storeVal;
			readersCount.set(readersCount.intValue()-1);
			writersLock.notifyAll();
		}
		return  retVal;**/


	public  void setStoreVal(int val) {
		synchronized (readersCount) { //no one is writing. Reading can happen as read is without a lock, only read count updation is with a lock.
			// But before writing write will have to wait until reading is not donee
			while(readersCount.intValue()>0) { //if there are readers (known with count), then wait
				try {
					System.out.println("Waiting "+Thread.currentThread().getName());
					readersCount.wait();
				} catch (InterruptedException e) {

				}
			}
			//no one is reading as read count is 0 and read lock is locked
			System.out.println("Setting " + val + " by " + Thread.currentThread().getName());
			storeVal = val;
			readersCount.notifyAll();
		}
	}
}
