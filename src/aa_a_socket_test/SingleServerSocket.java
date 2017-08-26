package aa_a_socket_test;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleServerSocket {
	
	static ServerSocket socket1;
	protected final static int port = 19999;
	static Socket connection;
	
	static boolean first;
	static StringBuffer process;
	static String TimeStamp;
	
	public static void main(String[] args) {
		
		try {
			
			socket1 = new ServerSocket(port);
			System.out.println("SingleSocketServer Initialized");
			int character;
			
			while (true) {
				
				connection = socket1.accept();
				
				BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
				
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				process = new StringBuffer();
				while((character = inputStreamReader.read()) != 13) {
					process.append((char)character);
				}
				System.out.println(process);
				
				// need to wait 10 seconds
				try{
					Thread.sleep(10000);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
				
				
				
				
			}
			
			
		}
		catch(Exception ex) {
			
		}
	}
	
	
}
