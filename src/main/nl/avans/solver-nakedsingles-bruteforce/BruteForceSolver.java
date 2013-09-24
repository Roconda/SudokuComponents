package nl.avans.solver-nakedsingles-bruteforce;


/**
 * Represents a Brute Force based Sudoku solver.
 * 
 * @author Dustin Sarioglu
 */
public class BruteForceSolver {
	
	private int[][] solutionGrid;
	
	private int puzzleSize;
	
	
	/**
	 * Initializes a new instance of the BruteForceSolver class.
	 * 
	 * @param puzzleSize
	 */
	BruteForceSolver(int puzzleSize) {
		
		this.puzzleSize = puzzleSize;
	}
	
	
	/**
	 * Solves a Sudoku grid using the Brute Force method.
	 * 
	 * @param grid		The Sudoku grid to solve.
	 * @return			The Brute Force solved Sudoku grid.
	 */
	int[][] solve(int[][] grid) {
		
		do {
			trySolve(grid);
		} while (solutionGrid == null || isEmpty(solutionGrid));
		
		return solutionGrid;
	}
	
	
	/**
	 * Solves a Sudoku grid using the Brute Force method.
	 * 
	 * @param grid		The Sudoku grid to solve.
	 * @return			True or false due another correct value was found.
	 * 
	 * NOTE:			The return parameter is only used by the recursive call in this method.
	 */
	private boolean trySolve(int[][] grid) {
		
		int[][] helpGrid = grid.clone();
		
		int row = 0,
			column = 0;
		boolean foundValue = false;
		
		for (row = 0; row < puzzleSize; row++) {
			for (column = 0; column < puzzleSize; column++) {
				if (helpGrid[row][column] == 0) {
					foundValue = true;
					break;
				}
			}
			
			if (foundValue)
				break;
		}
		
		if (!foundValue)
			return true;
		
		boolean digits[] = new boolean[(puzzleSize + 2)];
		for (int index = 0; index < puzzleSize; index++) {
			digits[helpGrid[row][index]] = true;
			digits[helpGrid[index][column]] = true;
		}
		
		int setRow = (getSetSize() * (row / getSetSize())),
			setColumn = (getSetSize() * (column / getSetSize()));
		for (int x = 0; x < getSetSize(); x++) {
			for (int y = 0; y < getSetSize(); y++) {
				digits[helpGrid[(setRow + x)][(setColumn + y)]] = true;
			}
		}
		
		for (int value = 1; value <= puzzleSize; value++) {
			if (!digits[value]) {
				helpGrid[row][column] = value;
				
				if (trySolve(helpGrid)) {
					
					solutionGrid = helpGrid.clone();
					
					return true;
				}
				
				helpGrid[row][column] = 0;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks wether the Sudoku grid is empty.
	 * 
	 * @param grid		The Sudoku grid.
	 * @return			True or false due the Sudoku grid is empty.
	 */
	private boolean isEmpty(int[][] grid) {
		
		for (int row = 0; row < puzzleSize; row++) {
			for (int column = 0; column < puzzleSize; column++) {
				if (grid[row][column] != 0)
					return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * Gets the size of a set, based on the Sudoku puzzle.
	 * 
	 * @return		The size of a set, based on the Sudoku puzzle.
	 */
	private int getSetSize() {
		
		return ((int) Math.sqrt(puzzleSize));
	}
}
