package TimeBaseKeyValStore;
import java.sql.Timestamp;
public class MessageCus{
	int key;
	int val;
	long timestamp;
	public MessageCus(){
		this.timestamp = System.currentTimeMillis();
	}
}
