package tttn;
import java.util.Scanner;

public class Board implements Printable 
{
	public static char [][] gameBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    public static boolean isGameOver()
    {
    	return winGameHuman() || winGameAi() || isFull();
    }
    public static void printGame()
    {
        System.out.println(gameBoard[0][0] + "|" + gameBoard[0][1] + "|" + gameBoard[0][2] +  "\n-+-+-\n"
                + gameBoard[1][0] + "|" + gameBoard[1][1] + "|" + gameBoard[1][2] +  "\n-+-+-\n"
                + gameBoard[2][0] + "|" + gameBoard[2][1] + "|" + gameBoard[2][2]);
    }
    public static void cleanBoard()
    {
    	for(int i=0;i<3;i++)
    	{
    		for(int j=0;j<3;j++)
    		{
    			gameBoard[i][j] = ' ';
    		}
    	}
    }
    
    public static boolean winGameHuman()
    {
        return (gameBoard[0][0] == Side.Human && gameBoard[1][1] == Side.Human && gameBoard[2][2] == Side.Human)
                || (gameBoard[0][0] == Side.Human && gameBoard[1][0] == Side.Human && gameBoard[2][0] == Side.Human)
                || (gameBoard[0][1] == Side.Human && gameBoard[1][1] == Side.Human && gameBoard[2][1] == Side.Human)
                || (gameBoard[0][2] == Side.Human && gameBoard[1][2] == Side.Human && gameBoard[2][2] == Side.Human)
                || (gameBoard[0][2] == Side.Human && gameBoard[1][1] == Side.Human && gameBoard[2][0] == Side.Human)
                || (gameBoard[0][0] == Side.Human && gameBoard[0][1] == Side.Human && gameBoard[0][2] == Side.Human)
                || (gameBoard[1][0] == Side.Human && gameBoard[1][1] == Side.Human && gameBoard[1][2] == Side.Human)
                || (gameBoard[2][0] == Side.Human && gameBoard[2][1] == Side.Human && gameBoard[2][2] == Side.Human);
    }
    
    public static boolean winGameAi()
    {
        return (gameBoard[0][0] == Side.Ai && gameBoard[1][1] == Side.Ai && gameBoard[2][2] == Side.Ai)
                || (gameBoard[0][0] == Side.Ai && gameBoard[1][0] == Side.Ai && gameBoard[2][0] == Side.Ai)
                || (gameBoard[0][1] == Side.Ai && gameBoard[1][1] == Side.Ai && gameBoard[2][1] == Side.Ai)
                || (gameBoard[0][2] == Side.Ai && gameBoard[1][2] == Side.Ai && gameBoard[2][2] == Side.Ai)
                || (gameBoard[0][2] == Side.Ai && gameBoard[1][1] == Side.Ai && gameBoard[2][0] == Side.Ai)
                || (gameBoard[0][0] == Side.Ai && gameBoard[0][1] == Side.Ai && gameBoard[0][2] == Side.Ai)
                || (gameBoard[1][0] == Side.Ai && gameBoard[1][1] == Side.Ai && gameBoard[1][2] == Side.Ai)
                || (gameBoard[2][0] == Side.Ai && gameBoard[2][1] == Side.Ai && gameBoard[2][2] == Side.Ai);
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
    
    public static void getPlayerMove()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введіть рядок 1 - 3:");
		int x = scan.nextInt()-1;
        System.out.print("Введіть стовпчик 1 - 3:");
        int y = scan.nextInt()-1;
        if(gameBoard[x][y] == ' ')
        {
        	gameBoard[x][y]=Side.Human;
        }
        else
        {
        	System.out.print("Введіть ще раз: " + '\n' + "Рядок 1-3: ");
			x = scan.nextInt()-1; 
			System.out.print("Стовпчик 1-3: ");
			y= scan.nextInt()-1;
			gameBoard[x][y] = Side.Human;
        }
    }
}
