import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SaloonControl {
	
	protected static int chairs = 10;
	protected boolean busy = false;
	public Barber[] barbers;
	public static int numberOfCustomers = 0; //Shared property
	public static BlockingQueue customers = new ArrayBlockingQueue<Customer>(20); //Collection of all our customers
	
	
	synchronized void takeChair() throws InterruptedException {
		while (chairs==0) wait();
		
		//System.out.println("Customer - " + customer + " Has a chair");
        --chairs;
        notifyAll();
	}
	
	synchronized void dropChair() {
		//System.out.println("Customer - " + customer + " Has dropped a chair");
	        ++chairs;
	        notifyAll();
	}
	
	synchronized void register(int customer) throws InterruptedException {
		//this.customer = customer;
		takeChair();
			
		while(busy) wait();

	   this.busy = true;
        notifyAll();
        this.dropChair();

	   
		
		
	}
}
