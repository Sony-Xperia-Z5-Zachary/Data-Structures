package priorityQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * An implementation of the Priority Queue interface using an array list.
 * 
 * @author Matt Boutell and <<TODO: Ruinan(Victor) Zhang>>>. Created Mar 29, 2014.
 * 
 * @param <T>
 *            Generic type of PQ elements
 */
public class ArrayListMinPQ<T extends Comparable<T>> {
	// Requirement: You must use this instance variable without changes.
	private ArrayList<T> items;

	public ArrayListMinPQ() {
		// TODO: implement
		this.items = new ArrayList<T>();
		
	}

	public T findMin() {
		// This is also known as peekMin
		// TODO: implement
		if (this.items.isEmpty()){
			return null;
		}
		ArrayList<T> tem_items = (ArrayList<T>) this.items.clone() ;
		Collections.sort(tem_items);
		T minT = tem_items.get(0);
		return minT;
	}

	public T deleteMin() {
		// TODO: implement
		if (this.isEmpty()){
			return null;
		}
		T obj = findMin();
		int delete_index = this.items.indexOf(obj);
		this.items.remove(delete_index);
		
		return obj;
	}

	public void insert(T item) {
		// TODO: implement
		this.items.add(item);
		}

	public int size() {
		// TODO: implement
		return this.items.size();
	}

	public boolean isEmpty() {
		// TODO: implement
		return this.size() ==0;
	}

	public void clear() {
		// TODO: implement
		this.items =new ArrayList<T>();
	}
}
