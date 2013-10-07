package nl.avans.generator;


import java.util.Iterator;


/**
 * This class helps creating a row of numbers between a specific range.
 * For example:
 * 1 till 9 will return:
 * 		1 2 3 4 5 6 7 8 9
 * 
 * @author Dustin Sarioglu
 *
 */
class Range implements Iterable<Integer> {

	/** The min. */
	private int min;
	
	/** The count. */
	private int count;
	
	
	/**
	 * Initializes a new instance of the Range class.
	 * 
	 * @param min		The start position of the range.
	 * @param count		The end position of the range.
	 */
	Range(int min, int count) {
		this.min = min;
		this.count = count;
	}
	
	
	/**
	 * Iterates from the start position of the range to the end position,
	 * creating all numbers for the row.
	 *
	 * @return the iterator
	 */
	public Iterator<Integer> iterator() {
		
		return new Iterator<Integer>() {
			
			private int current = min;
			private int count = Range.this.count;
			
			
			public boolean hasNext() {
				return (count != 0);
			}
			
			
			public Integer next() {
				count--;
				
				return current++;
			}
			
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
