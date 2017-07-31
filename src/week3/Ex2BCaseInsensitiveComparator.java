package week3;

import java.util.Comparator;

public class Ex2BCaseInsensitiveComparator implements Comparator {
	
	public int compare(Object one, Object two) {
		String stringOne = (String)one;
		String stringTwo = (String)two;
		
		return stringOne.toLowerCase().compareTo(stringTwo.toLowerCase());
	}
}