package week1;

import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Random;

// test for policy manager
// # java -Djava.security.manager -Djava.security.policy=test.policy EvilEmpire

public class Test {
	
	static Random rand = new Random();
	
	public static int[] generateRandomSortedArray(int maxArraySize, int maxValue) {
		int arraySize = 1 + rand.nextInt(maxArraySize);
		int[] randomArray = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			randomArray[i] = rand.nextInt(maxValue);
		}
		Arrays.sort(randomArray);
		return randomArray;
	}
	
	
	public static void main(String[] args) {
		int maxArraySize = 	Integer.MAX_VALUE / 517;	// Integer.MAX_VALUE / 1024;	// Integer.MAX_VALUE
		int maxValue = Integer.MAX_VALUE;	// Integer.MAX_VALUE
		int experiments = 1000;
		int[] testArray = null;
		int target = 0;
		int returnValue;
		
		System.out.println("Starting.....");
		System.out.println("ArraySize: " + maxArraySize);
		
		while (experiments-- > 0) {
			testArray = generateRandomSortedArray(maxArraySize, maxValue);
			if (rand.nextBoolean( )) {
				target = testArray[rand.nextInt(testArray.length)];
			} else {
				target = rand.nextInt( );
			}
//			returnValue = Util.binarySearch(testArray, target);
//			assertTheory1(testArray, target, returnValue);
//			assertTheory2(testArray, target, returnValue);
		}
		
		for (int i = 0; i < testArray.length; i++){
			 System.out.println("Element at index " + testArray[i]);
		}
		
		System.out.println("target: " + target);
		
		System.out.println(".....Completed");
	}

}
