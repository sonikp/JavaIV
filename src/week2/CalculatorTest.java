package week2;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {
	
  @Test	// (timeout=1005)
  public void evaluatesExpression() throws InterruptedException {
    Calculator calculator = new Calculator();
    int sum = calculator.evaluate("1+2+3");
    assertEquals(6, sum);
//    Thread.sleep(1000);
  }
  
//  @Test(expected = InterruptedException.class)
  @Test
  public void correctPassword() {
	  String password = "sandiego";
	    assertEquals("failure - strings are not equal", "sandiego", password);
	  
  }
  
  
  
}