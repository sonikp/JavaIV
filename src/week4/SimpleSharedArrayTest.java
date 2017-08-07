package week4;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleSharedArrayTest {
	
	public static void main(String[] args) {
		
		// construct the shared object
		SimpleArray simpleSharedArray = new SimpleArray(6);
		
		// create two tasks to write to the shared SimpleArray
		SimpleArrayWriter writer1 = new SimpleArrayWriter(1, simpleSharedArray);
		SimpleArrayWriter writer2 = new SimpleArrayWriter(11, simpleSharedArray);
		
		// execute the tasks with an ExecutorService
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(writer1);
		executorService.execute(writer2);
		
		executorService.shutdown();
		
		try {
			// wait 1 minute for both writers to finish executing
			boolean tasksEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);
			
			if (tasksEnded) {
				System.out.printf("%nContents of SimpleArray:%n");
				System.out.println(simpleSharedArray);
			}
			else {
				System.out.println("Timed out while waiting for tasks to finish.");
			}	
		} 
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	} // end main
}	// end class SimpleSharedArrayTest






























