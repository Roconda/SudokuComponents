package nl.avans.SudokuDatastructure;

class BoardDS {
	
	private int[][] board;
	
	public BoardDS(int x, int y) {
		makeStructure(x, y);
	}

	private void makeStructure(int x, int y) {
		board = new int[y][x];
	}
	
	public void setValue(int x, int y, int value) throws UnkownFieldException {
		try{
			board[y][x] = value;
		}catch(IndexOutOfBoundsException e){
			throw new UnkownFieldException();
		}
	}

}
