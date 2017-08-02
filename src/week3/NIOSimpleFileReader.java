package week3;

//http://www.deepakgaikwad.net/index.php/2013/08/26/java-nio-tutorial.html

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class NIOSimpleFileReader {
	
	public static void main(String[] args) {

		Path file = null;
		BufferedReader bufferedReader = null;
		try {
			file = Paths.get("NIODataFile.txt");

			InputStream inputStream = Files.newInputStream(file);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			System.out.println("Reading Line: " + bufferedReader.readLine());
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			}

		}

	}
}