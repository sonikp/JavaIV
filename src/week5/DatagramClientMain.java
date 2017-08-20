package week5;

import javax.swing.JFrame;

public class DatagramClientMain extends JFrame {
	
	public static void main(String[] args) {
		
		DatagramClient application = new DatagramClient();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setLocation(1500, 400);
		application.waitForPackets();
	}
}