package edu.ucsd.lecture5;

import java.net.*;

/**
 * Resolver
 * Usage: java Resolver URL
 * Example: java Resolver yahoo.com
 */
public class Resolver 
{ 
	public static void main( String args[] )
	{ 
		InetAddress ipAddr;
		
		//String url = "yahoo.com";
		try 
		{ 
			//ipAddr = InetAddress.getByName( url );
			ipAddr = InetAddress.getByName( args[0] );
			
			System.out.print( "IP address = " + ipAddr + "\n " ); 
		} 
		catch ( UnknownHostException ex )
		{ 
			System.out.println( "Unknown host " );
		}
	}
}