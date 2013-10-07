package nl.avans.datastructure;


/**
 * The Class Field. This class is abstract and represents a field in the sudoku.
 * Fields differ. Generated fields can not be changed by the user, for example.
 */
abstract class Field {
	
	/** The current value. */
	private int currentValue;
	
	/** The solution value. */
	private int solutionValue;

	/**
	 * Instantiates a new field.
	 *
	 * @param currentValue the current value
	 * @param solutionValue the solution value
	 */
	public Field(int currentValue, int solutionValue) {
		this.currentValue = currentValue;
		this.solutionValue = solutionValue;
	}

	/**
	 * Gets the current value.
	 *
	 * @return the current value
	 */
	public int getCurrentValue() {
		return this.currentValue;
	}

	/**
	 * Sets the current value.
	 *
	 * @param currentValue the new current value
	 */
	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}
	
	/**
	 * Gets the solution value.
	 *
	 * @return the solution value
	 */
	public int getSolutionValue() {
		return this.solutionValue;
	}
	
	/**
	 * Sets the solution value.
	 *
	 * @param solutionValue the new solution value
	 */
	public void setSolutionValue(int solutionValue) {
		this.solutionValue = solutionValue;
	}
}