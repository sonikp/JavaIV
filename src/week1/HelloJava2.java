package week1;


import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;


// file: HelloJava2.java

public class HelloJava2
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame( "HelloJava2" );
		frame.add( new HelloComponent2("Hello, Java!"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}

class HelloComponent2 extends JComponent implements MouseMotionListener
{
	// instance variables
	String theMessage;
	int messageX = 125, messageY = 95;	// Coordinates of the message
	
	// constructor
	public HelloComponent2( String message ) {
		theMessage = message;
		addMouseMotionListener(this);
	}
	
	public void paintComponent( Graphics g ) {
		g.drawString( theMessage, messageX, messageY );
	}
	
	public void mouseDragged(MouseEvent e) {
		// Save the mouse coordinates and paint the message
		messageX = e.getX();
		messageY = e.getY();
		repaint();
	}

	public void mouseMoved(MouseEvent e) {}
}
