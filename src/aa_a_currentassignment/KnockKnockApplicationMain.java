package aa_a_currentassignment;

import javax.swing.JFrame;

public class KnockKnockApplicationMain {
	
	
	
	public static void main(String[] args) {
		
		KnockKnockApplicationController application = new KnockKnockApplicationController();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setLocation(1500, 100);
		
	}
}