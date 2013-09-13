package nl.avans.SudokuDatastructure;

/**
 * Only class which is accessible from outside this package
 */
public class DsArray 
{
	private int size;
	private BoardDS ds;

	public DsArray(int size) {
		ds = new BoardDS(size, size);
		this.size = size;
	}
	
	/**
	 * @return the xSize
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return if a field exists on the grid or not
	 */
	public boolean setValue(int x, int y, int value) {
		try{
			ds.setValue(x, y, value);
			return true;
		}catch(UnkownFieldException e){
			return false;
		}
	}
	
	public int getValue(int x, int y) {
		try{
			return ds.getValue(x, y);
		}catch(UnkownFieldException e){
			// Out of index/grid 
			return -1;
		}catch(NullPointerException e){
			// Field has not yet been initialized
			return -1;
		}
	}

}
