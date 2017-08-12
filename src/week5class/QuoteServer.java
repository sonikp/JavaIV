package week5class;

import java.io.IOException;

public class QuoteServer 
{ 
	public static void main(String[] args) throws IOException 
	{ 
		new QuoteServerThread().start(); 
	} 
}