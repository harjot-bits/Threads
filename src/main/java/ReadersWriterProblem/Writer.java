package ReadersWriterProblem;


public class Writer implements Runnable {
	SharedBuffer buffer;
	int N;

	public Writer(SharedBuffer buffer, int N) {
		this.buffer = buffer;
		this.N = N;
	}

	public void run() {
		for (int i = 1; i <= N; i++) {
			buffer.produce(i); //sometimes it wont even add, so how will you know that after ith iteration it has been added? this thread should wait in produce method
		}
	}
}
