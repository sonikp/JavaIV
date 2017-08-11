package edu.ucsd.lecture5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class QuoteClient
{ 
	public static void main(String[] args) throws IOException 
	{

		if (args.length != 1) 
		{ 
			System.out.println("Usage: java QuoteClient <hostname>"); 
			return; 
		} DatagramSocket socket = new DatagramSocket();

		// send request 
		byte[] buf = new byte[256]; 
		InetAddress address = InetAddress.getByName(args[0]); 
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
		socket.send(packet);
		// get response 
		packet = new DatagramPacket(buf, buf.length); 
		socket.receive(packet);
		// display response
		String received = new String(packet.getData(), 0); 
		System.out.println("Quote of the Moment: " + received);
		socket.close();
	}
}
