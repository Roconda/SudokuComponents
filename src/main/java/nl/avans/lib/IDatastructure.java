package nl.avans.lib;

/**
 * Implementation of the Datastructure component.
 * 
 * @author Tim Slot
 */
public interface IDatastructure {
	
	/**
	 * Gets the current value of a specific field.
	 * 
	 * @param x				The x-coordinate of the field in the matrix.
	 * @param y				The y-coordinate of the field in the matrix.
	 * @return				The current value of the desired field.
	 */
	int getCurrentValue(int x, int y);

	/**
	 * Changes the current setting of the datastructure.
	 * 
	 * @param content
	 *            Puzzle's content.
	 * @return The current datastructure.
	 */
	IDatastructure setSettings(int content[][]);


	/**
	 * Sets the current value of a specific field.
	 * 
	 * @param x				The x-coordinate of the field in the matrix.
	 * @param y				The y-coordinate of the field in the matrix.
	 * @param currentValue	The desired value to set the field's current value to.
	 */
	boolean setCurrentValue(int x, int y, int currentValue);
	
	/**
	 * Gets the solution value of a specific field.
	 * 
	 * @param x				The x-coordinate of the field in the matrix.
	 * @param y				The y-coordinate of the field in the matrix.
	 * @return				The solution value of the desired field.
	 */
	int getSolutionValue(int x, int y);


	/**
	 * Sets the solution value for specific field.
	 *
 	 * @param x				The x-coordinate of the field in the matrix.
	 * @param y				The y-coordinate of the field in the matrix.
	 * @param value			The value to set as solution
	 */
	void setSolutionValue(int x, int y, int value);
	
	/**
	 * Gets all values in a specific sub-region within the puzzle's matrix.
	 * 
	 * @param subregion		The index of the sub-region.
	 * @return				A matrix based on the set-sizes of the puzzle's matrix, containing all values in its sub-region.
	 */
	int[][] getSubRegion(int subregion);
	
	/**
	 * Gets all values in a specific column within the puzzle's matrix.
	 * 
	 * @param column		The index of the column.
	 * @return				An array containing all values in the desired column of the puzzle's matrix.
	 */
	int[] getColumn(int column);
	
	/**
	 * Gets all values in a specific row within the puzzle's matrix.
	 * 
	 * @param row			The index of the row.
	 * @return				An array containing all values in the desired row of the puzzle's matrix.
	 */
	int[] getRow(int row);
	
	/**
	 * Gets the count of columns in the puzzle's matrix.
	 * 
	 * @return				The count of columns in the puzzle's matrix.
	 */
	int getNumberOfColumns();
	
	/**
	 * Gets the count of rows in the puzzle's matrix.
	 * 
	 * @return				The count of rows in the puzzle's matrix.
	 */
	int getNumberOfRows();
	
	/**
	 * Gets the size of the puzzle.
	 * 
	 * @return				The size of the puzzle.
	 */
	int getSize();
	
	/**
	 * Checks if the field is of the type GeneratedField
	 * 
	 * @param x			The x-coordinate of the field in the matrix.
	 * @param y			The y-coordinate of the field in the matrix.
	 * @return			True or false due the field is a GeneratedField or UserField.
	 */
	boolean isGeneratedField(int x, int y);
	
	/**
	 * Changes the current value of a random field to its solution value.
	 */
	void getHint();
}