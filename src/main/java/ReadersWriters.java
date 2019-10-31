import java.util.ArrayList;
import java.util.List;

public class ReadersWriters implements Runnable {
    String[] commands;
	int[] val;
	ReadersWritesStore store;

	public ReadersWriters(String[] commands, int[] val) {
    	this.commands = commands;
    	this.val = val;
    	store = new ReadersWritesStore();
	}
	public void run() {
		for(int i=0; i<commands.length; i++){
			if(commands[i]=="get") {
				store.getStoreVal();
			} else {
				store.setStoreVal(val[i]);
			}
		}
	}
}
