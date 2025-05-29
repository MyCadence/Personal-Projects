// TO DO: add your implementation and JavaDocs.

/**
 * Strie Class - contains StrieNodes in the form of a tree data structure.
 */
public class Strie{
	//----------------------------------------------------
	// NO MORE INSTANCE VARIABLES ALLOWED!
	//----------------------------------------------------
	
	// Do NOT change the name or type of these variables
	/**
	 * Root of the strie (tree data structure) object.
	 */
	private StrieNode root;  // the root of a strie
	/**
	 * Number of words found in the strie object.
	 */
	private int numWords = 0; // number of words represented by the strie
	
	//----------------------------------------------------
	// NO MORE INSTANCE VARIABLES ALLOWED!
	//----------------------------------------------------
	
	/**
	 * Default constructor of the strie class.
	 */
	public Strie(){
		// Constructor
		// Initialize root to be an empty node; initially no words are in the strie
		this.root = new StrieNode();
		this.numWords = 0;
		// O(1)
	}
	
	/**
	 * Number of words.
	 * @return numWords.
	 */
	public int numWords(){
		// return number of words in the strie
		
		// O(1)
	
		return numWords; //default return; change or remove as needed	
	}
	
	/**
	 * Root of strie.
	 * @return root.
	 */
	public StrieNode getRoot(){
		// return root of the strie
		
		// O(1)
		
		return root; //default return; change or remove as needed	
	
	}

	/**
	 * Adds a word to the strie object.
	 * @param word - the word to be added.
	 */
	public void insert(String word){
		// Insert word into your Strie. 

		StrieNode tempRoot = root;
		for(int i=0; i<word.length(); i++) {
			if(tempRoot.containsChild(word.charAt(i))) {
				tempRoot.putChild(word.charAt(i), tempRoot.getChild(word.charAt(i)));
			}
			else {
				tempRoot.putChild(word.charAt(i), new StrieNode());
			}
			tempRoot = tempRoot.getChild(word.charAt(i));
			
		}
		tempRoot.setEnd();
		numWords++;
			
		// O(n) where n is the number of characters in word
	}


	/**
	 * Checks whether the String word is in the strie object.
	 * @param word - the word being checked.
	 * @return boolean.
	 */
	public boolean contains(String word){
		// Returns true if Strie contains the given word.
		StrieNode temp = root;
		for(int i=0; i<word.length(); i++) {
			if(temp.containsChild(word.charAt(i))) {
				temp = temp.getChild(word.charAt(i));
				continue;
			}
			
			else {
				return false;
			}
		}
		// O(n) where n is the number of characters in word

		return temp.isEnd();//default return; change or remove as needed	
	}

