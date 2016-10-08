package maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

/**
 * Simple introduction or reminder about different kinds of maps.
 * 
 * @author Matt Boutell
 *
 */
public class MapsTest {

	@Test
	public void test() {
		// A map data structure is also called a dictionary. It is like an
		// array, but arbitrary values are used as _keys_ to look up data,
		// rather than using an integer index.

		// A HashMap is a good all-purpose map, since it is fast: O(1) search,
		// insertion, and deletion in many situations.
		Map<String, Integer> ages = new HashMap<String, Integer>();
		// Fill the map.
		ages.put("Bob", 17);
		ages.put("Shelley", 21);
		ages.put("Abby", 18);
		ages.put("Joel", 18);
		ages.put("Rhonda", 16);

		// You can check to see if a map contains a key or not.
		assertTrue(ages.containsKey("Shelley"));
		assertFalse(ages.containsKey("Michael"));

		// Get a value stored in the map by passing its key.
		assertEquals(new Integer(18), ages.get("Abby"));

		// The test below will fail because the test is incorrect. Change the
		// test so it passes. What we're really 
		// after is that you see how to use get to retrieve data.
		assertEquals(new Integer(17), ages.get("Bob"));

		// Why does this test fail? The map contains all the names and ages!
		// It's because hash maps order the data using a method we'll learn
		// about later this term. They are NOT sorted by key.
		// To make the test pass, you will need to replace the HashMap above
		// with another kind of map that sorts its data.
		// Hint: look up "java 8 collections api sortedmap", and find which
		// classes implement the SortedMap interface. Use the class that isn't a
		// ConcurrentSkipListMap.
		SortedMap<String,Integer> ages1 = new TreeMap<String,Integer>();
		ages1.put("Bob", 17);
		ages1.put("Shelley", 21);
		ages1.put("Abby", 18);
		ages1.put("Joel", 18);
		ages1.put("Rhonda", 16);
		assertEquals("{Abby=18, Bob=17, Joel=18, Rhonda=16, Shelley=21}", ages1.toString());
		System.out.println("MAPS passed the test");
		
	}

}
