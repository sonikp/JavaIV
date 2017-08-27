package a_chatserver_combining_bkup3RD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class KnockKnockServerController extends Thread {
	
	private KnockKnockServerView view;
	
	private Socket socket = null;
	private ServerSocket serverSocket = null;
	private boolean listening = true;
	private boolean serverRunningStatus = false;




	private PrintWriter outputToClient;
	private BufferedReader inputFromClient;
	private String inputLine, outputLine;

	private KnockKnockServerProtocol knockKnockProtocol;

	public KnockKnockServerController(Socket socket) throws IOException {
		super("KKMultiServerThread");

		this.socket = socket;

	}

	public void serverConnection() throws IOException {

		try {
			serverSocket = new ServerSocket(4444);
			setServerRunningStatus(true);
			System.out.println("ServerStatusRunning = " + isServerRunningStatus());
			

		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(-1);
		}
		while (listening)
			new KnockKnockServerController(serverSocket.accept()).start();
			

	}



	public void run() {

		try {
			outputToClient = new PrintWriter(socket.getOutputStream(), true);
			inputFromClient = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));


			knockKnockProtocol = new KnockKnockServerProtocol();
			outputLine = knockKnockProtocol.processInput(null);
			outputToClient.println(outputLine);

			while ((inputLine = inputFromClient.readLine()) != null) {
				outputLine = knockKnockProtocol.processInput(inputLine);
				outputToClient.println(outputLine);
				if (outputLine.equals("Bye"))
					break;
			}

			closeConnections();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeConnections() throws IOException {
		
		System.out.println("closeConnections()");
		outputToClient.close();
		inputFromClient.close();
		socket.close();
		serverSocket.close();
	}
	
	// getters and setters
	public boolean isServerRunningStatus() {
		return serverRunningStatus;
	}

	public void setServerRunningStatus(boolean serverRunningStatus) {
		this.serverRunningStatus = serverRunningStatus;
	}
	
}
