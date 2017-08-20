package week5;

// test class for Tic-Tac-Toe client.

import javax.swing.JFrame;

public class TicTacToeClientMain {
	
	public static void main(String[] args) {
		
		TicTacToeClient application;	// declare client application
		
		// if no command line args
		if (args.length == 0) {
			application = new TicTacToeClient("127.0.0.1");
		}
		else {
			application = new TicTacToeClient(args[0]);
		}
		application.setLocation(1500, 400);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}