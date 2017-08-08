package week4;

public class ABQ_CircularBuffer implements ABQ_Buffer {
	
	private final int[] buffer = {-1, -1, -1};
	
	private int occupiedCells = 0;
	private int writeIndex = 0;
	private int readIndex = 0;
	
	// place value into buffer
	public synchronized void blockingPut(int value) throws InterruptedException {
		
		// wait until buffer has space available, then write value
		// while no empty locations, place thread in blocked state
		while (occupiedCells == buffer.length) {
			
			System.out.printf("Buffer is full. Producer waits.%n");
			wait();
		}
		
		buffer[writeIndex] = value; // set new buffer value
		
		// update circular write index
		writeIndex = (writeIndex + 1) % buffer.length;
		
		++occupiedCells;	// one more buffer cell is full
		displayState("Producer writes " + value);
		notifyAll();
	}
	
	// return value from buffer
	public synchronized int blockingGet() throws InterruptedException {
		
		// wait until buffer has data, the read value;
		// while no data to read, place thread in waiting state
		while (occupiedCells == 0) {
			System.out.printf("Buffer is empty. Consumer waits.%n");
			wait();
		}
		
		int readValue = buffer[readIndex];	// read value from buffer
		
		// update circular read index
		readIndex = (readIndex + 1) % buffer.length;
		
		--occupiedCells;	// one fewer buffer cellse are occupied
		displayState("Consumer reads " + readValue);
		notifyAll();
		
		return readValue;
	}
	
	// display current operation and buffer state
	public synchronized void displayState(String operation) {
		
		// output operation and numbers of occupied buffer cells
		System.out.printf("%s%s%d)%n%s", operation, " (buffer cells occupied: ", occupiedCells, "buffer cells:  ");
		
		for (int value : buffer)
			System.out.printf(" %2d  ", value); // output values in buffer

		System.out.printf("%n               ");

		for (int i = 0; i < buffer.length; i++)
			System.out.print("---- ");

		System.out.printf("%n               ");
		
		for (int i = 0; i < buffer.length; i++) {
			
			if (i == writeIndex && i == readIndex) {
				System.out.print(" WR");	// both write and read index
			}
			else if (i == writeIndex) {
				System.out.print(" W   ");	// just write index
			}
			else if (i == readIndex) {
				System.out.print("  R  ");	// just read index
			}
			else {
				System.out.print("     "); // neither index
			}
		}
		
		System.out.printf("%n%n");
	      
	}
}


















