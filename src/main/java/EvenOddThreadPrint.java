public class EvenOddThreadPrint implements Runnable {
	private static int count = 1;
	private static int turn = 1;
	private  int myTurn = 0;
	private static int N = 10;


	public EvenOddThreadPrint(boolean isOdd) {
		myTurn = isOdd ? 1 : 0;
	}

	public void run() {
		synchronized (EvenOddThreadPrint.class) {
			while(count<=N) {
				while (turn != myTurn) {
					try {
						EvenOddThreadPrint.class.wait();
					} catch (InterruptedException e) {

					}
				}
				System.out.println(Thread.currentThread().getName()+" - "+count);
				count++;
				turn = 1 - turn;
				EvenOddThreadPrint.class.notifyAll();
				try {
					EvenOddThreadPrint.class.wait();
				} catch (InterruptedException e) {

				}
			}
		}
	}
}
