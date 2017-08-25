package ass3_test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerSideTest extends JFrame {
	
	
	private JButton serverStart;

	public ServerSideTest() {
		super("Knock-Knock Server Side");
		
		
		setLayout(new GridLayout(2, 1, 10, 10));
		
		serverStart = new JButton("SERVER = Start");
		serverStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent startSvr) {
				
				// https://stackoverflow.com/questions/15541804/creating-the-serversocket-in-a-separate-thread
				System.out.println("server START button clicked");

				startServerJFrame();
				
				
				/*
				 
				terminates listening port
	
				fuser -k 8008/tcp
				
				*/
				
				
			}
		});
		
		add(serverStart);



		
		setSize(400, 300);
		setVisible(true);

	}	// end constructor
	
	
	
	public void startServerJFrame() {
		System.out.println("serverStart()");
    	JFrame serverApp = new JFrame("this is the SERVER");
    	JTextArea serverDisplayArea = new JTextArea();
    	serverDisplayArea.setEditable(false);

    	serverApp.add(serverDisplayArea, BorderLayout.CENTER );
    	serverDisplayArea.setText( "Server awaiting connections.....\n" );
    	
    	
    	serverApp.setSize(400, 300);
    	serverApp.setVisible(true);
    	serverApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	serverApp.setLocation(1500, 400);
	}
	
	public static void main(String[] args) {
		
		ServerSideTest app = new ServerSideTest();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setLocation(1500, 100);
	}
}
	
