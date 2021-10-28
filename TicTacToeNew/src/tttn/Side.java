package tttn;
import java.util.Scanner;

public class Side 
{
	  public static char Ai;
	    public static char Human;

	    public Side(char ai , char human)
	    {
	        Ai = ai;
	        Human = human;
	    }

	    public static Side ChooseSide(){
	        char ai, human;
	        Scanner scan = new Scanner(System.in);
	        System.out.println("Виберіть сторону : 1 - Х, 2-О: ");
	        int side = scan.nextInt();
	        if(side == 1){
	            human = 'X';
	            ai='O';
	        }
	        else{
	            human = 'O';
	            ai='X';
	        }
	        return new Side(ai,human);
	    }
}
