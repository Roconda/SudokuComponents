package nl.avans;

import nl.avans.generator.Generator;

public abstract class GeneratorTester {
	
	private static int[][] puzzleGrid = new int[9][9];
	
	private static Generator generator;

	public static void main(String[] args) {
		
		/**
		 * Generator(4)			- 2x2 puzzle
		 * Generator(9)			- 3x3 puzzle
		 * Generator(16)		- 4x4 puzzle
		 */
		
		generator = new Generator();
		
		puzzleGrid = generator.generate(16, 4);
		
		System.out.println("Result**:");
		System.out.println(gridToString(puzzleGrid));
	}
	
	private static String gridToString(int[][] grid) {
		
		String gridString = "";
		
		for (int x = 0; x < grid.length; x++) {
			gridString += "{ ";
			for (int y = 0; y < grid[x].length; y++) {
				
				if ((y + 1) < grid[x].length)
					gridString += (grid[x][y] + ", ");
				else
					gridString += (grid[x][y] + " }");
			}
			
			if ((x + 1) < grid.length)
				gridString += ",\n";
			else
				gridString += "\n";
		}
		
		return gridString;
	}
}

