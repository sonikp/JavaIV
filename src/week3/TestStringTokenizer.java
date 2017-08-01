package week3;

import java.util.Scanner;
import java.util.StringTokenizer;

public class TestStringTokenizer {
	
	public static void main(String[] args) {
		
		String delims = ",";
		String splitString = "one,two,,three,four,,five";
		
		System.out.println("StringTokenizer Exampe: \n");
		
		StringTokenizer strtkn = new StringTokenizer(splitString, delims);
		while(strtkn.hasMoreTokens()) {
			System.out.println("StringTokenizer Output: " + strtkn.nextElement());
		}
		
		System.out.println("\n\nSplitExample: \n");
		String[] tokens = splitString.split(delims);
		int tokenCount = tokens.length;
		for (String myValue :  tokens ) {
			System.out.println(myValue);
		}
		
		System.out.println("\n\n=============================\n\n");
		
		String msg = "This program gives sample code for String Tokenizer";
        StringTokenizer st = new StringTokenizer(msg," ");
        while(st.hasMoreTokens()){
            System.out.print(st.nextToken() + ":\t:");
        }
		
        System.out.println("\n\n=============================\n\n");
		
        String ipAddr = "http://10.123.43.67:80";
        StringTokenizer stAddr = new StringTokenizer(ipAddr, "://.", true);
        while(stAddr.hasMoreTokens()) {
        	System.out.print(stAddr.nextToken() + "\t");
        }
		
	}
	
}