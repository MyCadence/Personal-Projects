// TO DO: add your implementation and JavaDocs.

import java.util.Iterator;

/**
 * The ThreeTenHashSet class of generic type T.
 * @param <T> - of generic type.
 */
class ThreeTenHashSet<T> {
	// This is the class that you need to write to implement a set 
	// using a hash table with _separate chaining_.

	// Underlying storage table -- you MUST use this for credit!
	// Do NOT change the name or type
	/**
	 * Member "table" an array of SimpleList objects.
	 */
	private SimpleList<T>[] table;

	/**
	 * Member "size" the number of Key,Value pairs in the SimpleList object array (table).
	 */
	private int size;
	
	/**
	 * Member "capacity" the number of array spots available to store Key,Value pairs.
	 */
	private int capacity;
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	
	/**
	 * Constructor of ThreeTenHashSet class that passes in the initial length (user preference).
	 * @param initLength - the starting capacity of the array (table) passed in.
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenHashSet(int initLength){
		// Create a hash table where the storage is with initLength 
		// Initially the table is empty 
		// You can assume initLength is >= 2
		this.table = (SimpleList<T>[]) new SimpleList[initLength];
		this.capacity = initLength;
		this.size = 0;
		// O(1)
	}

	/**
	 * capacity of the member table.
	 * @return capacity.
	 */
	public int capacity() {
		// return the storage length
		// O(1)
		
		return capacity; //default return; change or remove as needed
	}

	/**
	 * size of the member table.
	 * @return size.
	 */
	public int size() {
		// return the number of items in the table
		// O(1)
		
		return size; //default return; change or remove as needed
	}
	
	
	/**
	 * Adding a key,value pair into the table.
	 * @param value - of generic type, the key being passed in.
	 * @return boolean - whether operation was successful.
	 */
	public boolean add(T value) {
		
		// Add an item to the set 
		// - return true if you successfully add value; 
		// - return false if the value can not be added
		//    (i.e. the value already exists or is null)

		if(contains(value)==true||value==null)
			return false;
		
		if((double)size/(double)capacity>2.0) {
			rehash(2*capacity);
		}
		
		if(table[Math.abs(value.hashCode())%capacity]==null)
			table[Math.abs(value.hashCode())%capacity] = new SimpleList<>();
		table[Math.abs(value.hashCode())%capacity].addLast(value);
		size++;
		// NOTES:
		// - Always add value to the tail of the chain.
		// - If load of table is at or above 2.0, rehash() to double the length.
				
		// Time complexity not considering rehash(): 
		// O(N) worst case, where N is the number of values in table
		// O(N/M) average case where N is the number of values in table and M is the table length
		
		return true; //default return; change or remove as needed
	}
	
	/**
	 * Removing a key,value pair from the table.
	 * @param value - of generic type, the key being passed in.
	 * @return boolean - whether operation was successful.
	 */
	public boolean remove(T value) {
		// Removes a value from the set
		// - return true if you remove the item
		// - return false if the item is not present
		if(table[Math.abs(value.hashCode())%capacity]==null) {
			return false;
		}
		size--;
		return table[Math.abs(value.hashCode())%capacity].remove(value);
		// O(N) worst case, where N is the number of values in table
		// O(N/M) average case where N is the number of values in table and M is the table length

		//default return; change or remove as needed
	}
	
