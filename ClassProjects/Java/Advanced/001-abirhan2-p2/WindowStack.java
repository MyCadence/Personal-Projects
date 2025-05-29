//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A stack of windows within the window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 */
public class WindowStack {
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >

	/**
	 * Head of Type Node of Generic Type Window.
	 */
	private Node<Window> head;
	/**
	 * Tail of Type Node of Generic Type Window.
	 */
	private Node<Window> tail;
	/**
	 * numWindows of type int.
	 */
	private int numWindows;
	/**
	 * temp of Type Node of Generic Type Window.
	 */
	private Node<Window> temp;
	
	/**
	 * Default Constructor.
	 */
	public WindowStack() {
		//Any initialization code you need.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		this.head = null;
		this.tail = null;
		numWindows = 0;
	}
	
	/**
	 * Returns the head of the Window Stack.
	 * @return Node of Generic Type Window
	 */
	public Node<Window> getHead() {
		//Returns the head (top) of the stack of windows.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate head of the list.
		
		//< YOUR_CODE_HERE >
		
		return head; //dummy return, replace this!
	}
	
	/**
	 * Returns the tail of the Window Stack.
	 * @return Node of Generic Type Window
	 */
	public Node<Window> getTail() {
		//Returns the tail (bottom) of the stack of windows.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate tail of the list.
		
		//< YOUR_CODE_HERE >
		
		return tail; //dummy return, replace this!
	}
	
	/**
	 * Returns the number of windows.
	 * @return int
	 */
	public int numWindows() {
		//Gets the number of windows in the stack.
		
		//O(1)
		
		return numWindows;
	}
	
	/**
	 * Adds a new Window Object to the Window Stack.
	 * @param r - Window Object being added to Window Stack
	 */
	public void add(Window r) {
		//Add a window at the top of the stack.
		
		//O(1)
		
		//throw IllegalArgumentException for invalid windows
		
		//Note: the "top" of the stack should
		//be the head of your linked list.
		
		//< YOUR_CODE_HERE >
		if(r==null) {
			throw new IllegalArgumentException();
		}
		temp = new Node<Window>(r);

		
        if(head==null){
            head = temp;
            tail = temp;
            numWindows++;
            return;
        }//if there are no windows present
       
        head.prev = temp;
        temp.next = head;
        head = temp;
        temp.prev = null;
        //add to the front of the linked list (head)

        numWindows++;
	}
	
	/**
	 * Whether a right or left click action was performed.
	 * @param x - coordinate
	 * @param y - coordinate
	 * @param leftClick - left click is true, right click is false
	 * @return boolean
	 */
	public boolean handleClick (int x, int y, boolean leftClick) {
		//The mouse has been clicked at position (x,y).
		//Left clicks are move windows to the top of the
		//stack or pass the click on to the window at the
		//top. Right clicks remove windows.
		
		//Returns true if the click was handled, false
		//if no window was found.
		
		//O(n) where n is the number of windows in the stack
		
		
		//Details:
		
		//Find the top-most window on the stack (if any)
		//that contains the given coordinate.
		
		
		//For a left click:
		
		//If the window is not at the top of the stack,
		//move it to the top of the stack without
		//disturbing the order of the other windows.
		//Mark this window as the "selected" window (and
		//ensure the previous selected window is no longer
		//selected).
		
		//If the window is at the top of the stack already,
		//ask the window to handle a click-event (using the
		//Window's handleClick() method).
		
		//If none of the windows on the stack were clicked
		//on, just return.
		
		
		//For a right click:
		
		//Remove the window from the stack completely. The
		//window at the top of the stack should be the 
		//selected window.
		
		
		//Hint #1: This would be a great time to use helper
		//methods! If you just write one giant method...
		//it'll be much harder to debug...
		
		//Hint #2: Make sure to use the methods you wrote
		//in the Window class. Don't write those again!

		
		//< YOUR_CODE_HERE >
		temp = head;
		if(head==null)
			return false;//if nothing is in the stack just return false

		
		if(leftClick==true) {
			
			if(tail==head) {
				if(head.data.contains(x, y)) {
					head.data.handleClick(x, y);
					return true;
				}//add square to window if clicked
				else {
					return false;
				}//if not return false
			}//if one node (window) only
			temp = head;
			while(temp.next!=null){
		        if(temp.data.contains(x, y)) {
		        	if(temp==head) {
		        		head.data.handleClick(x, y);
		        		return true;
		        	}//if it is already in the front just add a square
		        	else {
		        		temp.prev.next = temp.next;
		        		temp.next.prev = temp.prev;
		        		head.prev = temp;
		        		temp.next = head;
		        		head = temp;
		        		temp.prev = null;
		        		return true;
		        	}//if it is not in front, bring it to front
		        }//does it click on a window?
				temp = temp.next;   
		    }//iterating backwards from tail
			if(temp==tail) {
				if(temp.data.contains(x, y)) {
				   	temp.next = head;
			        head.prev = temp;
			        tail = tail.prev;
			        temp.prev = null;
			        head = temp;
			        tail.next = null;
			        return true;
				}//bring window to front
				return false;//leave the windows as they are
			}//if window selected was lowest window in stack (head)
			
		}//if left click
		else {
			if(head.next==null) {
				head = null;
				tail = null;
				numWindows--;
				return true;
			}//if one window only
			if(head.data.contains(x, y)) {
				head.next.prev = null;
				head = head.next;
				numWindows--;
				return true;
			}//remove the tail (front window) from the stack and reference tail.prev (second to front window) as new tail (front window)
			return false;
		}//if right click
		
		return false;
	}

