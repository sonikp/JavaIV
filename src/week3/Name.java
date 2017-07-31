package week3;

public class Name implements Comparable {
	
	private String first;
	private String last;
	
	public static void main(String[] args) {
		Name n1 = new Name("bob","smith");
		Name n2 = new Name("bob","jane");
		
		System.out.println(n1.compareTo(n2));
		
		
	}
	
	public Name(String firstName, String lastName ) {
		first = firstName;
		last = lastName;
	}
	
	
	
	public String getFirst() {
		return first;
	}



	public String getLast() {
		return last;
	}



	public void setFirst(String first) {
		this.first = first;
	}



	public void setLast(String last) {
		this.last = last;
	}
	
	public boolean equals( Object o ) {
		boolean retval = false;
		
		if (o != null && o instanceof Name ) {
			Name n = (Name)o;
			retval = n.getFirst().equals(first) && n.getLast().equals(last);
		}
		
		return retval;
	}
	
	public int hashCode() {
		return first.hashCode() + last.hashCode();
	}
	
	



	@Override
	public String toString() {
		return "Name [first=" + first + ", last=" + last + "]";
	}



	@Override
	public int compareTo(Object o) throws ClassCastException {
		int retval;
		
		Name n = (Name)o;
		
		retval = last.compareTo(n.getLast());
		
		if ( retval == 0 )
			retval = first.compareTo(n.getFirst() );
		
		return retval;
	}
	
}















