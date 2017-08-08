package week4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ABQ_SharedBufferTest2 {
	
	public static void main(String[] args) throws InterruptedException {
		
		// create newCachedThreadPool
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// create synchronized buffer to store ints
		ABQ_Buffer sharedLocation = new ABQ_SynchronizedBuffer();
		
		System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation", "Buffer", "Occupied", "---------", "------\t\t--------");
		
		// execute the Producer and Consumer tasks
		executorService.execute(new ABQ_Producer(sharedLocation));
		executorService.execute(new ABQ_Consumer(sharedLocation));
		
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);
		
	}
	
	
}