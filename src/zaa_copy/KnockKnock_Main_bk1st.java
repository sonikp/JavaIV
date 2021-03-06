package zaa_copy;
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

public class KnockKnock_Main_bk1st extends JFrame {
	
	// components for starting the Knock Knock server application
	private final JPanel startServerJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
	private final JTextField startServerJTextField = new JTextField();
	private final JButton startServerJButton = new JButton("Start Server");
	private final JButton stopServerJButton = new JButton("Stop Server");
	private final JLabel startServerJLabel = new JLabel();
	
	// components for starting the Knock Knock client application
	private final JPanel startClientThreadJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
	private long n1 = 0;	// initialize first Fibonacci number
	private long n2 = 1;	// initialize second Fibonacci number
	private int count = 1;
	private final JLabel startClientJLabel = new JLabel("Fibonacci of 1: ");
	private final JLabel nFibonacciJLabel = new JLabel(String.valueOf(n2));
	private final JButton startClientJButton = new JButton("Start Client");
	private final JButton stopClientJButton = new JButton("Stop Client");
	
	// constuctor
	public KnockKnock_Main_bk1st() {
		
		super("KnockKnock Launch Application");
		setLayout(new GridLayout(2, 1, 10, 10));
		
		// add GUI components to the SwingWorker
		startServerJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Launch Server"));
//		startServerJPanel.add(new JLabel("Get Fibonacci of: "));
//		startServerJPanel.add(startServerJTextField);
		startServerJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Start SERVER button pressed");
				
//				int n;
//				
//				try {
//					// retrieve user's input as an integer
//					n = Integer.parseInt(startServerJTextField.getText());
//				}
//				catch (NumberFormatException ex) {
//					// display error if user did not enter an Integer
//					startServerJLabel.setText("Starting Server.");
//					return;
//				}
//				
//				// indicate that the calculation has begun
//				startServerJLabel.setText("Calculating....... ");
				
				// create a task to perform calculation in background
//				SW_BackgroundCalculator task = new SW_BackgroundCalculator(n, fibonacciJLabel);
//				task.execute();
				
			}
		});	// end anonymous inner class, and end call to addActionListener
		
		stopServerJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("stop SERVER button pressed");
				
//				int n;
//				
//				try {
//					// retrieve user's input as an integer
//					n = Integer.parseInt(startServerJTextField.getText());
//				}
//				catch (NumberFormatException ex) {
//					// display error if user did not enter an Integer
//					startServerJLabel.setText("Starting Server.");
//					return;
//				}
//				
//				// indicate that the calculation has begun
//				startServerJLabel.setText("Calculating....... ");
				
				// create a task to perform calculation in background
//				SW_BackgroundCalculator task = new SW_BackgroundCalculator(n, fibonacciJLabel);
//				task.execute();
				
			}
		});	// end anonymous inner class, and end call to addActionListener
		
		startServerJPanel.add(startServerJButton);
		startServerJPanel.add(startServerJLabel);
		startServerJPanel.add(stopServerJButton);
		
		// add GUI components to the event-dispatch thread panel
		startClientThreadJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Launch Clients"));
//		startClientThreadJPanel.add(startClientJLabel);
//		startClientThreadJPanel.add(nFibonacciJLabel);
		startClientJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				
				System.out.println("Start CLIENT button pressed");
				
//				// calculate the Fibonacci number after n2
//				long temp = n1 + n2;
//				n1 = n2;
//				n2 = temp;
//				++count;
//				
//				// display the next Fibonacci number
//				startClientJLabel.setText("Fibonacci of " + count + ": ");
//				nFibonacciJLabel.setText(String.valueOf(n2));
			}
		}); // end anonymous inner class, and end call to addActionListener
		
		stopClientJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				
				System.out.println("Stop CLIENT button pressed");
				
//				// calculate the Fibonacci number after n2
//				long temp = n1 + n2;
//				n1 = n2;
//				n2 = temp;
//				++count;
//				
//				// display the next Fibonacci number
//				startClientJLabel.setText("Fibonacci of " + count + ": ");
//				nFibonacciJLabel.setText(String.valueOf(n2));
			}
		}); // end anonymous inner class, and end call to addActionListener
		
		startClientThreadJPanel.add(startClientJButton);
		startClientThreadJPanel.add(stopClientJButton);
		
		add(startServerJPanel);
		add(startClientThreadJPanel);
		setSize(275, 200);
		setVisible(true);
	}	// end constructor
	
	// main method begins
	public static void main(String[] args) {
		
		KnockKnock_Main_bk1st application = new KnockKnock_Main_bk1st();
		application.setLocationRelativeTo(null);
		application.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}	// end class 

















