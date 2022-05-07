
import java.awt.List;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Barber extends Thread {
	
	public int ID;
	
	public Barber(int i){
		ID = i;
		
	}
	
	 public void run() {
		    
		 Customer customer;
		 	// While we have customer
		 	// Lets work
		 
		 	while(true) {
		 		try {
		 			System.out.println("Barber- "+this.ID +" Is A sleep ");
		 			
					customer = (Customer) SaloonController.customers.take();
					System.out.println("Barber- "+this.ID +" Is Up and ready ");
					
					//We might need to wait for the customer
					//while(!customer.ready) customer.wait();
					
					System.out.println("Barber- "+this.ID +" Is serving Customer "+customer.ID);
					// Lets return the chair to the list
					SaloonController.chairs.put(1);
					
					TimeUnit.SECONDS.sleep(1);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 //this._fireBarberIsReady();
			 
		 }
		 
	}
	 
}
