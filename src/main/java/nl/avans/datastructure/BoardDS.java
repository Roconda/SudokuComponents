package nl.avans.datastructure;

import nl.avans.lib.IGenerator;
import nl.avans.lib.ISolver;

/**
 * The Class BoardDS.
 *
 * @author Tim Slot
 * 		   Sander van Leeuwen
 */
class BoardDS {
	
	/** The board. */
	private Field[][] board;
	
	/** The generator. */
	private IGenerator generator;
	
	/** The solver. */
	private ISolver solver;
	
	/** The size. */
	private int size;
	
	/** The difficulty. */
	private int difficulty;

	/**
	 * Instantiates a new board ds.
	 *
	 * @param size 			the size
	 * @param difficulty 	the difficulty
	 * @param generator 	the generator
	 * @param solver 		the solver
	 */
	public BoardDS(int size, int difficulty, IGenerator generator,
			ISolver solver) {
		this.size = size;
		this.generator = generator;
		this.solver = solver;
		this.difficulty = difficulty;

		setup();
	}

	/**
	 * Sets up a new Sudoku puzzle.
	 */
	private void setup() {
		board = new Field[getSize()][getSize()];
		
		generate();
		solve();
	}

	/**
	 * Generates a new Sudoku puzzle.
	 */
	public void generate() {
		long startTime = System.currentTimeMillis();

		int[][] content = generator.generate(getSize(), getDifficulty());

		long finishTime = System.currentTimeMillis();
		System.out.println("Generating time: " + (finishTime - startTime)
				+ " ms");

		for (int x = 0; x < getNumberOfRows(); x++) {
			for (int y = 0; y < getNumberOfColumns(); y++) {
				if (content[x][y] == 0) {
					this.board[x][y] = new UserField(0, 0);
				} else {
					this.board[x][y] = new GeneratedField(content[x][y], 0);
				}
			}
		}
	}

	/**
	 * Solves a Sudoku puzzle.
	 */
	public void solve() {
		long startTime = System.currentTimeMillis();

		int[][] content = solver.solve(getBoardAsIntegers());

		long finishTime = System.currentTimeMillis();
		System.out.println("Solving time: " + (finishTime - startTime) + " ms");

		for (int x = 0; x < getNumberOfRows(); x++) {
			for (int y = 0; y < getNumberOfColumns(); y++) {
				if (board[x][y] instanceof UserField) {
					setSolutionValue(x, y, content[x][y]);
				}
			}
		}
	}

	/**
	 * Gets the board as integers.
	 *
	 * @return the board as integers
	 */
	private int[][] getBoardAsIntegers() {
		int[][] snapshot = new int[getNumberOfColumns()][getNumberOfRows()];

		for (int x = 0; x < getNumberOfRows(); x++) {
			for (int y = 0; y < getNumberOfColumns(); y++) {
				snapshot[x][y] = getCurrentValue(x, y);
			}
		}

		return snapshot;
	}

	/**
	 * Gets the current value.
	 *
	 * @param x 	the x
	 * @param y 	the y
	 * @return 		the current value
	 */
	public int getCurrentValue(int x, int y) {
		if (isFieldNotOnGrid(x, y))
			return -1;

		return this.board[x][y].getCurrentValue();
	}

	/**
	 * Sets the current value.
	 *
	 * @param x 	the x
	 * @param y 	the y
	 * @param 		currentValue the current value
	 */
	public void setCurrentValue(int x, int y, int currentValue) {
		if (isFieldNotOnGrid(x, y))
			throw new IllegalArgumentException("The field is not on the grid.");
		if (isIllegalValue(currentValue))
			throw new IllegalArgumentException(
					"The value is lower or higher than allowed.");

		if (this.board[x][y] instanceof UserField)
			this.board[x][y].setCurrentValue(currentValue);
	}

	/**
	 * Gets the solution value.
	 *
	 * @param x 	the x
	 * @param y 	the y
	 * @return 		the solution value
	 */
	public int getSolutionValue(int x, int y) {
		if (isFieldNotOnGrid(x, y))
			return -1;

		return this.board[x][y].getSolutionValue();
	}

	/**
	 * Sets the solution value.
	 *
	 * @param x 	the x
	 * @param y 	the y
	 * @param 		solutionValue the solution value
	 */
	private void setSolutionValue(int x, int y, int solutionValue) {
		if (isFieldNotOnGrid(x, y))
			throw new IllegalArgumentException("The field is not on the grid.");
		if (isIllegalValue(solutionValue))
			throw new IllegalArgumentException(
					"The value is lower or higher than allowed.");

		this.board[x][y].setSolutionValue(solutionValue);
	}

	/**
	 * Checks if is field not on grid.
	 *
	 * @param x 		the x
	 * @param y 		the y
	 * @return 			true, if is field not on grid
	 */
	private boolean isFieldNotOnGrid(int x, int y) {
		if ((x < 0 || x > getNumberOfRows())
				|| (y < 0 || y > getNumberOfColumns())) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is illegal value.
	 *
	 * @param value 	the value
	 * @return 			true, if is illegal value
	 */
	private boolean isIllegalValue(int value) {
		if (value < 0 || value > getSize()) {
			return true;
		}

		return false;
	}

	/**
	 * Gets the number of columns.
	 *
	 * @return 			the number of columns
	 */
	public int getNumberOfColumns() {
		return this.board[0].length;
	}

	/**
	 * Gets the number of rows.
	 *
	 * @return 			the number of rows
	 */
	public int getNumberOfRows() {
		return this.board.length;
	}

	/**
	 * Gets the size.
	 *
	 * @return 			the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the difficulty.
	 *
	 * @return 			the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}
}