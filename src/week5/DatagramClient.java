package week5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DatagramClient extends JFrame {
	
	private JTextField enterField;
	private JTextArea displayArea;
	private DatagramSocket socket;
	
	// constructor:: set-up GUI and DatagramSocket
	public DatagramClient() {
		super("Datagram Client");
		
		enterField = new JTextField("Type message here");
		enterField.addActionListener(new ActionListener() {
			
			@Override	// create and send packet
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get message from textfield
					String message = e.getActionCommand();
					displayArea.append("\nSending packet containing: " + message + "\n");
					
					byte[] data = message.getBytes();	// convert to bytes
					
					// create sendPacket
					DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
					
					socket.send(sendPacket);
					displayArea.append("Packet sent....\n");
					displayArea.setCaretPosition(displayArea.getText().length());
				}
				catch (IOException ioEx) {
					displayMessage(ioEx + "\n");
					ioEx.printStackTrace();
				}
			}
		});	// create packet listener event
		
		add(enterField, BorderLayout.NORTH);
		
		displayArea = new JTextArea();
		add(new JScrollPane(displayArea));
		
		setSize(400, 300);
		setVisible(true);
		
		// create DatagramSocket for sending and receiving packets
		try {
			socket = new DatagramSocket();
		}
		catch (SocketException sEx) {
			sEx.printStackTrace();
			System.exit(1);
		}
	}
	
	// wait for packets to arrive from Server, display packet contents
	public void waitForPackets() {
		
		while(true) {
			// receive packet and display contents
			try {
				byte[] data = new byte[100]; // set up a packet
				DatagramPacket receivePacket = new DatagramPacket(data, data.length);
				
				socket.receive(receivePacket);	// wait for packet
				
				// display info from received packet
				displayMessage("\nPacket received:" +
				"\nFrom host: " + receivePacket.getAddress() +
				"\nFrom port: " + receivePacket.getPort() +
				"\nLength:    " + receivePacket.getLength() +
				"\nContaining:\n\t" + new String(receivePacket.getData(), 0, receivePacket.getLength()));
			}
			catch (IOException ex) {
				displayMessage(ex + "\n");
				ex.printStackTrace();
			}
		}
	}	// end waitForPackets
	
	// manipulates displayArea in the event-dispatch thread
	private void displayMessage(final String messageToDisplay) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayArea.append(messageToDisplay);
			}
		});
	}
	
}	// end DatagramClient


































