package ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {
	private int maxSize;
	Queue<Integer> que;
	public SharedBuffer(int maxSize){
		this.maxSize = maxSize;
		que = new LinkedList<Integer>();
	}

	public  void produce(int no) {
		synchronized(que){
			while(que.size() == this.maxSize){ //keep waiting
				try{
					System.out.println("Producer Waiting");
					que.wait();
				} catch (Exception e) {

				}
			}
			System.out.println("Producing");
			que.add(no);
			que.notifyAll();
		}


	}

	public  int consume(){
		int val;
		synchronized(que) {
			while (que.size() == 0) { //keep waiting until no items
				try {
					System.out.println("Consumer Waiting");
					que.wait();
				} catch (Exception e) {

				}
			}
			System.out.println("Consuming");
			val = que.remove();
			que.notifyAll();
		}
		return val;
	}

}
