package week3;

import java.util.Comparator;

public class CGGradeComparator implements Comparator<CGStudent> {
	@Override
	public int compare(CGStudent o1, CGStudent o2) {

		// descending order (ascending order would be:
		// o1.getGrade()-o2.getGrade())
		return o2.getGrade() - o1.getGrade();
	}
}