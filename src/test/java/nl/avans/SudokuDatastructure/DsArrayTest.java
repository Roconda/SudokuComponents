package nl.avans.SudokuDatastructure;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the datastructure component.
 */
public class DsArrayTest 
    extends TestCase
{
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
        return new TestSuite( DsArrayTest.class );
    }

    public void testInsert()
    {
    	DsArray ds = new DsArray(8);
        assertEquals(ds.getValue(3, 4), -1);
    }
    
    public void testInsert2() {
    	DsArray ds = new DsArray(8);
        assertTrue(ds.setValue(3, 4, 5));
        assertEquals(ds.getValue(3, 4), 5);
    }
    
    public void testSize() {
    	DsArray ds = new DsArray(8);
    	assertEquals(ds.getSize(), 8);
    }
}
