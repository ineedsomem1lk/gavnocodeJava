package tttn;
import java.io.*;
import java.util.Scanner;

public class LeaderBoard
{

    public static void Sort() 
    {
        int someInt;
        String someString;
        for(int i = 0; i < Game.AllPlayers.size()-1; i++) 
        {
            for(int j = 0; j < Game.AllPlayers.size()-1; j++) 
            {
                Players currentPlayer = Game.AllPlayers.get(j);
                Players nextPlayer = Game.AllPlayers.get(j+1);	
                if (currentPlayer.Wins < nextPlayer.Wins) 
                {
                    someString = currentPlayer.Name;
                    currentPlayer.Name = nextPlayer.Name;
                    nextPlayer.Name = someString;
                    
                    someInt = currentPlayer.Wins;
                    nextPlayer.Wins = currentPlayer.Wins;
                    nextPlayer.Wins = someInt;
                    
                    someInt = currentPlayer.Loses;
                    currentPlayer.Loses = nextPlayer.Loses;
                    nextPlayer.Loses = someInt;
                    
                    someInt = currentPlayer.Draws;
                    currentPlayer.Draws = nextPlayer.Draws;
                    nextPlayer.Draws = someInt;
                }
            }
        }
    }
    
    public static void Write() throws IOException 
    {
    	Sort();
        FileWriter filewriter = new FileWriter("E:/courses/LeaderBoards.txt");
        for(int i = 0; i < Game.AllPlayers.size(); i++) 
        {
            Players currentPlayer = Game.AllPlayers.get(i);
            String strToWrite = currentPlayer.Name + " " + currentPlayer.Wins + " "+ currentPlayer.Draws + " " + currentPlayer.Loses + " " + '\n' ;
            filewriter.write(strToWrite);
        }
        filewriter.close();
    }
    
    public static void LeaderBoardOutPrint()
    {
    	Sort();
        for(int i = 0; i < Game.AllPlayers.size(); i++) 
        {
            Players currentPlayer = Game.AllPlayers.get(i);
            String strToWrite = currentPlayer.Name + " " + currentPlayer.Wins + " " + currentPlayer.Draws + " " + currentPlayer.Loses + " ";
            System.out.println(strToWrite);
        }
    }
}