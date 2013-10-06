package nl.avans.generator;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import nl.avans.generator.Generator;


/**
 * Represents the Unit Test class for testing the generator.
 * 
 * @author Dustin Sarioglu
 *
 */
public class GeneratorTest extends TestCase {
	
	private Generator generator;
	
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
	public GeneratorTest(String testName) {
		super(testName);
		
		generator = new Generator();
	}
	
	/**
     * @return the suite of tests being tested
     */
	public static Test suite() {
		return new TestSuite(GeneratorTest.class);
	}
	
	
	//TESTCASES
	
	/**
	 * Tests if a generated 2x2 puzzle has the minimum required fields
	 * being filled already.
	 */
	public void test2x2ValidFactor() {
		
		int[][] grid;
		int validFactor = (4 * 2) - 1;
		
		grid = generator.generate(4, 0);
		
		if (getFilledFields(grid) >= validFactor) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}
	
	/**
	 * Tests if a generated 3x3 puzzle has the minimum required fields
	 * being filled already.
	 */
	public void test3x3ValidFactor() {
		
		int[][] grid;
		int validFactor = (9 * 2) - 1;
		
		grid = generator.generate(9, 0);
		
		if (getFilledFields(grid) >= validFactor) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}
	
	/**
	 * Tests if a generated 4x4 puzzle has the minimum required fields
	 * being filled already.
	 */
	public void test4x4ValidFactor() {
		
		int[][] grid;
		int validFactor = (16 * 2) - 1;
		
		grid = generator.generate(16, 0);
		
		if (getFilledFields(grid) >= validFactor) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}
	
	
	/**
	 * Tests if a generated 2x2 puzzle has the valid with and height sizes.
	 */
	public void test2x2ValidSize() {
		
		int[][] grid = generator.generate(4, 0);
		
		assertEquals(grid.length, 4);
		assertEquals(grid[0].length, 4);
	}
	
	/**
	 * Tests if a generated 3x3 puzzle has the valid with and height sizes.
	 */
	public void test3x3ValidSize() {
		
		int[][] grid = generator.generate(9, 0);
		
		assertEquals(grid.length, 9);
		assertEquals(grid[0].length, 9);
	}
	
	/**
	 * Tests if a generated 4x4 puzzle has the valid with and height sizes.
	 */
	public void test4x4ValidSize() {
		
		int[][] grid = generator.generate(16, 0);
		
		assertEquals(grid.length, 16);
		assertEquals(grid[0].length, 16);
	}
	
	
	//TEST HELPERS
	
	/**
	 * Helps at checking how many fields in a Sudoku puzzle are filled already.
	 * 
	 * @param grid	The grid to check in.
	 * @return		The count of fields that are filled already.
	 */
	private int getFilledFields(int[][] grid) {
		int fields = 0;
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				if (grid[row][column] != 0) {
					fields++;
				}
			}
		}
		
		return fields;
	}
}