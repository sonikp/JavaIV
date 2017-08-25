package aa_bkup_old2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientSideApplication extends JFrame {
	 /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
	
	
	public ClientSideApplication() {
		super("Client");
	}
	
    public static void main(String[] args) throws IOException {
        /*
    	String serverAddress = JOptionPane.showInputDialog(
            "Enter IP Address of a machine that is\n" +
            "running the date service on port 8008:");
        Socket s = new Socket(serverAddress, 8008);
        */
    	System.out.println("client started");
    	Socket s = new Socket("127.0.0.1", 8008);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);
        System.exit(0);
    }

}