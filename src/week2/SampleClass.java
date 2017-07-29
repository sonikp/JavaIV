//package test;

package week2;

import junit.runner.Version;

/**
 * Video tutorial: https://www.youtube.com/watch?v=v2F49zLLj-8
 * @author James
 *
 */
public class SampleClass {
	
	public String concatenate(String one, String two)
	{
		return one + two;
	}

	public int multiply(int num1, int num2)
	{
		return num1 * num2;
	}
	
	public static void main(String[] args)
	{
	

		System.out.println("JUnit version is: " + Version.id());
	}
}
