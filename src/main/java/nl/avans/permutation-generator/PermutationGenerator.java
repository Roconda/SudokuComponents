import java.util.Random;

/**
 * The responsibility of the class PermutationGenerator is to generates a sudoku of a particular
 * size and difficulty.
 * 
 * @dependency: IGenerator, SudokuReader
 */
public class PermutationGenerator { //EXTEND: implements IGenerator {
	private int[][] sudoku;
	private int size; // REFACTOR: EnumDifficulty
	private int numberOfColumns;
	private int numberOfRows;
	
	public PermutationGenerator() {}
	
	/**
	 * The method generate uses the class SudokuReader to get a random sudoku from an
	 * xml-file and uses the permutation methods inside this class to randomly transform 
	 * it into another sudoku while retaining the same size and difficulty.
	 * 
	 * @param size	The size of a sudoku.
	 * @param difficulty	The level of difficulty of a sudoku.
	 * @return 	a generated sudoku inside a multidimensional array of integers. 
	 */
	public int[][] generate(int size, int difficulty) {
		SudokuReader reader = new SudokuReader(size, difficulty);
		this.sudoku = reader.getSudoku();
		this.size = size;
		this.numberOfColumns = sudoku[0].length;
		this.numberOfRows = sudoku.length;
		
		Random random = new Random();
		
		for(int i = 0; i < 10; i++) {
			switch(random.nextInt(5)) {
				case 0:
					rowPermutation(random.nextInt(size), random.nextInt(size));
					break;
				case 1:
					columnPermutation(random.nextInt(size), random.nextInt(size));
					break;
				case 2:
					bandPermutation(
						(int)Math.sqrt(size) * random.nextInt((int)Math.sqrt(size)),
						(int)Math.sqrt(size) * random.nextInt((int)Math.sqrt(size))
					);
						
					break;
				case 3:
					stackPermutation(
						(int)Math.sqrt(size) * random.nextInt((int)Math.sqrt(size)),
						(int)Math.sqrt(size) * random.nextInt((int)Math.sqrt(size))
					);
					
					break;
				case 4:
					symbolPermutation(random.nextInt(size) + 1, random.nextInt(size) + 1);
					break;
				case 5:
					this.sudoku = transposePermutation();
					break;
			}
		}
		
		return sudoku;
	}
	
	/**
	 * The method transposePermutation transposes the sudoku by swapping the rows and 
	 * columns with each other.
	 * 
	 * @return	an transposed array.
	 */
	private int[][] transposePermutation() {
		int[][] temp = new int[numberOfRows][numberOfColumns];
		
		for (int i = 0; i < numberOfRows; ++i) {
			for (int j = 0; j < numberOfColumns; ++j) {
				temp[i][j] = sudoku[size - j - 1][i];
			}
		}
		
		return temp;
	}

	/**
	 * The method rowPermutation swaps one row for another row in the sudoku.
	 * 
	 * @param firstRow 	The first row to be swapped.
	 * @param secondRow	The second row to be swapped.
	 */
	private void rowPermutation(int firstRow, int secondRow) {
		int[] temp = sudoku[firstRow];
		sudoku[firstRow] = sudoku[secondRow];
		sudoku[secondRow] = temp;
	}
	
	/**
	 * The method bandPermutation swaps one band with another band. A band is a row
	 * of blocks in the sudoku from left to right. For example: if the sudoku has a size
	 * of 9, then it has 3 blocks from left to right, and you can swap the first band
	 * with the third band with "bandPermutation(0, 6)".
	 * 
	 * @param firtBand The first band to be swapped.
	 * @param secondBand The second band to be swapped.
	 */
	private void bandPermutation(int firstBand, int secondBand) {
		if (numberOfRows % numberOfColumns != 0) {
			throw new IllegalArgumentException("The columns and rows of the sudoku cannot be evenly divided into subregions.");
		}
		
		for (int j = 0; j < Math.sqrt(size); j++) {
			rowPermutation(firstBand + j, secondBand + j);
		}
	}

	/**
	 * The method columnPermutation swaps one column for another column in the sudoku.
	 * 
	 * @param firstColumn	The first column to be swapped.
	 * @param secondColumn	The second column to be swapped.
	 */
	private void columnPermutation(int firstColumn, int secondColumn) {
		for (int i = 0; i < numberOfColumns; i++) {
			if (sudoku[i][firstColumn] != 0 || sudoku[i][secondColumn] != 0) {			
				int temp = sudoku[i][firstColumn];
				sudoku[i][firstColumn] = sudoku[i][secondColumn];
				sudoku[i][secondColumn] = temp;
			}
		}
	}
	
	/**
	 * The method stackPermutator swaps one stack with another stack. A stack is a row
	 * of blocks in the sudoku from up to down. For example: if the sudoku has a size
	 * of 9, then it has 3 blocks from up to down, and you can swap the first stack
	 * with the third stack with "stackPermutation(0, 6)".
	 * 
	 * @param firstStack 	The first stack to be swapped.
	 * @param secondStack 	The second stack to be swapped.
	 */
	private void stackPermutation(int firstStack, int secondStack) {
		if (numberOfRows % numberOfColumns != 0) {
			throw new IllegalArgumentException("The columns and rows of the sudoku cannot be evenly divided into subregions.");
		}
		
		for (int j = 0; j < Math.sqrt(size); j++) {
			columnPermutation(firstStack + j, secondStack + j);
		}
	}

	/**
	 * The method symbolPermutation swaps all numbers of two symbols in the sudoku.
	 * For example: '1' becomes '9' and '9' becomes '1'.
	 * 
	 * @param firstSymbol	The value of the first symbol
	 * @param secondSymbol	The value of the second symbol
	 */
	private void symbolPermutation(int firstSymbol, int secondSymbol) {
		// BUG: What if a parameter is not in the range of permitted numbers?
		
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				if (sudoku[i][j] == firstSymbol) {
					sudoku[i][j] = secondSymbol;
				} else if (sudoku[i][j] == secondSymbol) {
					sudoku[i][j] = firstSymbol;
				}
			}
		}
	}
}