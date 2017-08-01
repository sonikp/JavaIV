package week3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileOutputDemo {
	
	public static void main(String[] args) {
		
		String fileName = null;
		
		try {
			Scanner keyboard = new Scanner(System.in);
			
			System.out.println("Enter filename: ");
			fileName = keyboard.next();
			
			BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
			
			String line = null;
			
			System.out.println("The first line in " + fileName + " is: ");
			System.out.println(line);			
		}
		catch (FileNotFoundException ex) {
			System.out.println("File " + fileName + " not found.");
		}
		catch (IOException e) {
			System.out.println("Error reading from file " + fileName);
		}
		
	}
	
	
	{
//		Scanner keyboard = new Scanner(System.in);
//		PrintWriter outputStream = null;
//		
//		try {
//			outputStream = new PrintWriter(new FileOutputStream("demo_out.txt"));
//		}
//		catch (FileNotFoundException ex) {
//			System.out.println("Error opening the file demo_out.txt. " + ex.getMessage());
//			System.exit(0);
//		}
//		
//		System.out.println("Enter three lines of text: ");
//		String line = null;
//		int count;
//		
//		for (count = 1; count <= 3; count++) {
//			line = keyboard.nextLine();
//			outputStream.println(count + " " + line);
//		}
//		outputStream.close();
//		System.out.println("....written to demo_out.txt");
	}
}