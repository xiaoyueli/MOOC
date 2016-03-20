package captherFour;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in= new Scanner(System.in);
		int size = in.nextInt();
		
		int[][] board = new int[size][size];
		for(int i =0; i < board.length; i++)
			for(int j = 0; j <board[i].length; j ++)
				board[i][j] = in.nextInt();
		
		boolean isWin = false;
		int sumRD =0, sumLD = 0;
		for(int i =0; i < board.length; i++)
		{
			int sumRow = 0, sumCol =0;
			for(int j = 0; j <board[i].length; j ++)
			{
				sumRow += board[i][j];
				sumCol += board[j][i];
			}
			sumRD += board[i][i];
			sumLD += board[i][board.length-1-i];
			if(sumRow == 0 || sumCol == 0 || sumRD ==0 || sumLD == 0)
			{
				System.out.println("O");
				isWin = true;
				break;
			}
			else if(sumRow == board.length || sumCol == board.length || sumRD == board.length || sumLD == board.length)
			{
				System.out.println("X");
				isWin = true;
				break;
			}

		}
		
		if(!isWin)
		{
			System.out.println("NIL");
		}
		
		in.close();
	}

}
