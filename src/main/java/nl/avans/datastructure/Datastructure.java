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
	
	public int getSolutionValue(int x, int y) {
		return this.board.getSolutionValue(x, y);
	}
	
	public boolean setSolutionValue(int x, int y, int value) {
		return this.board.setSolutionValue(x, y, value);
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
	
	public int[][] getSubRegion(int subregion) {
		
		int setSize = (int) Math.sqrt(getSize());
		
		/* @TODO check if valid region
		 * 
			isValidSubRegion(subregion, setSize);
		*/
		
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
		
		/*
		 * @TODO check if valid column
			isValidColumn(column);
		*/
		
		int[] elements = new int[getNumberOfRows()];

		for (int i = 0; i < getNumberOfRows(); i++) {
			elements[i] = getCurrentValue(column, i);
		}

		return elements;
	}

	public int[] getRow(int row) {
		/**
		 * @TODO deze check oplossen
		 * 
		try {
			isValidRow(row);
		} catch (UnknownRowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/

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
	
	
	/**
	 * Checks if a field is a generated field. (When not, it's a userfield)
	 */
	public boolean isGeneratedField(int x, int y) {
		return this.board.isGeneratedField(x, y);
	}
	
	public void getHint() {
		boolean isFound = false;
		
		for (int x = 0; x < board.getNumberOfRows(); x++) {
			if(isFound) {
				break;
			}
			
			for (int y = 0; y < board.getNumberOfColumns(); y++) {
				if (getCurrentValue(x, y) == 0) {
					setCurrentValue(x, y, getSolutionValue(x, y));
					isFound = true;
				}
			}
		}
	}
	
	private boolean isValidSubRegion(int subregion, int setSize) {
		

		if (subregion < 0 ^ subregion > setSize - 1) return false;

		if (getSize() % setSize != 0) return false;
		
		return true;
	}
	
	private boolean isValidRow(int row) {
		if (row < 0 ^ row > getNumberOfColumns() - 1) return false;
		
		return true;
	}
	
	private boolean isValidColumn(int column) {
		if (column < 0 ^ column > getNumberOfColumns() - 1) return false;
		
		return true;
	}
}
