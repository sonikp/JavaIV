package week5;

import javax.swing.JFrame;

public class TicTacToeServerMain {
	
	public static void main(String[] args) {
		
		TicTacToeServer application = new TicTacToeServer();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setLocation(1500, 100);
		application.execute();
	}
}