package week4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ABQ_CircularBufferTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		// create new thread pool with two threads
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// create CircularBuffers to store ints
		ABQ_CircularBuffer sharedLocation = new ABQ_CircularBuffer();
		
		// display the initial state of the CircularBuffers
		sharedLocation.displayState("Initial State");
		
		// execute the Producer and Consumer tasks
		executorService.execute(new ABQ_Producer(sharedLocation));
		executorService.execute(new ABQ_Consumer(sharedLocation));
		
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);
		
		
		
	}
}