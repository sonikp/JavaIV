package a_a;





import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.ExecutionException;


public class KK_App_Main_START extends JFrame {
	
	// application objects
	private KK_ServerAppGUI serverapp; 
//	private String localhost = "127.0.0.1";
//	private KK_ClientGUI_App kkclientapp;
	private KK_ClientGUI_App kkclientapp;
	
	// components for starting the Knock Knock server application
	private final JPanel startServerJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
//	private final JTextField startServerJTextField = new JTextField();
	private final JButton startServerJButton = new JButton("Start Server");
	private final JButton stopServerJButton = new JButton("Stop Server");
	private final JLabel startServerJLabel = new JLabel();
	
	// components for starting the Knock Knock client application
	private final JPanel startClientThreadJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
	private final JButton startClientJButton = new JButton("Start Client");
	private final JButton stopClientJButton = new JButton("Stop Client");	//Logout
	private boolean shutdownClient;
	
	private Thread serverThread;
	
	// constructor
	public KK_App_Main_START() throws IOException {
		
		super("KnockKnock Launch Application");
		
		setLayout(new GridLayout(2, 1, 10, 10));
		
		// add GUI components to the SwingWorker
		startServerJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Launch Server"));
		startServerJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Start SERVER button pressed");
				serverapp = new KK_ServerAppGUI(4445);
				serverapp.serverStartStop();
				
			}
		});	// end anonymous inner class, and end call to addActionListener
		
		stopServerJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("stop SERVER button pressed");
				serverapp.serverStartStop();	
				
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
				
			}
		}); // end anonymous inner class, and end call to addActionListener
		
		stopClientJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("Client STOP button pressed");
				System.out.println(event);
				

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
						
						kkclientapp = new KK_ClientGUI_App();
//						client.setLocation(1500,100);
//						client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//						client.setVisible(true);


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
//				System.out.println("replaced serverSocket.close()");

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
		
		KK_App_Main_START application = new KK_App_Main_START();
		application.setLocationRelativeTo(null);
		application.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}	// end class 

















