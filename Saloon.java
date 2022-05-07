import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * The Sleepy Saloon
 * @author Jobizzness
 *
 */
public class Saloon {
	
	public SaloonController controller;
	
	/**
	 * Use the constructor to generate the barbers
	 */
	public Saloon(){
		
		this.controller = new SaloonController();
		
		try {
			this.open();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * 
	 * @throws InterruptedException 
	 * 
	 */
	public void open() throws InterruptedException{
		
		System.out.println("======= Saloon: We are Open! ==========");
		
		this.getBarbers(3);
		
		Customer customers[] = new Customer[30];
		Semaphore sem = new Semaphore(1, true);
		// Generating customers to come to our saloon
		for (int i = 0; i<30; i++) {
			
			customers[i] = new Customer(i, sem);
			customers[i].start();
			
			TimeUnit.SECONDS.sleep((long) 0.9);
		}

	}
	
	/**
	 * Generate barbers and add it to
	 * our barbers collection.
	 * 
	 * @param count
	 * @throws InterruptedException 
	 */
	private void getBarbers(int count) throws InterruptedException {
		
		this.controller.barbers = new Barber[count];
		
		for (int i = 0; i<count; i++) {
			
			//I am a new barber
			this.controller.barbers[i] = new Barber(i);
			this.controller.barbers[i].start();
			
			//Sleep for awhile
			TimeUnit.SECONDS.sleep((long) 0.5);
			
			
		}
	}
	
	
	/**
	 * 
	 */
	public void close(){
		System.out.println("The Saloon is Close!");
	}
	
	
}
