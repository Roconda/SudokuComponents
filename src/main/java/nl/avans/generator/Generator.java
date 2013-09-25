package nl.avans.generator;


import java.util.Random;

import nl.avans.lib.IGenerator;
import nl.avans.lib.EDifficulty;


/**
 * Generator class.
 * 
 * @author Dustin Sarioglu
 */
public class Generator implements IGenerator
{
	private static int[][] solvedGrid;
	
	private GeneratorHelper generatorHelper;
	
	private int blankValues;
	private int puzzleSize;
	
	
	/**
	 * Initializes a new instance of the Generator class.
	 */
    public Generator() { }
    
    
    /**
     * Generates a new Sudoku-puzzle.
     * 
     * @return		A new generated sudoku puzzle, based on the solved grid.
     */
    public int[][] generate(int puzzleSize, EDifficulty difficulty) {
    	
    	int[][] helpGrid;
    	
    	int blankValues = 0,
    		symmetryType = 0,
    		desiredBlankValues;
    	
    	this.puzzleSize = puzzleSize;
    	
    	generatorHelper = new GeneratorHelper(puzzleSize);
    	solvedGrid = generatorHelper.generate();
    	
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
}