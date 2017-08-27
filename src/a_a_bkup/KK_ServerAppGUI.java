package a_a_bkup;

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



public class KK_ServerAppGUI extends JFrame implements ActionListener {	// , WindowListener
	
//	private static final long serialVersionUID = 1L;
	// the stop and start buttons
	private JButton stopStart;
	// JTextArea for the chat room and the events
	private JTextArea event;	// chat, event;
	// The port number
	private JTextField tPortNumber;
	JPanel north;
	JPanel center;
	// my server
	private KK_ServerApp server;
//	private String portChat = "1500";
	private int portKK = 4444;
	
	//////////////////////////
	
	private JTextField serverTextField;	// inputs message from user
	private JTextArea displayAreaServer;
	
	
	// server constructor that receive the port to listen to for connection as parameter
	KK_ServerAppGUI(int port) {
		super("KK Server");
		System.out.println("ServerGUI constructor: " + port);
		knockKnockServer ();
//		chatServer();

	}	
	
	public void knockKnockServer () {
		server = null;
		// in the NorthPanel the PortNumber the Start and Stop buttons
		north = new JPanel();
//		north.add(new JLabel("Port number: "));
		north.add(new JLabel("Start/Stop Server: "));
		tPortNumber = new JTextField("  " + portKK);
//		north.add(tPortNumber);
		// to stop or start the server, we start with "Start"
		stopStart = new JButton("Start");
		stopStart.addActionListener(this);
		north.add(stopStart);
		add(north, BorderLayout.NORTH);
		
		// the event and chat room
		JPanel center = new JPanel(new GridLayout(1,1));	// new GridLayout(2,1)	2,1
//		chat = new JTextArea(80,80);
//		chat.setEditable(false);
//		appendRoom("Chat room.\n");
//		center.add(new JScrollPane(chat));
		event = new JTextArea();	// 80,80
		event.setEditable(false);
		appendEvent("Knock Knock Events log.\n");
		center.add(new JScrollPane(event));	
		add(center);
		
		// need to be informed when the user click the close button on the frame
//		addWindowListener(this);	// window listener
		setSize(450, 400);	// setSize(400, 600);
		setVisible(true);


		
	}
	

	

	// append message to the two JTextArea
	// position at the end
//	void appendRoom(String str) {
////		chat.append(str);	// chat.append(str);
////		chat.setCaretPosition(chat.getText().length() - 1);	// chat.setCaretPosition(chat.getText().length() - 1);
//	}
	
	// append message to the JTextArea at the end position
	void appendEvent(String str) {
		event.append(str);
		event.setCaretPosition(event.getText().length() - 1);	// 		event.setCaretPosition(chat.getText().length() - 1);
		
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
		server = new KK_ServerApp(portKK, this);		
		// and start it as a thread
		new ServerRunning().start();
		stopStart.setText("Stop");
		tPortNumber.setEditable(false);
		
	}
	


	// server thread
	class ServerRunning extends Thread {
		public void run() {
			server.startKnockKnock();
			// the server failed
			stopStart.setText("Start");
			tPortNumber.setEditable(true);
			appendEvent("Server stopped, no longer listening\n");
			server = null;
		}
	}

}

