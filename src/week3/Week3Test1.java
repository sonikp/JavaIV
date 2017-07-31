package week3;

import java.util.ArrayList;
import java.util.List;

public class Week3Test1<T> {
	
	private T t;
	public static <T> boolean isEqual(Week3Test1<T> g1, Week3Test1<T> g2) {
		return g1.get().equals(g2.get());
	}
	
	public T get() {
		return this.t;
	}
	
	public void set(T t1) {
		this.t = t1;
	}
	
	public interface Comparable<T> {
		public int compareTo(T o);
	}
	
	public static void main(String[] args) {
		
		Week3Test1<String> tg1 = new Week3Test1<String>();
		Week3Test1<String> g1 = new Week3Test1<>();
		Week3Test1<String> g2 = new Week3Test1<>();
		
		g1.set("generics new string");
		g2.set("generics new string");
		
		boolean isEqual = Week3Test1.<String>isEqual(g1, g2);
		System.out.println("is equal: " + Week3Test1.<String>isEqual(g1, g2));
		
		
		tg1.set("Pankaj");
		
		Week3Test1 tg2Raw = new Week3Test1();
		tg2Raw.set(10);
		tg2Raw.set("RawString");

		
		System.out.println(tg1.get());
		System.out.println(tg2Raw.get());
		
		
		
		
	}


}