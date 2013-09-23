package nl.avans.solver-nakedsingles-bruteforce;


public class Solver implements ISolver {
	
	private int puzzleSize;
	
	public Solver(int puzzleSize) {
		this.puzzleSize = puzzleSize;
	}
	
	
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
