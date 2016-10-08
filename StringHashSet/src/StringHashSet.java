import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * A hash set implementation for Strings. Cannot insert null into the set. Other
 * requirements are given with each method.
 *
 * @author Matt Boutell and <<TODO: your name here >>>. Created Oct 6, 2014.
 */
public class StringHashSet {

	// The initial size of the internal array.
	private static final int DEFAULT_CAPACITY = 5;
	private Node[] array;
	private int capacity,size;

	// You'll want fields for the size (number of elements) and the internal
	// array of Nodes. I also added one for the capacity (the length
	// of the internal array).
	

	public class Node {
		
		// TODO: Implement this class . These are just linked-list style
		// nodes, so you will need at least fields for the data and a reference
		// to the next node, plus a constructor. You can choose to use a
		// NULL_NODE at the end, or not. I chose not to do so this time.
		public String element;
		public Node next;

		public Node(String s) {
			this.element = s;
			this.next = null;
		}
	}

	/**
	 * Creates a Hash Set with the default capacity.
	 */
	public StringHashSet() {
		// Recall that using this as a method calls another constructor
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates a Hash Set with the given capacity.
	 */
	public StringHashSet(int initialCapacity) {
		initialize(initialCapacity);
	}

	private void initialize(int initialCapacity) {
		// TODO: Set the capacity to the given capacity, and initialize the
		// other fields.
		// Why did we pull this out into a separate method? Perhaps another
		// method needs to initialize the hash set as well? (Hint)
		this.capacity = initialCapacity;
		this.array = new Node[this.capacity];
	}

	/**
	 * Calculates the hash code for Strings, using the x=31*x + y pattern.
	 * Follow the specification in the String.hashCode() method in the Java API.
	 * Note that the hashcode can overflow the max integer, so it can be
	 * negative. Later, in another method, you'll need to account for a negative
	 * hashcode by adding Integer.MAX_VALUE before you mod by the capacity
	 * (table size) to get the index.
	 *
	 * This method is NOT the place to calculate the index though.
	 *
	 * @param item
	 * @return The hash code for this String
	 */
	public static int stringHashCode(String item) {
		// TODO: Write this.
		int size = item.length();
		int result = 0;
		for (int i=0;i<size;i++){
			char c = item.charAt(i);
			result = result*31;
			result = result + c;
		}
		return result;

	}

	/**
	 * Adds a new node if it is not there already. If there is a collision, then
	 * add a new node to the -front- of the linked list.
	 * 
	 * Must operate in amortized O(1) time, assuming a good hashcode function.
	 *
	 * If the number of nodes in the hash table would be over double the
	 * capacity (that is, lambda > 2) as a result of adding this item, then
	 * first double the capacity and then rehash all the current items into the
	 * double-size table.
	 *
	 * @param item
	 * @return true if the item was successfully added (that is, if that hash
	 *         table was modified as a result of this call), false otherwise.
	 */
	public boolean add(String item) {
		// TODO: Write this
		if (this.contains(item)){
			return false;
		}
		int hashcode = stringHashCode(item);
		if(hashcode<0)
			hashcode +=Integer.MAX_VALUE;
		int index = hashcode%capacity;
		if (this.array[index]==null){
			this.array[index] = new Node(item);
		}else{
			Node n = this.array[index];
			this.array[index] = new Node(item);
			this.array[index].next = n;
		}
		this.size++;
		
		// rehash
		if (this.size/this.capacity>=2 && this.size%this.capacity>0){
			Node[] old = this.array.clone();
			int oldCapacity = this.capacity;
			this.initialize(this.capacity*2);
			for (int i=0; i < oldCapacity;i++){
				Node n = old[i];
				while(n!=null){
					this.add(n.element);
					n = n.next;
				}
			}
		}
		
		return true;
	}

	/**
	 * Prints an array value on each line. Each line will be an array index
	 * followed by a colon and a list of Node data values, ending in null. For
	 * example, inserting the strings in the testAddSmallCollisions example
	 * gives "0: shalom hola null 1 bonjour null 2 caio hello null 3 null 4 hi
	 * null". Use a StringBuilder, so you can build the string in O(n) time.
	 * (Repeatedly concatenating n strings onto a growing string gives O(n^2)
	 * time)
	 * 
	 * @return A slightly-formatted string, mostly used for debugging
	 */
	public String toRawString() {
		// TODO: Write this
		StringBuilder s = new StringBuilder();
		for(int i = 0; i <capacity; i++){
			if (this.array[i]!=null){
				Node n = this.array[i];
				s = s.append(i);
				s = s.append(": ");
				while (n!=null){
					s.append(n.element);
					s.append(" ");
					n = n.next;
				}
			}else{
				s = s.append(i);
				s = s.append(": ");
			}
			s = s.append("null");
			s=s.append("\n");
		}
		return s.toString();
	}

	/**
	 * 
	 * Checks if the given item is in the hash table.
	 * 
	 * Must operate in O(1) time, assuming a good hashcode function.
	 *
	 * @param item
	 * @return True if and only if the item is in the hash table.
	 */
	public boolean contains(String item) {
		// TODO: Write this
		for (int i=0; i < this.capacity;i++){
			Node n = this.array[i];
			while(n!=null){
				if (n.element.equals(item)){
					return true;
				}
				n = n.next;
			}
		}
		return false;
	}

	/**
	 * Returns the number of items added to the hash table. Must operate in O(1)
	 * time.
	 *
	 * @return The number of items in the hash table.
	 */
	public int size() {
		// TODO: Write this
		return this.size;
	}

	/**
	 * @return True iff the hash table contains no items.
	 */
	public boolean isEmpty() {
		// TODO: Write this
		return this.size==0;
	}

	/**
	 * Removes all the items from the hash table. Resets the capacity to the
	 * DEFAULT_CAPACITY
	 */
	public void clear() {
		// TODO: Write this. Should take 1 line if you read carefully above.
		this.array = new Node[this.capacity];
		this.size = 0;
	}

	/**
	 * Removes the given item from the hash table if it is there. You do NOT
	 * need to resize down if the load factor decreases below the threshold.
	 * 
	 * @param item
	 * @return True If the item was in the hash table (or equivalently, if the
	 *         table changed as a result).
	 */
	public boolean remove(String item) {
		// TODO: Write this.
		for (int i=0; i < this.capacity;i++){
			Node n = this.array[i];
			if (n.element == item){
				this.array[i] = null;
				this.size--;
				return true;
			}else{
				while(n.next!=null){
					if (n.next.element.equals(item)){
						n.next = n.next.next;
						this.size--;
						return true;
					}
					n= n.next;
				}
			}
			
				
		}
		return false;
	}

	/**
	 * Adds all the items from the given collection to the hash table.
	 *
	 * @param collection
	 * @return True if the hash table is modified in any way.
	 */
	public boolean addAll(Collection<String> collection) {
		// TODO: Write this.
		for (String s : collection){
			this.add(s);
		}
		return true;
	}

	/**
	 * 
	 * Challenge Feature: Returns an iterator over the set. Return the items in
	 * any order that you can do efficiently. Should throw a
	 * NoSuchElementException if there are no more items and next() is called.
	 * Should throw a ConcurrentModificationException if next() is called and
	 * the set has been mutated since the iterator was created.
	 *
	 * @return an iterator.
	 */
	public Iterator<String> iterator() {
		return new StringHashSetIterator(this);
	}

	// Challenge Feature: If you have an iterator, this is easy. Use a
	// StringBuilder, so you can build the string in O(n) time. (Repeatedly
	// concatenating n strings onto a string gives O(n^2) time)
	// Format it like any other Collection's toString (like [a, b, c])
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(int i = 0; i <capacity; i++){
			if (this.array[i]!=null){
				Node n = this.array[i];
				//s = s.append(i);
				//s = s.append(": ");
				while (n!=null){
					s.append(n.element);
					s.append(", ");
					n = n.next;
				}
			}else{
				//s = s.append(i);
				//s = s.append(": ");
			}
			//s = s.append("null");
			//s=s.append("\n");
		}
		
		s.delete(s.length()-2, s.length());
		s.append("]");
		return s.toString();

	}
	
	public class StringHashSetIterator implements Iterator<String>{
		private int nodeIndex;
		private Node current;

		public StringHashSetIterator(StringHashSet stringHashSet) {
			// TODO Auto-generated constructor stub
			this.nodeIndex =0;
			while(array[nodeIndex] == null){
				nodeIndex++;
			}
			//System.out.println("innitialize"+this.nodeIndex);
			this.current = array[nodeIndex];
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			System.out.println(this.nodeIndex);
			if (nodeIndex>=capacity){
				return false;
			}
			return true;
		}

		@Override
		public String next() {
			// TODO Auto-generated method stub
			String result = current.element;
			if (current.next!=null){
				current = current.next;
			}else{
				//this.nodeIndex++;
				//System.out.println("capacity  "+capacity);
				while(array[nodeIndex]==null && nodeIndex < capacity){
					this.nodeIndex++;
				}
				if (this.nodeIndex>=capacity){
					current = null;
				}else
					current = array[nodeIndex];
			}
			//System.out.println(nodeIndex);
			//System.out.println("result   "+result);
			return result;
		}
		
	}
}


