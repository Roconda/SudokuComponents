package nl.avans;

/**
 * IGenerator interface.
 * 
 * @author Dustin Sarioglu
 */
public interface IGenerator {
	
	/**
	 * Generates a new sudoku-puzzle.
	 * 
	 * @return		A new generated sudoku puzzle, based on the solved grid.
	 */
	int[][] generate();
}