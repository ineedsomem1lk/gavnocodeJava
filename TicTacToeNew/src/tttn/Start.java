package tttn;

import java.io.IOException;
import java.util.Scanner;

public class Start extends Game
{
	public static void main(String[] args) throws IOException 
	{
		Scanner scan = new Scanner(System.in);
		boolean gameover = false;
		while(!gameover)
		{
			StartGame();
			System.out.print( '\n' + "Хочете продовжити : Так/Ні " + '\n');
			String answer = scan.next();
			if(answer.equals("Ні"))
			{
				gameover = true;
				System.out.print("Ваша Статистика за гру:" + '\n');
				LeaderBoard.LeaderBoardOutPrint();
			}
		}
	}
}
