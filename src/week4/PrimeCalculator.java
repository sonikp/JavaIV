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
	
}


