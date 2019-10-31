package ReadersWriterProblem;

public class ReadWriteLock {
	int readers = 0;
	int writers = 0;
	int writeRequests = 0;

	public synchronized void lockRead() throws InterruptedException {
		while (writers > 0 ||     writeRequests>0) { //give priority to waiting writes
			wait();
		}
		readers++;
	}

	public synchronized void unlockRead() {
		readers--;
		notifyAll();
	}

	public synchronized void lockWrite() throws InterruptedException {
		writeRequests++;
		while (readers > 0 || writers > 0) {
			wait();
		}
		writeRequests--;
		writers++;
	}

	public synchronized void unlockWrite() throws InterruptedException {
		writers--;
		notifyAll();
	}
}
