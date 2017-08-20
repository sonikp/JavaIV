package week5;

import javax.swing.JFrame;

public class DatagramServerMain {
	
	public static void main(String[] args) {
		
		DatagramServer application = new DatagramServer();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setLocation(1500, 100);
		application.waitForPackets();
	}
}