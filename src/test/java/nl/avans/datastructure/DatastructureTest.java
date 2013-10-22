package nl.avans.datastructure;

import nl.avans.lib.IDatastructure;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

/**
 * Unit test for the datastructure component.
 */
public class DatastructureTest 
    extends TestCase
{	
	int[][] board1 = {
			{2,6,2,8},
			{2,7,9,6},
			{2,3,5,3},
			{3,5,7,5}
	};

	int[][] board2 = {
			{2,0,2,8},
			{2,7,9,6},
			{2,3,5,3},
			{3,5,7,0}
	};
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DatastructureTest( String testName )
    {
        super( testName );
        
        //FIXME: Unable to create instance of datastructure. 
        //FIXME: Required implementation of Generator and Solver classes to instantiate.
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DatastructureTest.class );
    }
    
    public void testSize() {
    	IDatastructure ds = new Datastructure(board1);
    	assertEquals(ds.getSize(), 4);
    }
    
    public void testGetRow() {
    	IDatastructure ds = new Datastructure(board1);
    	Assert.assertArrayEquals(ds.getRow(0), new int[] { 2, 6, 2, 8});
    	Assert.assertArrayEquals(ds.getRow(9999), new int[] {});
    }
    
    public void testGetColumn() {
    	IDatastructure ds = new Datastructure(board1);
    	Assert.assertArrayEquals(ds.getColumn(0), new int[] { 2, 2, 2, 3});
    	Assert.assertArrayEquals(ds.getColumn(9999), new int[] {});
    }
    
    public void TestGetSubRegion() {
    	IDatastructure ds = new Datastructure(board1);
    	Assert.assertArrayEquals(ds.getSubRegion(0), new int[][] {{ 2, 6 }, { 2, 7 }});
    	Assert.assertArrayEquals(ds.getSubRegion(9999), new int[][] {});
    }

    public void testGetNumberOfColumns() {
    	IDatastructure ds1 = new Datastructure(board1).setSettings(board1);
    	assertEquals(ds1.getNumberOfColumns(), 4);
    }
    
    public void testGetNumberOfRows() {
    	IDatastructure ds1 = new Datastructure(board1);
    	assertEquals(ds1.getNumberOfRows(), 4);
    }
    
    public void testGetField() {
    	IDatastructure ds1 = new Datastructure(board1);
    	
    	assertEquals(ds1.getCurrentValue(0, 1), 6);
    	assertEquals(ds1.getCurrentValue(3, 3), 5);
    }
    
    public void testSetField() {
    	IDatastructure ds1 = new Datastructure(board2);
    	
    	assertEquals(ds1.setCurrentValue(0, 1, 4), true);
    	assertEquals(ds1.setCurrentValue(3, 3, 2), true);
    	assertEquals(ds1.setCurrentValue(0, 0, 1), false);
    	
    	assertEquals(ds1.getCurrentValue(0, 1), 4);
    	assertEquals(ds1.getCurrentValue(3, 3), 2);
    	assertEquals(ds1.getCurrentValue(0, 0), 2);
    }
    
    public void testIsGeneratedField() {
    	IDatastructure ds2 = new Datastructure(board2);
    	assertTrue(ds2.isGeneratedField(0, 0));
    	assertFalse(ds2.isGeneratedField(0, 1));
    }
}