	/**
	 * Checking for a key,value pair from the table.
	 * @param value - of generic type, the key being passed in.
	 * @return boolean - whether operation was successful.
	 */
	public boolean contains(T value) {
		// Return true if value is in the set
		// Return false otherwise
		if(table[Math.abs(value.hashCode())%capacity]==null)
			return false;
		
		return table[Math.abs(value.hashCode())%capacity].get(value)!=null;
		// O(N) worst case, where N is the number of values in table
		// O(N/M) average case where N is the number of values in table and M is the table length
		//default return; change or remove as needed
	}
	
	
	/**
	 * Retrieving a key,value pair from the table.
	 * @param value - of generic type, the key being passed in.
	 * @return the generic value - returns the value stored at hashIndex.
	 */
	public T get(T value) {
		if(table[Math.abs(value.hashCode())%capacity]==null)
			return null;
		// Return null if value is not present in set.
		// Return the item _FROM THE HASH TABLE_ if it was found
		//  - do not return the incoming parameter, while "equivalent" they
		// may not be the same)
		return table[Math.abs(value.hashCode())%capacity].get(value);
		// O(N) worst case, where N is the number of values in table
		// O(N/M) average case where N is the number of values in table and M is the table length
		
		
		// NOTE: HashMap uses a ThreeTenHashSet of Pair<Key,Value>. In that class,
		// this method is used in the following way:
		//
		// - HashMap passes in a Pair<Key,Value> to search for
		// - The key is "real", the value may be a "dummy" or null
		// - The Pair<Key,--> passed in and the Pair<Key,Value> in the hash table
		//   will match with .equals() -- see equals() in the Pair class
		// - If this method finds the Pair<Key,-->, the returned value must be the 
		//   **actual** hash table entry, which includes both matching key and a valid
		//   non-null value.  
		//
		// Because of this, get() in this class need to be careful too, and it *must*
		// return the value from the hash table and not the parameter.
		//default return; change or remove as needed
	}
	
	
	/**
	 * Resizes (increases), the capacity of the hashSet table if the load is >=2 by default.
	 * @param newCapacity - The new capacity of the hashSet table.
	 * @return boolean - whether the operation was successful.
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int newCapacity) {
		// Rehash to table size newCapacity
		// - If the new capacity is no greater than the current capacity,
		//   do not rehash and return false;
		// - otherwise, return true after resizing
		if(newCapacity<=capacity)
			return false;
		SimpleList<T>[] newTable = (SimpleList<T>[]) new SimpleList[newCapacity];
		for(int i=0; i<capacity; i++) {
			if(table[i]==null) {
				continue;
			}//if linked list is null from original table, ignore and continue to next linked list
			Iterator<T> iter = table[i].iterator();//create the iterator object

			while(iter.hasNext()) {//iterate until no elements remain in the linked list
				T temp = iter.next();
				if(newTable[Math.abs(temp.hashCode())%newCapacity]==null)//if we are adding to a new linked list in our new table...
					newTable[Math.abs(temp.hashCode())%newCapacity] = new SimpleList<T>();//create a new linked list object
				newTable[Math.abs(temp.hashCode())%newCapacity].addLast(temp);//add values to that linked list
			}
			
		}
		table = newTable;
		// You can assume the newCapacity is always < Integer.MAX_VALUE - 50.
		
		// O(N+M) where N is the number of values in table and M is the table size
		// Hint: Take advantage of the iterator of SimpleList to meet big-O requirements.
		capacity = newCapacity;
		return true; //default return; change or remove as needed
				
	}
	
	// Provided: do not change but you will need to add JavaDoc
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("ThreeTenHashSet (non-empty entries):\n");
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null && table[i].size()!=0) {
				s.append(i);
				s.append(" :");
				s.append(table[i]);
				s.append("\n");
			}
		}
		return s.toString().trim();
	}
	
	// Provided: do not change but you will need to add JavaDoc
	/**
	 * Formats the threetenhashset object into a user-friendly (readable) format.
	 * @return string - the formatted string object.
	 */
	public String toStringDebug() {
		StringBuilder s = new StringBuilder("ThreeTenHashSet (all entries):\n");
		for(int i = 0; i < table.length; i++) {
			s.append(i);
			s.append(" :");
			s.append(table[i]);
			s.append("\n");
		}
		return s.toString().trim();
	}

	// Provided: do not change but you will need to add JavaDoc
	/**
	 * Returns all items in a set as a list.
	 * @return SimpleList T type - the formatted version of the SimpleList object and every node object it contains.
	 */
	public SimpleList<T> allValues(){
		SimpleList<T> all = new SimpleList<>();
		for(int i = 0; i < table.length; i++) {
			if (table[i]!=null){
				for (T value: table[i])
					all.addLast(value);
			}
		}
		return all;
	}


}