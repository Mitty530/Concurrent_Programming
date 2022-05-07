import java.util.concurrent.Semaphore;

public class Customer extends Thread {

	public int ID;
	public boolean ready;
	public Semaphore controller;

	
	public Customer(int i, Semaphore sem) {
		this.ID = i;
		this.controller = sem;
	}
	
	 public void run() {
		 
		 try {
			 
			 while(this.ID != CustomerController.lastIn) Thread.sleep(500);
			 controller.acquire();
			 
			 System.out.println("Customer - " + this.ID + " walks in");
			 
			//Saloon is full, lets go back
			 if(this.saloonIsFull()) return;
			 
			 System.out.println("Customer - " + this.ID + " is standing.");
			 
			this.sit();
			SaloonController.customers.put(this);
			
			
		} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 
		 CustomerController.lastIn++;
		 controller.release();


	}

	 
	 
	private synchronized boolean saloonIsFull() {
		 //The saloon is full let's leave
		 if(SaloonController.customers.size() > 20){
			 System.out.println("Customer - " + this.ID + " Can't stay. Leaving now");
			 return true;
		 }
		 
		 return false;
	}
	

	private synchronized void sit() throws InterruptedException {
		//This will wait if no chairs are available
		SaloonController.chairs.take();
		System.out.println("Customer - " + this.ID + " Got a chair");
		 //We are ready now you can serve us
		 //this.ready = true;
	}
}
