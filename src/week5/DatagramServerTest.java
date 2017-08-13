package week5;

import javax.swing.JFrame;

public class DatagramServerTest {
	
	public static void main(String[] args) {
		
		DatagramServer application = new DatagramServer();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.waitForPackets();
	}
}