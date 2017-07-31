package week3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CGComparatorMain {
	
	public static void main(String[] args) {
		
		CGStudent student[] = new CGStudent[3];
		
		student[0] = new CGStudent();
		student[0].setName("Nick");
		student[0].setGrade(19);
		
		student[1] = new CGStudent();
		student[1].setName("Allen");
		student[1].setGrade(12);
		
		student[2] = new CGStudent();
		student[2].setName("Ross");
		student[2].setGrade(16);
		
		System.out.println("Order of students before sorting is: ");
		

		
		for (int i = 0; i < student.length; i++ ) {
			System.out.println(student[i].getName() + "\t" + student[i].getGrade());
		}
		
		Arrays.sort(student, new CGGradeComparator());
		System.out.println("Order of students after sorting by grade: ");
		
		for (int i = 0; i < student.length; i++ ) {
			System.out.println(student[i].getName() + "\t" + student[i].getGrade());
		}
		
		Arrays.sort(student, new CGNameComparator());
		System.out.println("Order of students after sorting by name: ");
		
		for (int i = 0; i < student.length; i++ ) {
			System.out.println(student[i].getName() + "\t" + student[i].getGrade());
		}
		
		
	}
}