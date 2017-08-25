package aa_bkup_old1;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSideApplication extends JFrame {
	

	// set up multi-threaded fields
	private ServerSocket serverSocket;
	private Thread serverThread;
	private boolean shutdownServer = false;
	KKMultiServerThread connect;
	KnockKnockClient client;
	
	////////Server side display fields//////
	private JFrame serverApp;
	private JTextArea serverDisplayArea;

	
	
	private final JButton serverStart;
	private final JButton serverStop;
	private final JButton clientStart;
	private final JButton clientStop;
	
	private final ExecutorService clientProcessingPool = Executors.newCachedThreadPool();
	private boolean shutdownClient;
	


	public ServerSideApplication() {
		super("Knock-Knock Application Launcher");		
	
		setLayout(new GridLayout(2, 1, 10, 10));
		
		serverStart = new JButton("SERVER = Start");
		serverStop = new JButton("SERVER = Stop");
		clientStart = new JButton("CLIENT = Connect");
		clientStop = new JButton("CLIENT = Disconnect");
		
		serverStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent startSvr) {
				
				// https://stackoverflow.com/questions/15541804/creating-the-serversocket-in-a-separate-thread
				System.out.println("server START button clicked");
				setShutdownServer(false);
				
				try {
					startServer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		
		serverStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent startSvr) {
				
				
				//https://stackoverflow.com/questions/10630737/how-to-stop-a-thread-created-by-implementing-runnable-interface
				System.out.println("server STOP button clicked");
				setShutdownServer(true);
				System.out.println("server shutdown selected = " + getShutdownServer());
				
				try {
					startServer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		
		clientStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent startSvr) {
				
				System.out.println("client CONNECT button clicked");
							
				try {
					startClient();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		});
		
		clientStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent startSvr) {
				
				System.out.println("client DISCONNECT button clicked");


			}
		});
		
		add(serverStart);
		add(serverStop);
		add(clientStart);
		add(clientStop);
		
		setSize(400, 300);
		setVisible(true);
		
	}
	
	    	
public void startClient() throws IOException {

	Runnable clientTask = null;

	if (!shutdownClient) {

		clientTask = new Runnable() {

			@Override
			public void run() {
				try {
					
					KnockKnockClient client = new KnockKnockClient();
					client.setLocation(1500,100);
					client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					client.setVisible(true);


				} catch (IOException e) {
					System.err.println("Unable to process client request");
					e.printStackTrace();
				}
			}
		};


	}
	else {
		System.out.println("!!!enter shutdown mode, shutdownServer = " + shutdownServer);

		try {

			clientProcessingPool.shutdown();

			try {
				serverSocket.close();
			}
			catch (SocketException ex) {
				ex.printStackTrace();
			}

		}
		catch (SocketException ex) {
			ex.printStackTrace();
		}
		finally {
			
			// currently empty
		}
	}

	serverThread = new Thread(clientTask);
	serverThread.start();

}  		
	    		
	
    public void startServer() throws IOException {
    	
    	Runnable serverTask = null;
    	
    	if (!shutdownServer) {
    		
    		serverTask = new Runnable() {
               	
            	@Override
            	public void run() {
            		try {
            			           			
            			KKMultiServerUI GUI = new KKMultiServerUI();
            			GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            			connect = new KKMultiServerThread(null);
            			connect.serverConnection();
        				
        			} catch (IOException e) {
        				System.err.println("Unable to process client request");
        				e.printStackTrace();
        			}
            	}
    		};
           		
    		
    	}
    	else {
    		System.out.println("!!!enter shutdown mode, shutdownServer = " + shutdownServer);

	        try {
	       
        	
	        	clientProcessingPool.shutdown();
	        	
	            
	            try {
	            	serverSocket.close();
	            }
	            catch (SocketException ex) {
	            	ex.printStackTrace();
	            }
	           
	        }
	        catch (SocketException ex) {
	        	ex.printStackTrace();
	        }
	        finally {
	        	// nothing here at the moment

	        }
 
    	}

        
        serverThread = new Thread(serverTask);
        serverThread.start();

    }


	public Boolean getShutdownServer() {
        return shutdownServer;
    }

    public void setShutdownServer(Boolean shutdownServer) {
        this.shutdownServer = shutdownServer;
    } 
	

	
}