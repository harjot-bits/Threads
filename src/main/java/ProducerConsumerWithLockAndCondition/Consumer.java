package ProducerConsumerWithLockAndCondition;



public class Consumer implements Runnable {
	SharedBuffer buffer ;
	int N;
	public Consumer(SharedBuffer buffer, int N){
		this.buffer = buffer;
		this.N = N;
	}
	public void run(){
		for(int i=1; i<=N; i++){
			System.out.println(buffer.consume()); //what if there are no items in the buffer?? after ith iteration you shud read val, wait until buffer is empty
		}
	}
}
