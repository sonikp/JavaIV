package week4;

public class SB_UnsynchronizedBuffer implements SB_Buffer {
	
	private int buffer = -1;	// shared by producer and consumer threads
	
	// place value into buffer
	public void blockingPut(int value) throws InterruptedException {
		
		System.out.printf("Producer writes \t%2d", value);
		buffer = value;
	}
	
	// return value from buffer
	public int blockingGet() throws InterruptedException {
		
		System.out.printf("Consumer reads \t%d", buffer);
		return buffer;
	}
} // end class UnsynchronizedBuffer