	/**
	 *  Gets an iterator for the stack of windows.
	 *  Windows are returned from bottom to top.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Window> windows() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...
		
		return new Iterator<>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Window> current = getTail();
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Window next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Window ret = current.data;
				current = current.prev;
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
	 * Sorts stack of Windows by Size.
	 */
	public void sortSize() {
		//Sorts the windows in the stack by their area (length x width).
		//MOST of this is done for you, but you still need to assign
		//the returned head and tail back.
		
		//unselect the top window
		this.getHead().data.setSelected(false);
		
		//create a way to compare windows by area
		Comparator<Window> comp = new Comparator<>() {
			/**
	    	 * Comparing objects to determine Greater Size Value.
	    	 * @param w1 - first window object
	    	 * @param w2 - second window object
	    	 * @return int
	    	 */
			public int compare(Window w1, Window w2) {
				return (w1.getWidth()*w1.getHeight())-(w2.getWidth()*w2.getHeight());//height, width are ints w1Area-w2Area=0 
			}
		};
		
		//create a pair of nodes to pass into the sort function
		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());
		
		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);
		
		//make the returned list the head and tail of this list
		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >
	
		this.head=pair.head;//returned's list's head and tail becomes
		this.tail=pair.tail;//this list's head and tail
		//re-select the top of the stack
		this.getHead().data.setSelected(true);
	}
	
	/**
	 * Sorts Stack of Windows by Location.
	 */
	public void sortLoc() {
		//Sorts the windows in the stack by their upper left
		//corner location. Right things (bigger-X) are on top
		//of left things (smaller-X). Tie-breaker: lower
		//things (bigger-Y) top of  higher things (smaller-Y).

		this.getHead().data.setSelected(false);
		
	    Comparator<Window> comp = new Comparator<>() {
	    	/**
	    	 * Comparing objects to determine Greater Location Value.
	    	 * @param w1 - first window object
	    	 * @param w2 - second window object
	    	 * @return int
	    	 */
	    	public int compare(Window w1, Window w2) {
	    		if(w1.getUpperLeftX()-w2.getUpperLeftX()==0) {
	    			return (w1.getUpperLeftY()-w2.getUpperLeftY());
	    		}//if x's are equal, break tie by Y value
	    		return (w2.getUpperLeftX()-w1.getUpperLeftX());
	    	}//otherwise just exit
	    };//compare by location
	    
	    
	    ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<Window>(getHead(),getTail());
	    
	    ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);
	    
	    this.head = pair.head;
	    this.tail = pair.tail;
	    this.getHead().data.setSelected(true);
	    
	}
}

