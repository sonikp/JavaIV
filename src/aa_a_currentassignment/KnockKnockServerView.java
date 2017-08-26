package aa_a_currentassignment;

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

public class KnockKnockServerView extends JFrame {
	
	// Application System components (MVC)
	private KnockKnockServerController threadServerController;
	private KnockKnockClient client;
	
	
	private JTextField serverTextField;	// inputs message from user
	private JTextArea displayAreaServer;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	

	
	public KnockKnockServerView() throws IOException {
		super("KK Multi Server");
		
		threadServerController = new KnockKnockServerController(null);

		serverTextField = new JTextField();
		serverTextField.setEditable(false);

		serverTextField.addActionListener(new ActionListener() {

			@Override	// send message to client
			public void actionPerformed(ActionEvent e) {

				sendData(e.getActionCommand());
				serverTextField.setText(threadServerController.getName());

			}
		});	// end enterField actionListener

		add(serverTextField, BorderLayout.SOUTH);

		displayAreaServer = new JTextArea();
		displayAreaServer.append("Knock Knock Server started"
				+ "\nListening on port 4444 (\"need to call validator\")?\n");
		displayAreaServer.append("Server Running = " + threadServerController.isServerRunningStatus());
		add(new JScrollPane(displayAreaServer), BorderLayout.CENTER);
		
		setSize(400, 300);
		setLocation(1500,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void sendData(String message) {
		
	}
	
}