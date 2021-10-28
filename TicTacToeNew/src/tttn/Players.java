package tttn;

public class Players 
{
	    String Name;
	    int Loses;
	    int Draws;
		int Wins;

	    public Players(String name)
	    {
	        this.Name = name;
	        this.Loses = 0;
	        this.Draws = 0;
	        this.Wins = 0;
	    }

	    public Players(String name, int draws, int loses, int wins)
	    {
	        this.Name = name;
	        this.Draws = draws;
	        this.Loses = loses;
	        this.Wins = wins;
	    }
	    public static int isPlayerKnown(String playerName)
	    {
	        for(int i = 0; i < Game.AllPlayers.size(); i++) {
	            if (Game.AllPlayers.get(i).Name.equals(playerName)){
	                return i;
	            }
	        }
	        return -1;
	    }
}
