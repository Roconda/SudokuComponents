package nl.avans.lib;

public interface IDatastructure {
	int getCurrentValue(int x, int y);
	void setCurrentValue(int x, int y, int currentValue);
	int getSolutionValue(int x, int y);
	int[][] getSubRegion(int subregion);
	int[] getColumn(int column);
	int[] getRow(int row);
	int getNumberOfColumns();
	int getNumberOfRows();
	int getSize();
}
