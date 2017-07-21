package week2;

import java.lang.reflect.Array;

public class Demo {
	
	public static void main(String[] args) throws ClassNotFoundException {
		DemoExtensionStudent michael = new DemoExtensionStudent();
//		michael.setName("michael");
//		michael.setEmployer("Sony");
//		
		printSuperclasses(michael);
		
		printInterfaceNames(michael);
		
		// manipulating arrays
		Class cls = Class.forName("java.lang.String");
		Object arr = Array.newInstance(cls, 5);
		Array.set(arr, 3, "this sets location 3 in the array");
		String s = (String)Array.get(arr, 3);
		System.out.println(s);
		
		
		
	}
	
	static void printSuperclasses(Object o) { 
		Class subclass = o.getClass();
		Class superclass = subclass.getSuperclass(); 
		while (superclass != null) {
	
	String className = superclass.getName(); System.out.println(className);
	subclass = superclass;
	superclass = subclass.getSuperclass();
		}
	}
	
	static void printInterfaceNames(Object o ) {
		
	}
	
	
}