package week2;

public class Calculator {
	
	String password = "sandiego";
	
	  public int evaluate(String expression) {
		  
	    int sum = 0;
	    for (String summand: expression.split("\\+"))
	      sum += Integer.valueOf(summand);
	    return sum;
	  }
	}