package aa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class KnockKnockServerController extends Thread {
	private Socket socket = null;
	private ServerSocket serverSocket = null;
	private boolean listening = true;


	private PrintWriter outputToClient;
	private BufferedReader inputFromClient;
	private String inputLine, outputLine;

	private KnockKnockServerProtocol kkp;

	public KnockKnockServerController(Socket socket) throws IOException {
		super("KKMultiServerThread");

		this.socket = socket;

	}

	public void serverConnection() throws IOException {

		try {
			serverSocket = new ServerSocket(4444);
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


			kkp = new KnockKnockServerProtocol();
			outputLine = kkp.processInput(null);
			outputToClient.println(outputLine);

			while ((inputLine = inputFromClient.readLine()) != null) {
				outputLine = kkp.processInput(inputLine);
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
		outputToClient.close();
		inputFromClient.close();
		socket.close();
		serverSocket.close();
	}
}
