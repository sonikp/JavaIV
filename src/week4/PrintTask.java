package week4;

import java.security.SecureRandom;

public class PrintTask implements Runnable {
	
	private static final SecureRandom generator = new SecureRandom();
	private final int sleepTime;	// random sleep time for threads
	private final String taskName;
	
	// constructor
	public PrintTask(String taskName) {
		this.taskName = taskName;
		
		// pick random sleep time between 0 & 5 secods
		sleepTime = generator.nextInt(5000);
	}
	
	// method run contains the code that a thread will execute
	@Override
	public void run() {
		// put thread to sleep for sleepTime amount of time
		try {
			System.out.printf("%s going to sleep for %d ms.%n", taskName, sleepTime);
			Thread.sleep(sleepTime);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
			Thread.currentThread().interrupt(); 	// re-interrupt the thread
		}
		
		// print task name
		System.out.printf("%s done sleeping%n", taskName);
	}
}	// end PrintTask.java