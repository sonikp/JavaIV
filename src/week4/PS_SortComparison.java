package week4;

// parallelSort

import java.time.Duration;
import java.time.Instant;
import java.text.NumberFormat;
import java.util.Arrays;
import java.security.SecureRandom;

public class PS_SortComparison {
	
	public static void main(String[] args) {
		
		SecureRandom random = new SecureRandom();
		
		// create array of random into, then copy it
		int[] array1 = random.ints(15_000_000).toArray();
		int[] array2 = new int[array1.length];
		System.arraycopy(array1, 0, array2, 0, array1.length);
		
		// time the sorting of array with Arrays method sort
		System.out.println("Starting sort");
		Instant sortStart = Instant.now();
		Arrays.sort(array1);
		Instant sortEnd = Instant.now();
		
		// display timing results
		long sortTime = Duration.between(sortStart, sortEnd).toMillis();
		System.out.printf("Total time in milliseconds: %d%n%n", sortTime);
		
		
		
		
	}
}	// end class SortComparison 


// deitel / pearsons case number: 01582962 

















