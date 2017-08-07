package week4;

import java.security.SecureRandom;

public class SB_Consumer implements Runnable {
	
	private static final SecureRandom generator = new SecureRandom();
	private final SB_Buffer sharedLocation;	// reference to shared object
	
	// constructor
	public SB_Consumer(SB_Buffer sharedLocation) {
		
		this.sharedLocation = sharedLocation;
	}
	
	// read sharedLocation's value 10 times and sum the values
	public void run() {
		
		int sum = 0;
		
		for (int count = 1; count <= 10; count++) {
			// sleep 0 to 3 seconds, read value from buffer and add to sum
			try {
				Thread.sleep(generator.nextInt(3000));
				sum += sharedLocation.blockingGet();
				System.out.printf("\t\t\t%2d%n", sum);
			}
			catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		
		System.out.printf("%n%s %d%n%s%n", "Consumer read values totaling", sum, "Terminating Consumer");
	}
} // end class Consumer


















