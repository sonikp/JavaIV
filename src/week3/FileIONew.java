package week3;

// http://www.codejava.net/java-se/file-io/java-scanner-tutorial-and-code-examples

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIONew {

	public static void main(String[] args) {
		try
		{
			Scanner inFileStream = new Scanner(new File("in.txt"));
			
			int sum = 0;
			
			while (inFileStream.hasNextInt()) {
				sum += inFileStream.nextInt();
			}
			
			System.out.println("Sum = " + sum);



			inFileStream.close();

		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			System.out.println("in.txt not found");
			System.exit(-1);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.out.println("Error reading in.txt");
			System.exit(-1);
		}



		try
		{
			PrintWriter outFileStream = new PrintWriter(new File("ChickenBalls.txt"));

			outFileStream.println("Hello World");
			outFileStream.println("Good Morning");

			outFileStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
			System.out.println("Can't open ChickenBalls.txt");
			System.exit(-1);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.out.println("Error writing out.txtChickenBalls.txt");
			System.exit(-1);
		}


		File outFile = new File("../FileIO/ChickenBalls.txt");
		System.out.println(outFile.exists());
		System.out.println(outFile.length());
		System.out.println(outFile.getName());
		System.out.println(outFile.getPath());
	}
}