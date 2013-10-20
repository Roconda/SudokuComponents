package nl.avans.nsbfsolver;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import nl.avans.nsbfsolver.Solver;


/**
 * Represents the Unit Test class for testing the generator.
 * 
 * @author Dustin Sarioglu
 *
 */
public class SolverTest extends TestCase {
	
	private Solver solver;
	
	
	private int[][] puzzle4x4 = {
			{ 4, 0, 0, 1 },
			{ 0, 0, 3, 0 },
			{ 2, 0, 1, 0 },
			{ 0, 1, 4, 0 }
	};
	private int[][] puzzle9x9 = {
			{ 8, 0, 6, 0, 0, 9, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 8, 0, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 4, 0 },
			{ 0, 2, 0, 6, 0, 0, 0, 7, 0 },
			{ 9, 8, 0, 0, 0, 0, 0, 0, 3 },
			{ 0, 7, 3, 0, 9, 8, 4, 2, 0 },
			{ 0, 0, 0, 0, 0, 3, 6, 0, 0 },
			{ 0, 0, 1, 0, 7, 0, 0, 0, 0 },
			{ 3, 0, 0, 0, 0, 0, 0, 0, 7 }
	};
	
	private int[][] solution4x4 = {
			{ 4, 3, 2, 1 },
			{ 1, 2, 3, 4 },
			{ 2, 4, 1, 3 },
			{ 3, 1, 4, 2 }
	};
	private int[][] solution9x9 = {
			{ 8, 4, 6, 7, 2, 9, 1, 3, 5 },
			{ 5, 3, 7, 4, 6, 1, 8, 9, 2 },
			{ 2, 1, 9, 3, 8, 5, 7, 4, 6 },
			{ 1, 2, 5, 6, 3, 4, 9, 7, 8 },
			{ 9, 8, 4, 2, 1, 7, 5, 6, 3 },
			{ 6, 7, 3, 5, 9, 8, 4, 2, 1 },
			{ 7, 9, 2, 1, 5, 3, 6, 8, 4 },
			{ 4, 6, 1, 8, 7, 2, 3, 5, 9 },
			{ 3, 5, 8, 9, 4, 6, 2, 1, 7 }
	};
	
	
	public SolverTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(SolverTest.class);
	}
	
	
	// TESTCASES
	
	
	/**
	 * Tests if a 4x4 Sudoku puzzle can be solved by the solver.
	 */
	public void testSolve4x4() {
		
		solver = new Solver(4);
		int[][] solution = solver.solve(puzzle4x4);
		
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				assertEquals(solution[row][column], solution4x4[row][column]);
			}
		}
	}
	
	
	/**
	 * Tests if a 9x9 Sudoku puzzle can be solved by the solver.
	 */
	public void testSolve9x9() {
		
		solver = new Solver(9);
		int[][] solution = solver.solve(puzzle9x9);
		
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				assertEquals(solution[row][column], solution9x9[row][column]);
			}
		}
	}
}
