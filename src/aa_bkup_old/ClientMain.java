package aa_bkup_old;

import javax.swing.JFrame;

public class ClientMain extends JFrame {
	
	public static void main(String[] args) {
		
		ClientSideApplication clientApp = new ClientSideApplication();
		clientApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientApp.setLocation(1500, 100);
	
	}
	
}