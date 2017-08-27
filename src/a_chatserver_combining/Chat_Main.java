package a_chatserver_combining;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import aa_a_currentassignment.KnockKnockClient;

import java.awt.Color;
import java.util.concurrent.ExecutionException;

public class Chat_Main extends JFrame {
	
	// application objects
	private ServerGUI serverapp; // = new ServerGUI(4444);
	private String localhost = "127.0.0.7";
	private ClientGUI clientapp; // = new ClientGUI(localhost, 4444);
	private KnockKnockClient kkclientapp;	
	
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
	private boolean shutdownClient;
	
	private Thread serverThread;
	
	// constructor
	public Chat_Main() throws IOException {
		
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
				
				try {
					startClient();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				try {
//					clientapp = new ClientGUI(localhost, 4444);
//					clientapp.clientStartStop(event);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
//				clientapp = new ClientGUI(localhost, 4444);
				
				
				
//				try {
//					kkclientapp = new KnockKnockClient();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				kkclientapp.clientStartStop(event);
//				try {
//					kkclientapp = new KnockKnockClient();
//					kkclientapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}	
//				
//				
//				
//				try {
//		
//					kkclientapp.setLocation(1500, 100);
//					kkclientapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					kkclientapp.setVisible(true);
//					kkclientapp.connectToServer();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
		
				

				

			}
		}); // end anonymous inner class, and end call to addActionListener
		
		stopClientJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println(event);
				
				

//				clientapp.clientStartStop(event);
				

			}
		}); // end anonymous inner class, and end call to addActionListener
		
		startClientThreadJPanel.add(startClientJButton);
		startClientThreadJPanel.add(stopClientJButton);
		
		add(startServerJPanel);
		add(startClientThreadJPanel);
		setSize(275, 200);
		setVisible(true);
	}	// end constructor
	
	
	public void startClient() throws IOException {

		Runnable clientTask = null;

		if (!shutdownClient) {

			clientTask = new Runnable() {

				@Override
				public void run() {
					try {
						
						KnockKnockClient client = new KnockKnockClient();
						client.setLocation(1500,100);
						client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						client.setVisible(true);


					} catch (IOException e) {
						System.err.println("Unable to process client request");
						e.printStackTrace();
					}
				}
			};


		}
		else {
			System.out.println("!!!enter shutdown mode, shutdownServer = " );	//+ shutdownServer
			
//			clientProcessingPool.shutdown();
			
			try {

//				clientProcessingPool.shutdown();
//				threadServerController.closeConnections();
				System.out.println("replaced serverSocket.close()");

//				try {
////					serverSocket.close();
//				}
//				catch (SocketException ex) {
//					System.out.println("enter printstacktrace()");
//					ex.printStackTrace();
//				}

			}
			finally {
				
				// currently empty
			}
		}

		serverThread = new Thread(clientTask);
		serverThread.start();

	} 
	
	// main method begins
	public static void main(String[] args) throws IOException {
		
		Chat_Main application = new Chat_Main();
		application.setLocationRelativeTo(null);
		application.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}	// end class 

















