package week3;

public class CGStudent {
	private String name;
	private int age;
	private String lesson;
	private int grade;

	public CGStudent() {
	}

	public CGStudent(String name, int age, String lesson, int grade) {
		super();
		this.name = name;
		this.age = age;
		this.lesson = lesson;
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "[name=" + this.name + ", age=" + this.age + ", lesson="
				+ this.lesson + ", grade=" + this.grade + "]";
	}
}