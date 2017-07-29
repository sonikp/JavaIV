//package test;
package week2;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class MultiplyTest {

	@Ignore
	@Test
	public void multiply() {
		SampleClass test = new SampleClass();
		int result = test.multiply(6, 3);
		assertEquals(18, result);
	}

}
