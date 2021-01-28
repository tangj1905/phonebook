import java.util.NoSuchElementException;
/**
 * PhBArrayList.java
 * @author jgt31
 */
public class PhBArrayList implements PhoneBook, Iterable<Person> {
	
	/**
	 * Iterator for this array list
	 */
	private class PhBArrayIterator implements PhBIterator {
		
		private int nextIndex; // always points to the next node, head is first returned upon first next() call
		
		public PhBArrayIterator() {
			nextIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			return nextIndex <= size() - 1;
		}

		@Override
		public Person next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			Person person = list[nextIndex];
			nextIndex++;
			return person;
		}

		@Override
		public boolean removePrevious() {
			// nextIndex always starts at 0 by default; if it's still at 0 we know next() hasn't been called yet...
			if(nextIndex <= 0 || nextIndex >= size()) {
				return false;
			}
			nextIndex--;
			PhBArrayList.this.remove(nextIndex);
			return true;
		}
		
	}
	
	private int size = 0;
	private Person[] list;
	
	
	/**
	 * Default constructor to create an empty ArrayList-based phonebook
	 */
	public PhBArrayList() {
		list = new Person[4]; // The minimum size of this array will always be 4, based on this resizing method
	}
	
	/**
	 * Gets the number of people in the phonebook
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Inserts a person at the specified index
	 * Running time O(N) - insertion at front requires shifting back every element of the array
	 */
	@Override
	public void insert(int index, Person person) {
		size++;
		
		// First, grow the array if it's the list is about to exceed the internal array's size
		if(size >= list.length) {
			Person[] newList = new Person[list.length * 2]; // Doubling the array size
			for(int i = 0; i < size; i++) {
				newList[i] = list[i];
			}
			list = newList;
		}
		
		// If the index is large we'll just place the person at the end
		if(index >= size - 1) {
			list[size - 1] = person;
		} else {
			// If the index is less than 0, we'll just place the person at the front
			if(index < 0) index = 0;
			
			// We have to move everyone over first
			for(int i = size - 1; i >= index; i--) {
				list[i + 1] = list[i];
			}
			list[index] = person;
		}
	}

	/**
	 * Removes and returns the person at the specified index
	 * Running time O(N) - removal at front requires shifting forward every element of the array
	 */
	@Override
	public Person remove(int index) {
		// Shrink the array if the number of elements is less than 1/4 the size
		if(size < list.length / 4) {
			Person[] newList = new Person[list.length / 2]; // Halving the array size
			for(int i = 0; i < size(); i++) {
				newList[i] = list[i];
			}
			list = newList;
		}
		
		// If the index is large or negative we'll just return nothing
		if(index >= size() || index < 0) {
			return null;
		} else {
			// Shifting everyone from the index to the back...
			Person person = list[index];
			for(int i = index; i < size; i++) {
				list[i] = list[i + 1];
			}
			size--;
			return person;
		}
	}

	/**
	 * Returns the person at the specified index
	 * Running time O(1) - random access for arrays take constant time
	 */
	@Override
	public Person lookup(int index) throws Exception {
		if(index >= size || index < 0) {
			throw new Exception("Out of bounds!");
		}
		return list[index];
	}
	
	/**
	 * Returns an iterator over the array list
	 */
	@Override
	public PhBIterator iterator() {
		return new PhBArrayIterator();
	}
	
	/**
	 * Returns a String representation of the ArrayList
	 */
	public String toString() {
		if(size == 0) { return "[]";}
		
		String str = "[";
		for(int i = 0; i < size; i++) {
			try {
				str += lookup(i) + ", ";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		str = str.substring(0, str.length() - 2) + "]";
		return str;
	}
}
