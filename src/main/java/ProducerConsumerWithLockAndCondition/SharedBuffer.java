package ProducerConsumerWithLockAndCondition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBuffer {
	Queue<Integer> que;
	Lock lock = new ReentrantLock();
	Condition emptyCond = lock.newCondition();
	Condition fullCond = lock.newCondition();
	private int maxSize;


	public SharedBuffer(int maxSize) {
		this.maxSize = maxSize;
		que = new LinkedList<Integer>();
	}

	public void produce(int no) {
		lock.lock();
		while (que.size() == this.maxSize) { //keep waiting
			try {
				System.out.println("Producer Waiting");
				fullCond.await();
			} catch (Exception e) {

			}
		}
		System.out.println("Producing");
		que.add(no);
		emptyCond.signalAll(); //different condition than what this thread awaits for
		lock.unlock();


	}

	public int consume() {
		int val;
		lock.lock();
		while (que.size() == 0) { //keep waiting until no items
			try {
				System.out.println("Consumer Waiting");
				emptyCond.await();
			} catch (Exception e) {

			}
		}
		System.out.println("Consuming");
		val = que.remove();
		fullCond.signalAll();
		lock.unlock();
		return val;
	}

}
