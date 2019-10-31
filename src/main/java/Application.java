import DelayedScheduler.MyDelayedScheduler;
import ProducerConsumerWithLockAndCondition.Consumer;
import ProducerConsumerWithLockAndCondition.Producer;
import ReadersWriterProblem.*;
import ReadersWriterProblem.SharedBuffer;
import TimeBaseKeyValStore.CustomHashMap;

import java.time.Clock;
import java.time.LocalDateTime;

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
	/**public static void main(String[] args) {
		EvenOddThreadPrint evenThread = new EvenOddThreadPrint(false);
		EvenOddThreadPrint oddThread = new EvenOddThreadPrint(true);
		Thread t1  = new Thread(evenThread);
		Thread t2  = new Thread(oddThread);
		t1.start();
		t2.start();

	}**/
	//Generic HashMap
	/**public static void main(String[] args) {
		LocalDateTime a = LocalDateTime.now(Clock.systemUTC());

		System.out.println(a);
		GenericHashMap map = new GenericHashMap<Integer,Integer>();
		map.put(1,1);
		map.put(2,2);
		map.put(3,"ajjd");
		System.out.println(map.size());
		System.out.println(map.get(1));
		System.out.println(map.get(2));
		System.out.println(map.get(3));
		map.remove(3);
		System.out.println(map.size());
		map.put(2,4);
		System.out.println(map.get(1));
		System.out.println(map.get(2));
		System.out.println(map.get(3));

	}**/
    //Readers Writers
	/**public static void main(String[] args) {
		ReadersWriters r1 = new ReadersWriters(new String[] {"get","get","get","get","get","get" }, new int[]{});
		ReadersWriters r2 = new ReadersWriters(new String[] {"get","get","get","get","get","get" }, new int[]{});
		ReadersWriters w1 = new ReadersWriters(new String[] {"set","set","set","set","set","set" }, new int[]{1,2,3,4,5,6});
		ReadersWriters w2 = new ReadersWriters(new String[] {"set","set","set","set","set","set" }, new int[]{7,8,9,10,11,12});


		Thread t3= new Thread(w1);
		Thread t4= new Thread(w2);
		Thread t1= new Thread(r1);
		Thread t2= new Thread(r2);
		t3.start();
		t4.start();
		t1.start();
		t2.start();

	}**/
	//TimeBasedKeyValStore
	/**public static void main(String[] args) {
		final CustomHashMap map = new CustomHashMap(5); //5 mins


		Thread t1 = new Thread(new Runnable() {
			public void run() {
				map.put(1,5);
				map.put(2,1);
				System.out.println(map.get(1));
				System.out.println(map.get(2));
				System.out.println(map.getAvg());
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(40000);
				} catch (Exception e){

				}

				map.put(1,1);
				map.put(2,5);
				map.put(5,6);
				System.out.println(map.get(1));
				System.out.println(map.get(2));
				System.out.println(map.getAvg());
			}
		});
		t1.start();
		t2.start();



	}**/
	//Producer Consumer
	/**public static void main(String[] args) {
		SharedBuffer buffer = new SharedBuffer(3); //5 mins
		Producer producer = new Producer(buffer, 5);
		Consumer consumer = new Consumer(buffer, 5);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();



	}**/
	 //Producer Consumer With Lock and Condition
/**	 public static void main(String[] args) {
	 SharedBuffer buffer = new SharedBuffer(3); //5 mins
	 Producer producer = new Producer(buffer, 5);
	 Consumer consumer = new Consumer(buffer, 5);
	 Thread t1 = new Thread(producer);
	 Thread t2 = new Thread(consumer);
	 t1.start();
	 t2.start();



	 }**/
//readers writers
	/**public static void main(String[] args) {
		SharedBuffer buffer = new SharedBuffer(3); //5 mins
		Writer producer = new Writer(buffer, 5);
		Reader consumer1 = new Reader(buffer, 5);
		Reader consumer2 = new Reader(buffer, 5);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer1);
		Thread t3 = new Thread(consumer2);
		t1.start();
		t2.start();
		t3.start();



	}**/
	//Delayed Scheduler
	public static void main(String[] args) {
		MyDelayedScheduler scheduler = new MyDelayedScheduler();
		Runnable r1 = new Runnable() {
			public void run() {
				System.out.println("Hello");
			}
		};
		Runnable r2 = new Runnable() {
			public void run() {
				System.out.println("Hi");
			}
		};
		long time = System.currentTimeMillis();
		scheduler.delayedRun(time+2, r1);
		scheduler.delayedRun(time+1, r2);
		scheduler.delayedRun(time+5, r1);
		scheduler.delayedRun(time+7, r2);
	}


}
