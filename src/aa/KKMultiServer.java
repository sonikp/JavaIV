package aa;

import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JFrame;

public class KKMultiServer {
    
	ServerSocket serverSocket = null;
	boolean listening = true;
	KKMultiServerThread connect;
	

	public KKMultiServer() throws IOException {
		
		KKMultiServerUI GUI = new KKMultiServerUI();
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// RB: calling this from the constructor DOES work here
		connect = new KKMultiServerThread(null);
		connect.serverConnection();
		

		

	}



	public static void main(String[] args) throws IOException {


		KKMultiServer app = new KKMultiServer();
		System.out.println("Main");



    }
}
