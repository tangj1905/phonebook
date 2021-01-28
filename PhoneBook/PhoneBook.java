/**
 * PhoneBook.java
 * @author jgt31
 */

public interface PhoneBook extends Iterable<Person>{
	int size();
	
	void insert(int i, Person person);
	
	Person remove(int i);
	
	Person lookup(int i) throws Exception;
	
	@Override
	PhBIterator iterator();
}