/**Node class.
 * 
 * @author aaron
 *
 * @param <T> Generic type
 */
class Node<T> {
	/**
	 * Data Field Data of Type Generic.
	 */
	public T data;
	/**
	 * Data Field next of Type Generic Node.
	 */
	public Node<T> next;
	/**
	 * Data Field prev of Type Generic Node.
	 */
	public Node<T> prev;
			
	/**
	 * Default Constructor.
	 */
	public Node() {
				
	}
			
	/**
	 * Non-Default Constructor.
	 * @param data Takes in generic data
	 */
	public Node(T data) {
		this.data = data;
	}
}