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

	private boolean isFieldNotOnGrid(int x, int y) {
		return ((x < 0 || x > getNumberOfRows()) || (y < 0 || y > getNumberOfColumns()));
	}

	private boolean isIllegalValue(int value) {
		return (value < 0 || value > getSize());
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
	
	private void generateCheck() throws CannotGenerateException {
		boolean canGenerate = true;
		StringBuilder message = new StringBuilder("Something bad has happened:\n\n");

		if(board == null) {
			 canGenerate = !canGenerate;
			 message.append("- Board not set\n");
		}
		
		if(!canGenerate) throw new CannotGenerateException(message.toString());
	}
}