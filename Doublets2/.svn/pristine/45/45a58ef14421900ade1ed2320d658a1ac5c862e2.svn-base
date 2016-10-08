import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

/**
 *  this class uses a Queue to hold prospective chains.
 * 
 * @author Bochuan Lu and Victor Zhang. Created Mar 26, 2016.
 * 
 */
public class QueueChainManager extends ChainManager {
	private Links links;
	private Queue<Chain> q;
	private String end;
	
	/** the constructor takes a starting word(root), ending word (end),
	* 	Link(l) which is the searching dictionary
	*/
	public QueueChainManager(String root, String end, Links l) {
		// q is the Queue storing all prospective Chains.
		this.q = new LinkedList<Chain>();
		this.end = end;
		this.links = l;
		// initialize the Queue by adding a chain that only contains 
		// the first word
		Chain c = new Chain();
		c = c.addLast(root);
		this.add(c);
	}

	@Override
	public Chain FindChain() throws NoSuchElementException {
		// this method uses a queue as a data structure to 
		// find possible chains.
		
		// initializing by adding the the chain with 
		// only the first word into the Queue
		Chain First = this.next();
		while (!CheckEnd(First)) {
			// call the addChildren function to add prospective chains 
			// into the queue for later examination.
			this.AddChilden(First);
			// if the queue is empty, throw an exception.
			if (this.q.size() == 0) {
				throw new NoSuchElementException();
			}
			First = this.next();
		}
		return First;
	}

	@Override
	public void add(Chain chain) {
		// add a Chain to the queue for examination
		this.q.add(chain);
	}

	@Override
	public Chain next() {
		// return the next element in the Queue
		this.incrementNumNexts();
		return (Chain) this.q.poll();
	}

	public boolean CheckEnd(Chain c) {
		// check if the last word in the chain is the wanted ending word
		return c.getLast().equals(this.end);
	}

	/*
	 *  This method add multiple prospective chains to the Queue
	 */
	public void AddChilden(Chain c) {
		// generate possible doublets based on the library.
		Set<String> set = this.links.getCandidates(c.getLast());
		// if there is possible doublets of a specific word
		// we add those possible doublets to the original chain
		// and put those new chains into the Queue
		if (set.size() != 0) {
			for (String str : set) {
				this.q.add(c.addLast(str));
			}
		}
		// the following code keeps track of 
		// the maximum size of the queue
		if (this.q.size() > this.maxSize()) {
			this.setMaxSize(this.q.size());
		}
	}

}
