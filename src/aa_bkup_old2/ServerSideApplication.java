package aa_bkup_old2;



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
	
//	private final JPanel gamePanelMain;
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
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

//				startServerJFrame();
				
				try {
					startServer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*
				 
				terminates listening port
	
				fuser -k 8008/tcp
				
				*/
				
				
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
//					startClientJPane();
					startCLI();
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
	

	
	
	public void startServerJFrame() {
		System.out.println("serverStart()");
    	serverApp = new JFrame("this is the SERVER");
    	serverDisplayArea = new JTextArea();
    	serverDisplayArea.setEditable(false);

    	serverApp.add(serverDisplayArea, BorderLayout.CENTER );
    	serverDisplayArea.setText( "Server starting.....\n" );
    	
    	
    	serverApp.setSize(400, 300);
    	serverApp.setVisible(true);
    	serverApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	serverApp.setLocation(1500, 400);
	}
	
	
	public void startClientJPane() throws IOException{
		
		KnockKnockClient client = new KnockKnockClient();
		client.setLocation(1500,100);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setVisible(true);
		
//		
//		
//		System.out.println("client started");
//    	Socket socketConnectTo = null;
////    	System.out.println("remoteAddress = " + clientSocket.getRemoteSocketAddress());
//		try {
//			socketConnectTo = new Socket("127.0.0.1", 8008);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        BufferedReader input = null;
//		try {
//			input = new BufferedReader(new InputStreamReader(socketConnectTo.getInputStream()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        String answer = null;
//		try {
//			answer = input.readLine();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        JOptionPane.showMessageDialog(null, answer);
////        System.exit(0);
	}
	
//	public void startClient() throws IOException {
//
//		Runnable clientTask = null;
//
//		if (!shutdownClient) {
//			clientTask = new Runnable() {
//
//				@Override
//				public void run() {
//					try {   
//						KnockKnockClient client = new KnockKnockClient();
//						client.setLocation(1500,100);
//						client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//						client.setVisible(true);
//					}
//					catch (Exception ex) {
//						ex.printStackTrace();
//					}
//				}
//
//			};
//		}
//	}
//}
	    	
	    	
public void startCLI() throws IOException {

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

            			
            			// RB: calling this from the constructor DOES work here
            			connect = new KKMultiServerThread(null);
            			connect.serverConnection();
            			
            			
            			
//        				serverSocket = new ServerSocket(8008);
//        				System.out.println("Server started listening 8008");
//
//        				
//        				System.out.println("Waiting for clients to connect...");
//
//        				while (true) {
//        					clientSocket = serverSocket.accept();
//        					clientProcessingPool.submit(new ClientTask(clientSocket));
//        				}
        				
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
	        	



	        }

	        
    	}


 
        
        serverThread = new Thread(serverTask);
        serverThread.start();

    }

    private class ClientTask implements Runnable {
        private Socket clientSocket;

        private ClientTask(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            System.out.println("Got a client !");

            // Do whatever required to process the client's request
            try {
            	
        		KnockKnockClient client = new KnockKnockClient();
        		client.setLocation(1500,100);
        		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		client.setVisible(true);
            	
            	
//				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//				out.println(new Date().toString());
			} 
            catch (IOException e) {
				e.printStackTrace();
			}


            try {
            	System.out.println("closing client connection");
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    
	public Boolean getShutdownServer() {
        return shutdownServer;
    }

    public void setShutdownServer(Boolean shutdownServer) {
        this.shutdownServer = shutdownServer;
    } 
	

	
}