package week4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RL_SharedBufferTest2 {
	
	public static void main(String[] args) throws InterruptedException {
		
		// create new thread pool with two threads
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// create SynchronizedBuffer to store ints
		RL_Buffer sharedLocation = new RL_SynchronizedBuffer();
		
		System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation", "Buffer", "Occupied", "---------", "------\t\t--------");
		
		// execute the Producer and Consumer tasks
		executorService.execute(new RL_Producer(sharedLocation));
		executorService.execute(new RL_Consumer(sharedLocation));
		
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);
		
	}
}