package week5;

import javax.swing.JFrame;

public class DatagramClientTest extends JFrame {
	
	public static void main(String[] args) {
		
		DatagramClient application = new DatagramClient();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.waitForPackets();
	}
}