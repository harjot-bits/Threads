public class Application {

	/**public static void main(String[] args) {
		Job job = new Job();
		Job job2 = new Job();
		Thread t1 = new Thread(job);
		//Thread t2 = new Thread(job); //one thread execution at a time for run method
		Thread t2 = new Thread(job2); //all threads will execute at the same time
		t1.start();
		t2.start();
	 put("non", "none");
	 put("sone", "some");
	 put("trut", "truth");
	 put("flwer", "flower");
	 put("sooon", "soon");
	 put("mun", "moon");
	 put("sune", "sun");
	 put("kimi", "kim");
	 put("citty", "city");
	 put("neighbur", "neighbor");
	 put("phon", "phone");
	 put("don", "done");
	 put("cay", "cat");
	}**/
	/**public static void main(String[] args) {
		ClosestValApp app1 = new ClosestValApp(new String[] {"non", "sone", "trut", "flwer", "mun", "sune", "kimi", "don"});
		ClosestValApp app2 = new ClosestValApp(new String[] {"non", "sone", "trut", "sooon", "citty", "neighbur", "phon", "don", "cay"} );
		Thread t1 = new Thread(app1);
		Thread t2 = new Thread(app2);
		Thread t3 = new Thread(app1);
		Thread t4 = new Thread(app2);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}**/
	public static void main(String[] args) {
		EvenOddThreadPrint evenThread = new EvenOddThreadPrint(false);
		EvenOddThreadPrint oddThread = new EvenOddThreadPrint(true);
		Thread t1  = new Thread(evenThread);
		Thread t2  = new Thread(oddThread);
		t1.start();
		t2.start();

	}

}
