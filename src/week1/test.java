package week1;

// test for policy manager
// # java -Djava.security.manager -Djava.security.policy=test.policy EvilEmpire

public class test {
	public static void main(String[] args) {
		
		int high = 6;
		int low = 4;
		
		System.out.println("orig (4 + 6) \\ 2 = " + ((low + high) / 2) );
		System.out.println("bttr 4 + (6 - 4) \\ 2) = " + (low + ((high - low) / 2)) );
		System.out.println("last (4 + 6) >>> 1 = " + ((low + high) >>> 1));
		

		
		
		
		
		System.out.println("132");
	}
}
