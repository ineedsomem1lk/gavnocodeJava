package tttnew;

import java.util.Random;

public class Game 
{
		char[][] gameBoard ;
		
		public int aiMove()
		{
		    int rand1 =0,rand2 = 0;
		    //Horizontally
		    for(int i = 0; i<3; i++)
		    {
		        if(gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == SideSet.Ai)
		        {
		        	if(gameBoard[2][i] == ' ')
		            {
		        		gameBoard[2][i] = SideSet.Ai;
		        		return 0;
		            }
		        }
		    }
		    for(int i=0;i<3;i++)
		    {
		    	if(gameBoard[2][i] == gameBoard[1][i] && gameBoard [2][i] == SideSet.Ai)
		    	{
		    		if(gameBoard[0][i] == ' ')
		    		{	
		    			gameBoard[0][i] = SideSet.Ai;
		    			return 0;
		    		}
		    	}
		    }
		    
		    //Vertically
		    for(int i=0;i<3;i++)
		    {
		    	if(gameBoard[i][0] == gameBoard[i][1] && gameBoard [i][0] == SideSet.Ai)
		    	{
		    		if(gameBoard[i][2] == ' ')
		    		{	
		    			gameBoard[i][2] = SideSet.Ai;
		    			return 0;
		    		}
		    	}
		    }
		    for(int i=0;i<3;i++)
		    {
		    	if(gameBoard[i][2] == gameBoard[i][1] && gameBoard [i][2] == SideSet.Ai)
		    	{
		    		if(gameBoard[i][0] == ' ')
		    		{	
		    			gameBoard[i][0] = SideSet.Ai;
		    			return 0;
		    		}
		    	}
		    }
		    //Diagonally
		    if(gameBoard[0][0] == gameBoard[1][1] && gameBoard [0][0] == SideSet.Ai)
	    	{
	    		if(gameBoard[2][2] == ' ')
	    		{	
	    			gameBoard[2][2] = SideSet.Ai;
	    			return 0;
	    		}
	    	}
		    if(gameBoard[2][2] == gameBoard[1][1] && gameBoard [2][2] == SideSet.Ai)
	    	{
	    		if(gameBoard[0][0] == ' ')
	    		{	
	    			gameBoard[0][0] = SideSet.Ai;
	    			return 0;
	    		}
	    	}
		    if(gameBoard[0][0] == gameBoard[1][1] && gameBoard [0][0] == SideSet.Ai)
	    	{
	    		if(gameBoard[2][2] == ' ')
	    		{	
	    			gameBoard[2][2] = SideSet.Ai;
	    			return 0;
	    		}
	    	}
		    if(gameBoard[0][2] == gameBoard[1][1] && gameBoard [0][2] == SideSet.Ai)
	    	{
	    		if(gameBoard[2][0] == ' ')
	    		{	
	    			gameBoard[2][0] = SideSet.Ai;
	    			return 0;
	    		}
	    	}
		    if(gameBoard[2][0] == gameBoard[1][1] && gameBoard [2][0] == SideSet.Ai)
	    	{
	    		if(gameBoard[0][2] == ' ')
	    		{	
	    			gameBoard[0][2] = SideSet.Ai;
	    			return 0;
	    		}
	    	}
		    //Defends
		    for(int i=0;i<3;i++)
		    {
		    	if(gameBoard[0][i] == gameBoard[1][i] && gameBoard [0][i] == SideSet.Human)
		    	{
		    		if(gameBoard[2][i] == ' ')
		    		{	
		    			gameBoard[2][i] = SideSet.Ai;
		    			return 0;
		    		}
		    	}
		    }
		    for(int i=0;i<3;i++)
		    {
		    	if(gameBoard[2][i] == gameBoard[1][i] && gameBoard [0][i] == SideSet.Human)
		    	{
		    		if(gameBoard[0][i] == ' ')
		    		{	
		    			gameBoard[0][i] = SideSet.Ai;
		    			return 0;
		    		}
		    	}
		    }
		    for(int i=0;i<3;i++)
		    {
		    	if(gameBoard[i][0] == gameBoard[i][1] && gameBoard [i][0] == SideSet.Human)
		    	{
		    		if(gameBoard[i][2] == ' ')
		    		{	
		    			gameBoard[i][2] = SideSet.Ai;
		    			return 0;
		    		}
		    	}
		    }
		    for(int i=0;i<3;i++)
		    {
		    	if(gameBoard[i][2] == gameBoard[i][1] && gameBoard [i][2] == SideSet.Human)
		    	{
		    		if(gameBoard[i][0] == ' ')
		    		{	
		    			gameBoard[i][0] = SideSet.Ai;
		    			return 0;
		    		}
		    	}
		    }
		    //Defends diagonally
		    if(gameBoard[0][0] == gameBoard[1][1] && gameBoard [0][0] == SideSet.Human)
	    	{
	    		if(gameBoard[2][2] == ' ')
	    		{	
	    			gameBoard[2][2] = SideSet.Ai;
	    			return 0;
	    		}
	    	}
		    if(gameBoard[2][2] == gameBoard[1][1] && gameBoard [2][2] == SideSet.Human)
	    	{
	    		if(gameBoard[0][0] == ' ')
	    		{	
	    			gameBoard[0][0] = SideSet.Ai;
	    			return 0;
	    		}
	    	}
		    if(gameBoard[0][2] == gameBoard[1][1] && gameBoard [0][2] == SideSet.Human)
	    	{
	    		if(gameBoard[2][0] == ' ')
	    		{	
	    			gameBoard[2][0] = SideSet.Ai;
	    			return 0;
	    		}
	    	}
		    if(gameBoard[2][0] == gameBoard[1][1] && gameBoard [2][0] == SideSet.Human)
	    	{
	    		if(gameBoard[0][2] == ' ')
	    		{	
	    			gameBoard[0][2] = SideSet.Ai;
	    			return 0;
	    		}
	    	}
		    Random rand = new Random();
		    while(Board.isFull() != true)
		    {
		    	rand1 = rand.nextInt(3);
		    	rand2 = rand.nextInt(3);
		    	if(gameBoard[rand1][rand2] == ' ')
		    	{
		    		gameBoard[rand1][rand2] = SideSet.Ai;
		    		return 0;
		    	}
		    }
		    return 0;
		}
}

