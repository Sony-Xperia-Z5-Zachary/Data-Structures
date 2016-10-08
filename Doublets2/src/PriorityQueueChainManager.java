import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *  this class uses a Priority Queue to hold prospective chains.
 * 
 * @author Bochuan Lu and Victor Zhang
 * 
 * written on 03/28
 */
 
public class PriorityQueueChainManager extends ChainManager {
	private Links links;
	private PriorityQueue q;
	private String end;

	@SuppressWarnings("unchecked")
	/** the constructor takes a starting word(root), ending word (end),
	 * 	Link(l) which is the searching dictionary
	 */
	public PriorityQueueChainManager(String root, String end, Links l) {
		this.end = end;

		// create a PriorityQueue as the data structure
		// to hold prospective chains

		// the Priority Queue is constructed by initialize
		// the initialize-size to be 1 and a new Comparator
		this.q = new PriorityQueue(1, new Comparator<Chain>() {
			// the comparator compares the possible doublets by
			// calculating the value = size + difference
			@Override
			public int compare(Chain c1, Chain c2) {
				int first = c1.length()
						+ PriorityQueueChainManager.this.estimateDifference(
								c1.getLast(),
								PriorityQueueChainManager.this.end);
				int second = c2.length()
						+ PriorityQueueChainManager.this.estimateDifference(
								c2.getLast(),
								PriorityQueueChainManager.this.end);
				return first - second;
			}

		});
		// initialize the PriorityQueue by adding a chain that only contains
		// the first word
		this.links = l;
		Chain c = new Chain();
		c = c.addLast(root);
		this.add(c);
	}

	@Override
	public Chain FindChain() throws NoSuchElementException {
		// initializing by adding the the chain with
		// only the first word into the Queue
		Chain First = this.next();
		while (!CheckEnd(First)) {
			this.AddChilden(First);
			// call the addChildren function to add prospective chains
			// into the priority queue for later examination.
			if (this.q.size() == 0) {
				// if the priority queue is empty, throw an exception.
				throw new NoSuchElementException();
			}
			First = this.next();
		}
		return First;
	}

	@Override
	public void add(Chain chain) {
		// add a Chain to the priority queue for examination
		this.q.offer(chain);
	}

	@Override
	public Chain next() {
		// return the next element in the Queue
		this.incrementNumNexts();
		return (Chain) this.q.poll();
	}

	public boolean CheckEnd(Chain c) {
		return c.getLast().equals(this.end);
	}

	public void AddChilden(Chain c) {
		// generate possible doublets based on the library.
		Set<String> set = this.links.getCandidates(c.getLast());
		// if there is possible doublets of a specific word
		// we add those possible doublets to the original chain
		// and put those new chains into the Queue
		if (set.size() != 0) {
			for (String str : set) {
				this.q.offer(c.addLast(str));
			}
		}
		// the following code keeps track of 
		// the maximum size of the queue
		if (this.q.size() > this.maxSize()) {
			this.setMaxSize(this.q.size());
		}
	}
	 /**
	  * this method calculate difference between two string.
	  */
	public int estimateDifference(String str1, String str2) {
		int count = 0;
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				count++;
			}
		}
		return count;
	}
}
