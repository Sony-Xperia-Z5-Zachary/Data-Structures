import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This class is used to read in the dictionary and store them in a HashMap.
 * 
 * @author Bochuan Lu and Victor Zhang. Created Mar 26, 2016.
 * 
 */
public class Links {
	// private Set<String> candidates;
	private HashMap<String, Set<String>> map;

	/**
	 * The constructor of Links.
	 * 
	 * @param filename
	 */
	public Links(String filename) {

		Scanner s;
		try {
			// Read in the file.
			s = new Scanner(new File(filename));
			this.map = new HashMap<String, Set<String>>();
			ArrayList<String> words = new ArrayList<String>();
			while (s.hasNextLine()) {
				words.add(s.nextLine());
			}

			// Find all doublets of the word.
			for (String word1 : words) {
				Set<String> c = new HashSet<String>();
				for (String word2 : words) {
					if (!word1.equals(word2) && this.FindDoublets(word1, word2)) {
						c.add(word2);
					}
				}
				if (c.size() == 0) {
					c = null;
				}
				// Store the word and its doublets in the map.
				map.put(word1, c);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Determine whether word2 is the doublets of word1.
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	public Boolean FindDoublets(String word1, String word2) {
		if (word1.length() != word2.length()) {
			return false;
		}
		int difference = 0;
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				difference++;
			}
			if (difference > 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get the candidates of the given word.
	 * 
	 * @param word1
	 * @return
	 */
	public Set<String> getCandidates(String word1) {
		if (!this.map.containsKey(word1)) {
			return null;
		}
		return this.map.get(word1);

	}

	/**
	 * Get the HashMap.
	 * 
	 * @return
	 */
	public HashMap<String, Set<String>> getMap() {
		return this.map;
	}

}
