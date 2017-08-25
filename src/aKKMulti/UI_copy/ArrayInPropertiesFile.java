package aKKMulti.UI_copy;

import java.io.FileInputStream;
import java.util.Properties;

public class ArrayInPropertiesFile {

	//Key for 2nd part of array
	final static int NAME = 0;
	final static int CAPITAL = 1;
	final static int NICKNAME = 2;

	public static void main(String[] args) throws Exception {
		//get properties file
		Properties prop = new Properties();
		prop.load(new FileInputStream("StateInfo.properties"));

		//get two dimensional array from the properties file that has been delineated
		String[][] stateInfos = fetchArrayFromPropFile("stateInfo", prop);


		//below code will print out all the states, their capitals, and nicknames
		for (int i = 0; i < stateInfos.length; i++) {
			System.out.print("State "+ i + ":");
			System.out.print("\n");
			System.out.print("Name: " + stateInfos[i][NAME]);
			System.out.print("\n");
			System.out.print("Capital: " + stateInfos[i][CAPITAL]);
			System.out.print("\n");
			System.out.print("NickName: " + stateInfos[i][NICKNAME]);
			System.out.print("\n");

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
			array[i] = a[i].split(",");
		}
		return array;
	}



}