	/**
	 * Removes the string word from the strie object (if present).
	 * @param word - word to be removed.
	 * @return boolean - whether the operation was successful or not.
	 */
	public boolean remove(String word){
		StrieNode tempRoot = root;
		for(int i=0; i<word.length();i++) {
			if(tempRoot.containsChild(word.charAt(i))) {
				if(tempRoot.getNumChildren()==0&&tempRoot.isEnd())
					break;//first condition passed
				tempRoot = tempRoot.getChild(word.charAt(i));
				continue;
			}//if it contains the character in its child nodes
			else {
				return false;
			}//if it doesn't contain the character return false
		}//iterate until end of a word is reached. if not, return false
		if(tempRoot.getChild(word.charAt(word.length()-1))!=null)
			tempRoot = tempRoot.getChild(word.charAt(word.length()-1));//set node mapping to last character of word to be removed
		if(tempRoot.isEnd()&&tempRoot.getNumChildren()>0) {
			tempRoot.unsetEnd();
			numWords--;
			return true;
		}//word within a word in strie

		
		//remove implementation below is only necessary for leaf nodes (letters with no successors)
		tempRoot = root;
		boolean hasMore = false;//does it have more sub trees? (different words branching off)
		for(int i=0; i<word.length(); i++) {
			hasMore = false;
				
			StrieNode checkWord = tempRoot.getChild(word.charAt(i));//checkWord starts its iteration as the child of tempRoot
			for(int j=i+1; j<word.length(); j++) {
				if(checkWord.getNumChildren()>1||checkWord.isEnd()) {
					hasMore=true;
					break;//has more words is true word if there are other nodes down the tree with more than 1 child node
				}
				checkWord = checkWord.getChild(word.charAt(j));//traverse
			}

			if(hasMore==false) {//if you're at a node that has only single nodes below it
				//remove the child node which contains the sub tree for the rest of the word (prefix not included)
				//example: 'word' 'won' --> remove 'n' to remove 'won' or.... to remove 'word', remove the isEnd tag on 'r': []-->'r'-->[]
				if(tempRoot.getChild(word.charAt(i)).getNumChildren()<=1) {
					tempRoot.removeChild(word.charAt(i));
					numWords--;
					return true;
				}
			}

			tempRoot = tempRoot.getChild(word.charAt(i));//if you're removing a child node from the sub tree
		}
		
		return false;//default return; change or remove as needed	
	}

	
	/**
	 * Prints out the characters in the order of each level and hashIndex precedence within each level.
	 * @return the keys of all StrieNodes within the Strie object.
	 */
	public String levelOrderTraversal(){
		StrieNode tempRoot = root;
		SimpleList<StrieNode> node = new SimpleList<StrieNode>();//Linked List of Generic Type <StrieNode>
		SimpleList<Character> letterNode = new SimpleList<Character>();// Linked List of Generic Type <Character>
		String str = "";
		
		node.addLast(tempRoot);//root

		while((letterNode.size()!=0||node.size()!=0)||tempRoot.getNumChildren()!=0) {
			if(tempRoot==root) {
				node.removeFirst();
			}
			for(char ch : tempRoot.getAllChildren().getKeys()) {
				letterNode.addLast(ch);
				node.addLast(tempRoot.getChild(ch));
				str+=ch;
				str+=" ";
			}
			letterNode.removeFirst();
			tempRoot = node.removeFirst();
		}//remove first
		
		tempRoot = root;
		str = str.substring(0,str.length()-1);
		return str;
		// Perform a Breadth First Traversal of your Strie tree
		// and return a string of all characters encountered in the traversal.
		// - If a Strie has no words, return an empty string.
		// - A single space should be padded between characters.
		// - For multiple children of a single node, use the order of characters in 
		// getKeys() of the hash map to determine the traverse order.
		//
		// Check main() for examples.
		
		// Hint: you can use SimpleList to implement a queue easily.
		
		// Note: While there are **no Big-O restrictions** on this
		// method, level order traversals are traditionally O(n)
		// where n is the number of nodes in the tree. This may not
		// be the case here due to the hash table implementation
		// of children.
		
	 //default return; change or remove as needed	
	}


	/**
	 * Retrieves all the words in the Strie object.
	 * @return SimpleList object of generic type (string).
	 */
	public SimpleList<String> getStrieWords(){
		// Return all words currently stored in Strie.
		// If Strie has no words, return null.
		if(root.getAllChildren()==null)
			return null;
		
		SimpleList<String> string = new SimpleList<String>();
		StrieNode tempRoot = root;
		String str = "";
		
		str = levelOrderTraversal();

		str = str.replaceAll("\\s", "");

		//temporary tree to remove the contents one word by one and every time adding the
		//removed word into SimpleList<String> string
		Strie tree = new Strie();
		Strie tempTree = new Strie();
		
		tree.root = root;
		tree.numWords = numWords;

		tempRoot = root;
		
		String addWord = "";
		int i=0;
		while(str.length()!=0) {
			if(tempRoot.containsChild(str.charAt(i))) {
				tempRoot = tempRoot.getChild(str.charAt(i));
				addWord += str.charAt(i);
			}
			i++;
			if(tempRoot.isEnd()) {
				i=0;
				tempRoot = root;
				tree.remove(addWord);
				tempTree.insert(addWord);
				if(tree.numWords!=0) {
					str = tree.levelOrderTraversal();
					str = str.replaceAll("\\s", "");
				}
				string.addLast(addWord);
				addWord = "";
				
				if(tree.numWords==0) 
					str = "";
			}
		}//loop through whole string of trie's characters - str
		tree = tempTree;
		root = tempTree.root;
		return string;//default return; change or remove as needed	
	}

}
