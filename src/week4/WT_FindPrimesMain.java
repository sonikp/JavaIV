package week4;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class WT_FindPrimesMain extends JFrame {
	
	private final JTextField highestPrimeJTextField = new JTextField();
	private final JButton getPrimesJButton = new JButton("Get Primes");
	private final JTextArea displayPrimesJTextArea = new JTextArea();
	private final JButton cancelJButton = new JButton("Cancel!");
	private final JProgressBar progressJProgressBar = new JProgressBar();
	private final JLabel statusJLabel = new JLabel();
	private WT_PrimeCalculator calculator;
	
	// constructor
	public WT_FindPrimesMain() {
		
		super("Finding Primes with SwingWorker");
		setLayout(new BorderLayout());
		
		// initialize panel to get a number from the user
		JPanel northJPanel = new JPanel();
		northJPanel.add(new  JLabel("Find primes less than: "));
		highestPrimeJTextField.setColumns(5);
		northJPanel.add(highestPrimeJTextField);
		getPrimesJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				progressJProgressBar.setValue(0);	// reset
				displayPrimesJTextArea.setText(""); // clear JTextArea
				statusJLabel.setText("");			// clear JLabel
				
				int number;		// search for primes up through this value
				
				try {
					
					// get user input
					number = Integer.parseInt(highestPrimeJTextField.getText());
				}
				catch (NumberFormatException ex) {
					statusJLabel.setText("Enter an integer.");
					return;
				}
				
				// construct a new PrimeCalculator object
				calculator = new WT_PrimeCalculator(number, displayPrimesJTextArea, statusJLabel, getPrimesJButton, cancelJButton);
				
				// listen for progress bar property changes
				calculator.addPropertyChangeListener(new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						
						// if the changed property progresses, update the progress bar
						if (evt.getPropertyName().equals("progress")) {
							int newValue = (Integer) evt.getNewValue();
							progressJProgressBar.setValue(newValue);
						}
					}
				});	// end calculator changelistener
				
				// disable Get Primes button and enable Cancel button
				getPrimesJButton.setEnabled(false);
				cancelJButton.setEnabled(true);
				
				calculator.execute();		// execute the PrimeCalculator object
			}
		});	// end ActionListener
		
		northJPanel.add(getPrimesJButton);
		
		// add scrollable JList to display results of calculation
		displayPrimesJTextArea.setEditable(false);
		add(new JScrollPane(displayPrimesJTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		
		// initialize a panel to display cancelJButton
		// progressJProgressBar, and statusJLabel
		JPanel southJPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		cancelJButton.setEnabled(false);
		cancelJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				calculator.cancel(true);
				
			}
		});	// end cancel button listener
		
		southJPanel.add(cancelJButton);
		progressJProgressBar.setStringPainted(true);
		southJPanel.add(progressJProgressBar);
		southJPanel.add(statusJLabel);
		
		add(northJPanel, BorderLayout.NORTH);
		add(southJPanel, BorderLayout.SOUTH);
		setSize(550, 300);
		setVisible(true);
	}	// end constructor
	
	// main method begins program execution
	public static void main(String[] args) {
		WT_FindPrimesMain application = new WT_FindPrimesMain();
		application.setDefaultCloseOperation(EXIT_ON_CLOSE);
		application.setLocationRelativeTo(null);
	}
	
}	// end class WT_FindPrimes






















