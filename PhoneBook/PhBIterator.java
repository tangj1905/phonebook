import java.util.Iterator;
/**
 * PhBIterator.java
 * @author jgt31
 *
 */
public interface PhBIterator extends Iterator<Person> {
	
	@Override
	boolean hasNext();
	
	@Override
	Person next();
	
	boolean removePrevious();
	
}