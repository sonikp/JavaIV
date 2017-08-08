package week4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class RL_SynchronizedBuffer implements RL_Buffer {
	
	// lock to control synchronization with this buffer
	private final Lock accessLock = new ReentrantLock();
	
	// conditions to control reading and writing
	private final Condition canWrite = accessLock.newCondition();
	private final Condition canRead = accessLock.newCondition();
	
	private int buffer = -1;
	private boolean occupied = false;
	
	// place int value into buffer
	public void blockingPut(int value) throws InterruptedException {
		
		accessLock.lock();
		
		// output thread info and buffer info, then wait
		try {
			
			// while buffer is not empty, place thread in waiting state
			while(occupied) {
				
				System.out.println("Producer tries to write.");
				displayState("Buffer full. Producer waits.");
				canWrite.await(); 		// wait until buffer is empty
			}
			
			buffer = value;	// set new buffer value
			
			// indicate producer cannot store another value until consumer retreives current buffer value
			occupied = true;
			
			displayState("Producer writes " + buffer);
			
			// signal and threads waiting to read from buffer
			canRead.signalAll();
		}
		finally {
			accessLock.unlock(); 	// unlock this object
		}
	}
	
	// return value from buffer
	public int blockingGet() throws InterruptedException {
		
		int readValue = 0;	// initialize value read from buffer
		accessLock.lock();	// lock this object
		
		// output thread info and buffer info, then wait
		try {
			
			// if threre is no data to read, place thread in waiting state
			while(!occupied) {
				
				System.out.println("Consumer tries to read.");
				displayState("Buffer empty. Consumer waits.");
				canRead.await();
			}
			
			// indicate that producer can store another value because consumer just retrieved buffer value
			occupied = false;
			
			readValue = buffer; 	// retrieve value from buffer
			displayState("Consumer reads " + readValue);
			
			canWrite.signalAll();
		}
		finally {
			
			accessLock.unlock();
		}
		
		return readValue;
	}
	
	// display current operation and buffer states
	private void displayState(String operation) {
		
		try {
			accessLock.lock(); 		// lock this object
			System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, occupied); 
		}
		finally {
			accessLock.unlock();
		}
	}
	
}







