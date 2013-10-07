package nl.avans.datastructure;

import nl.avans.lib.IGenerator;

class BoardDS {
	private final int PLACEHOLDER = -9999; 
	private Field[][] board;
	private IGenerator generator;
	private int size = PLACEHOLDER;
	private int difficulty = PLACEHOLDER;

	public BoardDS setSize(int size) {
		this.size = size;
		board = new Field[getSize()][getSize()];
		return this;
	}
	
	public BoardDS setGenerator(IGenerator generator) {
		this.generator = generator;
		return this;

	}
	
	public BoardDS setDifficulty(int difficulty) {
		this.difficulty = difficulty;
		return this;
	}

	public void generate() throws CannotGenerateException {
		generateCheck();
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
	
	public int getCurrentValue(int x, int y) throws CannotGenerateException {
		generateCheck();
		
		if (isFieldNotOnGrid(x, y))
			return -1;

		return this.board[x][y].getCurrentValue();
	}

	public void setCurrentValue(int x, int y, int currentValue) throws UnkownFieldException {
		if (isFieldNotOnGrid(x, y))
			throw new UnkownFieldException("The field is not on the grid.");
		if (isIllegalValue(currentValue))
			throw new UnkownFieldException("The value is lower or higher than allowed.");

		if (this.board[x][y] instanceof UserField)
			this.board[x][y].setCurrentValue(currentValue);
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

	public int getDifficulty() {
		return difficulty;
	}
	
	private void generateCheck() throws CannotGenerateException {
		boolean canGenerate = true;
		StringBuilder message = new StringBuilder("Something bad has happened:\n\n");
		
		if(generator == null) {
			canGenerate = false;
			message.append("- No generator found\n");
		}
		if(difficulty == PLACEHOLDER) {
			 canGenerate = false;
			 message.append("- No difficulty was set\n");
		}
		if(size == PLACEHOLDER) {
			 canGenerate = false;
			 message.append("- Size not set\n");
		}
		
		if(!canGenerate) throw new CannotGenerateException(message.toString());
	}
}