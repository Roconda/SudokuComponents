package nl.avans.game;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit Test for testing the Game.java class.
 * 
 * @author Dustin Sarioglu
 *
 */
public class GameTest extends TestCase {
	
	/** The game. */
	private Game game;
	
	/**
	 * Create the test case.
	 *
	 * @param testName name of the test case
	 */
    public GameTest( String testName )
    {
        super( testName );
        
        game = new Game(9, 0);
    }

    /**
     * Suite.
     *
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GameTest.class );
    }
    
    
    //TESTCASES
    
    /**
     * Test get current value.
     */
    public void testGetCurrentValue() {
    	
    	assertNotSame(game.getCurrentValue(1, 4), -1);
    	assertNotSame(game.getCurrentValue(5, 3), -1);
    	assertNotSame(game.getCurrentValue(8, 2), -1);
    	assertNotSame(game.getCurrentValue(7, 6), -1);
    }
    
    /**
     * Test get value out of bounds.
     */
    public void testGetValueOutOfBounds() {
    	
    	try {
    		game.getCurrentValue(9, 1);
    		fail("testGetValueOutOfBounds should throw an IllegalArgumentException!");
    	} catch (ArrayIndexOutOfBoundsException e) {
    		assertTrue(true);
    	}
    }
    
    /**
     * Test set current value.
     */
    public void testSetCurrentValue() {
    	
    	game.setCurrentValue(4, 8, 1);
    	game.setCurrentValue(2, 4, 3);
    	game.setCurrentValue(6, 3, 9);
    	
    	assertEquals(game.getCurrentValue(4, 8), 1);
    	assertEquals(game.getCurrentValue(2, 4), 3);
    	assertEquals(game.getCurrentValue(6, 3), 9);
    }
    
    /**
     * Test get subregion.
     */
    public void testGetSubregion() {
    	
    	assertEquals(game.getSubRegion(2).length, 3);
    	assertEquals(game.getSubRegion(2)[0].length, 3);
    	assertEquals(game.getSubRegion(1).length, 3);
    	assertEquals(game.getSubRegion(1)[0].length, 3);
    }
    
    /**
     * Test get illegal subregion.
     */
    public void testGetIllegalSubregion() {
    	assertEquals(game.getSubRegion(9)[0][0], -1);
    }
    
    /**
     * Test get row.
     */
    public void testGetRow() {
    	
    	assertEquals(game.getRow(0).length, 9);
    	assertEquals(game.getRow(2).length, 9);
    	assertEquals(game.getRow(5).length, 9);
    }
    
    /**
     * Test get illegal row.
     */
    public void testGetIllegalRow() {
    	
    	try {
    		game.getRow(9);
    		fail("testGetIllegalRow should throw an IllegalArgumentException!");
    	} catch (IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    /**
     * Test get column.
     */
    public void testGetColumn() {
    	
    	assertEquals(game.getColumn(1).length, 9);
    	assertEquals(game.getColumn(4).length, 9);
    	assertEquals(game.getColumn(6).length, 9);
    }
    
    /**
     * Test get illegal column.
     */
    public void testGetIllegalColumn() {
    	
    	try {
    		game.getColumn(9);
    		fail("testGetIllegalColumn should throw an IllegalArgumentException!");
    	} catch (IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    /**
     * Test get number of columns.
     */
    public void testGetNumberOfColumns() {
    	
    	assertEquals(game.getNumberOfColumns(), 9);
    }
    
    /**
     * Test get number of rows.
     */
    public void testGetNumberOfRows() {
    	
    	assertEquals(game.getNumberOfRows(), 9);
    }
    
    /**
     * Test get size.
     */
    public void testGetSize() {
    	
    	assertEquals(game.getSize(), 9);
    }
    
    /**
     * Test isWon.
     */
    public void testisWon() {
    	
    	assertFalse(game.isWon());
    	game.Solve();
    	assertTrue(game.isWon());
    }
    
    /**
     * Test isAllowed
     */
    public void testisAllowed() {
    	
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
        Game testGame = new Game(board);
        assertTrue(testGame.isAllowed(1, 1, 5));
        
        assertFalse(testGame.isAllowed(1, 1, 1));
        assertFalse(testGame.isAllowed(1, 1, 2));
        assertFalse(testGame.isAllowed(1, 1, 3));
        assertFalse(testGame.isAllowed(1, 1, 4));
        assertFalse(testGame.isAllowed(1, 1, 6));
        assertFalse(testGame.isAllowed(1, 1, 7));
        assertFalse(testGame.isAllowed(1, 1, 8));
        assertFalse(testGame.isAllowed(1, 1, 9));
    }
}
