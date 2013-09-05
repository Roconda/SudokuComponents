package nl.avans.SudokuDatastructure;

/**
 * Only class which is accessible from outside this package
 */
public class App 
{
	private int size;
	private BoardDS ds;

	public App(int size) {
		ds = new BoardDS(size, size);
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
		}catch(UnkownFieldException e){
			return false;
		}
		
		return true;
	}

}
