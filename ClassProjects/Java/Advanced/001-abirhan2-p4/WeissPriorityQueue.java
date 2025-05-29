//TODO:
//  (1) Update this code to meet the style and JavaDoc requirements.
//			Why? So that you get experience with the code for a heap!
//			Also, this happens a lot in industry (updating old code
//			to meet your new standards). We've done this for you in
//			WeissCollection and WeissAbstractCollection.
//  (2) Implement getIndex() method and the related map integration
//			 -- see project description
//  (3) Implement update() method -- see project description

import java.util.Iterator;
import java.util.Comparator;
import java.util.NoSuchElementException;

//You may use the JCF HashMap or the HashMap from Project 3
//that depends on your ThreeTenHashSet.

//To use the JCF class, uncomment this line:
//import java.util.HashMap;

//To use your code, just copy over HashMap and ThreeTenHashSet
//from Project 3 and DON'T uncomment the line above.


/**
 * PriorityQueue class implemented via the binary heap.
 * From your textbook (Weiss)
 * @param <AnyT> generic type
 */
public class WeissPriorityQueue<AnyT> extends WeissAbstractCollection<AnyT>
{
	//you may not have any other class variables, only this one
	//if you make more class variables your priority queue class
	//will receive a 0, no matter how well it works
	/**
	 * The default max number of open spots (may be altered in constructor parameters).
	 */
	private static final int DEFAULT_CAPACITY = 100;

	//you may not have any other instance variables, only these four
	//if you make more instance variables your priority queue class 
	//will receive a 0, no matter how well it works
	/**
	 * The current number of items in the data.
	 */
	private int currentSize;
	/**
	 * The array of type AnyT.
	 */
	private AnyT [ ] array;
	/**
	 * The compare instance to compare the items - for the priority queue.
	 */
	private Comparator<? super AnyT> cmp; 
	/**
	 * The hash map that contains all the indices of the items in the data - array.
	 */
	private HashMap<AnyT, Integer> indexMap;
	
	
	//you implement this
	/**
	 * Retrieved the index of any data inside the heap with the help of indexMap.
	 * @param x the data passed in
	 * @return the index of the data passed in
	 */
	public int getIndex(AnyT x) {
		//average case O(1)
		//returns the index of the item in the heap,
		//or -1 if it isn't in the heap
		
		if(indexMap.getValue(x)==null)
			return -1;

		return indexMap.getValue(x);
	}
	
	//you implement this
	/**
	 * Updates any particular data already present in the queue.
	 * @param x - the data replacing the particular data present in the queue
	 * @return whether it was successful
	 */
	public boolean update(AnyT x) {
		
		//O(lg n) average case
		//or O(lg n) worst case if getIndex() is guarenteed O(1)
		indexMap.update(x,getIndex(x));
		array[getIndex(x)] = x;
		
		buildHeap();
		return true; //dummy return, make sure to replace this!
	}
	
	/**
	 * Construct an empty PriorityQueue.
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue( )
	{
		currentSize = 0;
		cmp = null;
		array = (AnyT[]) new Object[ DEFAULT_CAPACITY + 1 ];
		indexMap = new HashMap<AnyT,Integer>(DEFAULT_CAPACITY+1);
	}
	
	/**
	 * Construct an empty PriorityQueue with a specified comparator.
	 * @param c - the comparator instance data
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue( Comparator<? super AnyT> c )
	{
		currentSize = 0;
		cmp = c;
		array = (AnyT[]) new Object[ DEFAULT_CAPACITY + 1 ];
		indexMap = new HashMap<AnyT,Integer>(DEFAULT_CAPACITY+1);
	}
	
	 
	/**
	 * Construct a PriorityQueue from another Collection.
	 * @param coll - a given collection now taken on by the queue
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue( WeissCollection<? extends AnyT> coll )
	{
		cmp = null;
		currentSize = coll.size( );
		array = (AnyT[]) new Object[ ( currentSize + 2 ) * 11 / 10 ];
		indexMap = new HashMap<AnyT,Integer>((currentSize+2)*11/10);
		int i = 1;
		for( AnyT item : coll ) {
			i++;
			array[ i ] = item;
			indexMap.add(item, i);
		}
		buildHeap( );
	}
	
	/**
	 * Compares lhs and rhs using comparator if
	 * provided by cmp, or the default comparator.
	 * @param lhs - the first data being compared
	 * @param rhs - the second data being compared
	 * @return the integer indicating which of the two data is greater/lesser
	 */
	@SuppressWarnings("unchecked")
	private int compare( AnyT lhs, AnyT rhs )
	{
		if( cmp == null )
			return ((Comparable)lhs).compareTo( rhs );
		else
			return cmp.compare( lhs, rhs );	
	}
	
