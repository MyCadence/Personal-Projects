// TO DO: add your implementation and JavaDocs.

/**
 * StrieNode class contains an important member "children" of HashMap type.
 */
public class StrieNode{

	// Use a HashMap to hold children nodes.
	// Keys of the map can be any Character while values are the children nodes.
	// Each key in the map leads to a child node of this node.
	/**
	 * Member "children" of data type HashMap.
	 */
	private HashMap<Character, StrieNode> children; 

	// Marks the end of a word
	/**
	 * Member "endMarker" of data type boolean. Marks the end of a word.
	 */
	private boolean endMarker;  
	
	// OPTIONAL boolean flag that you can use.
	// It is completely optional to use this in your implementation.
	// We will NOT test its usage but it is provided for more flexibility.
	// Still, remember to write JavaDoc for it.
	
	/**
	 * Member "INIT_MAP_LENGTH" of constant value 5. The default size of children.
	 */
	private static final int INIT_MAP_LENGTH = 5; //default length of the hashmap to start

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED

	/**
	 * Member "numChildren" the number of children within the StrieNode.
	 */
	private int numChildren;
	
	/**
	 * The StrieNode default constructor.
	 */
	public StrieNode(){
		// Constructor
		// Initialize anything that needs initialization
		// HashMap must start with INIT_MAP_LENGTH entries
		this.numChildren = 0;
		this.endMarker = false;
		children = new HashMap<>(INIT_MAP_LENGTH);
		// O(1)
	}

	/**
	 * Returns the number of children.
	 * @return numChildren.
	 */
	public int getNumChildren(){
		//report number of children nodes
		//O(1)
		
		return numChildren; 	//default return; change or remove as needed	
	}

	/**
	 * Returns the storage of all children.
	 * @return children.
	 */
	public HashMap<Character, StrieNode> getAllChildren(){
		// return the storage of all children
		// O(1)
		
		return children;//default return; change or remove as needed	
	}

	/**
	 * Sets the end marker to indicate this node is the end of a string/word.
	 */
	public void setEnd(){
		endMarker = true;
		// Sets the end marker to indicate this node is the end of a string/word
		// O(1)
	}
	
	/**
	 * Does the opposite of setEnd(). Unsets the end marker.
	 */
	public void unsetEnd(){
		endMarker = false;
		// Unsets the end marker
		// O(1)
	}
	
	/**
	 * Checks whether the current node is marked as the end of a string/word.
	 * @return endMarker.
	 */
	public boolean isEnd(){
		// Checks whether the current node is marked as the end of a string/word
		// O(1)
		
		return endMarker; //default return; change or remove as needed	
	}
	
	/**
	 * Returns true if node has a child corresponding to ch. Return false otherwise.
	 * @param ch - the character (key) of the StrieNode to be checked.
	 * @return whether it contains a child or not.
	 */
	public boolean containsChild(char ch){

		// O(1)
		// You can assume all HashMap operations are O(1)
		return children.contains(ch); //default return; change or remove as needed	
	}

	/**
	 * Returns the child of the StrieNode.
	 * @param ch - the character (key) of the StrieNode to be found
	 * @return The StrieNode child.
	 */
	public StrieNode getChild(char ch){
		// returns the child node corresponding to ch
		// returns null if no such node

		// O(1)
		// You can assume all HashMap operations are O(1)
		if(children.contains(ch)==false)
			return null;
		return children.getValue(ch); //default return; change or remove as needed	
	}

	/**
	 * Adds the child of the StrieNode to children (HashMap).
	 * @param ch - the character (key) of the StrieNode to be found.
	 * @param node - the child Node to be returned.
	 */
	public void putChild(char ch, StrieNode node){
		// set a child node corresponding to ch to node
		// if a node already exists, change the mapping of ch to the specified node

		if(children.contains(ch)) {
			children.update(ch, node);
			return;
		}
		numChildren++;
		children.add(ch, node);
		// O(1)
		// You can assume all HashMap operations are O(1) except getKeys() and toString()
	}
		
	/**
	 * Removes the child of the StrieNode.
	 * @param ch - the character (key) of the StrieNode to be found.
	 * @return The StrieNode child to be removed.
	 */
	public boolean removeChild(char ch){
		// remove child node corresponding to ch if a node is present
		// return true if a child was removed;
		// if no such child node, return false
	
		// O(1)
		// You can assume all HashMap operations are O(1) except getKeys() and toString()
		numChildren--;
		return children.remove(ch); //default return; change or remove as needed	
	}

	// Below are methods with the optional flag
	// - implementation of those are optional 
	// - no testing on them in grading
	// Still, remember to write JavaDoc for them.

}
