package week5;

// test class for Tic-Tac-Toe client.

import javax.swing.JFrame;

public class TTTClientTest {
	
	public static void main(String[] args) {
		
		TTTClient application;	// declare client application
		
		// if no command line args
		if (args.length == 0) {
			application = new TTTClient("127.0.0.1");
		}
		else {
			application = new TTTClient(args[0]);
		}
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}