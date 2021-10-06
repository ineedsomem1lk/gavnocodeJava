package tttnew;
import java.util.Scanner;

public class Player
{
	public static Scanner sc = new Scanner(System.in);
	Board  gameBoard;
	Game gameGame;
	
	public Player()
	{
		 
		 gameBoard = new Board();
		 gameGame = new Game();
	}
	
	public void choosePos()
	{
		while(Board.isGameOver() != true)
		{
			System.out.print("Введіть рядок 1 - 3:");
			int x = sc.nextInt()-1;
			System.out.print("Введіть стовпчик 1 - 3:");
			int y = sc.nextInt()-1;
			if(gameBoard.isEmpty(x,y))
			{
				gameBoard.makeMove(x,y,SideSet.Human);
				gameGame.aiMove();
			}
			else
			{
				System.out.print("Введіть ще раз: " + '\n' + "Рядок 1-3: ");
				x = sc.nextInt()-1; 
				System.out.print("Стовпчик 1-3: ");
				y= sc.nextInt()-1;
				gameBoard.makeMove(x, y,SideSet.Human);
				gameGame.aiMove();
			}
			gameBoard.printGame();
		}
	}
}
