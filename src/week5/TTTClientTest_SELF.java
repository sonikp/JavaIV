package week5;

// test class for Tic-Tac-Toe client.

import javax.swing.JFrame;

public class TTTClientTest_SELF {
	
	public static void main(String[] args) {
		
		TTTClient_SELF application;	// declare client application
		
		// if no command line args
		if (args.length == 0) {
			application = new TTTClient_SELF("127.0.0.1");
		}
		else {
			application = new TTTClient_SELF(args[0]);
		}
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}