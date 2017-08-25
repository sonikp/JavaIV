package aKKMulti.UI_copy_old;

import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JFrame;

public class KKMultiServer {
    
	ServerSocket serverSocket = null;
	boolean listening = true;
	

	public KKMultiServer() throws IOException {
		KKMultiServerUI GUI = new KKMultiServerUI();
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// RB: calling this from the constructor DOES work here
		KKMultiServerThread connect = new KKMultiServerThread(null);
		connect.serverConnection();
		

	}



	public static void main(String[] args) throws IOException {


		KKMultiServer app = new KKMultiServer();
		System.out.println("Main");



    }
}
