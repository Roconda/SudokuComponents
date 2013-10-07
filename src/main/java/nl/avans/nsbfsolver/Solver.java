package nl.avans.nsbfsolver;

import nl.avans.lib.ISolver;

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
	 * Solves a Sudoku puzzle.
	 * 
	 * @param input		The matrix based Sudoku puzzle to solve.
	 * @return			The matrix based Sudoku puzzle solved from the input.
	 */
	public int[][] solve(int[][] grid) {
		
		NakedSinglesSolver nakedSinglesSolver = new NakedSinglesSolver(puzzleSize);
		NakedPairsSolver nakedPairsSolver = new NakedPairsSolver(puzzleSize);
		BruteForceSolver bruteForceSolver = new BruteForceSolver(puzzleSize);
		
		int[][] singleSolutionGrid = nakedSinglesSolver.solve(grid);
		int[][] pairsSolutionGrid = nakedPairsSolver.solve(grid);
		int[][] solutionGrid;
		
		pairsSolutionGrid = (isSolved(singleSolutionGrid)) ? 
				singleSolutionGrid.clone() : 
				nakedPairsSolver.solve(singleSolutionGrid);
		
		if (pairsSolutionGrid != null) {
			solutionGrid = (isSolved(pairsSolutionGrid)) ?
				pairsSolutionGrid.clone() :
				bruteForceSolver.solve(pairsSolutionGrid);
		} else {
			solutionGrid = bruteForceSolver.solve(singleSolutionGrid);
		}
		
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