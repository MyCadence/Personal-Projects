//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A list of squares within a single window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 */
public class SquareList {
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >

	/**
	 * Data Field Head of type Square Node.
	 */
	private Node<Square> head;
	/**
	 * Data Field Tail of type Square Node.
	 */
	private Node<Square> tail;
	/**
	 * Data Field numSquares of type int.
	 */
	private int numSquares;
	/**
	 * Data Field Temp of type Square Node.
	 */
	private Node<Square> temp;
	/**
	 * Data Field did of type boolean.
	 */
	private boolean did;
	/**
	 *  Initialize an empty list of squares.
	 */
	public SquareList() {
		//Any initialization code you need.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		this.head = null;
		this.tail = null;
		this.numSquares=0;
		this.did = false;
		
	}
	
	/**
	 * Returns the head of the squarelist.
	 * @return Node of Generic Type Square
	 */
	public Node<Square> getHead() {
		//Returns the head of the list of squares.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementation of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate head of the list.
		
		//< YOUR_CODE_HERE >
		
		return head; //dummy return, replace this!
	}
	
	/**
	 * Returns the tail of the squarelist.
	 * @return Node of Generic Type Square
	 */
	public Node<Square> getTail() {
		//Returns the tail of the list of squares.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementation of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate tail of the list.
		
		//< YOUR_CODE_HERE >
		
		return tail; //dummy return, replace this!
	}
	
	/**
	 * Returns the number of squares in squarelist.
	 * @return int
	 */
	public int numSquares() {
		//Gets the number of squares in the list.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		
		return numSquares;
	}
	


	/**
	 * Adds a new Square object to the linked list.
	 * @param sq The new square object passed in.
	 */
	public void add(Square sq) {
		//Add a square to the list. Newly added squares
		//should be at the back end of the list.
		
		//O(1)
		
		//throw IllegalArgumentException for invalid squares
		
		//< YOUR_CODE_HERE >
		if(sq==null) {
			throw new IllegalArgumentException();
		}
		temp = new Node<Square>(sq);
		temp.next = null;
		temp.prev = null;
		if(tail==null||head==null) {
			head=temp;
			tail=temp;
			numSquares++;
			return;
		}//if there are no squares then start creating them
		temp.prev = tail;
		tail.next = temp;
		tail = tail.next;
		tail.next = null;

		numSquares++;
	}
	

	/**
	 * Whether a square was clicked or not - remove if so, else exit.
	 * @param x - coordinate of click.
	 * @param y - coordinate of click.
	 * @return boolean
	 */
	public boolean handleClick (int x, int y) {
		//Deletes all squares from the list that contain the 
		//position (x,y). Returns true if any squares get
		//deleted and returns false otherwise.
		
		//Returns true if any squares were deleted.
		
		//O(n) where n is the size of the list of squares
		
		//< YOUR_CODE_HERE >

		temp=tail;
		
		boolean did = false;
		if(tail==null)
			return false;
		if(head.next==null&&head.data.contains(x, y)){
			head = null;
			tail = null;
			numSquares--;
			return true;
	    }//if it's a single node list and equals value
	      
		while(temp!=null){
	    	if(temp.data.contains(x, y)) {
	    		if(temp==head&&head.data.contains(x, y)) {
	    			if(head.next!=null) {
	    				head = head.next;
	    			    head.prev = null;
	    			    numSquares--;
	    			    did=true;
	    		    }//if not the only node
	    		    
	    			if(head.next==null&&head.data.contains(x, y)) {
	    				head=null;
	    				tail=null;
	    				numSquares--;
	    				return true;
	    			}//if it is the last square
	    		}//if temp is head
	    		if(temp==tail&&tail.data.contains(x, y)) {
	    			if(tail.prev!=null) {
	    				tail = tail.prev;
	    				tail.next = null;
	    				numSquares--;
	    				did=true;
	    			}//if not the only node
	    			
	    			if(tail.prev==null&&tail.data.contains(x, y)) {
	    				head=null;
	    				tail=null;
	    				numSquares--;
	    				return true;
	    			}//if it is the last square
	    		}//if temp is tail
	    		
	    		if(temp.next!=null&&temp.prev!=null) {
	    			temp.prev.next = temp.next;
	    			temp.next.prev = temp.prev;
	    			numSquares--;
	    			did=true;
	    		}//if in between two squares clicked
	    	  

	    	  
	    	}//if (temp) square is clicked
	    	temp = temp.prev;
	    }
		return did;
	}   

	/**
	 *  Gets an iterator for the list of squares.
	 *  Squares are returned in the order added.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Square> elements() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...
		
		return new Iterator<>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Square> current = getHead();
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Square next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Square ret = current.data;
				current = current.next;
				return ret;
			}
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return (current != null);
			}
		};
	}
	
	/**
	 * Sorts the list of squares by creation; highest id (last added) gets priority.
	 */
	public void sortCreation() {
		Comparator<Square> comp = new Comparator<>() {
	    	public int compare(Square s1, Square s2) {
	    		return (s1.id()-s2.id());
	    	}//otherwise just exit
	    };//compare by location
	    
	    ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<Square>(getHead(),getTail());
	    
	    ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);
	    
	    this.head = pair.head;
	    this.tail = pair.tail;

		
	}
	
	/**
	 * Sorts the list of squares by location; right-most gets priority with lower y being tie breaker.
	 */
	public void sortLoc() {
		Comparator<Square> comp = new Comparator<Square>() {
	    	public int compare(Square s1, Square s2) {
	    		if(s1.getUpperLeftX()-s2.getUpperLeftX()==0) {
	    			return (s1.getUpperLeftY()-s2.getUpperLeftY());
	    		}//if x's are equal, break tie by Y value
	    		return (s1.getUpperLeftX()-s2.getUpperLeftX());
	    	}//otherwise just exit
	    };//compare by location
	    
	    ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<Square>(getHead(),getTail());
	    
	    ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);
	    
	    this.head = pair.head;
	    this.tail = pair.tail;
	}
}
