package nl.avans.datastructure;

class BoardDS {
	private final int PLACEHOLDER = -9999;
	private Field[][] board;
	private int size = PLACEHOLDER;

	public BoardDS fill(int[][] content) {
		
		// Initialize board
		this.size = content.length;
		board = new Field[getSize()][getSize()];

		for (int x = 0; x < getNumberOfRows(); x++) {
			for (int y = 0; y < getNumberOfColumns(); y++) {
				if (content[x][y] == 0) {
					this.board[x][y] = new UserField(0, 0);
				} else {
					this.board[x][y] = new GeneratedField(content[x][y], 0);
				}
			}
		}
		return this;
	}
	
    /**
     * Gets the current value.
     *
     * @param x         the x
     * @param y         the y
     * @return          the current value
     */
	public int getCurrentValue(int x, int y) throws CannotGenerateException {
		generateCheck();
		
		if (isFieldNotOnGrid(x, y))
			return -1;

		return this.board[x][y].getCurrentValue();
	}

	public boolean setCurrentValue(int x, int y, int currentValue) throws UnkownFieldException {
		if (isFieldNotOnGrid(x, y))
			throw new UnkownFieldException("BoardDS.setCurrentValue: The field is not on the grid.");
		if (isIllegalValue(currentValue))
			throw new UnkownFieldException("BoardDS.setCurrentValue: The value is lower or higher than allowed.");
		
		return this.board[x][y].setCurrentValue(currentValue);
	}

	public int getSolutionValue(int x, int y) {
		if (isFieldNotOnGrid(x, y))
			return -1;

		return this.board[x][y].getSolutionValue();
	}
	
    /**
     * Sets the solution value.
     *
     * @param x         the x
     * @param y         the y
     * @param           solutionValue the solution value
     */
	public boolean setSolutionValue(int x, int y, int value) {
		if (isFieldNotOnGrid(x, y))
			return false;
		
		if(isIllegalValue(value))
			return false;
		
		this.board[x][y].setSolutionValue(value);
		return true;
	}

    /**
     * Checks if is field not on grid.
     *
     * @param x                 the x
     * @param y                 the y
     * @return                  true, if is field not on grid
     */
	private boolean isFieldNotOnGrid(int x, int y) {
		return ((x < 0 || x > getNumberOfRows()) || (y < 0 || y > getNumberOfColumns()));
	}

    /**
     * Checks if is illegal value.
     *
     * @param value         the value
     * @return                         true, if is illegal value
     */
	private boolean isIllegalValue(int value) {
		return (value < 0 || value > getSize());
	}

    /**
     * Gets the number of columns.
     *
     * @return                         the number of columns
     */
	public int getNumberOfColumns() {
		return this.board[0].length;
	}

    /**
     * Gets the number of rows.
     *
     * @return                         the number of rows
     */
	public int getNumberOfRows() {
		return this.board.length;
	}

    /**
     * Gets the size.
     *
     * @return                         the size
     */
	public int getSize() {
		return size;
	}
	
	private void generateCheck() throws CannotGenerateException {
		boolean canGenerate = true;
		StringBuilder message = new StringBuilder("Something bad has happened:\n\n");

		if(board == null) {
			 canGenerate = !canGenerate;
			 message.append("- Board not set\n");
		}
		
		if(!canGenerate) throw new CannotGenerateException(message.toString());
	}
	
	/**
	 * Checks if a field is a generated field. (When not, it's a userfield)
	 */
	public boolean isGeneratedField(int x, int y) {
		if(board[x][y] instanceof GeneratedField) {
			return true;
		}
		
		return false;
	}
}