package a_atest;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import a_a.KK_ClientGUI_App;
import a_a.KK_ServerAppGUI;

public class test extends JFrame {

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
//	private final JLabel startServerJLabel = new JLabel();

	// components for starting the Knock Knock client application
	private final JPanel startClientThreadJPanel = new JPanel(new GridLayout(2, 2, 5, 5));
	private final JButton startClientJButton = new JButton("Start Client");
	private final JButton stopClientJButton = new JButton("Stop Client");	//Logout
	private boolean shutdownClient;

	private Thread serverThread;


	public test() {
		super("KnockKnock Launch Application");

		setLayout(new GridLayout(2, 1, 10, 10));

		// add GUI components to the SwingWorker
		startServerJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Launch Server"));
		startServerJButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Start SERVER button pressed");
//				serverapp = new KK_ServerAppGUI(4445);
//				serverapp.serverStartStop();

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
//		startServerJPanel.add(startServerJLabel);
		startServerJPanel.add(stopServerJButton);

		// add GUI components to the event-dispatch thread panel
		startClientThreadJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Launch Clients"));
		startClientJButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				System.out.println("Start CLIENT button pressed");

//				try {
////					startClient();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

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
	}


	public static void main(String[] args) {

		test t = new test();

	}


}