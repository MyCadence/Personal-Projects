/*
 * @author Arron Birhanu
  */
/**
 * t data type being compared.
 * @param <T> type being compared
 */
public class MySortedArray<T extends Comparable<T>> {

    /**
     *Default capacity is capacity of 2.
    */
    private static final int DEFAULT_CAPACITY = 2;
    
    //underlying array for storage -- you MUST use this for credit!
    //Do NOT change the name or type
    /**
     * generic data array declaration.
     */
    private T[] data;
    
    /**
     * size or number of non-null values in data.
     */
    private int size;
    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

    /**
     *constructor of MySortedArray Class.
     */
    @SuppressWarnings("unchecked")
    public MySortedArray() {
        // Constructor
        data = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
        // Initial capacity of the storage should be DEFAULT_CAPACITY
        // Hint: Can't remember how to make an array of generic Ts? It's in the textbook...

    }

    /**
     *creating an array with a capacity argument.
     * @param initialCapacity the starting capacity passed in
     */
    @SuppressWarnings("unchecked")
    public MySortedArray(int initialCapacity) {
        // Constructor
        // Initial capacity of the storage should be initialCapacity
        if(initialCapacity<2){
            throw new IllegalArgumentException("Capacity must be at least 2!");
        }
        data = (T[]) new Object[initialCapacity];
        size = 0;
        // Throw IllegalArgumentException if initialCapacity is smaller than 
        // 2 (which is the DEFAULT_CAPACITY). 
        // Use this _exact_ error message for the exception 
        //  (quotes are not part of the message):
        //    "Capacity must be at least 2!"

    }

    /**
     * number of non-null elements in the array.
     * @return returns the member size
     */
    @SuppressWarnings("unchecked")
    public int size() {	
        // Report the current number of elements
        // O(1)

        return size; //default return, remove/change as needed

    }  

    /**
     * capacity number of null values + number of non-null vals.
     * @return returns the capacity or .length of array
     */
    @SuppressWarnings("unchecked")
    public int capacity() { 
        // Report max number of elements before the next expansion
        // O(1)

        return data.length; //default return, remove/change as needed
    }

    /**
     * adds a value into the array and sorts it.
     * @param value the value being added into the array
     */
    @SuppressWarnings("unchecked")
    public void add(T value){
        T temp=null;
        if(value==null){
            throw new IllegalArgumentException("Cannot add: null value!");
        }
        if(size==capacity()){
            doubleCapacity();
        }
        if(size==capacity()){
            throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
        }

        size++;
        if(size==1){
            data[0]=value;
        }//alice null

        for(int i=size-2; i>=0; i--){
            if(value.compareTo(data[i])>=0){
                //System.out.println(value);
                data[i+1] = value;

                break;
            }//alice charlie

            data[i+1] = data[i];
            if(i==0)
                data[0]=value;
            // alice charlie bob
            // 1 2 2 4
            // 0 1 2 4
        }


        // 2 3 4
        // 1 2 3 4
        // 1 2 3 4 5 6 9
        // Insert the given value into the array and keep the array _SORTED_ 
        // in ascending order. 

        // If the array already contains value(s) that are equal to the new value,
        // make sure that the new value is added at the end of the group. Check examples
        // in project spec and main() below.

        // Remember to use .compareTo() to perform order/equivalence checking.

        // Note: You _can_ append an item (and increase size) with this method.
        // - You must call doubleCapacity() if no space is available. 
        // - Check below for details of doubleCapacity().
        // - For the rare case that doubleCapacity() fails to increase the max 
        //   number of items allowed, throw IllegalStateException.
        // - Use this _exact_ error message for the exception 
        //  (quotes are not part of the message):
        //    "Cannot add: capacity upper-bound reached!"


        // Note: The value to be added cannot be null.
        // - Throw IllegalArgumentException if value is null. 
        // - Use this _exact_ error message for the exception 
        //  (quotes are not part of the message):
        //    "Cannot add: null value!"

        // O(N) where N is the number of elements in the storage

    }

