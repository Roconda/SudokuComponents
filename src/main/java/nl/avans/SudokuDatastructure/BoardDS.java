package nl.avans.SudokuDatastructure;

class BoardDS {
	
	private Field[][] board;
	
	public BoardDS(int x, int y) {
		makeStructure(x, y);
	}

	private void makeStructure(int x, int y) {
		board = new Field[y][x];
	}
	
	public void setValue(int x, int y, int value) throws UnkownFieldException {
		try{
			board[y][x].setValue(value);
		}catch(IndexOutOfBoundsException e){
			throw new UnkownFieldException();
		}
	}

	public int getValue(int x, int y) throws UnkownFieldException {
		try{
			Field f = board[y][x];
			return f.getValue();
		}catch(IndexOutOfBoundsException e){
			throw new UnkownFieldException();
		}
	}

}
