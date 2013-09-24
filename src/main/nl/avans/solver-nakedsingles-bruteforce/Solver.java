package nl.avans.solver-nakedsingles-bruteforce;


/**
 * Represents the base system for solving Sudoku puzzles.
 * 
 * @author Dustin Sarioglu
 */
public class Solver implements ISolver {
	
	private int puzzleSize;
	
	/**
	 * Initializes a new instance of the Solver class.
	 * 
	 * @param puzzleSize		The size of the Sudoku puzzle to solve.
	 */
	public Solver(int puzzleSize) {
		this.puzzleSize = puzzleSize;
	}
	
	
	/**
	 * Solves a Sudoku grid.
	 * 
	 * First method is solving the puzzle based on all Naked Singles.
	 * Second method is solving the puzzle @ Brute Force.
	 * 
	 * @param grid		The Sudoku grid to solve.
	 * @return			The solved Sudoku grid.
	 */
	public int[][] solve(int[][] grid) {
		
		NakedSinglesSolver nakedSinglesSolver = new NakedSinglesSolver(puzzleSize);
		BruteForceSolver bruteForceSolver = new BruteForceSolver(puzzleSize);
		
		int[][] singleSolutionGrid = nakedSinglesSolver.solve(grid);
		int[][] solutionGrid;
		
		solutionGrid = (isSolved(singleSolutionGrid)) ? 
				singleSolutionGrid.clone() : 
				bruteForceSolver.solve(singleSolutionGrid);
		
		return solutionGrid;
	}
	
	
	/**
	 * Checks wether the Sudoku grid is solved.
	 * 
	 * @param grid		The Sudoku grid to check if it's solved or not.
	 * @return			True or false due the Sudoku grid is solved.
	 * 
	 * TODO:			Make this method internal static and remove the duplicated 
	 * 					method in NakedSinglesSolver.java.
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
}
