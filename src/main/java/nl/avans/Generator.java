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
		
	};
	
	private int blankValues;
	private int triesToBlankValue;
	
	
	private final int MIN_RANDOM_SYMTYPE = 0;
	private final int MAX_RANDOM_SYMTYPE = 2;
	
	private final int MAX_RECURSIONS = 9;
	
	
	/**
	 * Initializes a new instance of the Generator class.
	 * 
	 * @param solvedGrid	The already solved grid to hide numbers in.
	 */
    public Generator(int puzzleSize) {
    	solvedGrid = solvedGrid3x3;
    }
    
    
    /**
     * Generates a new Sudoku-puzzle.
     * 
     * @return		A new generated sudoku puzzle, based on the solved grid.
     */
    public int[][] generate()
    {
    	int[][] helpGrid,
    			puzzleGrid;
    	
    	int desiredValuesToBlank,
    		symmetryType;
    	
    	Random random = new Random();
    	
    	
    	blankValues = 0;
    	helpGrid = solvedGrid;
    	desiredValuesToBlank = 40;		//TODO: Add optional difficulty here...
    	symmetryType = (random.nextInt(MAX_RANDOM_SYMTYPE - MIN_RANDOM_SYMTYPE) + MIN_RANDOM_SYMTYPE);
    	
    	
    	do {
    		puzzleGrid = maskNumbers(helpGrid, symmetryType);
    	} while ((blankValues < desiredValuesToBlank) && (triesToBlankValue < MAX_RECURSIONS));
    	
    	return puzzleGrid;
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
    	int minRowIndex, maxRowIndex, minColumnIndex, maxColumnIndex;
    	
    	minRowIndex = minColumnIndex = 0;
    	maxRowIndex = maxColumnIndex = 8;
    	
    	int rowIndex = (random.nextInt(maxRowIndex - minRowIndex) + minRowIndex);
    	int columnIndex = (random.nextInt(maxColumnIndex - minColumnIndex) + minColumnIndex);
    	
    	while (grid[rowIndex][columnIndex] == 0) {
    		rowIndex = (random.nextInt(maxRowIndex - minRowIndex) + minRowIndex);
    		columnIndex = (random.nextInt(maxColumnIndex - minColumnIndex) + minColumnIndex);
    	}
    	
    	grid[rowIndex][columnIndex] = 0;
    	blankValues++;
    	
    	switch (symmetryType) {
	    	case 0:
	    		if (grid[rowIndex][(8 - columnIndex)] != 0)
	    			blankValues++;
	    		
	    		grid[rowIndex][(8 - columnIndex)] = 0;
	    		break;
	    		
	    	case 1:
	    		if (grid[(8 - rowIndex)][columnIndex] != 0)
	    			blankValues++;
	    		
	    		grid[(8 - rowIndex)][columnIndex] = 0;
	    		break;
	    		
	    	case 2:
	    		if (grid[columnIndex][rowIndex] != 0)
	    			blankValues++;
	    		
	    		grid[columnIndex][rowIndex] = 0;
	    		break;
	    		
	    	default:
	    		if (grid[rowIndex][(8 - columnIndex)] != 0)
	    			blankValues++;
	    		
	    		grid[columnIndex][rowIndex] = 0;
    	}
    	
    	return grid;
    }
}
