package week5;

// connectionless client/server computing with datagrams

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class DatagramServer extends JFrame {
	
	private JTextArea displayArea;
	private DatagramSocket socket;
	
	// constructor:: set-up GUI and DatagramSocket
	public DatagramServer() {
		
		super("Datagram Server");
		
		displayArea = new JTextArea();
		add(new JScrollPane(displayArea), BorderLayout.CENTER);
		setSize(400, 300);
		setVisible(true);
		
		try {
			socket = new DatagramSocket(5000);
		}
		catch (SocketException socketException) {
			socketException.printStackTrace();
			System.exit(1);
		}
		System.out.println("end constructor");
	}	// end constructor
	
	// wait for packets to arrive, display data and echo packet to client
	public void waitForPackets() {
		
		while(true) {
			try {
				byte[] data = new byte[1000];	// set up a packet
				DatagramPacket receivePacket = new DatagramPacket(data, data.length);
				
				socket.receive(receivePacket);
				
				// display info from received packet
				displayMessage("\nPacket received:" +
				"\nFrom host: " + receivePacket.getAddress() +
				"\nFrom port: " + receivePacket.getPort() +
				"\nLength:    " + receivePacket.getLength() +
				"\nContaining:\n\t" + new String(receivePacket.getData(), 0, receivePacket.getLength()));
				
				sendPacketToClient(receivePacket);
			}
			catch (IOException ioEx) {
				displayMessage(ioEx + "\n");
				ioEx.printStackTrace();
			}
		}
	}	// end waitForPackets
	
	// echo packet to client
	private void sendPacketToClient(DatagramPacket receivePacket) throws IOException {
		
		displayMessage("\n\nEcho data to client.....");
		
		// create packet to send
		DatagramPacket sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getLength(),
				receivePacket.getAddress(), receivePacket.getPort());
		
		socket.send(sendPacket);
		displayMessage("Packet sent");
	}
	
	// manipulates displayArea in the event-dispatch thread
	private void displayMessage(final String messageToDisplay) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayArea.append(messageToDisplay);
			}
		});
	}
		
}	// end class DatagramServer

















