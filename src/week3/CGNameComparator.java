package week3;

import java.util.Comparator;

public class CGNameComparator implements Comparator<CGStudent> {
	@Override
	public int compare(CGStudent o1, CGStudent o2) {
		String name1 = o1.getName();
		String name2 = o2.getName();

		// ascending order (descending order would be: name2.compareTo(name1))
		return name1.compareTo(name2);
	}
}