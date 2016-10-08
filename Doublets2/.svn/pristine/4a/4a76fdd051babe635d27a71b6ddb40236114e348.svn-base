import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * This class creates a DataStructure called Chain by using
 * LinkedHashSet store several strings
 * 
 * @author Bochuan Lu and Victor Zhang
 * 
 * created on 03/26
 */
public class Chain {
	private LinkedHashSet<String> chain;
	
	public Chain(){
		// We use LinkedHashSet as the chosen data structure
		// for Chain based on our understanding that LinkedHashSet
		// is the best candidate data structure for its usage.
		this.chain = new LinkedHashSet<String>();
	}
	public Chain(LinkedHashSet<String> c){
		this.chain=c;
	}
	public Chain addLast(String string) {
		// this method add the last element to the Chain
		// and return the Chain as a new Chain after insertion.
		LinkedHashSet<String> temp= new LinkedHashSet<String>();
		temp.addAll(chain);
		temp.add(string);
		return new Chain(temp);
	}
	
	

	public String getLast() {
		// This method return the last element in the chain.
		String result="";
		Iterator itr= this.chain.iterator();
		while(itr.hasNext()){
			result = (String) itr.next();
		}
		return result;
	}

	public int length() {
		// This method return the size of the Chain
		return this.chain.size();
	}
	public boolean contains(String string) {
		// This method checks does the Chain have a specific element
		// and return a boolean accordingly.
		return this.chain.contains(string);
	}
	public Iterator<String> iterator() {
		// this method return the Chain's iterator 
		return chain.iterator();
	}
	
	@Override
	public String toString(){
		// this method transfer the Chain to String.
		return this.chain.toString();
	}
}
