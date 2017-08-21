package aKKMulti.UI;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KnockKnockClient extends JFrame implements ActionListener {
    
	Socket kkSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = null;
    String fromServer;
    String fromUser;
//	private JTextField hostField;
//	private JTextField portField;
	private JTextField responseField;
//	private JButton connectButton;
//	private JButton quitButton;
	
	private JTextArea displayArea;
	
    public KnockKnockClient() throws IOException {
    	super("Super Knock-Knock Client");
    	
		responseField = new JTextField("who's there?");
		responseField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					sendResponse(responseField.getText());
					
					displayArea.append("\nClient: " + e.getActionCommand() + "\n"); //responseField.getText()
					out.println(responseField.getText());
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				responseField.setText("");
//				fromUser = responseField.getText();
//				fromUser = e.getActionCommand();
				/*
				try {
					stdIn = new BufferedReader(new InputStreamReader(System.in, e.getActionCommand()));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					fromUser = stdIn.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
//				displayArea.append("\nClient: " + fromUser + "\n");
//    			out.println(fromUser);
////		        out.flush();
//		        responseField.setText("");
				
			}
		});
		
		add(responseField, BorderLayout.SOUTH);
		
		displayArea = new JTextArea();
		add(new JScrollPane(displayArea));
    
        
        // showing the window on the screen
		setSize(400, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	connectToServer();
    	getJoke();
    	
    }
    
    public void connectToServer() throws IOException {
    	
        try {
            kkSocket = new Socket("127.0.0.1", 4444);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }
    	
    }
    
    public void sendResponse(String responseFeild) throws IOException {
    	String fromUser = responseFeild;

    	//        kkSocket = new Socket("127.0.0.1", 4444);
    	//        PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);	//getOutputStream()

    	if (fromUser != null) {
    		System.out.println("Client: " + fromUser);
    		displayArea.append("Client: " + fromUser  + "\n");
    		out.println(fromUser.toString());
    	}
    }
    
    public void getJoke() throws IOException {
    	
    	stdIn = new BufferedReader(new InputStreamReader(System.in));

    	while ((fromServer = in.readLine()) != null) {
    		System.out.println("Server: " + fromServer);
    		displayArea.append("Server: " + fromServer + "\n");
    		if (fromServer.equals("Bye.")) {
    			closeConnection();
    			break;
    		}
    			

    		fromUser = stdIn.readLine();		//stdIn
    		if (fromUser != null) {
    			System.out.println("Client: " + fromUser);
    			displayArea.append("Client: " + fromUser  + "\n");
    			out.println(fromUser);
    		}
    
    	}

    }
    
    public void closeConnection() throws IOException {
        out.close();
        in.close();
        stdIn.close();
        kkSocket.close();
    }
	
	public static void main(String[] args) throws IOException {
		
		KnockKnockClient client = new KnockKnockClient();
		client.setLocation(1500,100);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setVisible(true);


    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
