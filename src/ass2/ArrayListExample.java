package ass2;

// http://beginnersbook.com/2013/12/java-arraylist/

import java.util.ArrayList;

public class ArrayListExample {
	
	public static void main(String[] args) {
		

		
		// Creation of ArrayList: add String elements 
		
		ArrayList<String> obj = new ArrayList<String>();
		
		// add elements to arrayList
		obj.add("Ajeet");
		obj.add("Harry"); 
		obj.add("Chaitanya");
		obj.add("Steve");
		obj.add("Anuj");
		
		// Displaying array list elements
		System.out.println("Currently the array list had the following elements: " + obj );
		
		// add element at the given index
		obj.add(0, "Rahul");
		obj.add(1,"Justin");
		
		System.out.println("obj @ pos 2: " + obj.get(2));
		
		// Remove elements from array list like this
		obj.remove("Chaitanya");
		obj.remove("Harry");
		
		System.out.println("Current array list is: " + obj);
		
		// Remove element from the given index
		obj.remove(1);
		
		System.out.println("Current array list is: " + obj);
		
		
		
	}
}