package nl.avans.generator;

import java.util.Random;

import nl.avans.lib.IGenerator;

/**
 * Generator class.
 * 
 * @author Dustin Sarioglu
 */
public class Generator implements IGenerator {
	private static int[][] solvedGrid;

	private GeneratorHelper generatorHelper;

	private int blankValues;
	private int puzzleSize;
	private int minAvailableFields;
	
	
	/**
	 * These constants are used to help finding the desired values to blank, based
	 * on a specific difficulty.
	 */
	private static final int EASY = -5;
	private static final int NORMAL = 0;
	private static final int HARD = 5;
	private static final int VERY_HARD = 10;
	private static final int EXPERT = 15;
	

	/**
	 * Initializes a new instance of the Generator class.
	 */
	public Generator() {
	}

	/**
	 * Generates a new Sudoku-puzzle.
	 * 
	 * @return A new generated sudoku puzzle, based on the solved grid.
	 */
	public int[][] generate(int puzzleSize, int difficulty) {

		int[][] helpGrid;

		int blankValues = 0, symmetryType = 0, desiredBlankValues;

		this.puzzleSize = puzzleSize;
		
		minAvailableFields = (puzzleSize * 2) - 1;

		generatorHelper = new GeneratorHelper(puzzleSize);
		solvedGrid = generatorHelper.generate();

		helpGrid = solvedGrid.clone();

		Random random = new Random();

		desiredBlankValues = getDesiredValuesToBlank(difficulty);
		symmetryType = random.nextInt(2);

		do {
			helpGrid = maskNumbers(helpGrid, symmetryType);
			blankValues = this.blankValues;
		} while (blankValues < desiredBlankValues);

		return helpGrid;
	}

	/**
	 * Masks numbers in a grid @random.
	 * 
	 * @param grid
	 *            The solved grid to create the puzzle-grid out of.
	 * @param symmetryType
	 *            The used type of symmetry to mask numbers in.
	 * 
	 * @return The solved grid with one or more numbers masked.
	 */
	private int[][] maskNumbers(int[][] grid, int symmetryType) {

		Random random = new Random();

		int rowIndex, columnIndex;

		rowIndex = random.nextInt(puzzleSize - 1);
		columnIndex = random.nextInt(puzzleSize - 1);

		while (grid[rowIndex][columnIndex] == 0) {

			rowIndex = random.nextInt(puzzleSize - 1);
			columnIndex = random.nextInt(puzzleSize - 1);
		}

		grid[rowIndex][columnIndex] = 0;
		blankValues++;

		switch (symmetryType) {

		case 0:
			if (grid[rowIndex][((puzzleSize - 1) - columnIndex)] != 0)
				blankValues++;

			grid[rowIndex][((puzzleSize - 1) - columnIndex)] = 0;
			break;

		case 1:
			if (grid[((puzzleSize - 1) - rowIndex)][columnIndex] != 0)
				blankValues++;

			grid[((puzzleSize - 1) - rowIndex)][columnIndex] = 0;
			break;

		case 2:
			if (grid[columnIndex][rowIndex] != 0)
				blankValues++;

			grid[columnIndex][rowIndex] = 0;
			break;

		default:
			if (grid[rowIndex][((puzzleSize - 1) - columnIndex)] != 0)
				blankValues++;

			grid[columnIndex][rowIndex] = 0;
			break;
		}

		return grid;
	}
	
	
	/**
	 * Gets the desired count of values to blank in a Sudoku puzzle, based on the
	 * difficulty.
	 * 
	 * @param difficulty	The difficulty of the puzzle.
	 * @return				The desired count of values to blank.
	 */
	private int getDesiredValuesToBlank(int difficulty) {
		
		int desiredValuesToBlank = ((puzzleSize * puzzleSize) / 2);
		int difficultyOffset;
		int puzzleSurface = (puzzleSize * puzzleSize);
		
		switch (difficulty) {
			case 0:
				difficultyOffset = EASY;
				break;
			case 2:
				difficultyOffset = HARD;
				break;
			case 3:
				difficultyOffset = VERY_HARD;
				break;
			case 4:
				difficultyOffset = EXPERT;
				break;
				
			case 1:
			default:
				difficultyOffset = NORMAL;
				break;
		}
		
		while (difficultyOffset != 0) {
			
			if ((puzzleSurface - desiredValuesToBlank) <= minAvailableFields)
				break;
			
			
			if (difficultyOffset < 0) {
				desiredValuesToBlank += -1;
				difficultyOffset++;
			} else {
				desiredValuesToBlank += 1;
				difficultyOffset--;
			}
		}
		
		return desiredValuesToBlank;
	}
}