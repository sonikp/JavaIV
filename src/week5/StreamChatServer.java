package week5;

// server portion of a client/server stream-socket connection.

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class StreamChatServer extends JFrame {
	
	private JTextField enterField;	// inputs message from user
	private JTextArea displayArea;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private int counter = 1;
	
	// constructor :: set up GUI
	public StreamChatServer() {
		
		super("Server");
		
		enterField = new JTextField();
		enterField.setEditable(false);
		enterField.addActionListener(new ActionListener() {
			
			@Override	// send message to client
			public void actionPerformed(ActionEvent e) {
				
				sendData(e.getActionCommand());
				enterField.setText("");
				
			}
		});	// end enterField actionListener
		
		add(enterField, BorderLayout.NORTH);
		
		displayArea = new JTextArea();
		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		
		setSize(300, 150);
//		setLocationRelativeTo(null);
		setVisible(true);
	}	// end constructor
	
	// set-up and run server
	public void runServer() {
		
		// set up server to receive connection; process connection
		try {
			server = new ServerSocket(12345, 100);	
			
			while (true) {
				
				try {
					waitForConnection();	
					getStreams();
					processConnection();
				}
				catch (EOFException eofEx) {
					displayMessage("\nServer terminated connection");
				}
				finally {
					closeConnection();
					++counter;
				}
			}
		}
		catch (IOException ioEx) {
			ioEx.printStackTrace();
		}	// end try/catch
	}	// end method runServer
	
	// wait for connection to arrive, then display connection info
	private void waitForConnection() throws IOException {
		
		displayMessage("Waiting for connection\n");
		connection = server.accept();	// allows server to accept connection
		displayMessage("Connection " + counter + " received from: " + connection.getInetAddress().getHostName());
	}	// end waitForConnection
	
	// get streams to send and receive data
	private void getStreams() throws IOException {
		
		// set-up output stream for objects
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush(); 	// flush output buffer to send header info
		
		// set-up input stream for object
		input = new ObjectInputStream(connection.getInputStream());
		
		displayMessage("\nGot I/O streams\n");
	}	// end getStreams
	
	// process connection with client
	private void processConnection() throws IOException {
		
		String message = "Connection successful";
		sendData(message);
		
		// enable enterField so server user can send messages
		setTextFieldEditable(true);
		
		do {	// process messages sent from client
			try {
				message = (String) input.readObject(); // read new message
				displayMessage("\n" + message);
			}
			catch (ClassNotFoundException classNotFoundException) {
				displayMessage("\nUnknown object type received");
			}	// end try catch
		} while (!message.equals("CLIENT>>> TERMINATE"));
	}	// end processConnection
	
	// close stream and socket
	private void closeConnection() {
		
		displayMessage("\nTerminating connection \n");
		setTextFieldEditable(false);
		
		try {
			output.close();
			input.close();
			connection.close();
		}
		catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	}
	
	// send message to client
	private void sendData(String message) {
		// send object to client
		try {
			output.writeObject("SERVER>>> " + message);
			output.flush();	// flushing output to client
			displayMessage("\nSERVER>>> " + message);
		}
		catch (IOException ioEx) {
			displayArea.append("\nError writing object");
		}
	}
	
	// manipulates displayArea in the event-dispatch thread
	private void displayMessage(final String messageToDisplay) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {							// update displayArea
				displayArea.append(messageToDisplay);	// apppend message
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
	
}	// end class Server


































