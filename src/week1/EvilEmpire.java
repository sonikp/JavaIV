package week1;

import java.net.*;

public class EvilEmpire {
	
	public static void main(String[] args) throws Exception {
	
		try {
			Socket s = new Socket("172.217.11.164", 80);
			System.out.println("Connected!");
		}
		catch (SecurityException e) {
			System.out.println("SecurityException: could not connect!");
		}
		
	}
	
}