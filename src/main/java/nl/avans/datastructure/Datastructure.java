package nl.avans.datastructure;

import nl.avans.lib.IDatastructure;
import nl.avans.lib.IGenerator;
import nl.avans.lib.ISolver;

/**
 * Only class which is accessible from outside this package
 */
public class Datastructure implements IDatastructure {
	private BoardDS board;

	public Datastructure(int size, int difficulty, IGenerator generator,
			ISolver solver) {
		this.board = new BoardDS(size, difficulty, generator, solver);
	}

	public int getCurrentValue(int x, int y) {
		return this.board.getCurrentValue(x, y);
	}

	public void setCurrentValue(int x, int y, int currentValue) {
		this.board.setCurrentValue(x, y, currentValue);
	}

	public int getSolutionValue(int x, int y) {
		return this.board.getSolutionValue(x, y);
	}

	public int[][] getSubRegion(int subregion) {
		int columns = (int) Math.sqrt(getNumberOfColumns());
		int rows = (int) Math.sqrt(getNumberOfColumns());

		if (subregion < 0 ^ (subregion > columns - 1 && subregion > rows - 1)) {
			throw new IllegalArgumentException(
					"The parameter subregion cannot be smaller than 0 or larger than the number of subregions minus one.");
		}

		if (getNumberOfColumns() % columns != 0
				&& getNumberOfRows() % rows != 0) {
			throw new IllegalArgumentException(
					"The columns and rows of the sudoku cannot be evenly divided into subregions.");
		}

		int[][] elements = new int[columns][rows];

		for (int x = subregion * columns; x < getNumberOfColumns(); x++) {
			for (int y = subregion * rows; y < getNumberOfRows(); y++) {
				elements[x][y] = getCurrentValue(x, y);
			}
		}

		return elements;
	}

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

	public int getNumberOfColumns() {
		return this.board.getNumberOfColumns();
	}

	public int getNumberOfRows() {
		return this.board.getNumberOfRows();
	}

	public int getSize() {
		return this.board.getSize();
	}

	public int getDifficulty() {
		return this.board.getDifficulty();
	}
}