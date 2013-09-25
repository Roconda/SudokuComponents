package nl.avans.generator;


import java.util.ArrayList;
import java.util.Random;


class GeneratorHelper {
	
	private int[][] puzzleGrid;
	
	private int puzzleSize;
	
	GeneratorHelper(int puzzleSize) {
		
		this.puzzleSize = puzzleSize;
	}
	
	
	int[][] generate() {
		
		int[][] grid = new int[puzzleSize][puzzleSize];
		
		int row = 0,
			column = 0;
		Integer value;
		
		ArrayList<Integer> valuesSetA = new ArrayList<Integer>();
		ArrayList<Integer> valuesSetB = new ArrayList<Integer>();
		
		for (int range : new Range(1, puzzleSize)) {
			valuesSetA.add(range);
		}
		
		Random random = new Random();
		int randomIndex = random.nextInt((puzzleSize - 1));
		
		value = valuesSetA.get(randomIndex);
		grid[row][column] = value;
		valuesSetA.remove(value);
		
		//Add random numbers in the upper row
		for (row = 1; row < puzzleSize; row++) {
			randomIndex = random.nextInt(valuesSetA.size());
			value = valuesSetA.get(randomIndex);
			
			valuesSetB.add(value);
			valuesSetA.remove(value);
			
			grid[row][column] = value;
		}
		
		row = 0;
		
		//Add random numbers in the first column's set
		for (column = 1; column < getSetSize(); column++) {
			randomIndex = random.nextInt(valuesSetB.size());
			value = valuesSetB.get(randomIndex);
			
			while (isValueInFirstSet(grid, value)) {
				randomIndex = random.nextInt(valuesSetB.size());
				value = valuesSetB.get(randomIndex);
			}
			
			valuesSetB.remove(value);
			grid[row][column] = value;
		}
		
		//Add random numbers to the remaining empty fields in the first column
		for (column = getSetSize(); column < puzzleSize; column++) {
			randomIndex = random.nextInt(valuesSetB.size());
			value = valuesSetB.get(randomIndex);
			valuesSetB.remove(value);
			
			grid[row][column] = value;
		}
		
		finish(grid.clone());
		
		return puzzleGrid;
	}
	
	
	
	private boolean finish(int[][] grid) {
		
		int[][] helpGrid = grid.clone();
		
		int row = 0,
			column = 0;
		boolean foundValue = false;
		
		for (row = 0; row < puzzleSize; row++) {
			for (column = 0; column < puzzleSize; column++) {
				if (helpGrid[row][column] == 0) {
					foundValue = true;
					break;
				}
			}
			
			if (foundValue)
				break;
		}
		
		if (!foundValue)
			return true;
		
		boolean digits[] = new boolean[(puzzleSize + 2)];
		for (int index = 0; index < puzzleSize; index++) {
			digits[helpGrid[row][index]] = true;
			digits[helpGrid[index][column]] = true;
		}
		
		int setRow = (getSetSize() * (row / getSetSize())),
			setColumn = (getSetSize() * (column / getSetSize()));
		for (int x = 0; x < getSetSize(); x++) {
			for (int y = 0; y < getSetSize(); y++) {
				digits[helpGrid[(setRow + x)][(setColumn + y)]] = true;
			}
		}
		
		for (int value = 1; value <= puzzleSize; value++) {
			if (!digits[value]) {
				helpGrid[row][column] = value;
				
				if (finish(helpGrid)) {
					
					puzzleGrid = helpGrid.clone();
					
					return true;
				}
				
				helpGrid[row][column] = 0;
			}
		}
		
		return false;
	}
	
	
	private boolean isValueInFirstSet(int[][] grid, int value) {
		
		for (int row = 0; row < getSetSize(); row++) {
			if (grid[row][0] == value)
				return true;
		}
		
		return false;
	}
	
	
	private int getSetSize() {
		return ((int) Math.sqrt(puzzleSize));
	}
}
