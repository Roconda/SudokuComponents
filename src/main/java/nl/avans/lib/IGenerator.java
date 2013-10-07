package nl.avans.lib;

/**
 * Implementation of the Generator component.
 * 
 * @author Dustin Sarioglu
 */
public interface IGenerator {
	
	/**
	 * Generates a matrix based Sudoku puzzle with a specific size and difficulty.
	 * 
	 * @param size			The size of the puzzle.
	 * @param difficulty	The difficulty of the puzzle.
	 * @return				A matrix based Sudoku puzzle with the desired size and difficulty.
	 */
	int[][] generate(int size, int difficulty);
}
