package week4;


import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class PrimeCalculator extends SwingWorker<Integer, Integer> {
	
	private static final SecureRandom generator = new SecureRandom();
	private final JTextArea imtermediateJTextArea;	// displays found primes
	private final JButton getPrimesJButton;
	private final JButton cancelJButton;
	private final JLabel statusJLabel;		//display status of calculation
	private final boolean[] primes;			// boolean array for finding primes
	
	public PrimeCalculator(int max, JTextArea imtermediateJTextArea, JLabel statusJLabel, JButton getPrimesJButton, JButton cancelJButton) {
		this.imtermediateJTextArea = imtermediateJTextArea;
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
		
		
	}	// end method doInBackground
	
	
}


