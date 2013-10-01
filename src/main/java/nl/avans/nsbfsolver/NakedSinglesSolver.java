package nl.avans.nsbfsolver;


/**
 * Represents a Naked Singles based Sudoku solver.
 * 
 * @author Dustin Sarioglu
 */
class NakedSinglesSolver {
	
	private int puzzleSize;
	
	private boolean[] singlePossibilities;
	
	
	/**
	 * Initializes a new instance of the NakedSinglesSolver class.
	 * 
	 * @param puzzleSize
	 */
	NakedSinglesSolver(int puzzleSize) { 
		
		singlePossibilities = new boolean[(puzzleSize + 1)];
		
		this.puzzleSize = puzzleSize;
	}
	
	
	/**
	 * Solves a Sudoku grid using the Naked Singles method.
	 * 
	 * @param grid		The Sudoku grid to solve.
	 * @return			The 'Naked Singles' solved Sudoku grid.
	 */
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
	
	
	/**
	 * Checks wether the Sudoku grid is solved.
	 * 
	 * @param grid		The Sudoku grid to check if it's solved or not.
	 * @return			True or false due the Sudoku grid is solved.
	 * 
	 * TODO:			Remove this method and implement Solver.isSolved();.
	 */
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
	
	/**
	 * Checks wether a value is in the same row.
	 * 
	 * @param grid		The Sudoku grid to check in.
	 * @param row		The row to check in.
	 * @param value		The value to search for.
	 * @return			True or false due the value was found in the same row.
	 */
	private boolean isInRow(int[][] grid, int row, int value) {
		
		boolean  isInRow = false;
		for (int column = 0; column < puzzleSize; column++) {
			isInRow = (isInRow || (Math.abs(grid[row][column]) == value));
		}
		
		return isInRow;
	}
	
	/**
	 * Checks wether a value is in the same column.
	 * 
	 * @param grid		The Sudoku grid to check in.
	 * @param column	The column to check in.
	 * @param value		The value to search for.
	 * @return			True or false due the value was found in the same column.
	 */
	private boolean isInColumn(int[][] grid, int column, int value) {
		
		boolean  isInColumn = false;
		for (int row = 0; row < puzzleSize; row++) {
			isInColumn = (isInColumn || (Math.abs(grid[row][column]) == value));
		}
		
		return isInColumn;
	}
	
	/**
	 * Checks wether a value is in the same row.
	 * 
	 * @param grid		The Sudoku grid to check in.
	 * @param row		The row to check in.
	 * @param column	The column to check in.
	 * @param value		The value to search for.
	 * @return			True or false due the value was found in the same set.
	 */
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
	
	/**
	 * Checks wether a value is possible to fill in.
	 * 
	 * @param grid		The Sudoku grid to check in.
	 * @param row		The row to check for.
	 * @param column	The column to check for.
	 * @param value		The value we're trying to fill in.
	 * @return			True or false due the value can be filled in the row/column.
	 */
	private boolean isPossible(int[][] grid, int row, int column, int value) {
		
		return (!isInRow(grid, row, value) && !isInColumn(grid, column, value) && !isInSet(grid, row, column, value));
	}
	
	
	/**
	 * Gets the count of possibilities in a specific field.
	 * 
	 * @param row		The row-index of the field.
	 * @param column	The column-index of the field.
	 * @param grid		The Sudoku grid.
	 * @return			The count of possibilities in the desired field.
	 */
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
	
	/**
	 * Gets the value with the least possibilities.
	 * 
	 * @return 		The value with the least possibilities.
	 */
	private int getSmallestPossibility() {
		
		int value = 1, smallestPossibility = 0;
		
		while (smallestPossibility == 0 && value < (puzzleSize + 1)) {
			if (singlePossibilities[value])
				smallestPossibility = value;
			
			value++;
		}
		
		return smallestPossibility;
	}
	
	/**
	 * Gets the size of a set based on the puzzle's size.
	 * 
	 * For example: a puzzle with 9 by 9 fields has set's with
	 * a size of 3 by 3.
	 * 
	 * @return		The size of a set based on the puzzle's size.
	 */
	private int getSetSize() {
		
		return ((int) Math.sqrt(puzzleSize));
	}
	
	/**
	 * Gets the index of a set from a random row- or column-index.
	 * 
	 * @param rowColumn		The row or column to calculate the set-index from.
	 * @return				The set-index of the row or column.
	 */
	private int getSetIndex(int rowColumn) {
		
		return (rowColumn / getSetSize());
	}
	
	
	/**
	 * Clears the possibilities array.
	 */
	private void clearPossibilities() {
		
		for (int index = 0; index < singlePossibilities.length; index++) {
			singlePossibilities[index] = false;
		}
	}
}
