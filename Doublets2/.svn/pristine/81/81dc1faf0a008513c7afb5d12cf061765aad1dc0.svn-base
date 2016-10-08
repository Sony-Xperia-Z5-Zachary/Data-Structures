
/**
 * Contains the general algorithm to search for doublets.
 *
 * @author <<YOUR NAME(S) HERE>>
 *         Created Mar 18, 2011.
 */
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is the main class to interact with user.
 * 
 * @author Bochuan Lu and Victor Zhang. Created Mar 26, 2016.
 *
 */
public class Doublets {

	/**
	 * Main function.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Build Links
		Links links = new Links("english.cleaned.all.35.txt");
		System.out.println("Welcome to Doublets, a game of \"verbal torture.\"");
		Scanner first = new Scanner(System.in);
		String startWord;
		String endWord;
		String command;
		while (true) {
			System.out.print("Enter starting word: ");
			startWord = first.nextLine();
			System.out.print("Enter ending word: ");
			endWord = first.nextLine();
			System.out.print("Enter chain manager (s: stack, q: queue, p: priority queue, x: exit): ");
			command = first.nextLine();
			if (command.equals("x")) {
				System.out.println("Goodbye!");
				break;
			}
			printOutput(startWord, endWord, command, links);

		}
		first.close();

	}

	/**
	 * Choose the ChainManager by the input from user.
	 * 
	 * @param s
	 * @param start
	 * @param end
	 * @param links
	 * @return
	 */
	public static ChainManager ChooseChainManager(String s, String start, String end, Links links) {
		// s: stack, q: queue, p: priority queue
		if (s.equals("s")) {
			return new StackChainManager(start, end, links);
		} else if (s.equals("q")) {
			return new QueueChainManager(start, end, links);
		}
		return new PriorityQueueChainManager(start, end, links);
	}

	/**
	 * After user enter all the information. Print the output.
	 * 
	 * @param startWord
	 * @param endWord
	 * @param command
	 * @param links
	 */
	public static void printOutput(String startWord, String endWord, String command, Links links) {
		// If startWord has different length than endWord, print out a
		// particular sentence.
		if (startWord.length() != endWord.length()) {
			System.out.println("Please enter the words with same length.");
		} else {
			// Determine whether the map in links has the startWord and endWord.
			if (!links.getMap().containsKey(startWord)) {
				System.out.printf("The word \"%s\" is not valid. Please try again.\n", startWord);
			} else if (!links.getMap().containsKey(endWord)) {
				System.out.printf("The word \"%s\" is not valid. Please try again.\n", endWord);
			} else {
				// Choose the chain manager.
				ChainManager manager = ChooseChainManager(command, startWord, endWord, links);
				try {
					// Print out all chain, the length of chain, candidates and
					// max size.
					Chain c = manager.FindChain();
					System.out.printf("Chain: %s\n", c.toString());
					System.out.printf("Length: %d\n", c.length());
					System.out.printf("Candidates: %d\n", manager.getNumberOfNexts());
					System.out.printf("Max size: %d\n", manager.maxSize());
				} catch (Exception e) {
					System.out.printf("No doublet chain exists from %s to %s.\n", startWord, endWord);
				}
			}
		}
	}

}
