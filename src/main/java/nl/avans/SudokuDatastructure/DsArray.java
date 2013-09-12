package nl.avans.SudokuDatastructure;

/**
 * Only class which is accessible from outside this package
 */
public class DsArray {
	private int size;
	private BoardDS ds;

	public DsArray(int size) {
		ds = new BoardDS(size);
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
		try {
			ds.setValue(x, y, value);
		} catch (UnkownFieldException e) {
			return false;
		}

		return true;
	}

	public int getValue(int x, int y) {
		try {
			return ds.getValue(x, y);
		} catch (UnkownFieldException e) {
			return -1;
		}
	}

}
