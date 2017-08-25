package aKKMulti.UI_copy;

import java.io.FileInputStream;
import java.util.Properties;

public class KnockKnockArrayInPropertiesFile {

	//Key for 2nd part of array
	final static int CLUE = 0;
	final static int ANSWER = 1;


	public static void main(String[] args) throws Exception {
		//get properties file
		Properties prop = new Properties();
		prop.load(new FileInputStream("KnockKnockData.properties"));

		//get two dimensional array from the properties file that has been delineated
		String[][] knockknockInfo = fetchArrayFromPropFile("knockknockInfo", prop);
		
		System.out.println("==================================");
		
		int j = 2;
		System.out.println(knockknockInfo[j][CLUE]);
		System.out.println(knockknockInfo[j][ANSWER]);
		
		/*
		
		knockknockInfo[currentJoke][CLUE]
		knockknockInfo[currentJoke][ANSWER]
		
		*/


		//below code will print out all the states, their capitals, and nicknames
		for (int i = 0; i < knockknockInfo.length; i++) {
			System.out.print("\n" + i + ":");	// orig:  "State "+ i + ":"
			System.out.print("\n");
			System.out.print("Clue: " + knockknockInfo[i][CLUE]);
			System.out.print("\n");
			System.out.print("Answer: " + knockknockInfo[i][ANSWER]);



		}


	}


	
	/**
	 * Creates two dimensional array from delineated string in properties file
	 * @param propertyName name of the property as in the file
	 * @param propFile the instance of the Properties file that has the property
	 * @return two dimensional array
	 */
	private static String[][] fetchArrayFromPropFile(String propertyName, Properties propFile) {

		//get array split up by the semicolin
		String[] a = propFile.getProperty(propertyName).split(";");

		//create the two dimensional array with correct size
		String[][] array = new String[a.length][a.length];

		//combine the arrays split by semicolin and comma 
		for(int i = 0;i < a.length;i++) {
			array[i] = a[i].split(":");
		}
		return array;
	}



}