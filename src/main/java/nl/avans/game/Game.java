package nl.avans.game;

import nl.avans.datastructure.Datastructure;
import nl.avans.generator.Generator;
import nl.avans.lib.IDatastructure;
import nl.avans.lib.IGenerator;
import nl.avans.lib.ISolver;

import nl.avans.nsbfsolver.Solver;

/**
 * Only class which is accessible from outside this package
 */
public class Game {
	private IDatastructure board;

	/**
	 * TODO: Difficulty should be of type EDifficulty.
	 */
	public Game(int size, int difficulty) {
		ISolver solver = new Solver(size);
		IGenerator generator = new Generator();
		this.board = new Datastructure(size, difficulty, generator, solver);
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
		return this.board.getSubRegion(subregion);
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
}