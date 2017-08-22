package aKKMulti.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KKMultiServerUI extends JFrame {
	
	private JTextField serverTextField;	// inputs message from user
	private JTextArea displayAreaServer;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	KKMultiServerThread threadServer;
	
	public KKMultiServerUI() throws IOException {
		super("KK Multi Server");
		
		threadServer = new KKMultiServerThread(null);

		serverTextField = new JTextField();
		serverTextField.setEditable(false);

		serverTextField.addActionListener(new ActionListener() {

			@Override	// send message to client
			public void actionPerformed(ActionEvent e) {

				sendData(e.getActionCommand());
				serverTextField.setText(threadServer.getName());

			}
		});	// end enterField actionListener

		add(serverTextField, BorderLayout.SOUTH);

		displayAreaServer = new JTextArea();
		displayAreaServer.append("Who's there?\n");
		add(new JScrollPane(displayAreaServer), BorderLayout.CENTER);


		
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void sendData(String message) {
		
	}
	
}