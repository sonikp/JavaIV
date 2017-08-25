package aa_bkup_old;

import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JFrame;

public class KKMultiServer {
    
	ServerSocket serverSocket = null;
	boolean listening = true;
	KnockKnockServerController connect;
	

	public KKMultiServer() throws IOException {
		
		KnockKnockServerView GUI = new KnockKnockServerView();
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// RB: calling this from the constructor DOES work here
		connect = new KnockKnockServerController(null);
		connect.serverConnection();
		

		

	}



	public static void main(String[] args) throws IOException {


		KKMultiServer app = new KKMultiServer();
		System.out.println("Main");



    }
}
