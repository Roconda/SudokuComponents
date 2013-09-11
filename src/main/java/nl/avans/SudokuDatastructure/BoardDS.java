package nl.avans.SudokuDatastructure;

class BoardDS {

	private Field[][] board;

	public BoardDS(int x, int y) {
		makeStructure(x, y);
	}

	private void makeStructure(int x, int y) {
		board = new Field[y][x];
		// TODO call generator and ask for a new board. Load this into Field
		// objects.
		// TODO call solver with the generated board and load the solved values
		// in the corresponding field objects.
	}

	public void setValue(int x, int y, int value) throws UnkownFieldException {
		try {
			board[y][x].setCurrentValue(value);
		} catch (IndexOutOfBoundsException e) {
			throw new UnkownFieldException();
		}
	}

	public int getValue(int x, int y) throws UnkownFieldException {
		try {
			Field f = board[y][x];
			return f.getCurrentValue();
		} catch (IndexOutOfBoundsException e) {
			throw new UnkownFieldException();
		}
	}

}
