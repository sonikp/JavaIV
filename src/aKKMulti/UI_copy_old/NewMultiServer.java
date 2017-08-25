package aKKMulti.UI_copy_old;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;



public class NewMultiServer extends JFrame {
	
	private Socket socket;
	private ServerSocket serverSocket;


	public NewMultiServer(Socket socket) {
		super("NewMultiServer");
		this.socket = socket;
		
		
		setSize(300, 150);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void runServer() throws IOException {
		
		
		
		serverSocket = new ServerSocket(4444);
		System.out.println("Waiting for clients to connect...");
		
		while (true) {
			new NewMultiServer(serverSocket.accept()).start();		// KKMultiServerThread
			
		}
	}

	private void start() {
		// TODO Auto-generated method stub
		
	}
	
}