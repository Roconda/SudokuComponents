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
	
	private Game game;
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GameTest( String testName )
    {
        super( testName );
        
        game = new Game(9, 0);
        game.generate();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GameTest.class );
    }
    
    
    //TESTCASES
    
    public void testGetCurrentValue() {
    	
    	assertNotSame(game.getCurrentValue(1, 4), -1);
    	assertNotSame(game.getCurrentValue(5, 3), -1);
    	assertNotSame(game.getCurrentValue(8, 2), -1);
    	assertNotSame(game.getCurrentValue(7, 6), -1);
    }
    
    public void testGetValueOutOfBounds() {
    	
    	try {
    		game.getCurrentValue(9, 1);
    		fail("testGetValueOutOfBounds should throw an IllegalArgumentException!");
    	} catch (ArrayIndexOutOfBoundsException e) {
    		assertTrue(true);
    	}
    }
    
    public void testSetCurrentValue() {
    	
    	game.setCurrentValue(4, 8, 1);
    	game.setCurrentValue(2, 4, 3);
    	game.setCurrentValue(6, 3, 9);
    	
    	assertEquals(game.getCurrentValue(4, 8), 1);
    	assertEquals(game.getCurrentValue(2, 4), 3);
    	assertEquals(game.getCurrentValue(6, 3), 9);
    }
    
    public void testGetSubregion() {
    	
    	assertEquals(game.getSubRegion(2).length, 3);
    	assertEquals(game.getSubRegion(2)[0].length, 3);
    	assertEquals(game.getSubRegion(1).length, 3);
    	assertEquals(game.getSubRegion(1)[0].length, 3);
    }
    
    public void testGetIllegalSubregion() {
    	
    	try {
    		game.getSubRegion(9);
    		fail("testGetIllegalSubregion should throw an IllegalArgumentException!");
    	} catch (IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    public void testGetRow() {
    	
    	assertEquals(game.getRow(0).length, 9);
    	assertEquals(game.getRow(2).length, 9);
    	assertEquals(game.getRow(5).length, 9);
    }
    
    public void testGetIllegalRow() {
    	
    	try {
    		game.getRow(9);
    		fail("testGetIllegalRow should throw an IllegalArgumentException!");
    	} catch (IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    public void testGetColumn() {
    	
    	assertEquals(game.getColumn(1).length, 9);
    	assertEquals(game.getColumn(4).length, 9);
    	assertEquals(game.getColumn(6).length, 9);
    }
    
    public void testGetIllegalColumn() {
    	
    	try {
    		game.getColumn(9);
    		fail("testGetIllegalColumn should throw an IllegalArgumentException!");
    	} catch (IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    public void testGetNumberOfColumns() {
    	
    	assertEquals(game.getNumberOfColumns(), 9);
    }
    
    public void testGetNumberOfRows() {
    	
    	assertEquals(game.getNumberOfRows(), 9);
    }
    
    public void testGetSize() {
    	
    	assertEquals(game.getSize(), 9);
    }
}
