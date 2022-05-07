import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SaloonController {

	public static BlockingQueue<Integer> chairs;
	public static BlockingQueue<Customer> customers;
	public Barber[] barbers;
	
	SaloonController(){
		customers = new LinkedBlockingQueue<Customer>(20);
		chairs = new LinkedBlockingQueue<Integer>(10);
		
		for(int i = 0; i<10; i++){
			try {
				chairs.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
