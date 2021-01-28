/**
 * Person.java
 * @author jgt31
 */
public class Person {
	private String personID;
	private String phoneNum;
	
	/**
	 * Default constructor for a Person
	 */
	public Person(String personID, String phoneNum) {
		this.personID = personID;
		this.phoneNum = phoneNum;
	}
	
	/**
	 * Basic getter method for the person's name
	 */
	public String getID() {
		return personID;
	}
	
	/**
	 * Basic getter method for the person's phone number
	 */
	public String phoneNum() {
		return phoneNum;
	}
	
	/**
	 * Prints a basic string representation of the person
	 */
	@Override
	public String toString() {
		return "(" + getID() + ", " + phoneNum() + ")";
	}
}
