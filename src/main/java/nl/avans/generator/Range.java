package nl.avans.generator;


import java.util.Iterator;


class Range implements Iterable<Integer> {

	private int min;
	private int count;
	
	
	Range(int min, int count) {
		this.min = min;
		this.count = count;
	}
	
	
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
