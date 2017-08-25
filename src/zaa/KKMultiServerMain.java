package zaa;

import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JFrame;

public class KKMultiServerMain {
    
	ServerSocket serverSocket = null;
	boolean listening = true;
	

	public KKMultiServerMain() throws IOException {
		KKMultiServerUI GUI = new KKMultiServerUI();
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// RB: calling this from the constructor DOES work here
		KKMultiServerThread connect = new KKMultiServerThread(null);
		connect.serverConnection();
		

		

	}



	public static void main(String[] args) throws IOException {


		KKMultiServerMain app = new KKMultiServerMain();
		System.out.println("Main");



    }
}
