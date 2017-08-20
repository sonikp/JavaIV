package week5;

import javax.swing.JFrame;

public class StreamChatClientMain {
	
	public static void main(String[] args) {
		
		StreamChatClient application;
		
		// if no command line args
		if (args.length == 0 ) {
			application = new StreamChatClient("127.0.0.1");	// connection to localhost
		}
		else {
			application = new StreamChatClient(args[0]);
		}
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.runClient();
	}
}