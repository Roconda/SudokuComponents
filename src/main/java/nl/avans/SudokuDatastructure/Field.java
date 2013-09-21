package nl.avans.SudokuDatastructure;

// TODO: Auto-generated Javadoc
/**
 * The Class Field. This class is abstract and represents a field in the sudoku.
 * Fields differ. Generated fields can not be changed by the user, for example.
 */
abstract class Field {
	private int currentValue;
	private int solutionValue;

	public Field(int currentValue, int solutionValue) {
		this.currentValue = currentValue;
		this.solutionValue = solutionValue;
	}

	public int getCurrentValue() {
		return this.currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}
	
	public int getSolutionValue() {
		return this.solutionValue;
	}
	
	public void setSolutionValue(int solutionValue) {
		this.solutionValue = solutionValue;
	}
}
