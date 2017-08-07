package week4;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class PrintTaskExecutor {
	
	public static void main(String[] args) {
		
		// create and name each runnable
		PrintTask task1 = new PrintTask("task1");
		PrintTask task2 = new PrintTask("task2");
		PrintTask task3 = new PrintTask("task3");
		
		System.out.println("Starting Executor");
		
		// create ExecutorService to manage threads
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// start the three PrintTasks
		executorService.execute(task1);
		executorService.execute(task2);
		executorService.execute(task3);
		
		// shutdown the ExecutorService -- it decides when to shut down threads
		executorService.shutdown();
		
		System.out.printf("Tasks started, main ends.%n%n");
	}
}