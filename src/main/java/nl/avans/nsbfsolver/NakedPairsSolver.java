package nl.avans.nsbfsolver;

import java.util.Random;

/**
 * Represents a Naked Pairs based Sudoku solver.
 * 
 * @author Dustin
 *
 */
class NakedPairsSolver {
	
	private final int MAX_RECURSIONS = 1000;
	
	private int[][][] solutionGrids;
	private int[][] solutionGrid;
	private boolean[] possibilities;
	
	private int recursions;
	private int solutions;
	private int puzzleSize;
	
	private int row;
	private int column;
	private int possibles;
	
	private boolean stopSearching;
	
	
	NakedPairsSolver(int puzzleSize) {
		
		this.puzzleSize = puzzleSize;
		
		possibilities = new boolean[(puzzleSize + 1)];
		solutionGrids = new int[2][puzzleSize][puzzleSize];
	}
	
	int[][] solve(int[][] grid) {
		
		solveGrid(grid, false);
		
		return solutionGrid;
	}
	
	private boolean solveGrid(int[][] grid, boolean checkForUniqueSolutions) {
		
		int[][] gridClone = grid.clone();
		
		int index,
			currentValue;
		boolean isDone,
			foundValue,
			solvedValue,
			solvedGrid;
		
		foundValue = false;
		recursions++;
		
		if (isSolved(grid)) {
			if (solutions > 0) {
				stopSearching = true;
				solvedGrid = false;
			} else {
				solutions++;
				solutionGrids[solutions] = grid.clone();
				
				solvedGrid = true;
				solutionGrid = gridClone;
			}
		} else {
			if (!findFewestPossibilities(gridClone)) {
				solvedGrid = false;
			} else {
				index = 1;
				isDone = false;
				
				while (!isDone && index <= possibles) {
					currentValue = getValueForField();
					possibilities[currentValue] = false;
					
					gridClone[row][column] = currentValue;
					
					solvedValue = (recursions < MAX_RECURSIONS) ? 
							solveGrid(gridClone, checkForUniqueSolutions) : 
							false;
							
					if (stopSearching) {
						isDone = true;
						foundValue = true;
					} else {
						foundValue = (foundValue || solvedValue);
						if (!checkForUniqueSolutions) {
							isDone = foundValue;
						}
					}
					
					index++;
				}
				
				solvedGrid = foundValue;
			}
		}
		
		return solvedGrid;
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
	
	private boolean findFewestPossibilities(int[][] grid) {
		
		boolean[] fewestPossibilities = new boolean[(puzzleSize + 1)];
		
		int possiblesCount, fewestPossiblesRow, fewestPossiblesColumn, fewestPossiblesValue, currentRowIndex, currentColumnIndex;
		boolean isBadSolution, isValidFieldFound;
		
		fewestPossiblesValue = (puzzleSize + 1);
		fewestPossiblesRow = fewestPossiblesColumn = 0;
		
		for (int index = 1; index < (puzzleSize + 1); index++) {
			fewestPossibilities[index] = false;
		}
		
		isBadSolution = false;
		currentRowIndex = 0;
		
		while (!isBadSolution && currentRowIndex < puzzleSize) {
			currentColumnIndex = 0;
			while (!isBadSolution && currentColumnIndex < puzzleSize) {
				if (grid[currentRowIndex][currentColumnIndex] == 0) {
					possiblesCount = getPossibilities(grid, currentRowIndex, currentColumnIndex);
					if (possiblesCount == 0) {
						isBadSolution = true;
					} else {
						if (possiblesCount < fewestPossiblesValue) {
							fewestPossiblesValue = possiblesCount;
							fewestPossibilities = possibilities.clone();
							
							fewestPossiblesRow = currentRowIndex;
							fewestPossiblesColumn = currentColumnIndex;
						}
					}
				}
				
				currentColumnIndex++;
			}
			
			currentRowIndex++;
		}
		
		if (isBadSolution || fewestPossiblesValue == 10) {
			isValidFieldFound = false;
			row = 0;
			column = 0;
			possibles = 0;
		} else {
			isValidFieldFound = true;
			row = fewestPossiblesRow;
			column = fewestPossiblesColumn;
			possibles = fewestPossiblesValue;
			
			possibilities = fewestPossibilities.clone();
		}
		
		return isValidFieldFound;
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
	private int getPossibilities(int[][] grid, int row, int column) {
		
		int possiblesCount = 0;
		
		clearPossibilities();
		
		for (int value = 1; value < (puzzleSize + 1); value++) {
			
			if (isPossible(grid, row, column, value)) {
				possibilities[value] = true;
				possiblesCount++;
			} else {
				possibilities[value] = false;
			}
		}
		
		return possiblesCount;
	}
	
	private int getValueForField() {
		int index;
		int valueForField = 0;
		
		Random random = new Random();
		
		if (getSmallestPossibility() != 0) {
			index = random.nextInt(puzzleSize - 1) + 1;
			while (valueForField == 0) {
				if (possibilities[index]) {
					valueForField = index;
				} else {
					index++;
					if (index > puzzleSize)
						index = 1;
				}
			}
		} else {
			valueForField = 0;
		}
		
		return valueForField;
	}
	
	/**
	 * Gets the value with the least possibilities.
	 * 
	 * @return 		The value with the least possibilities.
	 */
	private int getSmallestPossibility() {
		
		int value = 1, smallestPossibility = 0;
		
		while (smallestPossibility == 0 && value < (puzzleSize + 1)) {
			if (possibilities[value])
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
		
		for (int index = 0; index < possibilities.length; index++) {
			possibilities[index] = false;
		}
	}
}