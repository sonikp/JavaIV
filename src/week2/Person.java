package edu.ucsd.lecture2;

/**
 * 
 * @author James Gappy
 *
 */
public class Person 
{
	//fields
	
	private String name;
	private int age;
	private double weight;
	
	
	
	
	//constructors
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", weight=" + weight + "]";
	}
	
	
	

	/**
	 * Parameterized constructor that takes all fields as parameters
	 * @param name name of the person
	 * @param age age of the person
	 * @param weight weight of the person
	 */
	public Person(String name, int age, double w) {
		this.name = name;  //this. is needed because the parameter name matches the field name
		this.age = age;    //  if the parameter name was different, then this. is not required 
		weight = w;		   //this. is not needed since we named the parameter somethign different (w)
	}
	
	/**
	 * Defualt parameter - initialized your fields to default values (usually blank or 0)
	 */
	public Person()
	{
		name = "unknown";
		age = 0;
		weight = 0;
	}
	
	public static void sayHello()
	{
		System.out.println("Hello");
	}
		
	//display method
	
	/**
	 * display prints out the values of the Person fields to the screen
	 */
	public void display()
	{
		System.out.println(name + " is " + age + " years old and weighs " + weight + " lbs.");
	}
	


	
	/**
	 * The equals method returns true if two people objects are the same, or false otherwise. It simply compares
	 * all the fields, and if all of them have the same values, it returns true.
	 * @param p the person object that the calling object is comparing itself to
	 * @return true if both people have the same fields, or false if they don't
	 */
	public boolean equals(Person p)
	{
		if (name.equals(p.name) && age==p.age && weight==p.weight)
			return true;
		else 
			return false;
	}
	
	
	
	//getter (accessor) and setter (mutator) methods
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	

}
