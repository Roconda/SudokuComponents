package nl.avans.permutationgenerator;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;

public class PermutationGeneratorMethodsTests {
	int[][] sudoku;
	
	public PermutationGeneratorMethodsTests() {
		int[][] sudoku = {
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 3, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 4, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 5, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 6, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 7, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 8, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 9 },
		};
		
		this.sudoku = sudoku;
	}

	public void printSudoku() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(this.sudoku[i][j] + " ");
			}
			
			System.out.print("\n");
		}
		
		System.out.println();
	}
	
	@Test
	public void TransposePermutationTest() {
		sudoku = PermutationGeneratorMethods.transposePermutation(this.sudoku);
		
		int[][] changed = {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 2, 0 },
			{ 0, 0, 0, 0, 0, 0, 3, 0, 0 },
			{ 0, 0, 0, 0, 0, 4, 0, 0, 0 },
			{ 0, 0, 0, 0, 5, 0, 0, 0, 0 },
			{ 0, 0, 0, 6, 0, 0, 0, 0, 0 },
			{ 0, 0, 7, 0, 0, 0, 0, 0, 0 },
			{ 0, 8, 0, 0, 0, 0, 0, 0, 0 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0 },
		};

		assertArrayEquals(sudoku, changed);
	}
	
	@Test
	public void RowPermutationTest() {
		PermutationGeneratorMethods.rowPermutation(this.sudoku, 0, 1);
		
		int[][] changed = {
			{ 0, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 3, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 4, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 5, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 6, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 7, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 8, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 9 },
		};

		assertArrayEquals(sudoku, changed);
	}
	
	Random random = new Random();
	
	@Test
	public void BandPermutationTest() {
		PermutationGeneratorMethods.bandPermutation(this.sudoku, 0, 3);
		
		int[][] changed = {
				{ 0, 0, 0, 4, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 5, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 6, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 7, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 8, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			};

		assertArrayEquals(sudoku, changed);
	}
	
	@Test
	public void ColumnPermutationTest()
	{
		PermutationGeneratorMethods.columnPermutation(this.sudoku, 0, 8);
		
		int[][] changed = {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 0, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 3, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 4, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 5, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 6, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 7, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 8, 0 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		
		assertArrayEquals(sudoku, changed);
	}
	
	@Test
	public void StackPermutationTest() {
		PermutationGeneratorMethods.stackPermutation(this.sudoku, 0, 3);
		
		int[][] changed = {
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 3, 0, 0, 0 },
			{ 4, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 5, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 6, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 7, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 8, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 9 },
		};
		
		assertArrayEquals(sudoku, changed);
	}
	
	@Test
	public void SymbolPermutationTest() {
		PermutationGeneratorMethods.symbolPermutation(this.sudoku, 1, 9);
		
		int[][] changed = {
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 3, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 4, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 5, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 6, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 7, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 8, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1 },
		};
		
		assertArrayEquals(sudoku, changed);
	}
}