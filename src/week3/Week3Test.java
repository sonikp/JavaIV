package week3;

import java.util.ArrayList;
import java.util.List;

public class Week3Test {
	public static void main(String[] args) {
		
		List<Date> dateList = new ArrayList<Date>();
		System.out.println( dateList instanceof List );
		System.out.println( dateList instanceof List );
		dateList.add( (Date) new Object() );
	}
	
	public class Date {
		
	}
}