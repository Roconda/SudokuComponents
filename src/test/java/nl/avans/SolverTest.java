package nl.avans;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nl.avans.lib.ISolver;
import nl.avans.solver.Solver;

/**
 * Unit test for simple App.
 */
public class SolverTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public SolverTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(SolverTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testSolve() {
		int[][] board = new int[9][9];
		board[0] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
		board[1] = new int[] { 4, 0, 6, 0, 0, 0, 0, 0, 0 };
		board[2] = new int[] { 7, 8, 9, 0, 0, 0, 0, 0, 0 };
		board[3] = new int[] { 2, 0, 0, 0, 0, 0, 8, 0, 0 };
		board[4] = new int[] { 5, 0, 0, 0, 0, 0, 0, 0, 0 };
		board[5] = new int[] { 3, 0, 0, 0, 0, 0, 0, 0, 0 };
		board[6] = new int[] { 8, 0, 0, 0, 0, 0, 0, 0, 0 };
		board[7] = new int[] { 0, 0, 0, 0, 8, 0, 0, 0, 0 };
		board[8] = new int[] { 9, 0, 0, 0, 0, 0, 0, 0, 0 };

		ISolver solver = new Solver();
		int[][] result = solver.solve(board);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.print("\n");
		}
		assertTrue(true);
	}
}
