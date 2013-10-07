package nl.avans.lib;

/**
 * Implementation for the Solver component.
 * 
 * @author Dustin Sarioglu
 */
public interface ISolver {
	
	/**
	 * Solves a Sudoku puzzle.
	 * 
	 * @param input		The matrix based Sudoku puzzle to solve.
	 * @return			The matrix based Sudoku puzzle solved from the input.
	 */
	int[][] solve(int[][] input);
}
