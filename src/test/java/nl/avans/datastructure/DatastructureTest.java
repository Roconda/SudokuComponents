package nl.avans.datastructure;

import nl.avans.generator.Generator;
import nl.avans.lib.IDatastructure;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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

    public void testGetNumberOfColumns() {
    	IDatastructure ds1 = new Datastructure(board1).setSettings(board1);
    	assertEquals(ds1.getNumberOfColumns(), 4);
    }
    
    public void testGetNumberOfRows() {
    	IDatastructure ds1 = new Datastructure(board1);
    	assertEquals(ds1.getNumberOfRows(), 4);
    }
    
    public void setField() {
    	IDatastructure ds1 = new Datastructure(board1);
    	
    	assertEquals(ds1.getCurrentValue(0, 1), 6);
    	assertEquals(ds1.getCurrentValue(3, 3), 5);
    	
    	ds1.setCurrentValue(0, 1, 7);
    	ds1.setCurrentValue(3, 3, 2);
    	
    	assertEquals(ds1.getCurrentValue(0, 1), 7);
    	assertEquals(ds1.getCurrentValue(3, 3), 2);
    }
}
