package nl.avans;

import java.util.Random;

/**
 * Generator class.
 * 
 * @author Dustin Sarioglu
 */
public class Generator implements IGenerator
{
	private static int[][] solvedGrid;
	
	private static int[][] solvedGrid2x2 = {
		{ 4, 1, 3, 2 },
		{ 3, 2, 1, 4 },
		{ 1, 4, 2, 3 },
		{ 2, 3, 4, 1 }
	};
	private static int[][] solvedGrid3x3 = {
		{ 7, 5, 4, 3, 2, 1, 9, 8, 6 },
		{ 1, 6, 8, 7, 5, 9, 2, 3, 4 },
		{ 9, 2, 3, 4, 8, 6, 7, 1, 5 },
		{ 3, 9, 7, 5, 1, 4, 6, 2, 8 },
		{ 6, 8, 2, 9, 3, 7, 5, 4, 1 },
		{ 5, 4, 1, 8, 6, 2, 3, 7, 9 },
		{ 8, 3, 9, 1, 7, 5, 4, 6, 2 },
		{ 4, 1, 6, 2, 9, 3, 8, 5, 7 },
		{ 2, 7, 5, 6, 4, 8, 1, 9, 3 }
	};
	private static int[][] solvedGrid4x4 = {
		{ 6, 16, 1, 8, 13, 10, 5, 3, 14, 12, 2, 4, 11, 15, 7, 9 },
		{ 15, 10, 13, 9, 1, 14, 2, 4, 16, 11, 3, 7, 6, 12, 8, 5 },
		{ 12, 14, 4, 11, 7, 6, 15, 9, 10, 5, 1, 8, 13, 2, 16, 3 },
		{ 2, 7, 3, 5, 12, 11, 8, 16, 15, 6, 9, 13, 1, 10, 4, 14 },
		{ 13, 6, 8, 2, 5, 12, 16, 15, 1, 3, 11, 10, 7, 14, 9, 4 },
		{ 3, 9, 12, 14, 11, 4, 7, 1, 8, 2, 13, 15, 16, 5, 6, 10 },
		{ 4, 15, 7, 16, 9, 8, 10, 13, 6, 14, 12, 5, 3, 1, 11, 2 },
		{ 10, 5, 11, 1, 6, 3, 14, 2, 7, 9, 4, 16, 12, 8, 15, 13 },
		{ 5, 2, 15, 6, 16, 13, 9, 10, 4, 7, 8, 12, 14, 11, 3, 1 },
		{ 14, 3, 10, 4, 8, 2, 1, 5, 11, 13, 15, 6, 9, 7, 12, 16 },
		{ 8, 13, 16, 12, 3, 7, 11, 6, 9, 1, 5, 14, 2, 4, 10, 15 },
		{ 1, 11, 9, 7, 14, 15, 4, 12, 2, 16, 10, 3, 5, 6, 13, 8 },
		{ 11, 4, 5, 13, 10, 9, 6, 14, 3, 8, 7, 1, 15, 16, 2, 12 },
		{ 7, 12, 2, 3, 4, 16, 13, 8, 5, 15, 14, 11, 10, 9, 1, 6 },
		{ 16, 8, 14, 15, 2, 1, 12, 11, 13, 10, 6, 9, 4, 3, 5, 7 },
		{ 9, 1, 6, 10, 15, 5, 3, 7, 12, 4, 16, 2, 8, 13, 14, 11 }
	};
	
	private int blankValues;
	private int puzzleSize;
	
	
	/**
	 * Initializes a new instance of the Generator class.
	 * 
	 * @param solvedGrid	The already solved grid to hide numbers in.
	 */
    public Generator(int puzzleSize) {
    	this.puzzleSize = puzzleSize;
    	
    	if (getSetSize() == 2) {
    		solvedGrid = solvedGrid2x2.clone();
    	} else if (getSetSize() == 3) {
    		solvedGrid = solvedGrid3x3.clone();
    	} else if (getSetSize() == 4) {
    		solvedGrid = solvedGrid4x4.clone();
    	}
    }
    
    
    public int[][] getSolvedGrid() {
    	
    	return solvedGrid;
    }
    
    
    /**
     * Generates a new Sudoku-puzzle.
     * 
     * @return		A new generated sudoku puzzle, based on the solved grid.
     */
    public int[][] generate() {
    	
    	int[][] helpGrid;
    	
    	int blankValues = 0,
    		symmetryType = 0,
    		desiredBlankValues;
    	
    	helpGrid = solvedGrid.clone();
    	
    	Random random = new Random();
    	
    	desiredBlankValues = ((puzzleSize * puzzleSize) / 2);
    	symmetryType = random.nextInt(2);
    	
    	do {
    		
    		helpGrid = maskNumbers(helpGrid, symmetryType);
    		blankValues = this.blankValues;
    	} while (blankValues < desiredBlankValues);
    	
    	return helpGrid;
    }
    
    
    /**
     * Masks numbers in a grid @random.
     * 
     * @param grid			The solved grid to create the puzzle-grid out of.
     * @param symmetryType	The used type of symmetry to mask numbers in.
     * 
     * @return				The solved grid with one or more numbers masked.
     */
    private int[][] maskNumbers(int[][] grid, int symmetryType) {
    	
    	Random random = new Random();
    	
    	int rowIndex,
    		columnIndex;
    	
    	rowIndex = random.nextInt(puzzleSize - 1);
    	columnIndex = random.nextInt(puzzleSize - 1);
    	
    	while (grid[rowIndex][columnIndex] == 0) {
    		
    		rowIndex = random.nextInt(puzzleSize - 1);
    		columnIndex = random.nextInt(puzzleSize - 1);
    	}
    	
    	grid[rowIndex][columnIndex] = 0;
    	blankValues++;
    	
    	
    	switch (symmetryType) {
    	
	    	case 0:
	    		if (grid[rowIndex][((puzzleSize - 1) - columnIndex)] != 0)
	    			blankValues++;
	    		
	    		grid[rowIndex][((puzzleSize - 1) - columnIndex)] = 0;
	    		break;
	    		
	    	case 1:
	    		if (grid[((puzzleSize - 1) - rowIndex)][columnIndex] != 0)
	    			blankValues++;
	    		
	    		grid[((puzzleSize - 1) - rowIndex)][columnIndex] = 0;
	    		break;
	    		
	    	case 2:
	    		if (grid[columnIndex][rowIndex] != 0)
	    			blankValues++;
	    		
	    		grid[columnIndex][rowIndex] = 0;
	    		break;
	    		
	    	default:
	    		if (grid[rowIndex][((puzzleSize - 1) - columnIndex)] != 0)
	    			blankValues++;
	    		
	    		grid[columnIndex][rowIndex] = 0;
	    		break;
    	}
    	
    	return grid;
    }
    
    
    private int getSetSize() {
    	
    	return (int) Math.sqrt(puzzleSize);
    }
}