	/**
	 * Adds an item to this PriorityQueue.
	 * @param x any object.
	 * @return true.
	 */
	public boolean add( AnyT x )
	{
		if( currentSize + 1 == array.length )
			doubleArray( );

		// Percolate up
		int hole = ++currentSize;
		array[ 0 ] = x;
		
		for( ; compare( x, array[ hole / 2 ] ) < 0; hole /= 2 ) {
			array[ hole ] = array[ hole / 2 ];
			indexMap.remove(array[hole/2]);
			indexMap.add(array[hole/2], hole);
		}
		
		array[ hole ] = x;
		indexMap.add(x, hole);
		return true;
	}
	
	/**
	 * Returns the number of items in this PriorityQueue.
	 * @return the number of items in this PriorityQueue.
	 */
	public int size( )
	{
		return currentSize;
	}
	
	/**
	 * Make this PriorityQueue empty.
	 */
	public void clear( )
	{
		currentSize = 0;
	}
	
	/**
	 * Returns an iterator over the elements in this PriorityQueue.
	 * The iterator does not view the elements in any particular order.
	 * @return the iterator object
	 */
	public Iterator<AnyT> iterator( )
	{
		return new Iterator<AnyT>( )
		{
			int current = 0;
			
			public boolean hasNext( )
			{
				return current != size( );
			}
			
			@SuppressWarnings("unchecked")
			public AnyT next( )
			{
				if( hasNext( ) )
					return array[ ++current ];
				else
					throw new NoSuchElementException( );
			}
			
			public void remove( )
			{
				throw new UnsupportedOperationException( );
			}
		};
	}
	 
	/**
	 * Returns the smallest item in the priority queue.
	 * @return the smallest item.
	 * @throws NoSuchElementException if empty.
	 */
	public AnyT element( )
	{
		if( isEmpty( ) )
			throw new NoSuchElementException( );
		percolateDown( 1 );
		return array[ 1 ];
	}
	
	/**
	 * Removes the smallest item in the priority queue.
	 * @return the smallest item.
	 * @throws NoSuchElementException if empty.
	 */
	public AnyT remove( )
	{
		if(indexMap.size()==0)
			throw new NoSuchElementException("The queue is empty and cannot be removed from, add items!");
		
		if(indexMap.size()==1) {
			AnyT minItem = array[currentSize];
			indexMap.remove(array[currentSize]);
			return minItem;
		}
		AnyT minItem = element( );
		AnyT temp = array[currentSize];
		AnyT temp1 = array[1];

		indexMap.remove(array[currentSize]);
		array[ 1 ] = array[currentSize];
		indexMap.remove(temp1);
		indexMap.add(temp, 1);
		
		currentSize--;
		percolateDown( 1 );

		
		return minItem;
	}


	/**
	 * Establish heap order property from an arbitrary
	 * arrangement of items. Runs in linear time.
	 */
	private void buildHeap( )
	{
		for( int i = currentSize / 2; i > 0; i-- )
			percolateDown( i );
	}

	/**
	 * Internal method to percolate down in the heap.
	 * @param hole the index at which the percolate begins.
	 */
	private void percolateDown( int hole )
	{
		int child;
		AnyT tmp = array[ hole ];

		for( ; hole * 2 <= currentSize; hole = child )
		{
			child = hole * 2;
			if( child != currentSize &&
					compare( array[ child + 1 ], array[ child ] ) < 0 )
				child++;
			if( compare( array[ child ], tmp ) < 0 ) {
				indexMap.update(array[child], hole);//hashmap edited to match array
				array[ hole ] = array[ child ];
				indexMap.update(tmp, child);//hashmap edited to match array
			}
			else
				break;
		}
		array[ hole ] = tmp;
	}
	
	/**
	 * Internal method to extend array.
	 */
	@SuppressWarnings("unchecked")
	private void doubleArray( )
	{
		AnyT [ ] newArray;

		newArray = (AnyT []) new Object[ array.length * 2 ];
		for( int i = 0; i < array.length; i++ )
			newArray[ i ] = array[ i ];
		array = newArray;
	}
}
