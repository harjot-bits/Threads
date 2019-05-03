public class Action {
	public void test() {
		synchronized(Action.class) { //only 1 thread at a time, thread belongs to different instances
			for (int i = 10; i < 100; i++) {
				System.out.println("Thread " + Thread.currentThread().getName() + " i " + i);
			}
		}
	}
}
