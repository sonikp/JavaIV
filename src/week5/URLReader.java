package edu.ucsd.lecture5;

import java.net.*;
import java.io.*;

public class URLReader 
{
	public static void main(String[] args) throws Exception 
	{
		URL myJava = new URL("http://www.ucsd.edu");
		BufferedReader in = new BufferedReader( new InputStreamReader( myJava.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close(); 
		System.in.read();  //WHY IS THIS HERE? 
	}
}