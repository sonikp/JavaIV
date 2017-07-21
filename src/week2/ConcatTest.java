package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConcatTest {

	@Test
	public void testConcatenate() {
		SampleClass test = new SampleClass();
		String result = test.concatenate("one", "two");
		assertEquals("onetwo", result);
	}

}
