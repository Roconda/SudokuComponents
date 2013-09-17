package nl.avans.utils;

import java.util.ArrayList;
import java.util.Random;


public final class Randomizer {
	
	public static int[][] randomize(int[][] grid) {
		
		ArrayList<RandomizeType> randomizeTypes = new ArrayList<RandomizeType>();
		Random random = new Random();
		
		int[][] puzzle = grid.clone();
		
		randomizeTypes.add(RandomizeType.NONE);
		randomizeTypes.add(RandomizeType.INVERSE);
		randomizeTypes.add(RandomizeType.SWAP_HORIZONTAL);
		randomizeTypes.add(RandomizeType.SWAP_VERTICAL);
		
		int randomIndex = random.nextInt(randomizeTypes.size() - 2);
		
		while (randomizeTypes.size() > randomIndex) {
			
			RandomizeType randomizeType = choose(randomizeTypes);
			puzzle = process(puzzle, randomizeType);
			
			randomizeTypes.remove(randomizeType);
		}
		
		return puzzle;
	}
	
	
	private static RandomizeType choose(ArrayList<RandomizeType> params) {
		
		Random random = new Random();
		int randomIndex = ((params.size() - 1) > 0) ? random.nextInt(params.size() - 1) : 0;
		
		return params.get(randomIndex);
	}
	
	
	private static int[][] process(int[][] grid, RandomizeType randomizeType) {
		
		int[][] result;
		
		if (randomizeType == RandomizeType.INVERSE)
			result = inverse(grid);
		else if (randomizeType == RandomizeType.SWAP_HORIZONTAL)
			result = swapHorizontal(grid);
		else if (randomizeType == RandomizeType.SWAP_VERTICAL)
			result = swapVertical(grid);
		else if (randomizeType == RandomizeType.NONE)
			result = grid.clone();
		else
			result = inverse(grid);
		
		return result;
	}
	
	
	private static int[][] inverse(int[][] grid) {
		
		int[][] inversed = new int[grid.length][grid[0].length];
		
		for (int x = 0, inversedX = (grid.length - 1); x < grid.length; x++, inversedX--) {
			
			for (int y = 0, inversedY = (grid[x].length - 1); y < grid[x].length; y++, inversedY--) {
				inversed[inversedX][inversedY] = grid[x][y];
			}
		}
		
		return inversed;
	}
	
	
	private static int[][] swapHorizontal(int[][] grid) {
		
		int[][] swapped = new int[grid.length][grid[0].length];
		
		for (int x = (grid.length - 1), inversedX = 0; x >= 0; x--, inversedX++) {
			swapped[inversedX] = grid[x].clone();
		}
		
		return swapped;
	}
	
	private static int[][] swapVertical(int[][] grid) {
		
		int[][] swapped = new int[grid.length][grid[0].length];
		
		for (int x = 0; x < grid.length; x++) {
			for (int y = (grid[x].length - 1), inversedY = 0; y >= 0; y--, inversedY++) {
				swapped[x][inversedY] = grid[x][y];
			}
		}
		
		return swapped;
	}
}
