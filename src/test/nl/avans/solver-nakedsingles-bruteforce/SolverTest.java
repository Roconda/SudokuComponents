package nl.avans.solver-nakedsingles-bruteforce;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the datastructure component.
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
		{ 7, 0, 0, 3, 0, 0, 9, 0, 6 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 4 },
		{ 9, 0, 3, 0, 0, 0, 0, 0, 5 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 8 },
		{ 0, 0, 2, 9, 0, 0, 0, 0, 1 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 9 },
		{ 8, 0, 9, 0, 0, 0, 0, 0, 2 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 7 },
		{ 2, 0, 0, 6, 0, 0, 1, 0, 3 }
	};
	private static int[][] puzzle4x4 = {
		{ 6, 0, 1, 0, 13, 10, 5, 3, 14, 12, 2, 4, 0, 15, 0, 9 },
		{ 15, 10, 13, 9, 0, 14, 0, 4, 16, 0, 3, 0, 6, 12, 8, 5 },
		{ 12, 14, 0, 0, 0, 6, 0, 9, 10, 0, 1, 0, 0, 0, 16, 3 },
		{ 2, 0, 0, 5, 12, 0, 0, 0, 0, 0, 0, 13, 1, 0, 0, 14 },
		{ 0, 0, 0, 0, 0, 0, 0, 15, 1, 0, 0, 0, 0, 0, 0, 0},
		{ 3, 0, 0, 14, 11, 0, 7, 0, 0, 2, 0, 15, 16, 0, 0, 10 },
		{ 4, 0, 7, 0, 9, 0, 0, 0, 0, 0, 0, 5, 0, 1, 0, 2 },
		{ 0, 5, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 15, 0 },
		{ 0, 0, 15, 0, 16, 13, 9, 10, 4, 7, 8, 12, 0, 11, 0, 0 },
		{ 14, 0, 0, 0, 0, 2, 0, 5, 11, 0, 15, 0, 0, 0, 0, 16 },
		{ 0, 13, 16, 0, 0, 7, 0, 6, 9, 0, 5, 0, 0, 4, 10, 0 },
		{ 0, 11, 0, 7, 14, 0, 4, 0, 0, 16, 0, 3, 5, 0, 13, 0 },
		{ 11, 0, 5, 13, 10, 9, 0, 0, 0, 0, 7, 1, 15, 16, 0, 12 },
		{ 7, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 6 },
		{ 16, 8, 0, 15, 2, 0, 0, 0, 0, 0, 0, 9, 4, 0, 5, 7 },
		{ 9, 1, 6, 10, 15, 5, 3, 7, 12, 4, 16, 2, 8, 13, 14, 11 }
	};
	private static int[][] result;
	
	
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
     */
    public DsArrayTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Solver.class );
    }
    
    
    public void testSolve2x2() {
    	
    	System.out.println("*** 2x2 Puzzle ***");
    	System.out.println(gridToString(puzzleGrid2x2));
    	
		Solver solver = new Solver(4);
		result = solver.solve(puzzle2x2);
		
		System.out.println("*** 2x2 Solution ***");
		System.out.println(gridToString(result));
    }
    
    public void testSolve3x3() {
    	
    	System.out.println("*** 3x3 Puzzle ***");
    	System.out.println(gridToString(puzzleGrid3x3));
    	
		Solver solver = new Solver(9);
		result = solver.solve(puzzle3x3);
		
		System.out.println("*** 3x3 Solution ***");
		System.out.println(gridToString(result));
    }
    
    public void testSolve4x4() {
    	
    	System.out.println("*** 4x4 Puzzle ***");
    	System.out.println(gridToString(puzzleGrid4x4));
    	
		Solver solver = new Solver(16);
		result = solver.solve(puzzle4x4);
		
		System.out.println("*** 4x4 Solution ***");
		System.out.println(gridToString(result));
    }
    
    
    private static String gridToString(int[][] grid) {
		
		String gridString = "";
		
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				
				if ((y + 1) < grid[x].length)
					gridString += (grid[x][y] + ", ");
				else
					gridString += (grid[x][y] + "\n");
			}
		}
		
		return gridString;
	}
}
