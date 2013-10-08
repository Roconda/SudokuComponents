package nl.avans.game;

import nl.avans.datastructure.Datastructure;
import nl.avans.generator.Generator;
import nl.avans.lib.IDatastructure;
import nl.avans.lib.IGenerator;
import nl.avans.lib.ISolver;

import nl.avans.nsbfsolver.Solver;

/**
 * Main initialization class for a Sudoku game.
 * 
 * @author	Christiaan Reubsaet
 */
public class Game {
	
	/** The board. */
	private IDatastructure board;

	/**
	 * Initializes a new instance of the Game class.
	 * 
	 * @param size			The size of the Sudoku puzzle's matrix.
	 * @param difficulty	The difficulty of the Sudoku puzzle.
	 */
	public Game(int size, int difficulty) {
		IGenerator generator = new Generator();
		int[][] board = generator.generate(size, difficulty);
		this.board = new Datastructure(size, difficulty, board);
	}

	
	/**
	 * Gets the current value of a specific field.
	 * 
	 * @param x				The x-coordinate of the field in the matrix.
	 * @param y				The y-coordinate of the field in the matrix.
	 * @return				The current value of the desired field.
	 */
	public int getCurrentValue(int x, int y) {
		return this.board.getCurrentValue(x, y);
	}

	/**
	 * Sets the current value of a specific field.
	 * 
	 * @param x				The x-coordinate of the field in the matrix.
	 * @param y				The y-coordinate of the field in the matrix.
	 * @param currentValue	The desired value to set the field's current value to.
	 */
	public void setCurrentValue(int x, int y, int currentValue) {
		this.board.setCurrentValue(x, y, currentValue);
	}

	/**
	 * Gets the solution value of a specific field.
	 * 
	 * @param x				The x-coordinate of the field in the matrix.
	 * @param y				The y-coordinate of the field in the matrix.
	 * @return				The solution value of the desired field.
	 */
	public int getSolutionValue(int x, int y) {
		return this.board.getSolutionValue(x, y);
	}

	/**
	 * Gets all values in a specific sub-region within the puzzle's matrix.
	 * 
	 * @param subregion		The index of the sub-region.
	 * @return				A matrix based on the set-sizes of the puzzle's matrix, containing all values in its sub-region.
	 */
	public int[][] getSubRegion(int subregion) {
		return this.board.getSubRegion(subregion);
	}

	/**
	 * Gets all values in a specific column within the puzzle's matrix.
	 * 
	 * @param column		The index of the column.
	 * @return				An array containing all values in the desired column of the puzzle's matrix.
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

	/**
	 * Gets all values in a specific row within the puzzle's matrix.
	 * 
	 * @param row			The index of the row.
	 * @return				An array containing all values in the desired row of the puzzle's matrix.
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

	/**
	 * Gets the count of columns in the puzzle's matrix.
	 * 
	 * @return				The count of columns in the puzzle's matrix.
	 */
	public int getNumberOfColumns() {
		return this.board.getNumberOfColumns();
	}

	/**
	 * Gets the count of rows in the puzzle's matrix.
	 * 
	 * @return				The count of rows in the puzzle's matrix.
	 */
	public int getNumberOfRows() {
		return this.board.getNumberOfRows();
	}

	/**
	 * Gets the size of the puzzle.
	 * 
	 * @return				The size of the puzzle.
	 */
	public int getSize() {
		return this.board.getSize();
	}
}