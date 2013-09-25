package nl.avans.datastructure;

import nl.avans.datastructure.Datastructure;
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
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DatastructureTest.class );
    }

    public void testInsert()
    {
    	//Datastructure ds = new Datastructure(8);
        //assertEquals(ds.getValue(3, 4), -1);
    }
    
    public void testInsert2() {
    	//Datastructure ds = new Datastructure(8);
        //assertTrue(ds.setValue(3, 4, 5));
        //assertEquals(ds.getValue(3, 4), 5);
    }
    
    public void testSize() {
    	//Datastructure ds = new Datastructure(8);
    	//sassertEquals(ds.getSize(), 8);
    }
}
