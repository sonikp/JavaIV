package aKKMulti.UI2;

import javax.swing.JFrame;

public class StreamChatServerMain {
	
	public static void main(String[] args) {
		
		StreamChatServer application = new StreamChatServer();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.runServer();
		
	}
}