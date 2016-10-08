package euclid;

public class Euclid {
	/**
	 * Implementation requirement: must do recursively, as given in the spec.
	 * @param a First integer
	 * @param b Second integer
	 * @return The greatest common divisor of a and b using Euclid's recursive algorithm. 
	 */
	public static long gcd(long a, long b) {
		// TODO: Write this.
		long bigger = Math.max(a,b);
		long smaller = Math.min(a,b);
		return rec(bigger,smaller);
	}
	
	public static long rec(long bigger, long smaller){
		
		// implement Euclid's algorithm
		if (bigger%smaller == 0){
			return smaller;
		}
		return rec(smaller,bigger%smaller);
	}

}
