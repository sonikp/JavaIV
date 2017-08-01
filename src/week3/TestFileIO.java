package week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestFileIO {
	
	public static void main(String[] args) throws FileNotFoundException {
		File numFile = new File("numbers.txt");
		
		if (numFile.exists()) {
			System.out.println(numFile.length());
		}
		
		PrintWriter smileyOutStream = new PrintWriter(new FileOutputStream("smiley.txt"));
		File smileyFile = new File("smiley.txt");
		if (smileyFile.canWrite()) {
			PrintWriter smileyOutStreamtwo = new PrintWriter(new FileOutputStream(smileyFile));
		}
		
		// Alternative with Scanner, instead of BufferedReader w. FileReader then StringTokenizer
		
//		Scanner inFile =  new Scanner(System.in);
		Scanner inFile = new Scanner(new File("numbers.txt"));
		int number = 7;
		while (inFile.hasNextInt()) {
			number = inFile.nextInt();
		}
		
	}
}