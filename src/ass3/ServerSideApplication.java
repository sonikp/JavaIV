package ass3;



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

	
	////////Server side display fields//////
	private JFrame serverApp;
	private JTextArea serverDisplayArea;

	
	
	private final JButton serverStart;
	private final JButton serverStop;
	private final JButton clientStart;
	private final JButton clientStop;
	
	private final ExecutorService clientProcessingPool = Executors.newCachedThreadPool();
	


	public ServerSideApplication() {
		super("Knock-Knock Server Side");
		
	
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

				startServerJFrame();
				
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
					startClientJPane();
				} catch (ConnectException e) {
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
	
	
	public void startClientJPane() throws ConnectException{
		System.out.println("client started");
    	Socket s = null;
//    	System.out.println("remoteAddress = " + clientSocket.getRemoteSocketAddress());
		try {
			s = new Socket("127.0.0.1", 8008);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String answer = null;
		try {
			answer = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JOptionPane.showMessageDialog(null, answer);
//        System.exit(0);
	}
	
	   
	
	
	
    public void startServer() throws IOException {
    	
    	Runnable serverTask = null;
    	
    	if (!shutdownServer) {
    		
    		serverTask = new Runnable() {
               	
            	@Override
            	public void run() {
            		try {
        				serverSocket = new ServerSocket(8008);
        				// TODO later
//        				serverDisplayArea.setText( "Server listening on port 8008\n" );
//        				serverDisplayArea.setText( "Waiting for clients to connect....\n" );
        				
        				System.out.println("Waiting for clients to connect...");

        				while (true) {
        					clientSocket = serverSocket.accept();
        					clientProcessingPool.submit(new ClientTask(clientSocket));
        				}
        			} catch (IOException e) {
        				System.err.println("Unable to process client request");
        				e.printStackTrace();
        			}
            	}
    		};
//            		
    		
    	}
    	else {
    		System.out.println("!!!enter shutdown mode, shutdownServer = " + shutdownServer);

	        try {
	       
	            //Stop accepting requests.
	        	// RB: I can't figure out how to close this without generating a:
	        	// java.net.SocketException: Socket closed
//	        	
	        	clientProcessingPool.shutdown();
	        	
	            
	            try {
	            	serverSocket.close();
	            }
	            catch (SocketException ex) {
	            	ex.printStackTrace();
	            }
	            
//	        	System.out.println("request to stop clientSocket");
//	            clientSocket.close();
//	        	System.out.println("request to stop clientProcessingPool");
//	        	
//	        	clientProcessingPool.shutdownNow();
//				System.out.println( "SUCCESS? = " + clientProcessingPool.isShutdown());
//	        	



	           
	        }
	        catch (SocketException ex) {
	        	ex.printStackTrace();
	        }
	        finally {
	        	// RB: I would like to reuse this again, by stopping/starting the server
	        	// without closing the app
//	        	System.exit(0);
//	        	
//	        	System.out.println("request to stop clientSocket");
//	            clientSocket.close();
//	        	System.out.println("request to stop clientProcessingPool");
//	        	
//	        	clientProcessingPool.shutdownNow();
//				System.out.println( "SUCCESS? = " + clientProcessingPool.isShutdown());
//	        	



	        }
//	        System.exit(0);
	        
    	}


    	/*
        Runnable serverTask = new Runnable() {
            // this doesn't do anything yet, it's an attempt to stop the server service listener
        	
        	
        	
        	@Override
        	public void run() {
//        		
        		
        		while (!shutdownServer) {
        			System.out.println("enter run, shutdownServer = " + shutdownServer);
        			if (!shutdownServer) {
        				try {
            				serverSocket = new ServerSocket(8008);
            				// TODO later
//            				serverDisplayArea.setText( "Server listening on port 8008\n" );
//            				serverDisplayArea.setText( "Waiting for clients to connect....\n" );
            				System.out.println("Waiting for clients to connect...");

            				while (true) {
            					clientSocket = serverSocket.accept();
            					clientProcessingPool.submit(new ClientTask(clientSocket));
            				}


            			} catch (IOException e) {
            				System.err.println("Unable to process client request");
            				e.printStackTrace();
            			}
        			}
        			else {
        				if (shutdownServer) {
                			System.out.println("enter shutdown mode, shutdownServer = " + shutdownServer);
                			
//                			shutdown = true;
                			
                			 //Stop the executor service.
                			
                	        try {
                	            //Stop accepting requests.
                	            serverSocket.close();
                	            clientSocket.close();
                	            clientProcessingPool.shutdownNow();
                	        } catch (IOException e) {
                	            System.out.println("Error in server shutdown");
                	            e.printStackTrace();
                	        }
                	        System.exit(0);
                		}
        			}
        			

        		}
        		
        		


        	}
        	
        	// these don't do anything yet, it's an attempt to stop the server service listener
  
        	
        };
        */
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
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				out.println(new Date().toString());
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