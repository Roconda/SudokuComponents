package nl.avans.datastructure;

import nl.avans.lib.IDatastructure;
import nl.avans.lib.IGenerator;
import nl.avans.lib.ISolver;

/**
 * Only class which is accessible from outside this package.
 */
public class Datastructure implements IDatastructure {
	
	/** The board. */
	private BoardDS board;

	/**
	 * Instantiates a new datastructure.
	 *
	 * @param size 			the size
	 * @param difficulty 	the difficulty
	 * @param generator 	the generator
	 * @param solver 		the solver
	 */
	public Datastructure(int size, int difficulty, IGenerator generator,
			ISolver solver) {
		this.board = new BoardDS(size, difficulty, generator, solver);
	}

	/* (non-Javadoc)
	 * @see nl.avans.lib.IDatastructure#getCurrentValue(int, int)
	 */
	public int getCurrentValue(int x, int y) {
		return this.board.getCurrentValue(x, y);
	}

	/* (non-Javadoc)
	 * @see nl.avans.lib.IDatastructure#setCurrentValue(int, int, int)
	 */
	public void setCurrentValue(int x, int y, int currentValue) {
		this.board.setCurrentValue(x, y, currentValue);
	}

	/* (non-Javadoc)
	 * @see nl.avans.lib.IDatastructure#getSolutionValue(int, int)
	 */
	public int getSolutionValue(int x, int y) {
		return this.board.getSolutionValue(x, y);
	}

	/* (non-Javadoc)
	 * @see nl.avans.lib.IDatastructure#getSubRegion(int)
	 */
	public int[][] getSubRegion(int subregion) {
		int setSize = (int) Math.sqrt(getSize());

		if (subregion < 0 ^ subregion > setSize - 1) {
			throw new IllegalArgumentException(
					"The parameter subregion cannot be smaller than 0 or larger than the number of subregions minus one.");
		}

		if (getSize() % setSize != 0) {
			throw new IllegalArgumentException(
					"The columns and rows of the sudoku cannot be evenly divided into subregions.");
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
				elements[setX][setY] = this.board.getCurrentValue(x, y);
				setY++;
			}
			
			setX++;
		}
		
		return elements;
	}

	/* (non-Javadoc)
	 * @see nl.avans.lib.IDatastructure#getColumn(int)
	 */
	public int[] getColumn(int column) {
		if (column < 0 ^ column > getNumberOfColumns() - 1) {
			throw new IllegalArgumentException(
					"The parameter column cannot be smaller than 0 or larger than the number of columns minus one.");
		}

		int[] elements = new int[getNumberOfRows()];

		for (int i = 0; i < getNumberOfRows(); i++) {
			elements[i] = getCurrentValue(column, i);
		}

		return elements;
	}

	/* (non-Javadoc)
	 * @see nl.avans.lib.IDatastructure#getRow(int)
	 */
	public int[] getRow(int row) {
		if (row < 0 ^ row > getNumberOfColumns() - 1) {
			throw new IllegalArgumentException(
					"The parameter row cannot be smaller than 0 or larger than the number of rows minus one.");
		}

		int[] elements = new int[getNumberOfColumns()];

		for (int i = 0; i < getNumberOfColumns(); i++) {
			elements[i] = getCurrentValue(i, row);
		}

		return elements;
	}

	/* (non-Javadoc)
	 * @see nl.avans.lib.IDatastructure#getNumberOfColumns()
	 */
	public int getNumberOfColumns() {
		return this.board.getNumberOfColumns();
	}

	/* (non-Javadoc)
	 * @see nl.avans.lib.IDatastructure#getNumberOfRows()
	 */
	public int getNumberOfRows() {
		return this.board.getNumberOfRows();
	}

	/* (non-Javadoc)
	 * @see nl.avans.lib.IDatastructure#getSize()
	 */
	public int getSize() {
		return this.board.getSize();
	}

	/**
	 * Gets the difficulty.
	 *
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return this.board.getDifficulty();
	}
}