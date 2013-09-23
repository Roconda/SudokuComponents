package nl.avans.solver-nakedsingles-bruteforce;


class NakedSinglesSolver {
	
	private int puzzleSize;
	
	private boolean[] singlePossibilities;
	
	
	NakedSinglesSolver(int puzzleSize) { 
		
		singlePossibilities = new boolean[(puzzleSize + 1)];
		
		this.puzzleSize = puzzleSize;
	}
	
	
	int[][] solve(int[][] grid) {
		
		boolean hasMadeChanges;
		int possibilities;
		
		int[][] singleSolution = grid.clone();
		
		
		do {
			
			hasMadeChanges = false;
			for (int row = 0; row < puzzleSize; row++) {
				for (int column = 0; column < puzzleSize; column++) {
					if (singleSolution[row][column] == 0) {
						
						possibilities = getPossibilities(row, column, singleSolution);
						if (possibilities == 1) {
							singleSolution[row][column] = getSmallestPossibility();
							hasMadeChanges = (singleSolution[row][column] != 0);
						}
					}
				}
			}
			
		} while (hasMadeChanges && !isSolved(singleSolution));
		
		return singleSolution;
	}
	
	
	private boolean isSolved(int[][] grid) {
		
		boolean isSolved = true;
		int row, column;
		
		row = 0;
		while (isSolved && row < puzzleSize) {
			column = 0;
			while (isSolved && column < puzzleSize) {
				isSolved = (grid[row][column] != 0);
				column++;
			}
			
			row++;
		}
		
		return isSolved;
	}
	
	private boolean isInRow(int[][] grid, int row, int value) {
		
		boolean  isInRow = false;
		for (int column = 0; column < puzzleSize; column++) {
			isInRow = (isInRow || (Math.abs(grid[row][column]) == value));
		}
		
		return isInRow;
	}
	
	private boolean isInColumn(int[][] grid, int column, int value) {
		
		boolean  isInColumn = false;
		for (int row = 0; row < puzzleSize; row++) {
			isInColumn = (isInColumn || (Math.abs(grid[row][column]) == value));
		}
		
		return isInColumn;
	}
	
	private boolean isInSet(int[][] grid, int row, int column, int value) {
		
		int lowestRow, lowestColumn;
		
		lowestRow = (getSetSize() * getSetIndex(row));
		lowestColumn = (getSetSize() * getSetIndex(column));
		
		for (int setRow = lowestRow; setRow < (lowestRow + getSetSize()); setRow++) {
			for (int setColumn = lowestColumn; setColumn < (lowestColumn + getSetSize()); setColumn++) {
				if (Math.abs(grid[setRow][setColumn]) == value) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean isPossible(int[][] grid, int row, int column, int value) {
		
		return (!isInRow(grid, row, value) && !isInColumn(grid, column, value) && !isInSet(grid, row, column, value));
	}
	
	
	private int getPossibilities(int row, int column, int[][] grid) {
		
		int possibilities = 0;
		
		clearPossibilities();
		
		for (int value = 1; value < (puzzleSize + 1); value++) {
			
			if (isPossible(grid, row, column, value)) {
				singlePossibilities[value] = true;
				possibilities++;
			} else {
				singlePossibilities[value] = false;
			}
		}
		
		return possibilities;
	}
	
	private int getSmallestPossibility() {
		
		int value = 1, smallestPossibility = 0;
		
		while (smallestPossibility == 0 && value < (puzzleSize + 1)) {
			if (singlePossibilities[value])
				smallestPossibility = value;
			
			value++;
		}
		
		return smallestPossibility;
	}
	
	private int getSetSize() {
		
		return ((int) Math.sqrt(puzzleSize));
	}
	
	private int getSetIndex(int rowColumn) {
		
		return (rowColumn / getSetSize());
	}
	
	
	private void clearPossibilities() {
		
		for (int index = 0; index < singlePossibilities.length; index++) {
			singlePossibilities[index] = false;
		}
	}
}
