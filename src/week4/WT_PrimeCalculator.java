package week4;

// extends SwingWorker to compute prime numbers in a 'worker thread'

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class WT_PrimeCalculator extends SwingWorker<Integer, Integer> {
	
	private static final SecureRandom generator = new SecureRandom();
	private final JTextArea intermediateJTextArea;	// displays found primes
	private final JButton getPrimesJButton;
	private final JButton cancelJButton;
	private final JLabel statusJLabel;		//display status of calculation
	private final boolean[] primes;			// boolean array for finding primes
	
	public WT_PrimeCalculator(int max, JTextArea intermediateJTextArea, JLabel statusJLabel, JButton getPrimesJButton, JButton cancelJButton) {
		this.intermediateJTextArea = intermediateJTextArea;
		this.statusJLabel = statusJLabel;
		this.getPrimesJButton = getPrimesJButton;
		this.cancelJButton = cancelJButton;
		primes = new boolean[max];
		Arrays.fill(primes, true);	// initialize all primes elements to true
	} // end constructor
	
	// find all primes up to max using ths Sieve of Eratosthenes
	public Integer doInBackground() {
		
		int count = 0;	// the number of primes found
		
		// starting at the third value, cycle through the array and pull false as the value of any greater number that is a multiple 
		for (int i = 2; i < primes.length; i++) {
			if (isCancelled()) { // if calculation has been cancelled
				return count;
			}
			else {
				setProgress(100 * (i + 1) / primes.length);
				
				try {
					Thread.sleep(generator.nextInt(5));
				}
				catch (InterruptedException ex) {
					statusJLabel.setText("Worker thread interrupted");
					return count;
				}
				
				if (primes[i]) {	// if i is a prime
					publish(i);		// make i available for display in prime list
					
					for (int j = i + i; j < primes.length; j += i) {
						primes[j] = false;	// i is not prime
					}
				}
			}
		}
		
		return count;
		
	}	// end method doInBackground
	
	// displays published values in primes list
	protected void process(List<Integer> publishedVals) {
		for (int i = 0; i < publishedVals.size(); i++) {
			
			intermediateJTextArea.append(publishedVals.get(i) + "\n");
		}
	}
	
	// code to execute when doInBackground completed
	protected void done() {
		getPrimesJButton.setEnabled(true);
		cancelJButton.setEnabled(false);
		
		try {
			// retrieve and display doInBackground return value
			statusJLabel.setText("Found " + get() + " primes.");
		}
		catch (InterruptedException | ExecutionException | CancellationException ex) {
			statusJLabel.setText(ex.getMessage());
		}
	}
}













