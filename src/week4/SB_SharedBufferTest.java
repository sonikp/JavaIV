package week4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class SB_SharedBufferTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		// create new thread pool with two threads
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// create UnsynchronizedBuffer to store ints
		SB_Buffer sharedLocation = new SB_UnsynchronizedBuffer();
		
		System.out.println("Action\t\tValue\tSum of Produced\tSum of Consumed");
		      System.out.printf("------\t\t-----\t---------------\t---------------%n%n");
		
		// execute the Producer and Consumer, giving each access to the sharedLocation
		executorService.execute(new SB_Producer(sharedLocation));
		executorService.execute(new SB_Consumer(sharedLocation));
		
		executorService.shutdown();	// terminate app when task complets
		executorService.awaitTermination(1, TimeUnit.MINUTES);
	}
}	// end class SharedBufferTest