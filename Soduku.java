import java.util.*;

public class Soduku 
{
	public int [][] board;
	
	public Soduku()
	{
		board = new int [9][9]; // created a 2d array to represent the board
	}

	public boolean safe(int row, int col, int num)
	{
		return RowCheck(row, num) // method to check tow column and 3x3 box for duplicate numbers
		&& ColumnCheck( col, num)
		&& BoxCheck(row, col,  num);
	}

	private boolean RowCheck(int row, int num)
	{
		for(int i = 0; i < 9; i++){ // checking for rows for duplicates
			if(board[row][i] == num){
				return false; 
			}
		}
		return true;
	}

	private boolean ColumnCheck(int col, int num)
	{
		for(int i = 0; i < 9; i++){ // checking for columns for duplicates
			if(board[i][col] == num){
				return false;
			}
		}
		return true;
	}

	private boolean BoxCheck(int row, int col, int num)
	{
		int rowStart = (row/3)*3; // checking the box for duplicates 
		int rowEnd = rowStart + 2; // the maths here is to find which box group is the number being added are in

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
		for (int row = 0; row< 9; row++)  // a solver using the recursive backtracking method
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
		for (int row = 0; row <  9; row++) { // printing the board for display
			for(int col = 0; col < 9; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	private void boardComplete()
	{
		boolean complete = true; // checking if there are any 0's left at the end and to print not completed

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
		Soduku s = new Soduku(); // created user interface to input numbers to solve any soduku puzzle
		Scanner scanner = new Scanner(System.in);
		Boolean userSafe = true;

		for(int i = 0; i < 9; i++)
		{
			System.out.println("Enter numbers for row " + (i+ 1) + ":");
			String string = scanner.nextLine();
			for(int c = 0; c < string.length(); c++){
				char ch = string.charAt(c);
				int num = Integer.parseInt(String.valueOf(ch));
				if(!s.safe(i, c, num) && num != 0)
				{
					userSafe = false;
					System.out.println("Error: You typed in a duplicate number"); // error for any duplicated numbers typed in by the user
				}
				s.board[i][c] = num;
			}
		}

		if(userSafe) {
			s.printBoard();
			System.out.println();
			s.solve();
			s.printBoard();
			s.boardComplete(); 
		}
	}
}
				
		

