package zaa_copy;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class zKK extends JFrame {
	

	
	
	public zKK () {
		super("KK Application");
		
        setTitle("My Gui");
        setSize(400, 400);

        // Create JButton and JPanel
        JButton button = new JButton("Click here!");
        JPanel panel = new JPanel();

        // Add button to JPanel
        panel.add(button);
        // And JPanel needs to be added to the JFrame itself!
        this.getContentPane().add(panel);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		

		setSize(275, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		zKK application = new zKK();
		application.setLocation(1500, 100);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}