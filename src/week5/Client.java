package week5;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {
	
	private JTextField enterField;		
	private JTextArea displayArea;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String chatServer;
	private Socket client;
	
	// constructor:: initialize chatServer and set up GUI
	public Client(String host) {
		
		super("Client");
		
		chatServer = host;	// set server to which this client connects
		
		enterField = new JTextField();
		enterField.setEditable(false);
		enterField.addActionListener(new ActionListener() {
			
			@Override	// send message to server
			public void actionPerformed(ActionEvent e) {
				sendData(e.getActionCommand());
				enterField.setText("");
				
			}
		});	// end enterField actionListener
		
		add(enterField, BorderLayout.NORTH);
		
		displayArea = new JTextArea();
		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		setSize(300, 150);
		setVisible(true);
	}	// end constructor
	
	// connect to server and process messages from server
	public void runClient() {
		
		try {
			connectToServer();
			getStreams();
			processConnection();
		}
		catch (EOFException eofEx) {
			displayMessage("\nClient Terminated connection");
		}
		catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
		finally {
			closeConnection();	// 
		}
	}
	
	// connect to server
	private void connectToServer() throws IOException {
		
		displayMessage("Attempting connection\n");
		
		// create socket to make connection to server
		client = new Socket(InetAddress.getByName(chatServer), 12345);
		
		// display connection info
		displayMessage("Connected to: " + client.getInetAddress().getHostName());
	}
	
	// get streams to send and receive data
	private void getStreams() throws IOException {
		
		// set up output stream for object
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();		// flush output buffer to send header info
		
		// set up input stream for objects
		input = new ObjectInputStream(client.getInputStream());
		
		displayMessage("\nGot I/O streams\n");
	}
	
	// process connection with server
	private void processConnection() throws IOException {
		
		// enable enterField so client user can send messages
		setTextFieldEditable(true);
		
		// process messages sent from server
		do {
			try {
				message = (String) input.readObject();	// read new message
				displayMessage("\n" + message);	
			}
			catch (ClassNotFoundException classNotFoundException) {
				displayMessage("\nUnknown object type received");
			}
		} while (!message.equals("SERVER>>> TERMINATE"));
	}
	
	// close streams and sockets
	private void closeConnection() {
		displayMessage("\nClosing connection");
		setTextFieldEditable(false);
		
		try {
			output.close();
			input.close();
			client.close();
		}
		catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	}
	
	// send message to server
	private void sendData(String message) {
		
		try {
			output.writeObject("CLIENT>>> " + message);
			output.flush();
			displayMessage("\nCLIENT>>> " + message);
		}
		catch (IOException ioEx) {
			displayArea.append("\nError writing object");
		}
	}
	
	// manipulates displayArea in the event-dispatch tread
	private void displayMessage(final String messageToDisplay) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayArea.append(messageToDisplay);
			}
		});	
	}
	
	// manipulates enterField in the event-dispatch thread
	private void setTextFieldEditable(final boolean editable) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				enterField.setEditable(editable);
			}
		});
	}
	
	
}	// end class Client


































