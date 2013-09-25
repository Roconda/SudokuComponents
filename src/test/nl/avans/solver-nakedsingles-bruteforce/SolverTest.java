package nl.avans.solver-nakedsingles-bruteforce;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the datastructure component.
 * 
 * @author		Dustin Sarioglu
 */
public class DsArrayTest 
	extends TestCase
{
	private static int[][] puzzle2x2 = {
		{ 2, 0, 0, 4 },
		{ 0, 1, 0, 3 },
		{ 0, 2, 0, 1 },
		{ 1, 0, 0, 2 }
	};
	private static int[][] puzzle3x3 = {
		{ 0, 4, 3, 9, 2, 0, 0, 0, 1 },
		{ 0, 0, 2, 3, 4, 7, 5, 0, 9 },
		{ 0, 6, 7, 0, 5, 0, 2, 3, 4 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 7 },
		{ 2, 8, 4, 0, 0, 9, 0, 0, 6 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
		{ 0, 2, 5, 0, 3, 0, 9, 7, 8 },
		{ 0, 0, 8, 7, 9, 5, 6, 0, 2 },
		{ 0, 7, 9, 6, 8, 0, 0, 0, 3 }
	};
	private static int[][] puzzle4x4 = {
		{ 0, 0, 0, 0, 0, 11, 3, 0, 7, 0, 0, 12, 15, 0, 14, 2 },
		{ 0, 2, 0, 0, 0, 0, 0, 0, 10, 0, 15, 0, 9, 0, 0, 13 },
		{ 16, 0, 9, 11, 0, 0, 14, 15, 0, 3, 4, 13, 5, 6, 7, 10 },
		{ 10, 0, 14, 15, 0, 0, 0, 16, 0, 0, 0, 0, 1, 3, 4, 8 },
		{ 0, 0, 0, 0, 0, 5, 6, 9, 0, 0, 11, 14, 0, 13, 15, 16 },
		{ 14, 0, 0, 6, 3, 1, 0, 0, 12, 0, 0, 0, 0, 9, 10, 11 },
		{ 0, 10, 11, 12, 0, 15, 0, 0, 0, 1, 2, 9, 0, 7, 0, 14 },
		{ 9, 0, 0, 0, 10, 14, 0, 12, 0, 6, 0, 0, 0, 0, 8, 3 },
		{ 2, 0, 0, 0, 5, 6, 0, 11, 0, 12, 0, 0, 0, 0, 16, 15 },
		{ 0, 6, 5, 9, 0, 2, 0, 0, 0, 16, 13, 3, 0, 10, 0, 4 },
		{ 12, 0, 0, 13, 9, 16, 0, 0, 5, 0, 0, 0, 0, 2, 1, 6 },
		{ 0, 0, 0, 0, 0, 8, 10, 4, 0, 0, 7, 1, 0, 5, 9, 12 },
		{ 1, 0, 2, 3, 0, 0, 0, 6, 0, 0, 0, 0, 16, 12, 13, 5 },
		{ 5, 0, 7, 4, 0, 0, 8, 2, 0, 9, 12, 6, 10, 15, 3, 1 },
		{ 0, 14, 0, 0, 0, 0, 0, 0, 13, 0, 1, 0, 11, 0, 0, 7 },
		{ 0, 0, 0, 0, 0, 10, 12, 0, 11, 0, 0, 5, 14, 0, 2, 9 }
	};
	
	private static int[][] puzzle2x2Solution = {
		{ 2, 3, 1, 4 },
		{ 4, 1, 2, 3 },
		{ 3, 2, 4, 1 },
		{ 1, 4, 3, 2 }
	};
	private static int[][] puzzle3x3Solution = {
		{ 5, 4, 3, 9, 2, 6, 7, 8, 1 },
		{ 8, 1, 2, 3, 4, 7, 5, 6, 9 },
		{ 9, 6, 7, 1, 5, 8, 2, 3, 4 },
		{ 3, 5, 1, 2, 6, 4, 8, 9, 7 },
		{ 2, 8, 4, 5, 7, 9, 3, 1, 6 },
		{ 7, 9, 6, 8, 1, 3, 4, 2, 5 },
		{ 6, 2, 5, 4, 3, 1, 9, 7, 8 },
		{ 1, 3, 8, 7, 9, 5, 6, 4, 2 },
		{ 4, 7, 9, 6, 8, 2, 1, 5, 3 }
	};
	private static int[][] puzzle4x4Solution = {
		{ 13, 1, 4, 5, 6, 11, 3, 10, 7, 8, 9, 12, 15, 16, 14, 2 },
		{ 3, 2, 6, 7, 1, 4, 5, 8, 10, 14, 15, 16, 9, 11, 12, 13 },
		{ 16, 8, 9, 11, 2, 12, 14, 15, 1, 3, 4, 13, 5, 6, 7, 10 },
		{ 10, 12, 14, 15, 7, 9, 13, 16, 2, 5, 6, 11, 1, 3, 4, 8 },
		{ 7, 3, 1, 2, 4, 5, 6, 9, 8, 10, 11, 14, 12, 13, 15, 16 },
		{ 14, 5, 8, 6, 3, 1, 2, 7, 12, 13, 16, 15, 4, 9, 10, 11 },
		{ 4, 10, 11, 12, 8, 15, 16, 13, 3, 1, 2, 9, 6, 7, 5, 14 },
		{ 9, 13, 15, 16, 10, 14, 11, 12, 4, 6, 5, 7, 2, 1, 8, 3 },
		{ 2, 4, 3, 1, 5, 6, 7, 11, 9, 12, 8, 10, 13, 14, 16, 15 },
		{ 8, 6, 5, 9, 12, 2, 1, 14, 15, 16, 13, 3, 7, 10, 11, 4 },
		{ 12, 7, 10, 13, 9, 16, 15, 3, 5, 11, 14, 4, 8, 2, 1, 6 },
		{ 11, 15, 16, 14, 13, 8, 10, 4, 6, 2, 7, 1, 3, 5, 9, 12 },
		{ 1, 9, 2, 3, 11, 7, 4, 6, 14, 15, 10, 8, 16, 12, 13, 5 },
		{ 5, 11, 7, 4, 14, 13, 8, 2, 16, 9, 12, 6, 10, 15, 3, 1 },
		{ 15, 14, 12, 10, 16, 3, 9, 5, 13, 4, 1, 2, 11, 8, 6, 7 },
		{ 6, 16, 13, 8, 15, 10, 12, 1, 11, 7, 3, 5, 14, 4, 2, 9 }
	};
	
	private static int[][] result;
	
	
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
     */
    public DsArrayTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( Solver.class );
    }
    
    
    /**
     * Tests the solver when solving a 2x2 Sudoku puzzle.
     */
    public void testSolve2x2() {
    	
		Solver solver = new Solver(4);
		result = solver.solve(puzzle2x2);
		
		assertEquals(result, puzzle2x2Solution);
    }
    
    /**
     * Tests the solver when solving a 3x3 Sudoku puzzle.
     */
    public void testSolve3x3() {
    	
		Solver solver = new Solver(9);
		result = solver.solve(puzzle3x3);
		
		assertEquals(result, puzzle3x3Solution);
    }
    
    /**
     * Tests the solver when solving a 4x4 Sudoku puzzle.
     */
    public void testSolve4x4() {
    	
		Solver solver = new Solver(16);
		result = solver.solve(puzzle4x4);
		
		assertEquals(result, puzzle4x4Solution);
    }
    
    /**
     * Tests the result grid being entirely filled.
     */
    public void testEntirelyFilled2x2() {
    	
    	Solver solver = new Solver(4);
    	result = solver.Solve(puzzle2x2);
    	
    	assertTrue(isEntirelyFilled(result));
    }
    
    /**
     * Tests the result grid being entirely filled.
     */
    public void testEntirelyFilled3x3() {
    	
    	Solver solver = new Solver(9);
    	result = solver.Solve(puzzle3x3);
    	
    	assertTrue(isEntirelyFilled(result));
    }
    
    /**
     * Tests the result grid being entirely filled.
     */
    public void testEntirelyFilled4x4() {
    	
    	Solver solver = new Solver(16);
    	result = solver.Solve(puzzle4x4);
    	
    	assertTrue(isEntirelyFilled(result));
    }
    
    /**
     * Tests the result grid having a correct size equal to the puzzle's desired size.
     */
    public void is2x2Length4() {
    	
    	Solver solver = new Solver(4);
    	result = solver.Solve(puzzle2x2);
    	
    	assertTrue(result.length == 4 && result[0].length == 4);
    }
    
    /**
     * Tests the result grid having a correct size equal to the puzzle's desired size.
     */
    public void is3x3Length9() {
    	
    	Solver solver = new Solver(9);
    	result = solver.Solve(puzzle3x3);
    	
    	assertTrue(result.length == 9 && result[0].length == 9);
    }
    
    /**
     * Tests the result grid having a correct size equal to the puzzle's desired size.
     */
    public void is4x4Length16() {
    	
    	Solver solver = new Solver(16);
    	result = solver.Solve(puzzle4x4);
    	
    	assertTrue(result.length == 16 && result[0].length == 16);
    }
    
    /**
     * Helps checking if each field in the puzzle is filled or not.
     * 
     * @param grid		The grid to check.
     * @return			True or false due the grid is entirely filled.
     */
    private boolean isEntirelyFilled(int[][] grid) {
    	
    	if (grid == null)
    		return false;
    	
    	for (int x = 0; x < grid.length; x++) {
    		
    		if (grid[x] == null)
    			return false;
    		
    		for (int y = 0; y < grid[x].length; y++) {
    			if (grid[x][y] == 0 ^ grid[x][y] == null)
    				return false;
    		}
    	}
    	
    	return true;
    }
}
