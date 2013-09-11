package nl.avans.SudokuDatastructure;

// TODO: Auto-generated Javadoc
/**
 * The Class Field. This class is abstract and represents a field in the sudoku.
 * Fields differ. Generated fields can not be changed by the user, for example.
 */
abstract class Field {

	/** The current value. */
	private int currentValue = -1;

	/** The solution value. */
	private int solutionValue = -1;

	/**
	 * Instantiates a new field.
	 * 
	 * @param currentValue
	 *            the current value
	 * @param solutionValue
	 *            the solution value
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
		return currentValue;
	}

	/**
	 * Sets the current value.
	 * 
	 * @param newValue
	 *            the new value
	 * @return true, if successful
	 */
	public boolean setCurrentValue(int newValue) {
		if (newValue >= 0 || newValue < 10) { // FIXME use the given board size
												// (and throw exception?)
			currentValue = newValue;
			return true;
		} else
			return false;
	}
}
