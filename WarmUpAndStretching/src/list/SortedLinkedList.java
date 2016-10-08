package list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;



/**
 * 
 * @author anderson
 * 
 * @param <T>
 *            Any Comparable type
 * 
 *            A linked list whose elements are kept in sorted order.
 */
public class SortedLinkedList<T extends Comparable<T>> extends
		DoublyLinkedList<T> {

	/**
	 * Create an empty list
	 * 
	 */
	public SortedLinkedList() {
		super();
	}

	/**
	 * Creates a sorted list containing the same elements as the parameter
	 * 
	 * @param list
	 *            the input list
	 */
	public SortedLinkedList(DoublyLinkedList<T> list) {
		super();

		// TODO: finish implementing this constructor
		ArrayList<T> tem = new ArrayList<T>();
		// put all the data into an arraylist
		while (!list.isEmpty()){
			tem.add(list.tail.prev.data);
			list.removeLast();
		}
		// sort the arraylist
		Collections.sort(tem);
		System.out.println(tem);
		// put the elements of the sorted arraylist into the doublylinked list
		for (int i = tem.size()-1;i>=0;i--){
			this.head.addAfter(tem.get(i));
		}
		
		
	}

	/**
	 * Adds the given element to the list, keeping it sorted.
	 */
	@Override
	public void add(T element) {
		// TODO: implement this method
		if(this.isEmpty()){
			this.head.addAfter(element);
		}
		else{
			if (element.compareTo(this.head.next.data)<=0){
				// compare the add element with the first elelent in the list
				this.head.addAfter(element);
				System.out.println(this.tail.prev.data);
			}else{
				// if add element bigger than first element, compare it with the second 
				// until we find an element in the list bigger than add element
				DoublyLinkedList<T>.Node track_node = new InternalNode();
				track_node = this.head.next;
				boolean small = false;
				while(track_node.next!=null){
					if (element.compareTo(track_node.data)<=0){
						small = true;
						break;
					}
					track_node = track_node.next;
				}
				if (small = true){
					track_node.prev.addAfter(element);
				}else{
					// if add element bigger than all elements in the list
					// add it at the end of the list
					track_node.addAfter(element);
				}
				
			}
		}
	}

	@Override
	public void addFirst(T element) {
		// TODO: throw UnsupportedOperationException exception
		throw new UnsupportedOperationException();
	}

	@Override
	public void addLast(T element) {
		// TODO: throw UnsupportedOperationException exception
		throw new UnsupportedOperationException();
	}
}
