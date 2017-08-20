package aKKMulti.UI;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class KnockKnockClient extends JFrame {
    
    private Socket kkSocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private BufferedReader stdIn = null;
	private String fromServer;
	private String fromUser;
	private String message = "";
    
	private JTextField enterField;
	private JTextArea displayArea;
	
	
	
	public KnockKnockClient() {
		super("KnockKnockClient");
		
		enterField = new JTextField();
		enterField.setEditable(false);
		enterField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("entered action performed");
				sendData(e.getActionCommand());
				enterField.setText("");
				/*
				// TODO Auto-generated method stub
				try {
					connectToServer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
				
			}
		});
		
		add(enterField, BorderLayout.NORTH);

		displayArea = new JTextArea();
		add(new JScrollPane(displayArea));

		setSize(400, 300);
		setVisible(true);
		setLocation(1500,100);
	}
	
	// connect to server and process messages from server
		public void runClient() throws ClassNotFoundException {
			System.out.println("runClient()");
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
	
	public void connectToServer() throws IOException {
		System.out.println("Attempting connectToServer()");
		displayMessage("Attempting connection\n");

		// create socket to make connection to server
		try {
			kkSocket = new Socket("127.0.0.1", 4444);
			
			displayMessage("Connected to: " + kkSocket.getInetAddress().getHostName());
			
			
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: localhost.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: localhost.");
			System.exit(1);
		}
		
		// haven' edited this
		stdIn = new BufferedReader(new InputStreamReader(System.in));


		while ((fromServer = in.readLine()) != null) {
			System.out.println("Server: " + fromServer);
			if (fromServer.equals("Bye."))
				break;

			fromUser = stdIn.readLine();
			if (fromUser != null) {
				System.out.println("Client: " + fromUser);
				out.println(fromUser);
			}
		}

//		out.close();
//		in.close();
//		stdIn.close();
//		kkSocket.close();
	}
	
	// get streams to send and receive data
		private void getStreams() throws IOException {
			
			// set up output stream for object
			out = new PrintWriter(kkSocket.getOutputStream(), true);
			out.flush(); 		// flush output buffer to send header info
			
			// set up input stream for objects
			in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
			
			displayMessage("\nGot I/O streams\n");
			/*
			// set up output stream for object
			output = new ObjectOutputStream(client.getOutputStream());
			output.flush();		// flush output buffer to send header info
			
			// set up input stream for objects
			input = new ObjectInputStream(client.getInputStream());
			
			displayMessage("\nGot I/O streams\n");
			*/
		}
	
		// process connection with server
		private void processConnection() throws IOException, ClassNotFoundException {
			
			// enable enterField so client user can send messages
			setTextFieldEditable(true);
			
			// process messages sent from server
			do {
				// message = (String) input.readObject();	// read new message
				fromServer = in.readLine();
				displayMessage("\n" + fromServer);	// message
			} while (!message.equals("SERVER>>> TERMINATE"));
		}
	
		// close streams and sockets
		private void closeConnection() {
			displayMessage("\nClosing connection");
			setTextFieldEditable(false);
			
			try {
				out.close();
				in.close();
				stdIn.close();
				kkSocket.close();
//				output.close();
//				input.close();
//				client.close();
			}
			catch (IOException ioEx) {
				ioEx.printStackTrace();
			}
		}
		
		
		// send message to server
		private void sendData(String message) {
			
			try {
//				output.writeObject("CLIENT>>> " + message);
//				output.flush();
				
				fromUser = stdIn.readLine();
				displayMessage("\nCLIENT>>> " + fromUser);  //message
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
	
	public static void main(String[] args) throws IOException {
		
		KnockKnockClient clientapp = new KnockKnockClient();
		clientapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientapp.setLocation(1500, 400);


        
    }
}
