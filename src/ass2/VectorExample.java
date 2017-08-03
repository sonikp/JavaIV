package ass2;

//http://beginnersbook.com/2013/12/vector-in-java/

import java.util.Enumeration;
import java.util.Vector;

public class VectorExample {
	
	public static void main(String[] args) {
		
		/* Vector of initial capacity(size) of 2 */
		
		Vector<String> vec = new Vector<String>(2);
		
		// adding elements
		vec.addElement("Apple");
		vec.addElement("Orange");
		vec.addElement("Mango");
		vec.addElement("Fig");
		
		// check size and capacity Increment
		System.out.println("Size is: " + vec.size());
		System.out.println("Default Capacity increment is: " + vec.capacity());
		
		vec.addElement("fruit1");
		vec.addElement("fruit2");
		vec.addElement("fruit3");
		
		// size and capacity increment after two insertions
		System.out.println("Size after addition: " + vec.size());
		System.out.println("Capacity after increment: " + vec.capacity());
		
		// display vector elements
		Enumeration<String> en = vec.elements();
		System.out.println("\nElements are: ");
		while(en.hasMoreElements()) {
			System.out.println(en.nextElement() + " ");
		}
		
		
	}
	
}