package week4;
// using SwingWorker to perform long calculations

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.util.concurrent.ExecutionException;

public class SW_FibonacciNumbers extends JFrame {
	
	// components for calculating the Fibonacci of a user-entered number
	private final JPanel workerJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
	private final JTextField numberJTextField = new JTextField();
	private final JButton goJButton = new JButton("Go!");
	private final JLabel fibonacciJLabel = new JLabel();
	
	// components and variables for getting the next Fibonacci number
	private final JPanel eventThreadJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
	private long n1 = 0;	// initialize first Fibonacci number
	private long n2 = 1;	// initialize second Fibonacci number
	private int count = 1;
	private final JLabel nJLabel = new JLabel("Fibonacci of 1: ");
	private final JLabel nFibonacciJLabel = new JLabel(String.valueOf(n2));
	private final JButton nextNumberJButton = new JButton("Next Number");
	
	// constuctor
	public SW_FibonacciNumbers() {
		
		super("Fibonacci Numbers");
		setLayout(new GridLayout(2, 1, 10, 10));
		
		// add GUI components to the SwingWorker
		workerJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "With SwingWorker"));
		workerJPanel.add(new JLabel("Get Fibonacci of: "));
		workerJPanel.add(numberJTextField);
		goJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int n;
				
				try {
					// retrieve user's input as an integer
					n = Integer.parseInt(numberJTextField.getText());
				}
				catch (NumberFormatException ex) {
					// display error if user did not enter an Integer
					fibonacciJLabel.setText("Enter an integer.");
					return;
				}
				
				// indicate that the calculation has begun
				fibonacciJLabel.setText("Calculating....... ");
				
				// create a task to perform calculation in background
				SW_BackgroundCalculator task = new SW_BackgroundCalculator(n, fibonacciJLabel);
				task.execute();
				
			}
		});	// end anonymous inner class, and end call to addActionListener
		
		workerJPanel.add(goJButton);
		workerJPanel.add(fibonacciJLabel);
		
		// add GUI components to the event-dispatch thread panel
		eventThreadJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Without SwingWorker"));
		eventThreadJPanel.add(nJLabel);
		eventThreadJPanel.add(nFibonacciJLabel);
		nextNumberJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				
				// calculate the Fibonacci number after n2
				long temp = n1 + n2;
				n1 = n2;
				n2 = temp;
				++count;
				
				// display the next Fibonacci number
				nJLabel.setText("Fibonacci of " + count + ": ");
				nFibonacciJLabel.setText(String.valueOf(n2));
			}
		}); // end anonymous inner class, and end call to addActionListener
		
		eventThreadJPanel.add(nextNumberJButton);
		
		add(workerJPanel);
		add(eventThreadJPanel);
		setSize(275, 200);
		setVisible(true);
	}	// end constructor
	
	// main method begins
	public static void main(String[] args) {
		
		SW_FibonacciNumbers application = new SW_FibonacciNumbers();
		application.setLocationRelativeTo(null);
		application.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}	// end class SW_FibonacciNumbers

















