import java.util.NoSuchElementException;
/**
 * PhBLinkedList.java
 * @author jgt31
 *
 */
public class PhBLinkedList implements PhoneBook, Iterable<Person> {
	
	/**
	 * Basic node class for the linked list
	 */
	private class PersonNode {
		private Person person;
		private PersonNode next;
			
		private PersonNode(Person p) {
			person = p;
		}
			
		private Person getPerson() {
			return person;
		}
			
		private PersonNode getNext() {
			return next;
		}
			
		private void setNext(PersonNode node) {
			next = node;
		}
	}
	
	/**
	 * Iterator for this linked list
	 */
	public class PhBListIterator implements PhBIterator {
		
		private PersonNode nextNode; // always points to the next node, head is first returned upon first next() call
		private int nextIndex; // integer storing the index of the pointer node
		
		public PhBListIterator() {
			nextIndex = 0;
			nextNode = head;
		}
		
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public Person next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			nextIndex++;
			Person person = nextNode.getPerson();
			nextNode = nextNode.getNext();
			return person;
		}

		@Override
		public boolean removePrevious() {
			// nextIndex always starts at 0 by default; if it's still at 0 we know next() hasn't been called yet...
			if(nextIndex <= 0 || nextIndex > size()) {
				return false;
			}
			nextIndex--;
			PhBLinkedList.this.remove(nextIndex);
			return true;
		}
		
	}
	
	private int size = 0;
	private PersonNode head;
	
	/**
	 * Default constructor for a linked list phonebook
	 */
	public PhBLinkedList() {
		head = null;
	}
	
	/**
	 * Returns the number of people in the phonebook
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Inserts a person at the specified index
	 * Running time O(N) - insertion at the end requires traversal through entire list
	 */
	@Override
	public void insert(int index, Person person) {
		PersonNode p = new PersonNode(person);
		
		// If the specified index is absurdly large we'll just place the person at the end
		if(index > size()) {
			index = size();
		}
		// If the index is zero, we'll just to change the head
		if(index == 0) {
			p.setNext(head);
			head = p;
		} else {
			PersonNode traverser = head;
			
			// Let's travel to the element before the specified index
			for(int i = 0; i < index - 1; i++) {
				traverser = traverser.getNext();
			}
			PersonNode temp = traverser.getNext();
			traverser.setNext(p);
			p.setNext(temp);
		}
		size++;
	}

	/**
	 * Removes and returns the person at the specified index
	 * Running time O(N) - deletion at the end requires traversal through entire list
	 */
	@Override
	public Person remove(int index) {
		// If the index is absurdly large or negative we'll just return nothing
		if(index >= size() || index < 0) {
			return null;
		} else {
			PersonNode traverser = head;
			size--;
			// If the requested index is zero we'll just return the head
			if(index == 0) {
				head = head.getNext();
				return traverser.getPerson();
			}
			
			// Travelling to the element before the specified index...
			for(int i = 0; i < index - 1; i++) {
				traverser = traverser.getNext();
			}
			PersonNode temp = traverser.getNext();
			traverser.setNext(traverser.getNext().getNext());
			return temp.getPerson();
		}
	}

	/**
	 * Returns the person at the specified index
	 * Running time O(N) - sequential access requires traversal through list
	 */
	@Override
	public Person lookup(int index) throws Exception {
		if(index >= size || index < 0) {
			throw new Exception("Out of bounds!");
		}
		
		PersonNode traverser = head;
		//Travelling to the element at the specified index...
		for(int i = 0; i < index; i++) {
			traverser = traverser.getNext();
		}
		return traverser.getPerson();
	}

	/**
	 * Returns an iterator over this linked list
	 */
	@Override
	public PhBIterator iterator() {
		return new PhBListIterator();
	}
	
	/**
	 * Returns a String representation of this linked list
	 */
	@Override
	public String toString() {
		if(size == 0) { return "[]";}
		
		String str = "[";
		PersonNode traverser = head;
		while(traverser != null) {
			str += traverser.getPerson() + ", ";
			traverser = traverser.getNext();
		}
		str = str.substring(0, str.length() - 2) + "]";
		return str;
	}
}
