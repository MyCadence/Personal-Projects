// TO DO: add your implementation and JavaDocs.

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleList Class of Generic type T implements Iterable Interface from Standard Library.
 * @param <T> Is the generic value passed into SimpleList.
 */
class SimpleList<T> implements Iterable<T> {
	
	// A linked list class 
	// You decide the internal attributes and node structure
	// But they should all be private

	// Class for the internal node: not visible to the outside 
	// Do not change the provided fields: otherwise the provided iterator() will not work
	
	//DO NOT CHANGE THIS CLASS EXCEPT TO ADD JAVADOCS
	/**
	 * Node Class of Generic type T.
	 * @param <T> - is the Generic valuel passed into Node.
	 */
	private class Node<T> {
		/**
		 * Member "value" of generic type.
		 */
		T value;		// data to store
		/**
		 * Member "next" of generic node object type.
		 */
		Node<T> next;	// link to the next node
		
		/**
		 * Constructor of Node Class.
		 * @param value - Passes in the generic value into a node object.
		 */
		public Node(T value){
			this.value = value;
		}
	}
	
	/**
	 * The head of the SimpleList object.
	 */
	private Node<T> head;  	// first node, not dummy
	
	/**
	 * The tail of the SimpleList object.
	 */
	private Node<T> tail;  	// last node, not dummy
	
	/**
	 * The number of node objects inside the SimpleList object.
	 */
	private int size;
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
		
	/**
	 * Default Constructor of SimpleList class.
	 */
	public SimpleList(){ 
		// Constructor
		// Initialize an empty list
		this.head = null;
		this.tail = null;
		this.size = 0;
		// O(1)
	}
		
	/**
	 * Returns the member "size".
	 * @return size
	 */
	public int size(){
		//Return the number of nodes in list
		//O(1)
		
		return size; //default return; change or remove as needed
	}

	/**
	 * Creates a new node at the tail end of the SimpleList object and sets it to the parameter "value".
	 * @param value - contains the generic value to be stored (within a new node).
	 */
	public void addLast(T value){
		// Add a new node to the tail of the linked list to hold value
		if(value==null) {
			throw new IllegalArgumentException("Cannot add null value!");
		}
		// O(1) 
		
		Node<T> temp = new Node<T>(value);
		size++;
		temp.next = null;
		if(head==null) {
			head = temp;
			tail = head;

			return;
		}

		tail.next = temp;
		tail = tail.next;
		// Note: The value to be added cannot be null.
		// - Throw IllegalArgumentException if value is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Cannot add null value!"
	}
	
	/**
	 * Removes the first (head) node object within the SimpleList object.
	 * @return temp.value - returns the deleted node's value.
	 */
	public T removeFirst(){
		// Remove the node from the head of the linked list 
		// and return the value from the node.
		// If linked list is empty, return null.
		if(head==null) {
			return null;
		}//empty
		Node<T> temp = head;
		head = head.next;
		size--;
		return temp.value;
		// O(1)

	}

		
	/**
	 * Removes the first occurence of a node whose value matches with the parameter's value.
	 * @param value - contains the generic data type. It looks to find and remove the node it shares the same value with.
	 * @return boolean - whether the function successfully achieved the remove operation.
	 */
	public boolean remove(T value){
		// Given a value, remove the first occurrence of that value
		// Return true if value removed
		// Return false if value not present
		Node<T> temp = head;
		if(head.value.equals(value)) {
			if(head==tail) {
				head = null;
				tail = null;
				size--;
				return true;
			}//set the whole list to empty if there's one node
			head = head.next;
			size--;
			return true;
		}//if the head matches the value to be removed
		
		while(temp.next!=null) {
			if(temp.next.value.equals(value)) {
				if(temp.next==tail) {
					tail = temp;
					temp.next = null;
					size--;
					return true;
				}//if tail matches the value to be removed cut it off and set it to current (temp) node
				temp.next = temp.next.next;
				size--;
				return true;
			}//traverse over the matching value to be removed
			temp = temp.next;
		}//iterate through linked list
		// O(N) where N is the number of nodes in list
		
		// NOTE: remember to use .equals() for comparison

		return false; //default return; change or remove as needed		
	}
	
	/**
	 * Retrives the node with a particular value (passed in through the parameter).
	 * @param value - The generic value of a node to be retrieved from the SimpleList object.
	 * @return temp.value - Returns null if value is not found, otherwise return the node's value.
	 */
	public T get(T value){
		Node<T> temp = head;
		while(temp!=null) {
			if(temp.value.equals(value)) {
				return temp.value;
			}
			
			temp = temp.next;
		}
		// Find the node with the specified value and
		// *RETURN THE VALUE STORED* from linked list,
		// do NOT return the incoming value.
		// Return null if value is not present.
		
		// O(N) where N is the number of nodes in list
		
		// NOTE: two values might be considered "equivalent" but not identical
		//       example: check Pair class in HashMap.java:
		//				Pair <k,v1> and <k,v2> equal to each other for different v1 and v2 
		// NOTE: remember to use .equals() for comparison

		return null; //default return; change or remove as needed	
	}

	// Provided: do not change but you will need to add JavaDoc
	/**
	 * The implementation of Iterator's interface into SimpleList class.
	 * @return generic value (toReturn) of the iterator's location in a SimpleList object. Otherwise, throw an exception.
	 */
	public Iterator<T> iterator(){
		// return a basic iterator of T
		// Note that this method uses your linked list!
		// so if the iterator doesn't work, that's on you...

		return new Iterator<>(){
			private Node<T> current = head;
		
			public boolean hasNext(){			
				return (current!=null);
			}
		
			public T next(){
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				T toReturn = current.value;
				current = current.next;
				return toReturn;
			}
		};
	}
	
	// Provided: do not change but you will need to add JavaDoc
	@Override
	public String toString(){
		// list all values from head to tail
		StringBuilder s = new StringBuilder("[");
		Node<T> current = head;
		String prefix="";
		while (current!=null){
			s.append(prefix);
			s.append(current.value);
			prefix=",";
			current = current.next;
		}
		s.append("]");
		return s.toString();

	}


}