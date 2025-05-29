//TODO: Linked list implementation (optional)
//      JavaDocs (not optional)
//      Static sorting methods (not optional)

import java.awt.HeadlessException;
import java.util.Comparator;

/**ThreeTenLinkedList Class.
 * 
 * @author aaron
 *
 * @param <T> Generic Type
 */
class ThreeTenLinkedList<T> {
	//You may, but are not required to, implement some or
	//all of this generic linked list class to help you with
	//this project. If you do, you MUST use the provided
	//Node class for this linked list class. This means
	//that it must be a doubly linked list (and links in
	//both directions MUST work).
	
	//Alternatively, you may implement this project using
	//only the Node class itself (i.e. use "bare nodes"
	//in the classes that require linked lists).
	
	//Either way, you MUST do all your own work. Any other
	//implementations you have done in the past, anything
	//from the book, etc. should not be in front of you,
	//and you certainly shouldn't copy and paste anything
	//from any other source.
	
	//This is the only class where you are allowed to
	//implement public methods.
	
	//In "Part 5" of the project, you will also be implementing
	//the following static methods:
	/**
	 * Returns whether or not a given subset of a linked list is sorted.
	 * @param <X> Generic Value
	 * @param pairs NodePair instance (head,tail) subset of generic type linked list
	 * @param comp Comparator instance - comparing two instances of generic type
	 * @return boolean
	 */
	static <X> boolean isSorted(NodePair<X> pairs, Comparator<X> comp) {
		//Determine if the provided list is sorted based on the comparator.
		
		//For an empty linked list (e.g. the head-tail pair contains two nulls)
		//return true (an empty list is sorted).
		
		//For a null comparator or null pairs, throw an IllegalArgumentException.
		
		//when you create a null pair, the pair object is null and has no address
		//when you create a head and tail pair, you create an address to store head and tail of pair
		//O(n)
		
		//< YOUR_CODE_HERE >
		
		if(pairs==null||comp==null) {
			throw new IllegalArgumentException();
		}//invalid null instance
		
		if(pairs.head==null&&pairs.tail == null) {
			return true;
		}//empty list
		
		
		Node<X> temp = pairs.head;//creating head temporary to access the pairs
		while(temp.next!=null) {
			if(comp.compare(temp.data, temp.next.data)<=-1) {
				temp.prev = temp;//temporary value will become the previous value in the next iteration
				temp = temp.next;//iterate to the next 
				//4      ->      2      ->        7     ->      11
				//t.prev=t      t=t.next
			//null -> t.prev- >t-> t.next
			}//if the current data is less than the next data we continue
			
			else {
				return false;
			}//if they're not sorted just exit
			
		}
		
		return true;
	}
	
	/**
	 * Returns the sorted subset of a linked list.
	 * @param <X> Generic Value
	 * @param pairs NodePair instance (head,tail) subset of generic type linked list
	 * @param comp Comparator instance - comparing two instances of generic type
	 * @return NodePair of Generic Type X
	 */
	static <X> NodePair<X> sort(NodePair<X> pairs, Comparator<X> comp) {
		
		//Using the comparator, sort the linked list. It is recommended that
		//you sort by moving *values* around rather than moving nodes.
		//Two simple sorting algorithms which will work well here (and
		//meet the big-O requirements if implemented correctly) are the
		//insertion sort (see textbook Ch8.3) and the selection sort.

		//Insertion sort quick summary: Go to each element in the linked list,
		//shift it "left" into the correct position.
		//Example: 1,3,0,2
		// 1 is at the start of the list, leave it alone
		// 3 is bigger than 1, leave it alone
		// 0 is smaller than 3, move it left: 1,0,3,2
		// 0 is smaller than 1, move it to the left: 0,1,3,2
		// 0 is at the start of the list, stop moving it left
		// 2 is smaller than 3, move it to the left: 0,1,2,3
		// 2 is bigger than 1, stop moving it to the left

		//Selection sort quick summary: Go to each index in the linked list,
		//find the smallest thing from that index and to the "right",
		//and swap it into that index.
		//Example: 1,3,0,2
		// index 0: the smallest thing from index 0 to the end is 0, swap it into the right place: 0,3,1,2
		// index 1: the smallest thing from index 1 to the end is 1, swap it into the right place: 0,1,3,2
		// index 2: the smallest thing from index 2 to the end is 2, swap it into the right place: 0,1,2,3
		// index 3: there is only one item from index 3 to the end, so this is in the right places
		
		//Regardless of the method you choose, your sort should be a stable sort.
		//This means that if there are two equal values, they do not change their
		//order relative to each other.
		//Example: 1, 2, 1
		//The first "1" (which I'll call "1a") should be sorted before
		//the second "1" (1b), so that the output is "1a, 1b, 2" and
		//never "1b, 1a, 2". The easiest way to test this is to put two
		//equal items in the list, sort, and confirm using == that the
		//correct object is in the correct place.
		
		//For an empty linked list (e.g. the head-tail pair contains two nulls)
		//return the original pairs back to the user.
		
		//For a null comparator or null pairs, throw an IllegalArgumentException.
		
		//O(n^2)
		
		//outer loop to access the linked list or subset (pairs)
		//inner loop to move the selected node value to the left of the list as much as necessary
		//top of the list is the left, left most is the least in value
		
		//< YOUR_CODE_HERE >
		
		Node<X> temp1 = pairs.head;//temporary node //curr
		Node<X> temp2;//index

		X storeData;
		
		if(pairs==null||comp==null) {
			throw new IllegalArgumentException();
		}//invalid null instance
		
		if(pairs.head==null&&pairs.tail == null) {
			return pairs;
		}//empty list
        while(temp1.next!=null) {
            //Index will point to node next to current  
            for(temp2 = temp1.next; temp2 != null; temp2 = temp2.next) {  
                //If current's data is greater than index's data, swap the data of current and index  
                if(comp.compare(temp1.data, temp2.data)>0) {  
                    storeData = temp1.data;  
                    temp1.data = temp2.data;  
                    temp2.data = storeData;  
                }
            }
            temp1 = temp1.next;
        }  
		
		return pairs; //replace this!
	}

	//Which uses the following nested class:
	/**NodePair Class.
	 * 
	 * @author aaron
	 *
	 * @param <Y> Generic Type
	 */
	public static class NodePair<Y> {
		/**
		 * Head: Node of Generic Type.
		 */
		public Node<Y> head;
		/**
		 * Tail: Node of Generic Type.
		 */
		public Node<Y> tail;
		/**
		 * Non-default constructor passes in a head and tail of generic type.
		 * @param head - Head Node of Generic Type
		 * @param tail - Tail Node of Generic Type
		 */
		public NodePair(Node<Y> head, Node<Y> tail) {
			this.head = head;
			this.tail = tail;
		}
		
	}

}