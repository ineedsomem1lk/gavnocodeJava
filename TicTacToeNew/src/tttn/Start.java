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
			System.out.print( '\n' + "������ ���������� : ���/ͳ " + '\n');
			String answer = scan.next();
			if(answer.equals("ͳ"))
			{
				gameover = true;
				System.out.print("���� ���������� �� ���:" + '\n');
				LeaderBoard.LeaderBoardOutPrint();
			}
		}
	}
}
