package nl.avans.datastructure;

import nl.avans.generator.Generator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the datastructure component.
 */
public class DatastructureTest 
    extends TestCase
{	
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
    
    public void testInitializeDS() {
    	Datastructure ds = new Datastructure(20, 1, new Generator());
    	assertEquals(ds.getDifficulty(), 1);
    	assertEquals(ds.getSize(), 20);
    }
    
    public void testGetNumberOfColumns() {
    	Datastructure ds1 = new Datastructure(20, 1, new Generator());
    	assertEquals(ds1.getNumberOfColumns(), 20);
    	
    	Datastructure ds2 = new Datastructure(999, 1, new Generator());
    	assertEquals(ds2.getNumberOfColumns(), 999);
    }
    
    public void testGetNumberOfRows() {
    	Datastructure ds1 = new Datastructure(2, 1, new Generator());
    	assertEquals(ds1.getNumberOfRows(), 2);
    	
    	Datastructure ds2 = new Datastructure(43, 1, new Generator());
    	assertEquals(ds2.getNumberOfRows(), 43);
    }
    
    public void setField() {
    	Datastructure ds1 = new Datastructure(43, 1, new Generator());
    	
    	ds1.setCurrentValue(23, 2, 4);
    	ds1.setCurrentValue(3, 4, 2);
    	
    	assertEquals(ds1.getCurrentValue(23, 2), 4);
    	assertEquals(ds1.getCurrentValue(3, 4), 2);
    }
}
