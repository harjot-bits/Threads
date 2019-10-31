package ReadersWriterProblem;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {
	Integer rCount = 0;
	Integer wCount = 0;
	Queue<Integer> que;
	ReadWriteLock readWriteLock;
	private int maxSize;

	public SharedBuffer(int maxSize) {
		this.maxSize = maxSize;
		que = new LinkedList<Integer>();
		readWriteLock = new ReadWriteLock();
	}

	public void produce(int no) { //no readers, no writers
		try {
			readWriteLock.lockWrite(); //no writer/reader should be active
		} catch (Exception e) {

		}

		/**while(que.size() == this.maxSize){ //keep waiting, can cause deadlock, as no reader will enter.
		 try{
		 System.out.println("Producer Waiting");
		 bufferFull.wait();
		 } catch (Exception e) {

		 }
		 }**/
		if(que.size()==maxSize){
			//release lock so that consumers can enter and reacquire the write lock
		}
		System.out.println("Producing");
		que.add(no);
		try {
			readWriteLock.unlockWrite(); //no writer/reader should be active
		} catch (Exception e) {

		}


	}

	public int consume() {
		try {
			readWriteLock.lockRead(); //no writer should be active
		} catch (Exception e) {

		}

		int val;

		/** HOW TO HANDLE THIS - take lock on queue and wait while holding read lock which means no writer will be able to enter
		 * readers are waiting for data, and writers are waiting for readers to come out
		 * SO reader should release the lock
		 * while (que.size() == 0) { //keep waiting until no items
		 try {
		 System.out.println("Consumer Waiting");
		 que.wait();
		 } catch (Exception e) {

		 }
		 }**/
		if(que.size()==0){
			//unlock and reacquire the read lock =put entire code in while loop, if data found break fromwhile loop
		}
		System.out.println("Consuming");
		val = que.peek();
		try {
			readWriteLock.unlockRead();
		} catch (Exception e) {

		}

		return val;
	}

}
