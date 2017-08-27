package a_a_bkup1ST;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



/*
 * http://www.dreamincode.net/forums/topic/259777-a-simple-chat-program-with-clientserver-gui-optional/
 * 
 * The server as a GUI
 */
public class KK_ServerGUI extends JFrame implements ActionListener {	// , WindowListener
	
	private static final long serialVersionUID = 1L;
	// the stop and start buttons
	private JButton stopStart;
	// JTextArea for the chat room and the events
	private JTextArea chat, event;
	// The port number
	private JTextField tPortNumber;
	JPanel north;
	// my server
	private KK_Server server;
	private String portChat = "1500";
	private String portKK = "4444";
	
	//////////////////////////
	
	private JTextField serverTextField;	// inputs message from user
	private JTextArea displayAreaServer;
	
	
	// server constructor that receive the port to listen to for connection as parameter
	KK_ServerGUI(int port) {
		super("KK Server");
		System.out.println("ServerGUI constructor: " + port);
		knockKnockServer ();
//		chatServer();

	}	
	
	public void knockKnockServer () {
		server = null;
		// in the NorthPanel the PortNumber the Start and Stop buttons
		north = new JPanel();
		north.add(new JLabel("Port number: "));
		tPortNumber = new JTextField("  " + portKK);
		north.add(tPortNumber);
		// to stop or start the server, we start with "Start"
		stopStart = new JButton("Start");
		stopStart.addActionListener(this);
		north.add(stopStart);
		add(north, BorderLayout.NORTH);
		
		// the event and chat room
		JPanel center = new JPanel(new GridLayout(2,1));
		chat = new JTextArea(80,80);
		chat.setEditable(false);
		appendRoom("Chat room.\n");
		center.add(new JScrollPane(chat));
		event = new JTextArea(80,80);
		event.setEditable(false);
		appendEvent("Knock Knock Events log.\n");
		center.add(new JScrollPane(event));	
		add(center);
		
		// need to be informed when the user click the close button on the frame
//		addWindowListener(this);	// window listener
		setSize(400, 600);
		setVisible(true);

//		server = null;
//
//		serverTextField = new JTextField();
//		serverTextField.setEditable(false);
//
//		serverTextField.addActionListener(new ActionListener() {
//
//			@Override	// send message to client
//			public void actionPerformed(ActionEvent e) {
//
////				sendData(e.getActionCommand());
//				serverTextField.setText("Setting Text");		// threadServerController.getName()
//
//			}
//		});	// end enterField actionListener
//
//		add(serverTextField, BorderLayout.SOUTH);
//
//		displayAreaServer = new JTextArea();
//		displayAreaServer.append("Knock Knock Server started"
//				+ "\nListening on port 4444 (\"need to call validator\")?\n");
//		displayAreaServer.append("Server Running = " );		// + threadServerController.isServerRunningStatus()
//		add(new JScrollPane(displayAreaServer), BorderLayout.CENTER);
//
//		setSize(400, 300);
//		setLocation(1500,400);
//		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
//	public void chatServer() {
//		server = null;
//		// in the NorthPanel the PortNumber the Start and Stop buttons
//		north = new JPanel();
//		north.add(new JLabel("Port number: "));
//		tPortNumber = new JTextField("  " + portChat);
//		north.add(tPortNumber);
//		// to stop or start the server, we start with "Start"
//		stopStart = new JButton("Start");
//		stopStart.addActionListener(this);
//		north.add(stopStart);
//		add(north, BorderLayout.NORTH);
//		
//		// the event and chat room
//		JPanel center = new JPanel(new GridLayout(2,1));
//		chat = new JTextArea(80,80);
//		chat.setEditable(false);
//		appendRoom("Chat room.\n");
//		center.add(new JScrollPane(chat));
//		event = new JTextArea(80,80);
//		event.setEditable(false);
//		appendEvent("Events log.\n");
//		center.add(new JScrollPane(event));	
//		add(center);
//		
//		// need to be informed when the user click the close button on the frame
//		addWindowListener(this);
//		setSize(400, 600);
//		setVisible(true);
//	}
	

	// append message to the two JTextArea
	// position at the end
	void appendRoom(String str) {
		chat.append(str);
		chat.setCaretPosition(chat.getText().length() - 1);
	}
	void appendEvent(String str) {
		event.append(str);
		event.setCaretPosition(chat.getText().length() - 1);
		
	}
	
	// start or stop where clicked
	public void actionPerformed(ActionEvent e) {
		// if running we have to stop
		serverStartStop();

	}
	
	public void serverStartStop() {
		
		if(server != null) {
			server.stop();
			server = null;
			tPortNumber.setEditable(true);
			stopStart.setText("Start");
			return;
		}
      	// OK start the server	
		int port;
		try {
			port = Integer.parseInt(tPortNumber.getText().trim());
		}
		catch(Exception er) {
			appendEvent("Invalid port number");
			return;
		}
		// create a new Server
		server = new KK_Server(port, this);
		// and start it as a thread
		new ServerRunning().start();
		stopStart.setText("Stop");
		tPortNumber.setEditable(false);
		
	}
	
	// entry point to start the Server
//	public static void main(String[] arg) {
//		// start server default port 1500
////		int startupPort = ((int)portKK);
//		new ServerGUI(4444);	// 	1500
//	}

	/*
	 * If the user click the X button to close the application
	 * I need to close the connection with the server to free the port
	 */
	public void windowClosing(WindowEvent e) {
		// if my Server exist
		if(server != null) {
			try {
				server.stop();			// ask the server to close the conection
			}
			catch(Exception eClose) {
			}
			server = null;
		}
		// dispose the frame
		dispose();
		System.exit(0);
	}
	// I can ignore the other WindowListener method
//	public void windowClosed(WindowEvent e) {}
//	public void windowOpened(WindowEvent e) {}
//	public void windowIconified(WindowEvent e) {}
//	public void windowDeiconified(WindowEvent e) {}
//	public void windowActivated(WindowEvent e) {}
//	public void windowDeactivated(WindowEvent e) {}

	/*
	 * A thread to run the Server
	 */
	class ServerRunning extends Thread {
		public void run() {
//			server.startChat();         // should execute until if fails
			server.startKnockKnock();
			// the server failed
			stopStart.setText("Start");
			tPortNumber.setEditable(true);
			appendEvent("Server stopped, no longer listening\n");
			server = null;
		}
	}

}

