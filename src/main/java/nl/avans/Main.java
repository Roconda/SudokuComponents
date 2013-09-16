package nl.avans;

public class Main {
	public static void main(String[] args)
	{
		int[][] board = new int[9][9];
		board[0] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
		board[1] = new int[]{4, 0, 6, 0, 0, 0, 0, 0, 0};
		board[2] = new int[]{7, 8, 9, 0, 0, 0, 0, 0, 0};
		board[3] = new int[]{2, 0, 0, 0, 0, 0, 8, 0, 0};
		board[4] = new int[]{5, 0, 0, 0, 0, 0, 0, 0, 0};
		board[5] = new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0};
		board[6] = new int[]{8, 0, 0, 0, 0, 0, 0, 0, 0};
		board[7] = new int[]{0, 0, 0, 0, 8, 0, 0, 0, 0};
		board[8] = new int[]{9, 0, 0, 0, 0, 0, 0, 0, 0};
		
		ISolver solver = new Solver();
		int[][] result = solver.Solve(board);
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(result[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
}