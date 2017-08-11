package edu.ucsd.lecture5;

import java.net.*; 
import java.io.*;
public class URLConnectionReader { 
	public static void main(String[] args) throws Exception 
	{ 
		URL myJava = new URL("http://java.sun.com:80/docs/books/" 
				+ "tutorial/index.html#DOWNLOADING"); 
		URLConnection theConnection = myJava.openConnection(); 
		BufferedReader in = new BufferedReader( new InputStreamReader( theConnection.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) 
			System.out.println(inputLine); 
		in.close();
		System.in.read();  
	}
}