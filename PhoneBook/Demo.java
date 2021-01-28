/**
 * Demo.java
 * @author jgt31
 *
 */
public class Demo {
	public static void main(String[] args) throws Exception {
		PhoneBook pb = new PhBArrayList();
		PhoneBook pb2 = new PhBLinkedList();
		Person p1 = new Person("Mickey Mouse", "123");
		Person p2 = new Person("Minnie Mouse", "234");
		Person p3 = new Person("Donald Duck", "345");
		Person p4 = new Person("Goofy", "456");
		Person p5 = new Person("Pluto", "567");
		Person p6 = new Person("Daisy Duck", "678");
		Person p7 = new Person("Chip", "789");
		Person p8 = new Person("Dale", "890");
		
		pb.insert(0, p1); // For an array list, insertion at the end is the fastest (no need to shift elements over)
		pb.insert(1, p2);
		pb.insert(2, p3);
		pb.insert(3, p4);
		pb.insert(4, p5);
		pb.insert(5, p6);
		
		pb2.insert(0, p1); // For a linked list, insertion at the front is the fastest (no need for list traversal)
		pb2.insert(0, p2);
		pb2.insert(0, p3);
		pb2.insert(0, p7);
		pb2.insert(0, p8);
		
		System.out.println("List 1: " + pb); // Mickey, Minnie, Donald, Goofy, Pluto, Daisy
		System.out.println("List 2: " + pb2); // Dale, Chip, Donald, Minnie, Mickey
		
		removeDuplicates(pb, pb2); // This should remove Minnie, Mickey, and Donald from list 1
		
		System.out.println("After removing duplicates...");
		System.out.println("List 1: " + pb); // Goofy, Pluto, Daisy
		System.out.println("List 2: " + pb2); // Should remain unchanged
	}
	
	/**
	 * Compares the two phonebooks and removes every duplicated person name from p1
	 * Running time O(N^3) - requires one traversal thru p2 for each element of p1, plus O(N) for element removal
	 * (is there a more efficient way other than simply using a Set?)
	 */
	public static void removeDuplicates(PhoneBook p1, PhoneBook p2) {
		for(PhBIterator i1 = p1.iterator(); i1.hasNext();) {
			Person a = i1.next();
			for(PhBIterator i2 = p2.iterator(); i2.hasNext();) {
				Person b = i2.next();
				if(a.getID().equals(b.getID())) {
					i1.removePrevious();
				}
			}
		}
	}
}