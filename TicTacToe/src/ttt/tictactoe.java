package ttt;
import java.util.Random;
import java.util.Scanner;

public class tictactoe {
	public static Scanner sc = new Scanner(System.in);
	public static char ai = 'O';
	public static char player = 'X';
	public static char [][] gameBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
	
	public static void choosePos()
	{
		while(!isGameOver())
		{
			System.out.print("Введіть рядок 1 - 3:");
			int x = sc.nextInt()-1;
			System.out.print("Введіть стовпчик 1 - 3:");
			int y = sc.nextInt()-1;
			if(gameBoard[x][y] == ' ')
			{
				gameBoard[x][y] = player;
				aiMove();
			}
			else
			{
				System.out.print("Введіть ще раз: " + '\n' + "Рядок 1-3: ");
				x = sc.nextInt()-1; 
				System.out.print("Стовпчик 1-3: ");
				y= sc.nextInt()-1;
				gameBoard[x][y] = player;
				aiMove();
			}
			printGame();
		}
	}
	public static int aiMove()
	{
	    int rand1 =0,rand2 = 0;
	    //Horizontally
	    for(int i = 0; i<3; i++)
	    {
	        if(gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == ai)
	        {

	        	if(gameBoard[2][i] == ' ')
	            {
	        		gameBoard[2][i] = ai;
	        		return 0;
	            }
	        }
	    }
	    for(int i=0;i<3;i++)
	    {
	    	if(gameBoard[2][i] == gameBoard[1][i] && gameBoard [2][i] == ai)
	    	{
	    		if(gameBoard[0][i] == ' ')
	    		{	
	    			gameBoard[0][i] = ai;
	    			return 0;
	    		}
	    	}
	    }
	    
	    //Vertically
	    for(int i=0;i<3;i++)
	    {
	    	if(gameBoard[i][0] == gameBoard[i][1] && gameBoard [i][0] == ai)
	    	{
	    		if(gameBoard[i][2] == ' ')
	    		{	
	    			gameBoard[i][2] = ai;
	    			return 0;
	    		}
	    	}
	    }
	    for(int i=0;i<3;i++)
	    {
	    	if(gameBoard[i][2] == gameBoard[i][1] && gameBoard [i][2] == ai)
	    	{
	    		if(gameBoard[i][0] == ' ')
	    		{	
	    			gameBoard[i][0] = ai;
	    			return 0;
	    		}
	    	}
	    }
	    //Diagonally
	    if(gameBoard[0][0] == gameBoard[1][1] && gameBoard [0][0] == ai)
    	{
    		if(gameBoard[2][2] == ' ')
    		{	
    			gameBoard[2][2] = ai;
    			return 0;
    		}
    	}
	    if(gameBoard[2][2] == gameBoard[1][1] && gameBoard [2][2] == ai)
    	{
    		if(gameBoard[0][0] == ' ')
    		{	
    			gameBoard[0][0] = ai;
    			return 0;
    		}
    	}
	    if(gameBoard[0][0] == gameBoard[1][1] && gameBoard [0][0] == ai)
    	{
    		if(gameBoard[2][2] != player)
    		{	
    			gameBoard[2][2] = ai;
    			return 0;
    		}
    	}
	    if(gameBoard[0][2] == gameBoard[1][1] && gameBoard [0][2] == ai)
    	{
    		if(gameBoard[2][0] == ' ')
    		{	
    			gameBoard[2][0] = ai;
    			return 0;
    		}
    	}
	    if(gameBoard[2][0] == gameBoard[1][1] && gameBoard [2][0] == ai)
    	{
    		if(gameBoard[0][2] == ' ')
    		{	
    			gameBoard[0][2] = ai;
    			return 0;
    		}
    	}
	    //Defends
	    for(int i=0;i<3;i++)
	    {
	    	if(gameBoard[0][i] == gameBoard[1][i] && gameBoard [0][i] == player)
	    	{
	    		if(gameBoard[2][i] == ' ')
	    		{	
	    			gameBoard[2][i] = ai;
	    			return 0;
	    		}
	    	}
	    }
	    for(int i=0;i<3;i++)
	    {
	    	if(gameBoard[2][i] == gameBoard[1][i] && gameBoard [0][i] == player)
	    	{
	    		if(gameBoard[0][i] == ' ')
	    		{	
	    			gameBoard[0][i] = ai;
	    			return 0;
	    		}
	    	}
	    }
	    for(int i=0;i<3;i++)
	    {
	    	if(gameBoard[i][0] == gameBoard[i][1] && gameBoard [i][0] == player)
	    	{
	    		if(gameBoard[i][2] == ' ')
	    		{	
	    			gameBoard[i][2] = ai;
	    			return 0;
	    		}
	    	}
	    }
	    for(int i=0;i<3;i++)
	    {
	    	if(gameBoard[i][2] == gameBoard[i][1] && gameBoard [i][2] == player)
	    	{
	    		if(gameBoard[i][0] == ' ')
	    		{	
	    			gameBoard[i][0] = ai;
	    			return 0;
	    		}
	    	}
	    }
	    //Defends diagonally
	    if(gameBoard[0][0] == gameBoard[1][1] && gameBoard [0][0] == player)
    	{
    		if(gameBoard[2][2] == ' ')
    		{	
    			gameBoard[2][2] = ai;
    			return 0;
    		}
    	}
	    if(gameBoard[2][2] == gameBoard[1][1] && gameBoard [2][2] == player)
    	{
    		if(gameBoard[0][0] == ' ')
    		{	
    			gameBoard[0][0] = ai;
    			return 0;
    		}
    	}
	    if(gameBoard[0][2] == gameBoard[1][1] && gameBoard [0][2] == player)
    	{
    		if(gameBoard[2][0] == ' ')
    		{	
    			gameBoard[2][0] = ai;
    			return 0;
    		}
    	}
	    if(gameBoard[2][0] == gameBoard[1][1] && gameBoard [2][0] == player)
    	{
    		if(gameBoard[0][2] == ' ')
    		{	
    			gameBoard[0][2] = ai;
    			return 0;
    		}
    	}
	    Random rand = new Random();
	    while(!isFull())
	    {
	    	rand1 = rand.nextInt(3);
	    	rand2 = rand.nextInt(3);
	    	if(gameBoard[rand1][rand2] == ' ')
	    	{
	    		gameBoard[rand1][rand2] = ai;
	    		return 0;
	    	}
	    }
	    return 0;
	}
	public static boolean isGameOver()
	{
		if(winGameX() || winGameO() || isFull())
		{
			return true;
		}
		return false;
	}
	public static void printGame()
	{
		System.out.println(gameBoard[0][0] + "|" + gameBoard[0][1] + "|" + gameBoard[0][2] +  "\n-+-+-\n" 
		+ gameBoard[1][0] + "|" + gameBoard[1][1] + "|" + gameBoard[1][2] +  "\n-+-+-\n" 
		+ gameBoard[2][0] + "|" + gameBoard[2][1] + "|" + gameBoard[2][2]);
	}
	public static boolean winGameX()
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
	public static boolean isFull()
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
	public static void main(String[] args) 
	{
		
		printGame();
		choosePos();
		if(winGameX())
		{
			System.out.println("Player 1 Win!!!!");
			return;
		}
		if(winGameO())
		{
			System.out.println("Player 2 Win!!!!");
			return;
		}
		if(isFull())
		{
			System.out.print("Draw!!!!");
		}
	}
	
}