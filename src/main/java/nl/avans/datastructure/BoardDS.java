package nl.avans.datastructure;

import nl.avans.lib.IGenerator;
import nl.avans.lib.ISolver;

class BoardDS {
	private Field[][] board;
	private IGenerator generator;
	private ISolver solver;
	private int size;
	private int difficulty;

	public BoardDS(int size, int difficulty, IGenerator generator,
			ISolver solver) {
		this.size = size;
		this.generator = generator;
		this.solver = solver;
		this.difficulty = difficulty;

		setup();
	}

	private void setup() {
		board = new Field[getSize()][getSize()];
		
		generate();
		solve();
	}

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

	private int[][] getBoardAsIntegers() {
		int[][] snapshot = new int[getNumberOfColumns()][getNumberOfRows()];

		for (int x = 0; x < getNumberOfRows(); x++) {
			for (int y = 0; y < getNumberOfColumns(); y++) {
				snapshot[x][y] = getCurrentValue(x, y);
			}
		}

		return snapshot;
	}

	public int getCurrentValue(int x, int y) {
		if (isFieldNotOnGrid(x, y))
			return -1;

		return this.board[x][y].getCurrentValue();
	}

	public void setCurrentValue(int x, int y, int currentValue) {
		if (isFieldNotOnGrid(x, y))
			throw new IllegalArgumentException("The field is not on the grid.");
		if (isIllegalValue(currentValue))
			throw new IllegalArgumentException(
					"The value is lower or higher than allowed.");

		if (this.board[x][y] instanceof UserField)
			this.board[x][y].setCurrentValue(currentValue);
	}

	public int getSolutionValue(int x, int y) {
		if (isFieldNotOnGrid(x, y))
			return -1;

		return this.board[x][y].getSolutionValue();
	}

	private void setSolutionValue(int x, int y, int solutionValue) {
		if (isFieldNotOnGrid(x, y))
			throw new IllegalArgumentException("The field is not on the grid.");
		if (isIllegalValue(solutionValue))
			throw new IllegalArgumentException(
					"The value is lower or higher than allowed.");

		this.board[x][y].setSolutionValue(solutionValue);
	}

	private boolean isFieldNotOnGrid(int x, int y) {
		if ((x < 0 || x > getNumberOfRows())
				|| (y < 0 || y > getNumberOfColumns())) {
			return true;
		}

		return false;
	}

	private boolean isIllegalValue(int value) {
		if (value < 0 || value > getSize()) {
			return true;
		}

		return false;
	}

	public int getNumberOfColumns() {
		return this.board[0].length;
	}

	public int getNumberOfRows() {
		return this.board.length;
	}

	public int getSize() {
		return size;
	}

	public int getDifficulty() {
		return difficulty;
	}
}