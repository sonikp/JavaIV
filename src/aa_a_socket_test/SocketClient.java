package aa_a_socket_test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

// https://edn.embarcadero.com/article/31995

public class SocketClient {
	
	
	public static void main(String[] args) throws IOException {
		
		// =============class elements==================
		
		// define host server
		String host = "localhost";
		// define port
		int port = 19999;
		
		StringBuffer inputStream = new StringBuffer();
		String TimeStamp;
		System.out.println("SocketClient initialized");
		
		try {
			
			// =============requesting a socket and establishing a connection==================
			
			// Obtain an address object of the server
			InetAddress address = InetAddress.getByName(host);
			System.out.println("name : " + InetAddress.getByName(host));
			
			// Establish socket connection
			Socket connection = new Socket(address, port);
			
			// =============writing to the socket==================
			
			// Instantiate a BufferedOutputStream object
			BufferedOutputStream bufferedStream = new BufferedOutputStream(connection.getOutputStream());
			
			// Instantiate an OutputStreamWriter object with the optional character encoding
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedStream, "US-ASCII");
			
			TimeStamp = new Date().toString();
			
			String process = "Calling the Socket Server on " + host + " port " + port +
					" at " + TimeStamp + (char) 13;
			
			// Write across the socket connection and flush the buffer
			outputStreamWriter.write(process);
			outputStreamWriter.flush();
			
			// =============reading from the socket==================
			
			BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
			
			// instantiate in inputStreamReader with the optional character encoding
			
			InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, "US-ASCII");
			
			// read the sockets inputstream and append to a StringBuffer
			
			int c;
			while ((c = inputStreamReader.read()) != 13 ) {
				inputStream.append((char) c);
			}
			
			connection.close();
			System.out.println(inputStream);
			
		}
		catch (IOException ex) {
			System.out.println("IOExecption: " + ex);
		}
		catch (Exception ex) {
			System.out.println("Exception: " + ex);
		}
		finally {
			System.out.println("Completed finally");
		}
				
	}

	
}