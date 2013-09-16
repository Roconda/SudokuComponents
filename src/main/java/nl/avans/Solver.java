package nl.avans;

public class Solver implements ISolver
{
	private int SIZE;
	private int SQRT_SIZE;
	private int[][] board;
	private boolean[][][] possibilities;
	
	
    public int[][] Solve(int[][] board)
    {
    	this.SIZE = board.length;
    	this.SQRT_SIZE = (int) Math.sqrt(SIZE);
    	this.board = board;
    	this.possibilities = new boolean[SIZE][SIZE][SIZE+1];
    	
    	for(int count = 0; count <= 1000000; count++)
    	{
    		checkPossibilities();
    		fillSpot();
            fillOnlyNumber();
    	}
    	
    	return board;
    }
    
    private void checkPossibilities()
    {
    	for(int i = 0; i < SIZE; i++)
    	{
    		for(int j = 0; j < SIZE; j++)
    		{
    			if(board[i][j] != 0)
    			{
    				int number = board[i][j];
    				for(int k = 0; k < SIZE; k++)
    				{
    					possibilities[i][j][k] = true; //No other number possible at this spot
    					possibilities[i][k][number] = true; //This number not possible in column
    					possibilities[k][j][number] = true; //This number not possible in row
    				}
    				for(int x = (i/SQRT_SIZE)*SQRT_SIZE; x < ((i/SQRT_SIZE)+1)*SQRT_SIZE; x++)
    				{
    					for(int y = (j/SQRT_SIZE)*SQRT_SIZE; y < ((j/SQRT_SIZE)+1)*SQRT_SIZE; y++)
        				{
        					possibilities[x][y][number] = true; //This number not possible in block
        				}
    				}
    			}
    		}
    	}
    }
    
    private void fillSpot()
    {
    	for(int i = 0; i < SIZE; i++)
    	{
    		for(int j = 0; j < SIZE; j++)
    		{
    			if(board[i][j] == 0)
    			{
    				int possibleNumbers = 0;
    				int possibleNumber = 0;
    				for(int k = 1; k < (SIZE + 1); k++)
    				{
    					if(!possibilities[i][j][k])
    					{
    						possibleNumbers++;
    						possibleNumber = k;
    					}
    				}
    				if(possibleNumbers == 1)
    				{
    					board[i][j] = possibleNumber;
    				}
    			}
    		}
    	}
    }

    private void fillOnlyNumber()
    {
        for(int k = 0; k < SIZE; k++)
        {
            for(int i = 0; i < SIZE; i++)
            {
                int columnSpot = 0;
                int possibleColumnSpots = 0;
                for (int j = 0; j < SIZE; j++)
                {
                    if(!possibilities[i][j][k])
                    {
                        columnSpot = j;
                        possibleColumnSpots++;
                    }
                }
                if(possibleColumnSpots == 1)
                {
                    board[i][columnSpot] = k;
                }

                int rowSpot = 0;
                int possibleRowSpots = 0;
                for (int j = 0; j < SIZE; j++)
                {
                    if(!possibilities[j][i][k])
                    {
                        rowSpot = j;
                        possibleRowSpots++;
                    }
                }
                if(possibleRowSpots == 1)
                {
                    board[rowSpot][i] = k;
                }
            }
        }
    }
}
