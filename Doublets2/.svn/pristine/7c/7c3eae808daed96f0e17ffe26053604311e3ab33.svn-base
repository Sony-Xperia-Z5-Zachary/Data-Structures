import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * This class is a chain manager using a stack.
 * 
 * @author Bochuan Lu and Victor Zhang. Created Mar 26, 2016.
 *
 */
public class StackChainManager extends ChainManager {
	// Fields
	private Stack<Chain> s;
	private String end;
	private Links links;

	/**
	 * The constructor of StackChainManager.
	 * 
	 * @param root
	 * @param end
	 * @param l
	 */
	public StackChainManager(String root, String end, Links l) {
		this.s = new Stack<Chain>();

		this.end = end;
		this.links = l;
		Chain c = new Chain();
		c = c.addLast(root);
		this.add(c);
	}

	/**
	 * Stores the given chain so it can be processed later.
	 *
	 * @param chain
	 */
	@Override
	public void add(Chain chain) {
		this.s.push(chain);

		// Set the max size if the current size is maximum.
		if (this.s.size() > this.maxSize()) {
			this.setMaxSize(this.s.size());
		}
	}

	/**
	 * Removes and returns the next Chain that the ChainManager has stored.
	 *
	 * @return The next Chain, or null if the manager has no Chains.
	 */
	@Override
	public Chain next() {
		// increment the number of next.
		this.incrementNumNexts();
		return this.s.pop();
	}

	/**
	 * Find the chain from the startWord and the endWord.
	 * 
	 * @return
	 */
	@Override
	public Chain FindChain() throws NullPointerException {
		Chain result = AddChildren();
		if (result == null) {
			throw new NullPointerException();
		}
		return result;
	}

	/**
	 * This method add multiple prospective chains to the Stack
	 */
	public Chain AddChildren() {
		HashSet<String> set = new HashSet<String>();

		// While stack isn't empty, add the candidate chain of the old chain
		// into the stack.
		while (!this.s.empty()) {
			Chain old_chain = this.next();

			// If old chain is from start word to end word, set result and
			// return.
			if (old_chain.getLast().equals(this.end)) {
				return old_chain;
			}

			// add the candidate chain of the old chain into the stack.
			Set<String> original = this.links.getCandidates(old_chain.getLast());
			for (String word : original) {
				if (!set.contains(word)) {
					this.add(old_chain.addLast(word));
				}
				set.add(word);
				if (word.equals(this.end)) {
					return old_chain.addLast(word);
				}
			}
		}
		return null;
	}

}
