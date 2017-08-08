package week4;

// Buffer interface specifies methods called by Producer and Consumer.
public interface ABQ_Buffer
{
   // place int value into Buffer
   public void blockingPut(int value) throws InterruptedException; 

   // obtain int value from Buffer
   public int blockingGet() throws InterruptedException; 
} // end interface Buffer

