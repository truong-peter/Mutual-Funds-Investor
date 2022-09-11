
/**
 * List.java
 * @author Peter Truong
 * @author Tammy Dinh
 * CIS 22C, Lab 5
 */

import java.util.NoSuchElementException;

public class List<T> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTOR ****/

	/**
	 * Instantiates a new List with default values
	 * 
	 * @postcondition A New list object created with default values.
	 */
	public List() {
		length = 0;
		first = null;
		last = null;
		iterator = null;
	}

	/**
	 * Instantiates a new List by copying another List
	 * 
	 * @param original the List to make a copy of
	 * @postcondition a new List object, which is an identical but separate copy of
	 *                the List original
	 */
	public List(List<T> original) {
		if (original == null) {
			return; // the original List is null, return it.
		}
		if (original.length == 0) {
			length = 0;
			first = null;
			last = null;
			iterator = null; // Check if list is empty, if yes, create the New List as empty.

		} else {
			Node temp = original.first;
			while (temp != null) {
				addLast(temp.data);
				temp = temp.next;
			}
			iterator = null; // set iterator to NULL to be different than original iterator
		} // location.
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored in the first node
	 * 
	 * @precondition length != 0
	 * @return the value stored at node first
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst: No element in list!");
		}

		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition length != 0
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast: No element in list!");
		}
		return last.data;
	}

	/**
	 * Returns the current length of the list
	 * 
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the list is currently empty
	 * 
	 * @return whether the list is empty
	 */
	public boolean isEmpty() {
		if (length == 0) {
			return true;
		} else
			return false;
	}

	/**
	 * Returns the element currently pointed at by the iterator
	 * 
	 * @precondition iterator != null
	 * @return the value pointed at by the iterator
	 * @throws NullPointerException when the precondition is violated
	 */
	public T getIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("getIterator: iterator is off the end of the List.");
		} else {
			return iterator.data;
		}
	}

	/**
	 * Returns whether or not the iterator is off end of the list. (NULL)
	 * 
	 * @return whether the iterator is null
	 */
	public boolean offEnd() {
		if (iterator == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines whether two Lists have the same data in the same order
	 * 
	 * @param L the List to compare to this List
	 * @return whether the two Lists are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof List)) {
			return false;
		} else {
			List<T> L = (List<T>) o;
			if (this.length != L.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = L.first;
				while (temp1 != null) { // Lists are same length
					if (!(temp1.data.equals(temp2.data))) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return true;
			}
		}
	}

	/**
	 * Points the iterator at first and then advances it to the specified index
	 * 
	 * @param index the index where the iterator should be placed
	 * @precondition 0 < index <= length
	 * @throws IndexOutOfBoundsException when precondition is violated
	 */
	public void iteratorToIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("iteratorToIndex: Index doesn't exist.");
		}
		iterator = first;
		for (int i = 1; i < index; i++) {
			advanceIterator();
		}

	}

	/**
	 * Searches the List for the specified value using the linear search algorithm
	 * 
	 * @param value the value to search for
	 * @return the location of value in the List or -1 to indicate not found Note
	 *         that if the List is empty we will consider the element to be not
	 *         found post: position of the iterator remains unchanged
	 */
	public int linearSearch(T value) {
		if (length == 0) {
			return -1;
		}
		Node temp = first;
		int i = 0;
		while (temp != null) {

			if (temp.data == value) {
				return i;
			}

			i++;
			temp = temp.next;
		}

		return -1;
	}

	/**** MUTATORS ****/

	/**
	 * Creates a new first element
	 * 
	 * @param data the data to insert at the front of the list
	 * @postcondition Adding an additional element to beginning of linked list.
	 */
	public void addFirst(T data) {
		Node f = new Node(data);
		if (length == 0) {
			first = last = f;
		} else { // general case
			f.next = first;
			first.prev = f;
			first = f;
		}
		length++;
		return;
	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the list
	 * @postcondition Adding an additional element to end of linked list.
	 */
	public void addLast(T data) {
		Node l = new Node(data);
		if (first == null) {
			first = last = l;
		} else {
			last.next = l;
			l.prev = last;
			last = l;
		}

		length++;
		return;
	}

	/**
	 * removes the element at the front of the list
	 * 
	 * @precondition length !=0
	 * @postcondition removes first element in linked list and sets iterator to null
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) { // precondition
			throw new NoSuchElementException("removeFirst: cannot remove from an empty list!");
		}

		if (iterator == first) { // edge case
			iterator = null;
		}

		if (length == 1) { // edge case
			first = last = null;
		} else { // general case
			first = first.next;
			first.prev = null;
		}
		length--;
	}

	/**
	 * removes the element at the end of the list
	 * 
	 * @precondition length !=0
	 * @postcondition removes last element of the linked list and sets iterator to
	 *                null
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast: cannot remove from an empty list!");
		}
		if (iterator == last) { // edge case
			iterator = null;
		}

		if (length == 1) {
			first = last = null;
		} else {
			last = last.prev;
			last.next = null;
			// last.prev.next = null; // this could work too?
		}
		length--;
		return;
	}

	/**
	 * moves iterator at the start of list
	 * 
	 * @postcondition moves the iterator to the start of the list
	 *
	 */
	public void placeIterator() {
		iterator = first;
	}

	/**
	 * removes the element currently referenced by the iterator
	 * 
	 * @precondition iterator != null
	 * @throws NullPointerException when iterator is off end
	 * @postcondition iterator will be null
	 */
	public void removeIterator() throws NullPointerException {
		if (iterator == null) { // precondition
			throw new NullPointerException("removeIterator: iterator is off end.");
		} else if (iterator == first) { // edge case
			removeFirst(); // should set iterator to null in this case
		} else if (iterator == last) { // edge case
			removeLast(); // should set iterator to null in this case
		} else { // general case
			iterator.next.prev = iterator.prev;
			iterator.prev.next = iterator.next;
			iterator = null;
			length--;
		}

	}

	/**
	 * Inserts a new element after the node currently referenced by the iterator
	 * 
	 * @param a new element to insert
	 * @precondition iterator != null
	 * @throws NullPointerException when iterator is off end (precondition is
	 *                              violated)
	 */

	public void addIterator(T data) throws NullPointerException {
		if (iterator == null) { // precondition
			throw new NullPointerException("addIterator: iterator is off end.");
		} else if (iterator == last) { // edge case
			addLast(data);
		} else { // general case
			Node n = new Node(data);
			n.next = iterator.next;
			n.prev = iterator;
			iterator.next.prev = n;
			iterator.next = n;
			length++;
		}

	}

	/**
	 * Advances the iterator by one node
	 * 
	 * @precondition !offEnd
	 * @throws NullPointerException when precondition is violated
	 */
	public void advanceIterator() throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("advanceIterator: Iterator is offEnd and cannot advance"); // do we need
		} else {
			iterator = iterator.next;
		}

	}

	/**
	 * Moves the iterator down by one node
	 * 
	 * @precondition !offEnd
	 * @throws NullPointerException when precondition is violated
	 */
	public void reverseIterator() throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("reverseIterator: Iterator is offEnd and cannot move iterator back"); // do
																													// we
																													// need
		} else {
			iterator = iterator.prev;
		}

	}

	/**** ADDITIONAL OPERATIONS ****/

	/**
	 * List with each value on its own line At the end of the List a new line
	 * 
	 * @return the List as a String for display
	 */
	@Override
	public String toString() {
		String result = " ";
		Node temp = first;
		while (temp != null) {
			result += temp.data + " ";
			temp = temp.next;
		}
		return result;
	}

	/**
	 * prints the contents of the linked list to the screen
	 * 
	 * @return the contents for display in the format '#: <element> followed by a
	 *         newline'
	 */

	public String printNumberedList() {
		String result = " ";
		int x = 1;
		placeIterator();
		while (iterator != null) {
			System.out.println(x + ": " + getIterator() + "\n");
			advanceIterator();
			x++;

		}
		return result;

	}

}