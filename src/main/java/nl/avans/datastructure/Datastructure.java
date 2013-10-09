package nl.avans.datastructure;

import nl.avans.lib.*;

/**
 * Only class which is accessible from outside this package
 */
public class Datastructure implements IDatastructure {
	private BoardDS board;

	public Datastructure(int[][] generatedBoard) {
		board = new BoardDS().fill(generatedBoard);
	}
	
	public IDatastructure setSettings(int[][] content) {
		board = new BoardDS().fill(content);
		return (IDatastructure)this;
	}
	
	public int getCurrentValue(int x, int y) {
		try {
			return this.board.getCurrentValue(x, y);
		} catch (CannotGenerateException e) {
			// TODO Printen of niet?
			e.printStackTrace();
			return -1;
		}
	}

	public boolean setCurrentValue(int x, int y, int currentValue) {
		try {
			return this.board.setCurrentValue(x, y, currentValue);
		} catch (UnkownFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public int getSolutionValue(int x, int y) {
		return this.board.getSolutionValue(x, y);
	}

	public int[][] getSubRegion(int subregion) {
		
		int setSize = (int) Math.sqrt(getSize());
		
		try {
			isValidSubRegion(subregion, setSize);
		} catch (UnknownSubRegionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[][] elements = new int[setSize][setSize];
		
		int setIndex = (subregion / setSize);
		int lowestRow = (setSize * setIndex),
			lowestColumn = (setSize * setIndex);
		
		int setX = 0,
			setY = 0;
		for (int x = lowestRow; x < (lowestRow + setSize); x++) {
			setY = 0;
			for (int y = lowestColumn; y < (lowestColumn + setSize); y++) {
				try {
					elements[setX][setY] = this.board.getCurrentValue(x, y);
				} catch (CannotGenerateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setY++;
			}
			
			setX++;
		}
		
		return elements;
	}

	public int[] getColumn(int column) {
		
		try {
			isValidColumn(column);
		} catch (UnknownColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] elements = new int[getNumberOfRows()];

		for (int i = 0; i < getNumberOfRows(); i++) {
			elements[i] = getCurrentValue(column, i);
		}

		return elements;
	}

	public int[] getRow(int row) {
		
		try {
			isValidRow(row);
		} catch (UnknownRowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int[] elements = new int[getNumberOfColumns()];

		for (int i = 0; i < getNumberOfColumns(); i++) {
			elements[i] = getCurrentValue(i, row);
		}

		return elements;
	}

	public int getNumberOfColumns() {
		return this.board.getNumberOfColumns();
	}

	public int getNumberOfRows() {
		return this.board.getNumberOfRows();
	}

	public int getSize() {
		return this.board.getSize();
	}
	
	
	private void isValidSubRegion(int subregion, int setSize) throws UnknownSubRegionException {
		

		if (subregion < 0 ^ subregion > setSize - 1) {
			throw new UnknownSubRegionException(
					"Datastructure.getSubRegion: The parameter subregion cannot be smaller than 0 or larger than the number of subregions minus one.");
		}

		if (getSize() % setSize != 0) {
			throw new UnknownSubRegionException(
					"Datastructure.getSubRegion: The columns and rows of the sudoku cannot be evenly divided into subregions.");
		}
	}
	
	private void isValidRow(int row) throws UnknownRowException {
		if (row < 0 ^ row > getNumberOfColumns() - 1) {
			throw new UnknownRowException(
					"Datastructure.getRow: The parameter row cannot be smaller than 0 or larger than the number of rows minus one.");
		}
	}
	
	private void isValidColumn(int column) throws UnknownColumnException {
		if (column < 0 ^ column > getNumberOfColumns() - 1) {
			throw new UnknownColumnException(
					"Datastructure.getColumn: The parameter column cannot be smaller than 0 or larger than the number of columns minus one.");
		}
	}
}
