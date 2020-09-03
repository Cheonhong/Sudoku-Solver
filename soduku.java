import java.util.*;

public class Soduku 
{
	public int [][] board;
	
	public Soduku()
	{
		board = new int [9][9];

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
					board[i][j] = 0;
			}
		}

		board[0][0] = 5;
		board[0][1] = 3;
		board[0][4] = 7;
		board[1][0] = 6;
		board[1][3] = 1;
		board[1][4] = 9;
		board[1][5] = 5;
		board[2][1] = 9;
		board[2][2] = 8;
		board[2][7] = 6;
		board[3][0] = 8;
		board[3][4] = 6;
		board[3][8] = 3;
		board[4][0] = 4;
		board[4][3] = 8;
		board[4][5] = 3;
		board[4][8] = 1;
		board[5][0] = 7;
		board[5][4] = 2;
		board[5][8] = 6;
		board[6][1] = 6;
		board[6][6] = 2;
		board[6][7] = 8;
		board[7][3] = 4;
		board[7][4] = 1;
		board[7][5] = 9;
		board[7][8] = 5;
		board[8][4] = 8;
		board[8][7] = 7;
		board[8][8] = 9;
	}

	public boolean safe(int row, int col, int num)
	{
		return RowCheck(row, num)
		&& ColumnCheck( col, num)
		&& BoxCheck(row, col,  num);
	}

	private boolean RowCheck(int row, int num)
	{
		for(int i = 0; i < 9; i++){
			if(board[row][i] == num){
				return false; 
			}
		}
		return true;
	}

	private boolean ColumnCheck(int col, int num)
	{
		for(int i = 0; i < 9; i++){
			if(board[i][col] == num){
				return false;
			}
		}
		return true;
	}

	private boolean BoxCheck(int row, int col, int num)
	{
		int rowStart = (row/3)*3;
		int rowEnd = rowStart + 2;

		int colStart = (col/3)*3;
		int colEnd = colStart+2;

		for (int i = rowStart; i < rowEnd; i++) {
			for (int r = colStart; r < colEnd; r++) {
				if (board[i][r] == num) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean solve() 
	{
		for (int row = 0; row< 9; row++) 
		{
			for( int col = 0; col < 9; col++) 
			{
				if (board[row][col] == 0) 
				{
					for ( int num = 1; num <= 9; num++) 
					{
						if ( safe(row, col, num)) 
						{
							board[row][col] = num;
							if (solve()) {
								return true;
							} else {
							board[row][col] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private void printBoard()
	{
		for (int row = 0; row <  9; row++) {
			for(int col = 0; col < 9; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	private void boardComplete()
	{
		boolean complete = true;

		for (int row = 0; row <  9; row++) {
			for(int col = 0; col < 9; col++) {
				if (board[row][col] == 0)
				{
					complete = false;
				}
			}
		}
		if (complete == false)
		{
			System.out.println("Error: Could not Complete Board");
		}
	}

	public static void main (String[] args)
	{
		Soduku s = new Soduku();
		Scanner scanner = new Scanner(System.in);

		for(int i = 0; i < 9; i++)
		{
			System.out.println("Enter numbers for row" + (i+ 1) + ":");
			String string = scanner.nextLine();
			String[] arr = string.split("");
			int size = arr.length;

			int [] numbers = new int [size];
			for(int j = 0; j<size; j++)
			{
				numbers[j] = Integer.parseInt(arr[j]);
			}
			System.out.println(Arrays.toString(numbers));
			// for(int c = 0; c < string.length(); c++)
			// {
			// 	int num = Character.getNumericValue(c);
			// 	s.board[i][c] = num;
			// }
		}

	}
}

	// public static void main(String[] args){
	// 	Sudoku s = new Sudoku();
	// 	Scanner scanner = new Scanner(System.in);
	// 	for(int i = 0; i < 9; i++){
	// 		System.out.println(“Enter numbers for row” + (i+ 1) + “:”);
	// 		String string = scanner.nextLine();
	// 		for(int c = 0; c < string.length(); c++){
	// 			Int num = Character.getNumericValue(c);
	// 			s.board[i][c] = num;
				
		

