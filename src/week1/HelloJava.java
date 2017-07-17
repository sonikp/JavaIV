package week1;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloJava {
	public static void main(String[] args) {
		//System.out.println("Hello, Java!");
		JFrame frame = new JFrame( "Hello, Java!");
		JLabel label = new JLabel( "Hello, Java!", JLabel.CENTER);
		//frame.add(label);	// first version
		frame.add( new HelloComponent());
		frame.setLocationRelativeTo(null);
		frame.setSize( 300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}