    /**
     * getting the value at index number.
     * @param index the int representing the index of an element
     * @return returns the element of datatype generic at index
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        // Return the item at the given index
        if(index>size-1 || index<0)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
        // O(1)
        return data[index];
        // For an invalid index, throw an IndexOutOfBoundsException.
        // Use this code to produce the correct error message for
        // the exception (do not use a different message):
        //	  "Index " + index + " out of bounds!"

    }

    /**
     * replacing values at the index of array with generic value.
     * @param index the location of element in the generic array
     * @param value the value of the element meant to replace
     * @return returns whether or not it successfully replaced
     */
    @SuppressWarnings("unchecked")
    public boolean replace(int index, T value) {
        if(value==null){
            throw new IllegalArgumentException("Cannot add: null value!");
        }
        if(index<0 || index>size-1){
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
        }

        if(index==0){
            if(value.compareTo(data[index+1])==1){
                return false;
            }
            data[index]=value;
            return true;
        }

        if(index==size-1){
            if(value.compareTo(data[index-1])==-1){
                return false;
            }
            data[index]=value;
            return true;
        }
        if((value.compareTo(data[index-1])==0||value.compareTo(data[index-1])==1)&&(value.compareTo(data[index+1])==0||value.compareTo(data[index+1])==-1)){
            data[index] = value;
            return true;
        }
        else{
            return false;
        }
        // Change the item at the given index to be the given value.

        // For an invalid index, throw an IndexOutOfBoundsException. 
        // Use the same error message as get(index).
        // Note: You _cannot_ add new items with this method.

        // For a valid index, if value is null, throw IllegalArgumentException.
        // Use the exact same error message as add(value).

        // The array must keep to be sorted in ascending order after updating. 
        // For a valid index, return false if setting the value at index violates 
        // the required order hence can not be performed; no change should be made 
        // to the array.  Otherwise, change the item and return true. 

        // O(1)

    }

