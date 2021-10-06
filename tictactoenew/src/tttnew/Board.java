package tttnew;

public class Board {
	public static char [][] gameBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
	
	public Board()
	{
		gameBoard = new char[3][3];
	}
	public static  boolean isGameOver()
	{
		if(winGameX() || winGameO() || isFull())
		{
			return true;
		}
		return false;
	}
	public void printGame()
	{
		System.out.println(gameBoard[0][0] + "|" + gameBoard[0][1] + "|" + gameBoard[0][2] +  "\n-+-+-\n" 
		+ gameBoard[1][0] + "|" + gameBoard[1][1] + "|" + gameBoard[1][2] +  "\n-+-+-\n" 
		+ gameBoard[2][0] + "|" + gameBoard[2][1] + "|" + gameBoard[2][2]);
	}
	public static  boolean winGameX()
	{
		if((gameBoard[0][0] == 'X' && gameBoard[1][1]=='X' && gameBoard[2][2] == 'X') 
		|| (gameBoard[0][0] == 'X' && gameBoard[1][0]=='X' && gameBoard[2][0] == 'X')
		|| (gameBoard[0][1] == 'X' && gameBoard[1][1]=='X' && gameBoard[2][1] == 'X')
		|| (gameBoard[0][2] == 'X' && gameBoard[1][2]=='X' && gameBoard[2][2] == 'X')
		|| (gameBoard[0][2] == 'X' && gameBoard[1][1]=='X' && gameBoard[2][0] == 'X')
		|| (gameBoard[0][0] == 'X' && gameBoard[0][1]=='X' && gameBoard[0][2] == 'X')
		|| (gameBoard[1][0] == 'X' && gameBoard[1][1]=='X' && gameBoard[1][2] == 'X')
		|| (gameBoard[2][0] == 'X' && gameBoard[2][1]=='X' && gameBoard[2][2] == 'X'))
		{
			return true;
		
		}
		return false;
	}
	public static boolean winGameO()
	{
		if((gameBoard[0][0] == 'O' && gameBoard[1][1]=='O' && gameBoard[2][2] == 'O') 
			|| (gameBoard[0][0] == 'O' && gameBoard[1][0]=='O' && gameBoard[2][0] == 'O')
			|| (gameBoard[0][1] == 'O' && gameBoard[1][1]=='O' && gameBoard[2][1] == 'O')
			|| (gameBoard[0][2] == 'O' && gameBoard[1][2]=='O' && gameBoard[2][2] == 'O')
			|| (gameBoard[0][2] == 'O' && gameBoard[1][1]=='O' && gameBoard[2][0] == 'O')
			|| (gameBoard[0][0] == 'O' && gameBoard[0][1]=='O' && gameBoard[0][2] == 'O')
			|| (gameBoard[1][0] == 'O' && gameBoard[1][1]=='O' && gameBoard[1][2] == 'O')
			|| (gameBoard[2][0] == 'O' && gameBoard[2][1]=='O' && gameBoard[2][2] == 'O'))
			{
				return true;
			}
		return false;
	}
	public static  boolean isFull()
	{
		for(int i = 0; i < 3; i++) {
			for(int g = 0; g < 3; g++) {
				if(gameBoard[i][g]== ' ')
				{
					return false;
				}
			}
		}
		return true;
	}
	public void makeMove(int x,int y, char player)
	{
		gameBoard[x][y] = player;
	}
	public boolean isEmpty(int x,int y)
	{
		if(gameBoard[x][y] == ' ')
		{
			return true;
		}
		return false;
	}
}
