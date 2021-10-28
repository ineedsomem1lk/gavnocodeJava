package tttn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game 
{	
	static ArrayList<Players> AllPlayers = new ArrayList<Players>();
	
	public static void StartGame () throws IOException 
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Введіть Ваше Ім'я: ");
       	String Name = scan.nextLine();
       	int PlayerInt = Players.isPlayerKnown(Name);
       	
       	if(PlayerInt == -1)
       	{
       		Players player = new Players(Name);
       		AllPlayers.add(player);
        	LeaderBoard.Sort();
        	LeaderBoard.Write();
       	}
	    Side.ChooseSide();
	    while(!Board.isGameOver())
	    {
	        if(Side.Human == 'X') {   	 
	             Board.getPlayerMove();
	             Ai.aiMove();
		         Board.printGame();
	        }
	        else {
	            Ai.aiMove();
	            Board.printGame();
	            Board.getPlayerMove();
	        }
	         if(Board.winGameHuman())
		     {
		    	 System.out.print("Гравець " + Name +  " виграв!");
		    	 for (int i = 0; i < Game.AllPlayers.size(); i++) 
		    	 {
	                    if (Name.equals(AllPlayers.get(i).Name)) 
	                    {
	                        Players currentPlayer = Game.AllPlayers.get(i);
	                        currentPlayer.Wins = currentPlayer.Wins + 1;
	                    }
	                }
		    	 LeaderBoard.Write();
		    	 Board.cleanBoard();
		    	 return;
		     }
		     if(Board.winGameAi())
		     {
		    	 System.out.print("Аі виграв!");
		    	 for (int i = 0; i < Game.AllPlayers.size(); i++) {
	                    if (Name.equals(AllPlayers.get(i).Name)) {
	                        Players currentPlayer = Game.AllPlayers.get(i);
	                        currentPlayer.Loses = currentPlayer.Loses + 1;
	                    }
	                }
		    	 LeaderBoard.Write();
		    	 Board.cleanBoard();
		    	 return;
		     }
		     if(Board.isFull()) 
		     { 
		    	 System.out.print("Нічия!");
		    	 for (int i = 0; i < AllPlayers.size(); i++) 
		    	 {
	                    if (Name.equals(AllPlayers.get(i).Name)) 
	                    {
	                        Players currentPlayer = Game.AllPlayers.get(i);
	                        currentPlayer.Draws = currentPlayer.Draws + 1;
	                    }
		    	 }
		    	 LeaderBoard.Write();
		    	 Board.cleanBoard();
		    	 return;
		     }
	     }
	 }
}
