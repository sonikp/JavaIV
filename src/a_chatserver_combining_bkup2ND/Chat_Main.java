package a_chatserver_combining_bkup2ND;


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

public class Chat_Main extends JFrame {
	
	// application objects
	private ServerGUI serverapp; // = new ServerGUI(4444);
	private String localhost = "127.0.0.7";
	private ClientGUI clientapp; // = new ClientGUI(localhost, 4444);
	
	// components for starting the Knock Knock server application
	private final JPanel startServerJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
//	private final JTextField startServerJTextField = new JTextField();
	private final JButton startServerJButton = new JButton("Start Server");
	private final JButton stopServerJButton = new JButton("Stop Server");
	private final JLabel startServerJLabel = new JLabel();
	
	// components for starting the Knock Knock client application
	private final JPanel startClientThreadJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
	private final JButton startClientJButton = new JButton("Start Client");
	private final JButton stopClientJButton = new JButton("Logout");
	
	// constructor
	public Chat_Main() {
		
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
				serverapp = new ServerGUI(4445);
				serverapp.serverStartStop();
//				System.out.println(serverapp.getServerState());

				
				// create a task to perform calculation in background
//				SW_BackgroundCalculator task = new SW_BackgroundCalculator(n, fibonacciJLabel);
//				task.execute();
				
			}
		});	// end anonymous inner class, and end call to addActionListener
		
		stopServerJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("stop SERVER button pressed");
				serverapp.serverStartStop();
				

				
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
		startClientJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				
				System.out.println("Start CLIENT button pressed");
				
				clientapp = new ClientGUI(localhost, 4444);
				
				clientapp.clientStartStop(event);
				

				

			}
		}); // end anonymous inner class, and end call to addActionListener
		
		stopClientJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println(event);
				
				

				clientapp.clientStartStop(event);
				

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
		
		Chat_Main application = new Chat_Main();
		application.setLocationRelativeTo(null);
		application.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}	// end class 

















