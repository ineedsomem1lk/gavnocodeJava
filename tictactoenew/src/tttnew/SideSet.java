package tttnew;

import java.util.Scanner;

public class SideSet {
	public static char Ai;
    public static char Human;
    
    
    public SideSet(char Ai,char Human)
    {
    	SideSet.Human = Human;
    	SideSet.Ai = Ai;
    }
    public static SideSet chooseSide()
    {
    	Scanner sc = new Scanner(System.in);
    	char ai,human;
        System.out.println("Виберіть сторону : 1 - X , 2 - O : ");
        int op = sc.nextInt();
        if(op==1)
        {
            human = 'X';
            ai = 'O';
            return new SideSet(ai,human);
        }
        else
        {
            human ='O';
            ai ='X';
            return new SideSet(ai,human);
        }
    }
}
