package ass3;



import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSideApplication extends JFrame {
	
//	private final JPanel gamePanelMain;
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Thread serverThread;
//	private boolean shutdown = false;

	


	
	
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
				startServer();
				
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
				stopServer();
				
				
				
			}
		});
		
		clientStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent startSvr) {
				
				System.out.println("client CONNECT button clicked");
				
				
				
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
	
	public void stopServer() {
		System.out.println("stopServer() clicked");
		
//		shutdown = true;
		
		 //Stop the executor service.
		clientProcessingPool.shutdownNow();
        try {
            //Stop accepting requests.
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error in server shutdown");
            e.printStackTrace();
        }
        System.exit(0);
		
	 }
	
    public void startServer() {


        Runnable serverTask = new Runnable() {
            // this doesn't do anything yet, it's an attempt to stop the server service listener
        	private Boolean stop = false;
        	
        	
        	@Override
        	public void run() {
        		System.out.println(stop);
        		
        		while (!stop) {

        			try {
        				serverSocket = new ServerSocket(8008);
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


        	}
        	
        	// these don't do anything yet, it's an attempt to stop the server service listener
        	public Boolean getStop() {
                return stop;
            }

            public void setStop(Boolean stop) {
                this.stop = stop;
            }   
        	
        };
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
	

	
}