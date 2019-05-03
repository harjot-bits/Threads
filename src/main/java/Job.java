import java.util.ArrayList;
import java.util.List;

public class Job implements Runnable {
	//public static Action act = new Action(); //one thread execution at a time for test
	//public Action act = new Action(); //all threads will execute test at the same time
	//public Action act;
	private List<Action> lista = new ArrayList<Action>();
	public synchronized void run() {
		//try{
		//for(int i=0; i<100; i++) {
		//	System.out.println("Thread "+Thread.currentThread().getName()+" i "+i);

			//if(Thread.currentThread().getName().equals("Thread-0")) {
			//	System.out.println("Thread "+Thread.currentThread().getName()+" sleeping");
			//	Thread.sleep(2000); //will hold lock while sleeping
			//	System.out.println("Thread "+Thread.currentThread().getName()+" awake");
			//}

		//}}
		//catch (Exception e) {

		//}
		//Action act = new Action(); //all threads will execute test at the same time
		/**
		 *  ProvisioningAction action = (ProvisioningAction) Class
		 .forName("com.adobe.campaign.provisioningservice.action." + actionName
		 + "Action").newInstance();
		 */
		try {
			//act = (Action) Class.forName("Action").newInstance(); ////all threads will execute test at the same time
			Action act = (Action) Class.forName("Action").newInstance(); //all threads will execute test at the same time
			lista.add(act);
			act.test();
		}catch (Exception e) {
				System.out.println(e);
		}
	}
}
