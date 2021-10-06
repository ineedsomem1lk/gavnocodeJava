package tttnew;
import java.util.Scanner;

public class Choose 
{
	public static Scanner sc = new Scanner(System.in);
	public char Ai;
    public char Human;

    public Choose(char Human, char Ai)
    {
        this.Human=Human;
        this.Ai=Ai;
    }
    public static Choose GetPlayerChar()
    {
        char human,ai;
        System.out.println("Choose side : 1 - X , 2 - O : ");
        int op = sc.nextInt();
        if(op==1){
            human = 'X';
            ai = 'O';
            return new Choose(human,ai);
        }
        else{
            human='O';
            ai='X';
            return new Choose(human,ai);
        }

    }
}
