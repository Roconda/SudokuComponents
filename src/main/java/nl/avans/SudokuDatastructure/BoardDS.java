package nl.avans.SudokuDatastructure;

class BoardDS {
	private Field[][] board;
	private IGenerator generator;
	private ISolver solver;
	private EDifficulty difficulty;
	private int size;

	public BoardDS(int size, EDifficulty difficulty, IGenerator generator, ISolver solver) {
		this.size = size;
		this.difficulty = difficulty;
		this.generator = generator;
		this.solver = solver;
		
		setup();
	}

	private void setup() {
		board = new Field[getSize()][getSize()];
		generate();
		solve();
	}
	
	private void generate() {
		int[][] content = generator.generate(getDifficulty(), getSize());
		
		for(int x = 0; x < getNumberOfColumns(); x++) {
			for(int y = 0; y < getNumberOfRows(); y++) {
				if(content[x][y] == -1) {
					this.board[x][y] = new UserField(-1, -1);
				} else {
					this.board[x][y] = new GeneratedField(content[x][y], -1);
				}
			}
		}
	}
	
	private void solve() {
		int[][] content = solver.solve(getBoardAsIntegers());
		
		for(int x = 0; x < getNumberOfColumns(); x++) {
			for(int y = 0; y < getNumberOfRows(); y++) {
				if(board[x][y] instanceof UserField) {
					setSolutionValue(x, y, content[x][y]);	
				}
			}
		}
	}
	
	private int[][] getBoardAsIntegers()
	{
		int[][] snapshot = new int[getNumberOfColumns()][getNumberOfRows()];
		
		for(int x = 0; x < getNumberOfColumns(); x++) {
			for(int y = 0; y < getNumberOfRows(); y++) {
				snapshot[x][y] = getCurrentValue(x, y);
			}
		}
		
		return snapshot;
	}
	
	public int getCurrentValue(int x, int y)
	{
		if(isFieldNotOnGrid(x, y)) throw new IllegalArgumentException("The field is not on the grid.");
		
		return this.board[x][y].getCurrentValue();
	}
	
	public void setCurrentValue(int x, int y, int currentValue) {
		if(isFieldNotOnGrid(x, y)) throw new IllegalArgumentException("The field is not on the grid.");
		if(isIllegalValue(currentValue)) throw new IllegalArgumentException("The value is lower or higher than allowed.");
		
		this.board[x][y].setCurrentValue(currentValue);
	}
	
	public int getSolutionValue(int x, int y) {
		if(isFieldNotOnGrid(x, y)) throw new IllegalArgumentException("The field is not on the grid.");

		return this.board[x][y].getSolutionValue();
	}
	
	private void setSolutionValue(int x, int y, int solutionValue) {
		if(isFieldNotOnGrid(x, y)) throw new IllegalArgumentException("The field is not on the grid.");
		if(isIllegalValue(solutionValue)) throw new IllegalArgumentException("The value is lower or higher than allowed.");
		
		this.board[x][y].setSolutionValue(solutionValue);
	}
	
	private boolean isFieldNotOnGrid(int x, int y) {
		if((x < 0 || x > getNumberOfColumns()) || (y < 0 || y > getNumberOfRows())) {
			return false;
		}

		return true;
	}
	
	private boolean isIllegalValue(int value) {
		if(value < 1 || value > getSize()) {
			return false;
		}
		
		return true;
	}
		
	public int getNumberOfColumns()
	{
		return this.board.length;
	}
	
	public int getNumberOfRows()
	{
		return this.board[0].length;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public EDifficulty getDifficulty() {
		return difficulty;
	}
}