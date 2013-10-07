package nl.avans.generator;


import java.util.ArrayList;
import java.util.Random;


/**
 * Helps the generator generating a Sudoku puzzle @ random.
 * 
 * @author Dustin Sarioglu
 */
class GeneratorHelper {
	
	/** The puzzle grid. */
	private int[][] puzzleGrid;
	
	/** The puzzle size. */
	private int puzzleSize;
	
	
	/**
	 * Initializes a new instance of the GeneratorHelper class.
	 * 
	 * @param puzzleSize	The size of the puzzle to generate.
	 */
	GeneratorHelper(int puzzleSize) {
		
		this.puzzleSize = puzzleSize;
	}
	
	
	/**
	 * Generates a new Sudoku puzzle @ random.
	 * 
	 * @return		The @ random generated Sudoku puzzle.
	 */
	int[][] generate() {
		
		int[][] grid = new int[puzzleSize][puzzleSize];
		
		int row = 0,
			column = 0;
		Integer value;
		
		ArrayList<Integer> valuesSetA = new ArrayList<Integer>();
		ArrayList<Integer> valuesSetB = new ArrayList<Integer>();
		
		for (int range : new Range(1, puzzleSize)) {
			valuesSetA.add(range);
		}
		
		Random random = new Random();
		int randomIndex = random.nextInt((puzzleSize - 1));
		
		value = valuesSetA.get(randomIndex);
		grid[row][column] = value;
		valuesSetA.remove(value);
		
		//Add random numbers in the upper row
		for (row = 1; row < puzzleSize; row++) {
			randomIndex = random.nextInt(valuesSetA.size());
			value = valuesSetA.get(randomIndex);
			
			valuesSetB.add(value);
			valuesSetA.remove(value);
			
			grid[row][column] = value;
		}
		
		row = 0;
		
		//Add random numbers in the first column's set
		for (column = 1; column < getSetSize(); column++) {
			randomIndex = random.nextInt(valuesSetB.size());
			value = valuesSetB.get(randomIndex);
			
			while (isValueInFirstSet(grid, value)) {
				randomIndex = random.nextInt(valuesSetB.size());
				value = valuesSetB.get(randomIndex);
			}
			
			valuesSetB.remove(value);
			grid[row][column] = value;
		}
		
		//Add random numbers to the remaining empty fields in the first column
		for (column = getSetSize(); column < puzzleSize; column++) {
			randomIndex = random.nextInt(valuesSetB.size());
			value = valuesSetB.get(randomIndex);
			valuesSetB.remove(value);
			
			grid[row][column] = value;
		}
		
		finish(grid.clone());
		
		return puzzleGrid;
	}
	
	
	/**
	 * Finishes a Sudoku puzzle grid for usage.
	 * 
	 * @param grid		The grid to finish.
	 * @return			The finished Sudoku puzzle grid.
	 */
	private boolean finish(int[][] grid) {
		
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
				
				if (finish(helpGrid)) {
					
					puzzleGrid = helpGrid.clone();
					
					return true;
				}
				
				helpGrid[row][column] = 0;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks whether a specific value is (already) filled in the first set of the puzzle.
	 * 
	 * @param grid		The grid to do the check in.
	 * @param value		The value to check for.
	 * @return			True or false due the value is (already) filled in the first set of the puzzle.
	 */
	private boolean isValueInFirstSet(int[][] grid, int value) {
		
		for (int row = 0; row < getSetSize(); row++) {
			if (grid[row][0] == value)
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * Gets the size of a set based on the puzzle's size.
	 * 
	 * @return		The size of a set based on the puzzle's size.
	 */
	private int getSetSize() {
		
		return ((int) Math.sqrt(puzzleSize));
	}
}
