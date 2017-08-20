package ass3;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerSideApplication extends JFrame {
	
//	private final JPanel gamePanelMain;
	
	protected PrintWriter socket;

	public ServerSideApplication() {
		super("Knock-Knock Server Side");
		
//		gamePanelMain = new JPanel(new GridLayout(2, 2, 5, 5));
//		gamePanelMain.setBorder(new TitledBorder(new LineBorder(Color.black), "Start Server"));
		
		setLayout(new GridLayout(2, 1, 10, 10));
		
		JButton startServer = new JButton("SERVER = Start");
		JButton stopServer = new JButton("SERVER = Stop");
		JButton startClient = new JButton("CLIENT = Start");
		JButton stopClient = new JButton("CLIENT = stop");
		
		startServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent startSvr) {
				
				System.out.println("server start button clicked");
				
				try {
					ServerSocket listener = new ServerSocket(8008);
					System.out.println("server socket listening 8008");
					
					while (true) {
						Socket socket = listener.accept();
						
						try {
							PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
							out.println(new Date().toString());
						}
						finally {
							System.out.println("server socket connection closed");
							socket.close();
						}
						
					}
					
				} 
				catch (IOException e) {
					e.printStackTrace();
				} 
				finally {
					socket.close();
				}
				
			}
		});
		
		add(startServer);
		add(stopServer);
		add(startClient);
		add(stopClient);
		
		setSize(400, 300);
		setVisible(true);
		
	}
}