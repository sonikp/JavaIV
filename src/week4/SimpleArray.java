package week4;

import java.security.SecureRandom;
import java.util.Arrays;

// CAUTION: NOT THREAD SAFE
public class SimpleArray {
	
	private static final SecureRandom generator = new SecureRandom();
	private final int[] array;	// the shared integer array
	private int writeIndex = 0;
	
	// construct a SimpleArray of a given size
	public SimpleArray(int size) {
		
		array = new int[size];
		
	}
	
	// add a value to the shared array
	public synchronized void add(int value) {
		int position = writeIndex;	// store the write index
		
		try {
			// put thread to sleep for 0-499 ms
			Thread.sleep(generator.nextInt(500));
		}
		catch (InterruptedException ex) {
			Thread.currentThread().interrupt(); // re-interrupt the thread
		}
		
		// put value in the appropriate element
		array[position] = value;
		System.out.printf("%s wrote %2d to element %d.%n",  Thread.currentThread().getName(), value, position);
		
		++writeIndex;	// increment index of element to be written next
		System.out.printf("Next write index: %d%n", writeIndex);
	}

	@Override
	public synchronized String toString() {
		return Arrays.toString(array);
	}
	
	
	
	
	
}




















