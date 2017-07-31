package week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap;

public class Ex2BSort {
	
	public static void main (String[] args) {
		
		List aList = new ArrayList();
		
		for (int i = 0; i < args.length; i++) {
			aList.add(args[i]);
		}
		
		Collections.sort(aList, new Ex2BCaseInsensitiveComparator());
		System.out.println( aList );
	}
}