    /**
     * adds a value of generic type at location index of array.
     * @param index the location of element meant to be added
     * @param value the value being added into the generic array
     * @return returns whether or not successfully added
     */
    @SuppressWarnings("unchecked")
    public boolean add(int index, T value) {
        if(value==null){
            size--;
            throw new IllegalArgumentException("Cannot add: null value!");}
        if(index>size || index<0){
            size--;
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");}
        if(size==capacity())
            doubleCapacity();
        if(size==capacity()){
            throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
        }

        size++;
        if(size==1&&index==0){
            data[0]=value;
            return true;
        }
        if(size==2&&index==1){
            if(data[0].compareTo(value)<=-1){
                data[index]=value;
                return true;
            }
            else{
                size--;
                return false;
            }
        }
        if(size==2&&index==0){
            if(data[0].compareTo(value)>=1){
                data[1] = data[0];
                data[index]=value;
                return true;
            }
            else{
                size--;
                return false;
            }
        }

        if(index==size){
            if(value.compareTo(data[index-1])<=-1){
                size--;
                return false;
            }
            data[index]=value;
            return true;
        }//added as the last element

        if((data[index-1]==null||value.compareTo(data[index-1])==0||value.compareTo(data[index-1])>0)&&(data[index]==null||value.compareTo(data[index])==0||value.compareTo(data[index])<0)){
            for(int i=size-2; i>=index; i--){
                data[i+1] = data[i];
            }
            data[index] = value;
            return true;
        }
        //  0 2 null null
        // (2,4)
        else{
            size--;
            return false;
        }
        // Insert the given value at the given index. Shift elements if needed.
        // Double capacity if no space available. 

        // For an invalid index, throw an IndexOutOfBoundsException. 
        // Use the same error message as get(index).
        // Note: You _can_ append items with this method, which is different from replace().

        // For a valid index, if value is null, throw IllegalArgumentException.
        // Use the exact same error message as add(value). See add(value) above.

        // The array must keep to be sorted in ascending order after updating. 
        // For a valid index, return false if inserting the value at index violates 
        // the required order hence can not be performed; no change should be made 
        // to the array.  Otherwise, insert the value and return true. 

        // You must call doubleCapacity() if no space is available. 
        // Throw IllegalStateException if doubleCapacity() fails.
        // Use the exact same error message as add(value). See add(value) above.

        // O(N) where N is the number of elements in the storage
    } 

    /**
     * deletes the element at index.
     * @param index location of the element
     * @return returns the value of generic type that was deleted
     */
    @SuppressWarnings("unchecked")
    public T delete(int index) {
        if(index>size-1 || index<0)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
        T removed = data[index];
        for(int i=index; i<size-1;i++){
            data[i]=data[i+1];
        }
        data[size-1] = null;//finished deleting
        size--;
        if(size<=capacity()/3){
            halveCapacity();
        }

        return removed;
        // Remove and return the element at the given index. Shift elements
        // to remove the gap. Throw an exception when there is an invalid
        // index (see replace(), get(), etc. above).

        // After deletion, if the number of elements falls below 1/3 
        // of the capacity, you must call halveCapacity() to shrink the storage.
        // - Check halveCapacity() below for details.
        // - If the capacity would fall below the DEFAULT_CAPACITY, 
        //   shrink to the DEFAULT_CAPACITY. This should be implemented by
        //   halveCapacity(). remember to set the arraylist equal to the arraylist call

        // O(N) where N is the number of elements currently in the storage

    }  

    /**
     * doubles the capacity of the array to make space for data.
     * @return returns whether it successfully doubled capacity
     */
    @SuppressWarnings("unchecked")
    public boolean doubleCapacity(){
        // Double the max number of items allowed in data storage.
        // Remember to copy existing items to the new storage after expansion.

        if(capacity()==Integer.MAX_VALUE){
            return false;
        }
        if(capacity()*2>Integer.MAX_VALUE){
            T dataUpdate[] = (T[]) new Comparable[Integer.MAX_VALUE];
            for(int i=0; i<capacity();i++){
                dataUpdate[i] = data[i];
            }
            data = dataUpdate;
            return true;
        }
        // - Out of abundance of caution, we will use (Integer.MAX_VALUE - 50)
        //   as the upper-bound of our capacity.
        // - If double the current capacity would go beyond this upper-bound,
        //   use this upper-bound value as the new capacity.
        // - If the current capacity is already this upper-bound (Integer.MAX_VALUE - 50), 
        //   do not expand and return false.

        // Return true for a successful expansion.

        // O(N) where N is the number of elements in the array
        T dataUpdate[] = (T[]) new Comparable[capacity()*2];
        for(int i=0; i<capacity();i++){
            dataUpdate[i] = data[i];
        }
        data = dataUpdate;
        return true; //default return, remove/change as needed

    }

    /**
     * divides the capacity in two if there is alot of null space.
     * @return whether or not it successfully divided cap by two
     */
    @SuppressWarnings("unchecked")
    public boolean halveCapacity(){
        int halfCap = capacity()/2;
        if(halfCap<DEFAULT_CAPACITY){
            halfCap = DEFAULT_CAPACITY;
        }
        if(size>halfCap)
            return false;
        // Reduce the max number of items allowed in data storage by half.
        // - If the current capacity is an odd number, _round down_ to get the 
        //   new capacity;
        // - If the new capacity would fall below the DEFAULT_CAPACITY, 
        //   shrink to the DEFAULT_CAPACITY;
        // - If the new capacity (after necessary adjustment to DEFAULT_CAPACITY) 
        //   cannot hold all existing items, do not shrink and return false;
        // - Return true for a successful shrinking.

        // Remember to copy existing items to the new storage after shrinking.

        // O(N) where N is the number of elements in the array
        T dataUpdate[] = (T[]) new Comparable[halfCap];
        for(int i=0; i<halfCap;i++){
            dataUpdate[i] = data[i];
        }
        data = dataUpdate;
        return true; //default return, remove/change as needed
    }
    //******************************************************
    //*******     BELOW THIS LINE IS TESTING CODE    *******
    //*******      Edit it as much as you'd like!    *******
    //*******		Remember to add JavaDoc			 *******
    //******************************************************

    /**
     * the toString method from Object being overridden here.
     * @return the string format of the generic array
     */
    @SuppressWarnings("unchecked")
    public String toString() {
        //This method is provided for debugging purposes
        //(use/modify as much as you'd like), it just prints
        //out the MySortedArray for easy viewing.
        StringBuilder s = new StringBuilder("MySortedArray with " + size()
                + " items and a capacity of " + capacity() + ":");
        for (int i = 0; i < size(); i++) {
            s.append("\n  ["+i+"]: " + get(i));
        }
        return s.toString();

    }


    /**
     * main method goes here.
     * @param args the command line arguments
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args){
        //These are _sample_ tests. If you're seeing all the "yays" that's
        //an excellend first step! But it might not mean your code is 100%
        //working... You may edit this as much as you want, so you can add
        //own tests here, modify these tests, or whatever you need!

        //create a MySortedArray of integers
        MySortedArray<Integer> nums = new MySortedArray<>();
        if((nums.size() == 0) && (nums.capacity() == 2)){
            System.out.println("Yay 1");
        }

        //append some numbers 
        for(int i = 0; i < 3; i++) {
            nums.add(i,i*2);
        }
        //uncomment to check the array details
        //System.out.println(nums.toString());
        //

        if(!nums.add(nums.size(),1) && nums.size() == 3 && nums.get(2) == 4 && nums.capacity() == 4){
            System.out.println("Yay 2");
        }
        //System.out.println(nums.toString());

        //add more numbers, your insertion need to keep the array sorted
        nums.add(1);
        nums.add(-1);
        nums.add(5);
        //System.out.println(nums.toString());
        if (nums.size() == 6 && nums.get(0)==-1 && nums.get(2) == 1 && nums.get(5) == 5){
            System.out.println("Yay 3");
        }
        //System.out.println(nums.toString());

        //add with index
        if (nums.add(4,2) && nums.add(3,2) && nums.get(3) == nums.get(4) 
            && nums.get(4) == nums.get(5) && nums.get(5)== 2){ 	
            System.out.println("Yay 4");		
        }
        //System.out.println(nums.toString());

        //replace with index
        if (nums.replace(5,3) && nums.get(5)==3 && nums.replace(6,5) && nums.get(6)==5
            && !nums.replace(1,2) && nums.get(1) == 0){
            System.out.println("Yay 5");				
        }
        //System.out.println(nums.toString());

        MySortedArray<String> names = new MySortedArray<>();

        //insert some strings
        names.add("alice");
        names.add("charlie");

        names.add("bob");
        //System.out.println(names.toString());
        names.add("adam");
        //System.out.println(names.toString());

        //delete
        if (names.add(4,"emma") && names.delete(3).equals("charlie")){
            System.out.println("Yay 6");
        }

        names.delete(0);
        names.delete(0);

        //shrinking
        if (names.size()==2 && names.capacity() == 4){
            System.out.println("Yay 7");
        }
        //System.out.println(names.toString());

        //insert equal values: sorted by insertion order
        String dylan1 = new String("dylan");
        String dylan2 = new String("dylan");
        names.add(dylan1);
        names.add(dylan2);
        if (names.size()==4 && names.get(1) == dylan1 && names.get(2) == dylan2
            && names.get(1)!=names.get(2)){
            System.out.println("Yay 8");		
        }
        //System.out.println(names.toString());

        // exception checking example
        // make sure you write more testings by yourself
        try{
            names.get(-1);
        }
        catch(IndexOutOfBoundsException ex){
            if (ex.getMessage().equals("Index -1 out of bounds!"))
                System.out.println("Yay 9");
        }

        // call doubleCapacity() and halveCapacity() directly
        if (names.doubleCapacity() && names.capacity() == 8 
            && names.halveCapacity() && names.capacity() == 4
            && !names.halveCapacity() && names.capacity() == 4){
            System.out.println("Yay 10");

        }
        //System.out.println(names.toString());

    }
	

}