package DelayedScheduler;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyDelayedScheduler implements   DelayedScheduler {
	PriorityQueue<Task> pq;
	Thread t;
	BackgroundScheduler scheduler;
	Lock lock = new ReentrantLock();
	Condition newInsertionSignal = lock.newCondition();
	public MyDelayedScheduler(){
		pq = new PriorityQueue<Task>(new Comparator<Task>() {
			public int compare(Task o1, Task o2) {
				return o1.time.compareTo(o2.time);
			}
		});
		scheduler = new BackgroundScheduler(pq, newInsertionSignal, lock);
		t = new Thread(scheduler);
		t.start();
	}
	public void delayedRun(long time, Runnable runnable){
		lock.lock();
		pq.add(new Task(time, runnable)); //remove/add it in thread safe way with 1 lock
		newInsertionSignal.signalAll();
		lock.unlock();
		//scheduler.updateSleep(); //use conditions

	}

}
class Task {
	Runnable toRun;
	Long time;
	public Task(long time, Runnable runnable){
		this.toRun = runnable;
		this.time = time;
	}
}
class BackgroundScheduler implements Runnable{
	PriorityQueue<Task> pq = null;
	Condition condition;
	Lock lock;
	public BackgroundScheduler(PriorityQueue<Task> pq, Condition condition, Lock lock){
		this.pq = pq;
		this.condition = condition;
		this.lock = lock;
	}
	public void run(){
		try {
			while (true) {
				lock.lock();
				while (pq.isEmpty()) {
					condition.await();
				}
				//System.out.println("Entered2");
				long currTime = System.currentTimeMillis();
				long nextTime = pq.peek().time;
                if(nextTime-currTime<=0){
                	Runnable task = pq.poll().toRun; //remove/add it in thread safe way
                	Thread t = new Thread(task);
                	t.start();
				} else
				//System.out.println("Waiting for "+(nextTime-currTime));
					condition.await(nextTime-currTime, TimeUnit.MILLISECONDS);
				lock.unlock();
			}
		}catch (Exception e){

		}
	}

	/**public void updateSleep(){
		long currTime = System.currentTimeMillis();
		if(!pq.isEmpty()){
			long nextTime = pq.peek().time;

		}
	}**/
}
