package week4;

import java.security.SecureRandom;

public class SB_Producer implements Runnable {
	
	private static final SecureRandom generator = new SecureRandom();
	private final SB_Buffer sharedLocation;	// reference to shared object
	
	// constructor
	public SB_Producer(SB_Buffer sharedLocation) {
		
		this.sharedLocation = sharedLocation;
		
	}
	
	// store value from 1 to 10 in sharedLocation
	public void run() {
		
		int sum = 0;
		
		for (int count = 1; count <= 10; count++) {
			// sleep 0 to 3 seconds, then place value in Buffer
			try {
				int randomSleep = generator.nextInt(3000);
				Thread.sleep(randomSleep);
				sharedLocation.blockingPut(count);
				sum += count;	// increment sum of values
				System.out.printf("\t%2d%n", sum);
				
			}
			catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		
		System.out.printf("Producer done producing%nTerminating Producer%n");
	}
}	// end class Producer
















