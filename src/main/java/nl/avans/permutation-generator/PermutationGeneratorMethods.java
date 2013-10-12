
/**
 * The class PermutationGeneratorMethods is a way to test the method separately from
 * the class PermutationGenerator. 
 */
public class PermutationGeneratorMethods {
	public static int[][] transposePermutation(int[][] sudoku) {
		int[][] temp = new int[sudoku.length][sudoku[0].length];
		
		for (int i = 0; i < sudoku.length; ++i) {
			for (int j = 0; j < sudoku.length; ++j) {
				temp[i][j] = sudoku[sudoku.length - j - 1][i];
			}
		}
		
		return temp;
	}
	
	public static void rowPermutation(int[][] suduku, int firstRow, int secondRow) {
		int[] temp = suduku[firstRow];
		suduku[firstRow] = suduku[secondRow];
		suduku[secondRow] = temp;
	}
	
	public static void bandPermutation(int[][] suduku, int firstStack, int secondStack) {
		if (suduku.length % suduku.length != 0) {
			throw new IllegalArgumentException("The columns and rows of the sudoku cannot be evenly divided into subregions.");
		}
		
		for (int j = 0; j < Math.sqrt(suduku.length); j++) {
			rowPermutation(suduku, firstStack + j, secondStack + j);
		}
	}

	public static void columnPermutation(int[][] suduku, int firstColumn, int secondColumn) {
		for (int i = 0; i < suduku.length; i++) {
			if (suduku[i][firstColumn] != 0 || suduku[i][secondColumn] != 0) {			
				int temp = suduku[i][firstColumn];
				suduku[i][firstColumn] = suduku[i][secondColumn];
				suduku[i][secondColumn] = temp;
			}
		}
	}
	
	public static void stackPermutation(int[][] suduku, int firstStack, int secondStack) {
		if (suduku.length % suduku.length != 0) {
			throw new IllegalArgumentException("The columns and rows of the sudoku cannot be evenly divided into subregions.");
		}
		
		for (int j = 0; j < Math.sqrt(suduku.length); j++) {
			columnPermutation(suduku, firstStack + j, secondStack + j);
		}
	}

	public static void symbolPermutation(int[][] suduku, int firstSymbol, int secondSymbol) {
		for (int i = 0; i < suduku.length; i++) {
			for (int j = 0; j < suduku.length; j++) {
				if (suduku[i][j] == firstSymbol) {
					suduku[i][j] = secondSymbol;
				} else if (suduku[i][j] == secondSymbol) {
					suduku[i][j] = firstSymbol;
				}
			}
		}
	}
}